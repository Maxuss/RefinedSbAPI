package space.maxus.refsb.api.items

import org.bukkit.ChatColor

/**
 * Enum representing rarity of item
 */
enum class SkyblockRarity(display: String, color: ChatColor, val index: Int) {
    ERROR(ChatColor.MAGIC.toString() + "ERROR", ChatColor.YELLOW, 0),

    COMMON(
        "COMMON",
        ChatColor.WHITE,
        1
    ),
    UNCOMMON("UNCOMMON", ChatColor.GREEN, 2),
    RARE("RARE", ChatColor.BLUE, 3),
    EPIC(
        "EPIC",
        ChatColor.DARK_PURPLE,
        4
    ),
    LEGENDARY("LEGENDARY", ChatColor.GOLD, 5),
    MYTHIC(
        "MYTHIC",
        ChatColor.LIGHT_PURPLE,
        6
    ),
    SUPREME("SUPREME", ChatColor.DARK_RED, 8),
    SPECIAL("SPECIAL", ChatColor.RED, 9),
    VERY_SPECIAL(
        "VERY SPECIAL",
        ChatColor.RED,
        10
    );

    val displayName: String = color.toString() + "" + ChatColor.BOLD + display
    val unformattedName: String = display
    val displayColor: ChatColor = color

    companion object {
        fun byIndex(index: Int): SkyblockRarity {
            return if (index >= values().size || index < 0) VERY_SPECIAL else values()[index]
        }
    }

}