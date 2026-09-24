package org.rsmod.content.generic.npcs.lootingbag

import jakarta.inject.Inject
import org.rsmod.api.config.refs.invs
import org.rsmod.api.config.refs.params
import org.rsmod.api.invtx.invTransaction
import org.rsmod.api.invtx.select
import org.rsmod.api.player.protect.ProtectedAccess
import org.rsmod.api.player.vars.VarPlayerIntMapSetter
import org.rsmod.api.script.onOpHeld3
import org.rsmod.api.script.onOpHeldU
import org.rsmod.api.script.onPlayerLogin
import org.rsmod.api.type.refs.obj.ObjReferences
import org.rsmod.content.interfaces.bank.BankTab
import org.rsmod.content.interfaces.bank.bankCapacity
import org.rsmod.game.entity.Player
import org.rsmod.game.type.inv.InvTypeList
import org.rsmod.game.type.obj.ObjTypeList
import org.rsmod.objtx.TransactionResult
import org.rsmod.objtx.isOk
import org.rsmod.plugin.scripts.PluginScript
import org.rsmod.plugin.scripts.ScriptContext

public class LootingBagBanking
@Inject
constructor(private val invTypes: InvTypeList, private val objTypes: ObjTypeList) : PluginScript() {
    private val Player.bank
        get() = invMap.getOrPut(invTypes[invs.bank])

    override fun ScriptContext.startup() {
        // Repair only the main-tab span if the previous Looting bag implementation left its size
        // smaller than the backing bank inventory. We never reorder or compress bank objects here.
        onPlayerLogin { player.repairMainBankSpan() }

        // Using an item on either Looting bag state sends that item to the bank.
        onOpHeldU(LootingBagObjs.lootingBag) { depositToBank(it.secondSlot) }
        onOpHeldU(LootingBagObjs.lootingBagOpen) { depositToBank(it.secondSlot) }

        // OSRS uses held op 3 for the Looting bag's "Deposit" option. In this server the option
        // acts as a convenient "deposit inventory" action and leaves the Looting bag untouched.
        onOpHeld3(LootingBagObjs.lootingBag) { depositInventoryToBank() }
        onOpHeld3(LootingBagObjs.lootingBagOpen) { depositInventoryToBank() }
    }

    private fun Player.repairMainBankSpan() {
        val bank = bank
        val firstMainSlot = BankTab.tabs.sumOf { vars[it.sizeVarBit] }
        if (firstMainSlot !in 0..bank.size) {
            return
        }

        val maxMainSize = bank.size - firstMainSlot
        val lastOccupied = bank.lastOccupiedSlot()
        val requiredMainSize =
            if (lastOccupied < firstMainSlot) {
                0
            } else {
                (lastOccupied - firstMainSlot + 1).coerceAtMost(maxMainSize)
            }

        val currentMainSize = vars[BankTab.Main.sizeVarBit]
        when {
            currentMainSize > maxMainSize ->
                VarPlayerIntMapSetter.set(this, BankTab.Main.sizeVarBit, maxMainSize)
            currentMainSize < requiredMainSize ->
                VarPlayerIntMapSetter.set(this, BankTab.Main.sizeVarBit, requiredMainSize)
        }
    }

    private fun ProtectedAccess.depositInventoryToBank() {
        var deposited = 0
        var unbankable = 0
        var noSpace = 0
        var fullStack = 0
        var hadCandidate = false

        // Inventory transactions leave empty slots in place, so it is safe to walk the original
        // slot range while transferring objects out of it.
        for (slot in inv.indices) {
            val obj = inv[slot] ?: continue
            val type = objTypes[obj]
            if (LootingBagObjs.isLootingBag(type.id)) {
                continue
            }

            hadCandidate = true
            when (depositToBank(slot, sendMessage = false)) {
                DepositStatus.Success -> deposited++
                DepositStatus.Unbankable -> unbankable++
                DepositStatus.BankFull -> noSpace++
                DepositStatus.StackFull -> fullStack++
                DepositStatus.LootingBag -> Unit
                DepositStatus.Failed -> Unit
            }
        }

        when {
            !hadCandidate -> mes("You have nothing to send to your bank.")
            deposited > 0 && unbankable == 0 && noSpace == 0 && fullStack == 0 ->
                mes("You send your inventory items to your bank.")
            deposited > 0 ->
                mes("You send some items to your bank. Some items could not be stored.")
            noSpace > 0 -> mes("Your bank cannot hold your items.")
            unbankable > 0 -> mes("Your items cannot be stored in the bank.")
            fullStack > 0 -> mes("You already have a full stack of that item in the bank.")
            else -> mes("You have nothing to send to your bank.")
        }
    }

    private fun ProtectedAccess.depositToBank(
        slot: Int,
        sendMessage: Boolean = true,
    ): DepositStatus {
        val obj = inv[slot] ?: return DepositStatus.Failed
        val objType = objTypes[obj]

        if (LootingBagObjs.isLootingBag(objType.id)) {
            if (sendMessage) {
                mes("You can't put the Looting bag inside itself.")
            }
            return DepositStatus.LootingBag
        }

        // Match BankInvScript.invDeposit: test the actual inventory object. Note conversion is
        // performed later by the transfer itself.
        if (objType.param(params.no_bank) != 0) {
            if (sendMessage) {
                mes("A magical force prevents you from banking this item!")
            }
            return DepositStatus.Unbankable
        }

        val tab = BankTab.Main
        val placeholder = objTypes.placeholder(objType)
        val containedObjSlot = bank.indexOfFirst { it?.id == obj.id || it?.id == placeholder.id }
        val prioritySlot =
            if (containedObjSlot != -1) {
                containedObjSlot
            } else {
                tab.slotRange(this).firstOrNull { bank[it] == null }
            }

        // This is intentionally the same transaction shape used by the normal bank's single-item
        // deposit path. Do not compress/reorder the bank or manually shrink tab sizes.
        val tabSlots = tab.slotRange(this)
        val insertQuery =
            player.invTransaction(inv, bank) {
                val fromInv = select(inv)
                val bankInv = select(bank)
                transfer {
                    this.from = fromInv
                    this.into = bankInv
                    this.fromSlot = slot
                    this.intoSlot = prioritySlot ?: tabSlots.first
                    this.intoCapacity = bankCapacity
                    this.count = obj.count
                    this.uncert = true
                    this.strict = false
                }
            }
        val result = insertQuery.results.last()

        if (result == TransactionResult.NotEnoughSpace && bank.occupiedSpace() >= bankCapacity) {
            if (sendMessage) {
                mes("You don't have enough space in your bank account.")
            }
            return DepositStatus.BankFull
        }

        if (result == TransactionResult.NotEnoughSpace) {
            if (sendMessage) {
                mes("You already have a full stack of that item in the bank.")
            }
            return DepositStatus.StackFull
        }

        if (!result.isOk()) {
            return DepositStatus.Failed
        }

        val expectedSlot = tabSlots.last + 1
        val expectedObj = objTypes.uncert(obj)
        if (bank[expectedSlot]?.id == expectedObj.id) {
            tab.increaseSize(this)
        }

        if (!result.fullSuccess) {
            if (sendMessage) {
                mes("You already have a full stack of that item in the bank.")
            }
            return DepositStatus.StackFull
        }

        if (sendMessage) {
            mes("You send the item to your bank.")
        }
        return DepositStatus.Success
    }

    private enum class DepositStatus {
        Success,
        LootingBag,
        Unbankable,
        BankFull,
        StackFull,
        Failed,
    }
}

internal object LootingBagObjs : ObjReferences() {
    val lootingBag = find("looting_bag")
    val lootingBagOpen = find("looting_bag_open")

    fun isLootingBag(id: Int): Boolean = id == lootingBag.id || id == lootingBagOpen.id
}
