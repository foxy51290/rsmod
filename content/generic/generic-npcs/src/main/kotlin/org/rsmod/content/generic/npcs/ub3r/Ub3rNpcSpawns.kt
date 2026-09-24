package org.rsmod.content.generic.npcs.ub3r

import org.rsmod.api.type.builders.map.npc.MapNpcSpawnBuilder

internal object Ub3rNpcSpawns : MapNpcSpawnBuilder() {
    override fun onPackMapTask() {
        resourceFile<Ub3rNpcSpawns>("map/npcs.toml")
    }
}
