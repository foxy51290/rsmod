package org.rsmod.content.interfaces.worldmap.script

import org.rsmod.api.config.refs.components
import org.rsmod.api.config.refs.interfaces
import org.rsmod.api.player.output.runClientScript
import org.rsmod.api.player.ui.ifOpenTop
import org.rsmod.api.script.onIfOverlayButton
import org.rsmod.plugin.scripts.PluginScript
import org.rsmod.plugin.scripts.ScriptContext

public class WorldMapScript : PluginScript() {

    override fun ScriptContext.startup() {
        onIfOverlayButton(components.orbs_worldmap) {
            player.runClientScript(1749, player.coords.packed)
            player.ifOpenTop(interfaces.worldmap)
        }

        onIfOverlayButton(components.worldmap_close) {
            player.ifOpenTop(interfaces.toplevel_osrs_stretch)
        }
    }
}
