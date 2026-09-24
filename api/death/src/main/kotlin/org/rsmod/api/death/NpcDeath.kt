package org.rsmod.api.death

import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.rsmod.api.config.constants
import org.rsmod.api.config.refs.params
import org.rsmod.api.config.refs.varns
import org.rsmod.api.config.refs.varps
import org.rsmod.api.drops.DropEntry
import org.rsmod.api.drops.DropRepository
import org.rsmod.api.drops.DropRollType
import org.rsmod.api.npc.access.StandardNpcAccess
import org.rsmod.api.npc.vars.typePlayerUidVarn
import org.rsmod.api.player.output.soundSynth
import org.rsmod.api.player.vars.intVarp
import org.rsmod.api.player.vars.typeNpcUidVarp
import org.rsmod.api.random.GameRandom
import org.rsmod.api.repo.npc.NpcRepository
import org.rsmod.api.repo.obj.ObjRepository
import org.rsmod.game.entity.Npc
import org.rsmod.game.entity.Player
import org.rsmod.game.entity.PlayerList
import org.rsmod.game.entity.npc.NpcUid
import org.rsmod.game.type.seq.SeqTypeList
import org.rsmod.map.CoordGrid

@Singleton
public class NpcDeath
@Inject
constructor(
    private val npcRepo: NpcRepository,
    private val seqTypes: SeqTypeList,
    private val players: PlayerList,
    private val objRepo: ObjRepository,
    private val random: GameRandom,
    private val drops: DropRepository,
) {
    public suspend fun deathNoDrops(access: StandardNpcAccess) {
        access.death(npcRepo, seqTypes, players)
    }

    public suspend fun deathWithDrops(
        access: StandardNpcAccess,
        dropCoords: CoordGrid = access.coords,
    ) {
        access.death(npcRepo, seqTypes, players)
        access.npc.spawnDeathDrops(dropCoords)
    }

    private fun Npc.spawnDeathDrops(dropCoords: CoordGrid) {
        val hero = findHero(players) ?: return
        val table = drops[type] ?: return
        val duration = hero.lootDropDuration ?: constants.lootdrop_duration

        val guaranteed = table.entries.filter { it.rollType == DropRollType.Guaranteed }
        for (entry in guaranteed) {
            repeat(entry.rolls) { spawnDropEntry(entry, dropCoords, duration, hero) }
        }

        val main = table.entries.filter { it.rollType == DropRollType.Main }
        for ((rolls, entries) in main.groupBy { it.rolls }) {
            rollExclusiveOrFallback(entries, rolls, dropCoords, duration, hero)
        }

        val groups = table.entries.filter { it.rollType == DropRollType.Group }
        for ((_, entries) in groups.groupBy { it.rollGroup ?: "group" }) {
            val rolls = entries.maxOfOrNull { it.rolls } ?: 1
            rollExclusiveOrFallback(entries, rolls, dropCoords, duration, hero)
        }

        val independent = table.entries.filter { it.rollType == DropRollType.Independent }
        for (entry in independent) {
            rollIndependent(entry, dropCoords, duration, hero)
        }
    }

    private fun rollExclusiveOrFallback(
        entries: List<DropEntry>,
        rolls: Int,
        dropCoords: CoordGrid,
        duration: Int,
        hero: Player,
    ) {
        val totalChance = entries.sumOf { it.numerator.toDouble() / it.denominator.toDouble() }
        if (totalChance > 1.000000001) {
            // Some flattened Wiki datasets merge alternate/versioned or linked drops. In those
            // cases an exclusive roll cannot preserve the supplied marginal probabilities safely,
            // so retain independent row rolls until that table has an explicit relationship model.
            for (entry in entries) {
                rollIndependent(entry, dropCoords, duration, hero)
            }
            return
        }

        repeat(rolls) {
            val roll = random.randomDouble()
            var cumulative = 0.0
            for (entry in entries) {
                cumulative += entry.numerator.toDouble() / entry.denominator.toDouble()
                if (roll < cumulative) {
                    spawnDropEntry(entry, dropCoords, duration, hero)
                    return@repeat
                }
            }
        }
    }

    private fun rollIndependent(
        entry: DropEntry,
        dropCoords: CoordGrid,
        duration: Int,
        hero: Player,
    ) {
        repeat(entry.rolls) {
            if (random.of(entry.denominator) >= entry.numerator) {
                return@repeat
            }
            spawnDropEntry(entry, dropCoords, duration, hero)
        }
    }

    private fun spawnDropEntry(
        entry: DropEntry,
        dropCoords: CoordGrid,
        duration: Int,
        hero: Player,
    ) {
        val amount = random.of(entry.min, entry.max)
        objRepo.add(entry.obj, dropCoords, duration, hero, amount)
    }

    public fun spawnDrops(access: StandardNpcAccess, dropCoords: CoordGrid = access.coords) {
        access.npc.spawnDeathDrops(dropCoords)
    }
}

private var Player.lastCombat: Int by intVarp(varps.lastcombat)
private var Player.aggressiveNpc: NpcUid? by typeNpcUidVarp(varps.aggressive_npc)
private var Npc.aggressivePlayer by typePlayerUidVarn(varns.aggressive_player)

/** Handles the death sequence of this [StandardNpcAccess.npc]. */
public suspend fun StandardNpcAccess.death(
    npcRepo: NpcRepository,
    seqTypes: SeqTypeList,
    players: PlayerList,
) {
    walk(coords)
    noneMode()
    hideAllOps()
    arriveDelay()

    val aggressivePlayer = npc.aggressivePlayer
    if (aggressivePlayer != null) {
        val player = aggressivePlayer.resolve(players)

        val deathSound = paramOrNull(params.death_sound)
        if (deathSound != null && player != null) {
            player.soundSynth(deathSound)
        }

        if (player != null && player.aggressiveNpc == npc.uid) {
            player.lastCombat = 0
        }
    }

    val deathAnim = param(params.death_anim)
    anim(deathAnim)
    delay(seqTypes[deathAnim])

    if (npc.respawns) {
        npcRepo.despawn(npc, npc.type.respawnRate)
        return
    }

    npcRepo.del(npc, Int.MAX_VALUE)
}
