package org.rsmod.content.skills.prayer.scripts

import jakarta.inject.Inject
import org.rsmod.api.config.refs.locs
import org.rsmod.api.config.refs.objs
import org.rsmod.api.config.refs.stats
import org.rsmod.api.script.onOpHeld1
import org.rsmod.api.script.onOpLocU
import org.rsmod.game.type.obj.ObjType
import org.rsmod.game.type.seq.SeqTypeList
import org.rsmod.plugin.scripts.PluginScript
import org.rsmod.plugin.scripts.ScriptContext

public class PrayerBones @Inject constructor(private val seqTypes: SeqTypeList) : PluginScript() {

    override fun ScriptContext.startup() {
        // Bones
        registerBones(objs.bones, 4.5)
        registerBones(objs.bones_burnt, 4.5)
        registerBones(objs.bat_bones, 5.3)
        registerBones(objs.big_bones, 15.0)
        registerBones(objs.zogre_bones, 22.5)
        registerBones(objs.babydragon_bones, 30.0)
        registerBones(objs.wyrm_bones, 50.0)
        registerBones(objs.babywyrm_bones, 50.0)
        registerBones(objs.dragon_bones, 72.0)
        registerBones(objs.wyvern_bones, 72.0)
        registerBones(objs.lava_dragon_bones, 85.0)
        registerBones(objs.dagannoth_king_bones, 125.0)
        registerBones(objs.dragon_bones_superior, 150.0)
        registerBones(objs.drake_bones, 80.0)
        registerBones(objs.hydra_bones, 110.0)
        registerBones(objs.wolf_bones, 4.5)
        registerBones(objs.tbwt_jogre_bones, 15.0)

        // Monkey bones
        registerBones(objs.mm_small_ninja_monkey_bones, 5.0)
        registerBones(objs.mm_medium_ninja_monkey_bones, 5.0)
        registerBones(objs.mm_normal_gorilla_monkey_bones, 5.0)
        registerBones(objs.mm_bearded_gorilla_monkey_bones, 5.0)
        registerBones(objs.mm_normal_monkey_bones, 5.0)
        registerBones(objs.mm_small_zombie_monkey_bones, 5.0)
        registerBones(objs.mm_large_zombie_monkey_bones, 5.0)

        // Ancestral bones
        registerBones(objs.zogre_ancestral_bones_fayg, 84.0)
        registerBones(objs.zogre_ancestral_bones_raurg, 96.0)
        registerBones(objs.zogre_ancestral_bones_ourg, 140.0)

        // Shade remains
        registerBones(objs.shade_bones1, 1.0)
        registerBones(objs.shade_bones2, 2.0)
        registerBones(objs.shade_bones3, 3.0)
        registerBones(objs.shade_bones4, 4.0)
        registerBones(objs.shade_bones5, 5.0)
        registerBones(objs.shade_bones6, 5.0)

        // Demon ashes
        registerAshes(objs.fiendish_ashes, 10.0)
        registerAshes(objs.vile_ashes, 25.0)
        registerAshes(objs.malicious_ashes, 65.0)
        registerAshes(objs.abyssal_ashes, 85.0)
        registerAshes(objs.infernal_ashes, 110.0)
    }

    private fun ScriptContext.registerBones(obj: ObjType, xp: Double) {
        onOpHeld1(obj) {
            val buryAnim = seqTypes[827]
            if (buryAnim != null) {
                anim(buryAnim)
            }

            it.inventory[it.slot] = null
            statAdvance(stats.prayer, xp)
            mes("You bury the bones.")
        }

        registerAltar(locs.altar, obj, xp)
        registerAltar(locs.chaosaltar, obj, xp)
    }

    private fun ScriptContext.registerAltar(
        altar: org.rsmod.game.type.loc.LocType,
        obj: ObjType,
        xp: Double,
    ) {
        onOpLocU(altar, obj) {
            val slot = it.invSlot

            if (inv[slot] == null) {
                return@onOpLocU
            }

            val offerAnim = seqTypes[827]
            if (offerAnim != null) {
                anim(offerAnim)
            }

            inv[slot] = null
            statAdvance(stats.prayer, xp * ALTAR_XP_MULTIPLIER)
            mes("The gods are very pleased with your offering.")
        }
    }

    private fun ScriptContext.registerAshes(obj: ObjType, xp: Double) {
        onOpHeld1(obj) {
            it.inventory[it.slot] = null
            statAdvance(stats.prayer, xp)
            mes("You scatter the ashes.")
        }
    }

    private companion object {
        const val ALTAR_XP_MULTIPLIER: Double = 3.5
    }
}
