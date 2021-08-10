package space.maxus.refsb.api.craft

import org.bukkit.inventory.ItemStack
import space.maxus.refsb.api.Key
import space.maxus.refsb.api.SkyblockPlugin

/**
 * Recipe choice that is based on exact item
 */
class SimpleChoice(private val item: ItemStack, private val plugin: SkyblockPlugin) : RecipeChoice<ItemStack> {
    override fun test(that: ItemStack): Boolean {
        if(item == that) return true

        if((item.hasItemMeta() && that.hasItemMeta()))
            return item.itemMeta == that.itemMeta

        return false
    }

    override fun clone(): RecipeChoice<ItemStack> {
        return SimpleChoice(item, plugin)
    }

    /**
     * Gets the item from the choice if the item is custom. Otherwise return null
     */
    override fun getItem(): ItemStack {
        return item
    }

    /**
     * Gets the id of custom item to be used. If custom item is not used returns null
     */
    override fun getItemKey(): Key {
        return plugin.items.keyByValue(item) ?: Key.key(plugin, "null")
    }

}