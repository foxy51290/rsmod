package org.rsmod.content.generic.npcs.ub3r

import org.rsmod.api.type.editors.npc.NpcEditor
import org.rsmod.api.type.refs.npc.NpcReferences

/**
 * Server-side stat overrides ported from the ub3r NPC definitions.
 *
 * The NPC cache/internal names are resolved by rsmod's existing NPC name mapping.
 * Attack/death/respawn animation overrides remain in the cache-enricher TOML.
 */
internal object Ub3rNpcReferences : NpcReferences() {
    val ghost = find("ghost")
    val hellhound = find("hellhound")
    val wolf = find("wolf")
    val terrorbird = find("terrorbird")
    val red_dragon = find("red_dragon")
    val black_dragon = find("black_dragon")
    val green_dragon = find("green_dragon")
    val blue_dragon = find("blue_dragon")
    val ghoul = find("ghoul")
    val highwayman = find("highwayman")
    val chaos_druid = find("chaos_druid")
    val chaos_druid_warrior = find("chaos_druid_warrior")
    val watchtower_wizard = find("watchtower_wizard")
    val chadwell = find("chadwell")
    val kalphite_worker = find("kalphite_worker")
    val kalphite_soldier = find("kalphite_soldier")
    val black_demon = find("black_demon")
    val lesser_demon = find("lesser_demon")
    val greater_demon = find("greater_demon")
    val abyssal_leech = find("abyssal_leech")
    val shadow_warrior = find("shadow_warrior")
    val deadly_red_spider = find("deadly_red_spider")
    val druid = find("druid")
    val hero = find("hero")
    val knight_of_ardougne = find("knight_of_ardougne")
    val san_tojalon = find("san_tojalon")
    val black_knight_titan = find("black_knight_titan")
    val kalphite_queen = find("kalphite_queen")
    val ogre_shaman = find("ogre_shaman")
    val desert_wolf = find("desert_wolf")
    val ice_queen = find("ice_queen")
    val skeleton_hellhound = find("skeleton_hellhound")
    val albino_bat = find("harmless_island_albino_bat")
    val desert_lizard = find("slayer_lizard_large1_green")
    val crawling_hand = find("slayer_crawling_hand_1")
    val infernal_mage = find("slayer_infernal_mage_1")
    val aberrant_spectre = find("slayer_abberant_spectre_1")
    val slayer_banshee_1 = find("slayer_banshee_1")
    val slayer_pyrefiend_1 = find("slayer_pyrefiend_1")
    val slayer_jelly_1 = find("slayer_jelly_1")
    val slayer_bloodveld = find("slayer_bloodveld")
    val slayer_gargoyle_1 = find("slayer_gargoyle_1")
    val firegiant = find("firegiant")
    val mossgiant = find("mossgiant")
    val giant_rockcrab = find("giant_rockcrab")

    val nechryael = find("slayer_nechryael")
    val dwarf = find("dwarf_normal")
    val chaos_dwarf = find("dwarf_chaos")
    val mithril_dragon = find("brut_mithril_dragon")
    val scorpion = find("scorpion")
    val tzhaar_mej = find("tzhaar_mej1")
    val tzhaar_xil = find("tzhaar_xil1")
    val tzhaar_ket = find("tzhaar_ket1")

