package space.maxus.refsb.api.items

import org.bukkit.ChatColor

/**
 * Enum representing all types for item abilities.
 */
enum class AbilityType(shown: String) {
    PASSIVE           ("${ChatColor.GOLD}Item Ability: %NAME%"),
    RIGHT_CLICK       ("${ChatColor.GOLD}Item Ability: %NAME% "+ChatColor.YELLOW+""+ChatColor.BOLD+"RIGHT CLICK"),
    LEFT_CLICK        ("${ChatColor.GOLD}Item Ability: %NAME% "+ChatColor.YELLOW+""+ChatColor.BOLD+"LEFT CLICK"),
    FULL_SET_BONUS    ("${ChatColor.GOLD}Full Set Bonus: %NAME%"),
    SNEAK             ("${ChatColor.GOLD}Item Ability: %NAME% "+ChatColor.YELLOW+""+ChatColor.BOLD+"SNEAK"),
    SNEAK_RIGHT_CLICK ("${ChatColor.GOLD}Item Ability: %NAME% "+ChatColor.YELLOW+""+ChatColor.BOLD+"SNEAK RIGHT CLICK"),
    SNEAK_LEFT_CLICK  ("${ChatColor.GOLD}Item Ability: %NAME% "+ChatColor.YELLOW+""+ChatColor.BOLD+"SNEAK LEFT CLICK"),
    DOUBLE_RIGHT_CLICK("${ChatColor.GOLD}Item Ability: %NAME% "+ChatColor.YELLOW+""+ChatColor.BOLD+"DOUBLE RIGHT CLICK"),
    DOUBLE_LEFT_CLICK ("${ChatColor.GOLD}Item Ability: %NAME% "+ChatColor.YELLOW+""+ChatColor.BOLD+"DOUBLE LEFT CLICK")
    ;

    val display: String = shown
}