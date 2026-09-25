@file:Suppress("SpellCheckingInspection", "unused")

package org.rsmod.api.config.refs

import org.rsmod.api.type.refs.loc.LocReferences

typealias locs = BaseLocs

object BaseLocs : LocReferences() {
    val altar = find("altar")
    val chaosaltar = find("chaosaltar")
}
