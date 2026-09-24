package org.rsmod.api.drops

import org.rsmod.game.type.obj.ObjType

public enum class DropRollType {
    Independent,
    Guaranteed,
    Main,
    Group,
}

public data class DropEntry(
    public val obj: ObjType,
    public val min: Int = 1,
    public val max: Int = min,
    public val denominator: Int = 1,
    public val numerator: Int = 1,
    public val rolls: Int = 1,
    public val rollType: DropRollType = DropRollType.Independent,
    public val rollGroup: String? = null,
)

public data class DropTable(public val entries: List<DropEntry>)

public class DropTableBuilder {
    private val entries = mutableListOf<DropEntry>()

    public fun add(
        obj: ObjType,
        min: Int = 1,
        max: Int = min,
        denominator: Int = 1,
        numerator: Int = 1,
        rolls: Int = 1,
        rollType: DropRollType = DropRollType.Independent,
        rollGroup: String? = null,
    ) {
        entries +=
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

    public fun build(): DropTable = DropTable(entries)
}
