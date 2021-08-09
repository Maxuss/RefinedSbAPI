package space.maxus.refsb.util

import org.bukkit.ChatColor
import java.awt.Color

@Suppress("UNUSED")
/**
 * This enum should be used instead of Bukkit's ChatColor, because it has operator function, more useful for Kotlin
 */
enum class LegacyColor {
    BLACK,
    DARK_BLUE,
    DARK_GREEN,
    DARK_AQUA,
    DARK_RED,
    DARK_PURPLE,
    GOLD,
    GRAY,
    DARK_GRAY,
    BLUE,
    GREEN,
    AQUA,
    RED,
    LIGHT_PURPLE,
    YELLOW,
    WHITE,
    MAGIC,
    BOLD,
    STRIKETHROUGH,
    UNDERLINE,
    ITALIC,
    RESET
    ;

    @Suppress("CouldBePrivate")
    fun asBukkit() : ChatColor {
        return ChatColor.valueOf(name)
    }

    fun asJava() : Color {
        return asBukkit().asBungee().color
    }

    operator fun plus(other: String) : String {
        return asBukkit().toString()+other
    }

    override fun toString(): String {
        return asBukkit().toString()
    }
}