    val man = find("man")
    val zombie_unarmed = find("zombie_unarmed")
    val zombie2 = find("zombie2")
    val skeleton_unarmed = find("skeleton_unarmed")
    val chicken = find("chicken")
    val woman = find("woman")
    val guard = find("guard1")
    val icewarrior = find("icewarrior")
    val zogre_1 = find("zogre_1")
    val doorman = find("doorman")
    val sigbert_the_adventurer = find("sigbert_the_adventurer")
    val gnomeballer = find("gnomeballer")
    val escaped_slave = find("escaped_slave")
    val captain_rovin = find("captain_rovin")
    val mazchna = find("slayer_master_2_mazchna")
    val duradel = find("slayer_master_5_duradel")
    val mummy = find("deserttreasure_mummy_1")
    val jungle_horror = find("harmless_island_jungle_horror_worker")
    val cave_horror = find("harmless_island_cave_horror_worker")
    val vulture = find("rag_vulture")
    val dagannoth = find("horror_dagannoth_medium")
    val slash_bash = find("zogre_slash_bash")
    val werewolf = find("canafis_werewolf_man1")
    val crocodile = find("bcs_crocodile")
    val jackal = find("ics_little_jackal")
    val dad = find("fd_troll_dad")
    val venenatis = find("venenatis")
    val horvik_the_armourer = find("horvik_the_armourer")
    val postie_pete = find("postie_pete")
    val customs_officer = find("customs_officer")
    val lunar_moonclan_baba_yaga = find("lunar_moonclan_baba_yaga")
    val ungadulu_good = find("ungadulu_good")
    val giant_spider = find("giantspider1")
    val wilderness_bandit = find("wilderness_bandit")
    val yanille_watchman = find("yanille_watchman")
    val master_farmer_1 = find("master_farmer_1")
    val hill_giant = find("wilderness_hill_giant")
    val shantay = find("shantay")
    val tanner = find("tanner")
    val aubury = find("aubury")
    val jatix = find("jatix")
    val head_mourner = find("sote_head_mourner")
}

