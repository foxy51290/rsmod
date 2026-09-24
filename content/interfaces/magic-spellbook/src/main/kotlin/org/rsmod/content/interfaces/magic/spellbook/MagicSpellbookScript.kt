package org.rsmod.content.interfaces.magic.spellbook

import jakarta.inject.Inject
import org.rsmod.api.config.refs.components
import org.rsmod.api.config.refs.seqs
import org.rsmod.api.config.refs.spotanims
import org.rsmod.api.player.protect.ProtectedAccessLauncher
import org.rsmod.api.realm.Realm
import org.rsmod.api.script.onIfOverlayButton
import org.rsmod.plugin.scripts.PluginScript
import org.rsmod.plugin.scripts.ScriptContext

public class MagicSpellbookScript
@Inject
constructor(private val protectedAccess: ProtectedAccessLauncher, private val realm: Realm) :
    PluginScript() {

    override fun ScriptContext.startup() {
        onIfOverlayButton(components.magic_spellbook_teleport_home_standard) {
            protectedAccess.launch(player) {
                anim(seqs.emote_panic)
                spotanim(spotanims.teleport_casting)
                delay(seqs.teleport)
                telejump(realm.config.spawnCoord)
            }
        }
    }
}
