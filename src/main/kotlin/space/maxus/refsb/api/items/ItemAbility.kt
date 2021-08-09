package space.maxus.refsb.api.items

import org.bukkit.ChatColor
import space.maxus.refsb.api.SkyblockConstants

/**
 * Represents ability of item
 */
class ItemAbility(var name: String?, t: AbilityType?, descr: List<String?>?) {
    var type: AbilityType? = t
    var description: List<String?>? = descr
    var manaCost = 0
    var cooldown = 0
    var soulflowCost = 0

    fun generate(): List<String?> {
        val f: MutableList<String?> = ArrayList()
        f.add(type?.display?.replace("%NAME%", this.name ?: "undefined?!"))
        f.addAll(description ?: mutableListOf())
        if (manaCost > 0) f.add(ChatColor.DARK_GRAY.toString() + "Mana cost: " + ChatColor.AQUA + manaCost)
        if (manaCost > 0) f.add(ChatColor.DARK_GRAY.toString() + "Cooldown: " + ChatColor.GREEN + cooldown)
        if (soulflowCost > 0) f.add(ChatColor.DARK_GRAY.toString() + "Soulflow cost: " + ChatColor.DARK_AQUA + soulflowCost + SkyblockConstants.OVERFLOW)
        return f
    }
}