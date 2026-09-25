package org.rsmod.server.install

import java.nio.file.Paths
import org.openrs2.cache.Cache
import org.rsmod.api.cache.types.loc.LocTypeDecoder

fun main() {
    val path = Paths.get("C:\\Projects\\rsmod\\.data\\cache\\game")

    Cache.open(path).use { cache ->
        val locs = LocTypeDecoder.decodeAll(cache)
        val ids = listOf(409, 411)

        for (id in ids) {
            val type = locs.types[id] ?: error("loc $id not found")
            println("===== LOC $id =====")
            println("name=${type.name}")
            println("internalName=${type.internalName}")
            println("ops=${type.op.contentToString()}")
            println("multiVarBit=${type.multiVarBit}")
            println("multiVarp=${type.multiVarp}")
            println("multiLocDefault=${type.multiLocDefault}")
            println("multiLoc=${type.multiLoc.contentToString()}")
            println("hash=${type.computeIdentityHash()}")
            println()
        }
    }
}