internal object Ub3rNpcEdits : NpcEditor() {
    init {
        edit(Ub3rNpcReferences.giant_spider) {
            attack = 19
            strength = 21
            defence = 16
            hitpoints = 2
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.hill_giant) {
            attack = 20
            strength = 20
            defence = 20
            hitpoints = 35
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.ungadulu_good) {
            attack = 110
            strength = 80
            defence = 90
            hitpoints = 120
            ranged = 180
            magic = 65
        }
        edit(Ub3rNpcReferences.mummy) {
            attack = 75
            strength = 90
            defence = 75
            hitpoints = 100
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.jungle_horror) {
            attack = 60
            strength = 80
            defence = 45
            hitpoints = 52
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.guard) {
            attack = 8
            strength = 9
            defence = 10
            hitpoints = 22
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.icewarrior) {
            attack = 47
            strength = 47
            defence = 47
            hitpoints = 60
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.zogre_1) {
            attack = 20
            strength = 36
            defence = 35
            hitpoints = 50
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.nechryael) {
            attack = 140
            strength = 130
            defence = 140
            hitpoints = 350
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.dwarf) {
            attack = 8
            strength = 15
            defence = 5
            hitpoints = 10
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.chaos_dwarf) {
            attack = 43
            strength = 43
            defence = 43
            hitpoints = 62
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.mithril_dragon) {
            attack = 200
            strength = 230
            defence = 220
            hitpoints = 250
            ranged = 168
            magic = 168
        }
        edit(Ub3rNpcReferences.scorpion) {
            attack = 55
            strength = 50
            defence = 60
            hitpoints = 55
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.tzhaar_mej) {
            attack = 50
            strength = 60
            defence = 85
            hitpoints = 115
            ranged = 1
            magic = 1500
        }
        edit(Ub3rNpcReferences.tzhaar_xil) {
            attack = 130
            strength = 100
            defence = 100
            hitpoints = 135
            ranged = 120
            magic = 40
        }
        edit(Ub3rNpcReferences.tzhaar_ket) {
            attack = 100
            strength = 120
            defence = 100
            hitpoints = 155
            ranged = 1
            magic = 40
        }
        edit(Ub3rNpcReferences.ghost) {
            attack = 10
            strength = 20
            defence = 15
            hitpoints = 20
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.hellhound) {
            attack = 90
            strength = 90
            defence = 90
            hitpoints = 116
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.wolf) {
            attack = 30
            strength = 30
            defence = 30
            hitpoints = 70
            ranged = 1
        }
        edit(Ub3rNpcReferences.terrorbird) {
            attack = 43
            strength = 43
            defence = 43
            hitpoints = 34
        }
        edit(Ub3rNpcReferences.red_dragon) {
            attack = 130
            strength = 140
            defence = 130
            hitpoints = 150
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.black_dragon) {
            attack = 190
            strength = 210
            defence = 190
            hitpoints = 200
            ranged = 1
            magic = 100
        }
        edit(Ub3rNpcReferences.green_dragon) {
            attack = 65
            strength = 70
            defence = 60
            hitpoints = 80
            ranged = 1
            magic = 68
        }
        edit(Ub3rNpcReferences.blue_dragon) {
            attack = 90
            strength = 95
            defence = 90
            hitpoints = 110
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.ghoul) {
            attack = 90
            strength = 100
            defence = 80
            hitpoints = 115
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.highwayman) {
            attack = 6
            strength = 12
            defence = 2
            hitpoints = 11
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.chaos_druid) {
            attack = 25
            strength = 20
            defence = 20
            hitpoints = 30
            ranged = 1
            magic = 10
        }
        edit(Ub3rNpcReferences.chaos_druid_warrior) {
            attack = 18
            strength = 32
            defence = 22
            hitpoints = 24
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.watchtower_wizard) {
            attack = 20
            strength = 36
            defence = 35
            hitpoints = 71
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.chadwell) {
            attack = 68
            strength = 70
            defence = 50
            hitpoints = 70
        }
        edit(Ub3rNpcReferences.kalphite_worker) {
            attack = 15
            strength = 15
            defence = 10
            hitpoints = 22
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.kalphite_soldier) {
            attack = 65
            strength = 65
            defence = 50
            hitpoints = 70
        }
        edit(Ub3rNpcReferences.black_demon) {
            attack = 185
            strength = 200
            defence = 185
            hitpoints = 360
        }
        edit(Ub3rNpcReferences.lesser_demon) {
            attack = 80
            strength = 90
            defence = 90
            hitpoints = 110
        }
        edit(Ub3rNpcReferences.greater_demon) {
            attack = 95
            strength = 99
            defence = 95
            hitpoints = 166
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.abyssal_leech) {
            attack = 50
            strength = 50
            defence = 30
            hitpoints = 35
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.shadow_warrior) {
            attack = 45
            strength = 50
            defence = 55
            hitpoints = 65
            ranged = 60
            magic = 60
        }
        edit(Ub3rNpcReferences.deadly_red_spider) {
            attack = 50
            strength = 68
            defence = 38
            hitpoints = 75
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.druid) {
            attack = 30
            strength = 15
            defence = 15
            hitpoints = 30
        }
        edit(Ub3rNpcReferences.hero) {
            attack = 45
            strength = 45
            defence = 40
            hitpoints = 83
        }
        edit(Ub3rNpcReferences.knight_of_ardougne) {
            attack = 30
            strength = 40
            defence = 20
            hitpoints = 52
        }
        edit(Ub3rNpcReferences.san_tojalon) {
            attack = 130
            strength = 120
            defence = 150
            hitpoints = 295
        }
        edit(Ub3rNpcReferences.black_knight_titan) {
            attack = 110
            strength = 160
            defence = 150
            hitpoints = 335
        }
        edit(Ub3rNpcReferences.kalphite_queen) {
            attack = 180
            strength = 290
            defence = 250
            hitpoints = 900
            ranged = 240
            magic = 150
        }
        edit(Ub3rNpcReferences.desert_wolf) {
            attack = 50
            strength = 60
            defence = 50
            hitpoints = 66
        }
        edit(Ub3rNpcReferences.ice_queen) {
            attack = 95
            strength = 135
            defence = 115
            hitpoints = 285
        }
        edit(Ub3rNpcReferences.skeleton_hellhound) {
            attack = 93
            strength = 81
            defence = 79
            hitpoints = 247
        }
        edit(Ub3rNpcReferences.aberrant_spectre) {
            attack = 80
            strength = 80
            defence = 80
            hitpoints = 120
            ranged = 1
            magic = 105
        }
        edit(Ub3rNpcReferences.infernal_mage) {
            attack = 60
            strength = 55
            defence = 45
            hitpoints = 60
            ranged = 60
            magic = 60
        }
        edit(Ub3rNpcReferences.crawling_hand) {
            attack = 15
            strength = 8
            defence = 8
            hitpoints = 16
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.desert_lizard) {
            attack = 22
            strength = 22
            defence = 22
            hitpoints = 33
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.albino_bat) {
            attack = 50
            strength = 60
            defence = 35
            hitpoints = 43
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.slayer_banshee_1) {
            attack = 40
            strength = 60
            defence = 20
            hitpoints = 60
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.slayer_pyrefiend_1) {
            attack = 25
            strength = 15
            defence = 12
            hitpoints = 45
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.slayer_jelly_1) {
            attack = 50
            strength = 50
            defence = 50
            hitpoints = 75
            ranged = 1
            magic = 45
        }
        edit(Ub3rNpcReferences.slayer_bloodveld) {
            attack = 75
            strength = 45
            defence = 30
            hitpoints = 120
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.slayer_gargoyle_1) {
            attack = 95
            strength = 95
            defence = 70
            hitpoints = 150
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.firegiant) {
            attack = 65
            strength = 80
            defence = 65
            hitpoints = 105
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.mossgiant) {
            attack = 30
            strength = 40
            defence = 30
            hitpoints = 47
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.giant_rockcrab) {
            attack = 1
            strength = 1
            defence = 1
            hitpoints = 40
            ranged = 1
            magic = 1
        }

        edit(Ub3rNpcReferences.man) {
            attack = 40
            strength = 40
            defence = 50
            hitpoints = 7
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.zombie2) {
            attack = 17
            strength = 17
            defence = 17
            hitpoints = 30
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.skeleton_unarmed) {
            attack = 13
            strength = 13
            defence = 18
            hitpoints = 29
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.chicken) {
            attack = 1
            strength = 1
            defence = 1
            hitpoints = 3
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.woman) {
            attack = 1
            strength = 1
            defence = 1
            hitpoints = 7
            ranged = 1
            magic = 1
        }
        edit(Ub3rNpcReferences.doorman) {
            attack = 8
            strength = 9
            defence = 10
            hitpoints = 22
            ranged = 1
            magic = 1
        }

        edit(Ub3rNpcReferences.sigbert_the_adventurer) {
            attack = 8
            strength = 9
            defence = 10
            hitpoints = 22
            ranged = 1
            magic = 1
        }

        edit(Ub3rNpcReferences.gnomeballer) {
            attack = 60
            strength = 30
            defence = 40
            hitpoints = 40
            ranged = 1
            magic = 1
        }

        edit(Ub3rNpcReferences.cave_horror) {
            attack = 80
            strength = 95
            defence = 55
            hitpoints = 70
            ranged = 140
            magic = 80
        }

        edit(Ub3rNpcReferences.vulture) {
            attack = 25
            strength = 25
            defence = 25
            hitpoints = 30
            ranged = 1
            magic = 1
        }

        edit(Ub3rNpcReferences.dagannoth) {
            attack = 1
            strength = 1
            defence = 1
            hitpoints = 71
            ranged = 1
            magic = 1
        }

        edit(Ub3rNpcReferences.slash_bash) {
            attack = 100
            strength = 120
            defence = 60
            hitpoints = 100
            ranged = 100
            magic = 1
        }

        edit(Ub3rNpcReferences.werewolf) {
            attack = 72
            strength = 80
            defence = 70
            hitpoints = 105
            ranged = 1
            magic = 1
        }

        edit(Ub3rNpcReferences.crocodile) {
            attack = 65
            strength = 70
            defence = 55
            hitpoints = 78
            ranged = 1
            magic = 1
        }

        edit(Ub3rNpcReferences.jackal) {
            attack = 25
            strength = 34
            defence = 30
            hitpoints = 39
            ranged = 1
            magic = 1
        }

        edit(Ub3rNpcReferences.dad) {
            attack = 80
            strength = 110
            defence = 90
            hitpoints = 120
            ranged = 1
            magic = 1
        }

        edit(Ub3rNpcReferences.venenatis) {
            attack = 160
            strength = 170
            defence = 250
            hitpoints = 900
            ranged = 1
            magic = 666
        }

        edit(Ub3rNpcReferences.wilderness_bandit) {
            attack = 66
            strength = 66
            defence = 66
            hitpoints = 66
            ranged = 66
            magic = 66
        }

        edit(Ub3rNpcReferences.yanille_watchman) {
            attack = 16
            strength = 30
            defence = 20
            hitpoints = 22
            ranged = 1
            magic = 1
        }

        edit(Ub3rNpcReferences.head_mourner) {
            attack = 132
            strength = 230
            defence = 122
            hitpoints = 280
            ranged = 1
            magic = 1
        }
    }
}
