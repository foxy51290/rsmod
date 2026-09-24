package org.rsmod.content.skills.prayer.scripts

import org.rsmod.api.config.refs.objs
import org.rsmod.api.config.refs.stats
import org.rsmod.api.script.onOpHeld1
import org.rsmod.plugin.scripts.PluginScript
import org.rsmod.plugin.scripts.ScriptContext

public class PrayerBones : PluginScript() {

    override fun ScriptContext.startup() {
        println("!!!!!!!!!!!!!!!! PRAYER BONES SCRIPT LOADED !!!!!!!!!!!!!!!!")

        onOpHeld1(objs.bones) {
            println("!!!!!!!!!!!!!!!! BURY EVENT FIRED !!!!!!!!!!!!!!!!")

            it.inventory[it.slot] = null
            statAdvance(stats.prayer, 4.0)
            mes("You bury the bones.")
        }
    }
}
