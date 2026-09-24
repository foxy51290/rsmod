package org.rsmod.api.drops

import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.io.BufferedReader
import java.io.InputStreamReader
import org.rsmod.api.random.GameRandom
import org.rsmod.game.type.npc.UnpackedNpcType
import org.rsmod.game.type.obj.ObjTypeList

@Singleton
public class DropRepository
@Inject
constructor(private val objTypes: ObjTypeList, private val random: GameRandom) {
    private val osrsTables: Map<Int, DropTable> by lazy { loadOsrsTables() }

    public operator fun get(npc: UnpackedNpcType): DropTable? {
        return osrsTables[npc.id]
    }

    private fun loadOsrsTables(): Map<Int, DropTable> {
        val stream =
            javaClass.getResourceAsStream("/org/rsmod/api/drops/osrs-monster-drops.tsv")
                ?: error("Missing OSRS monster drop resource")
        val grouped = hashMapOf<Int, MutableList<DropEntry>>()
        BufferedReader(InputStreamReader(stream)).useLines { lines ->
            lines
                .filterNot { it.isBlank() || it.startsWith("#") }
                .forEach { line ->
                    val p = line.split('\t', limit = 9)
                    if (p.size < 8) return@forEach
                    val npcId = p[0].toInt()
                    val objId = p[1].toInt()
                    val min = p[2].toInt()
                    val max = p[3].toInt()
                    val numerator = p[4].toInt()
                    val denominator = p[5].toInt()
                    val rolls = p[6].toInt()
                    val objType = objTypes[objId] ?: return@forEach
                    val obj = objType.toHashedType()
                    val rollCode = p.getOrNull(8) ?: "I"
                    val rollType =
                        when {
                            rollCode == "G" -> DropRollType.Guaranteed
                            rollCode == "M" -> DropRollType.Main
                            rollCode.startsWith("R:") -> DropRollType.Group
                            else -> DropRollType.Independent
                        }
                    val rollGroup =
                        rollCode.removePrefix("R:").takeIf { rollType == DropRollType.Group }
                    grouped.getOrPut(npcId) { mutableListOf() } +=
                        DropEntry(
                            obj = obj,
                            min = min,
                            max = max,
                            denominator = denominator,
                            numerator = numerator,
                            rolls = rolls,
                            rollType = rollType,
                            rollGroup = rollGroup,
                        )
                }
        }
        return grouped.mapValues { DropTable(it.value) }
    }
}
