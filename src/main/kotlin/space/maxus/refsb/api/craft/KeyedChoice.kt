package space.maxus.refsb.api.craft

import org.bukkit.inventory.ItemStack
import space.maxus.refsb.api.Key
import space.maxus.refsb.api.SkyblockPlugin

/**
 * Recipe choice, that is based on Key
 */
class KeyedChoice(val key: Key, val plugin: SkyblockPlugin) : RecipeChoice<Key> {

    override fun test(that: Key): Boolean {
        return key.test(that)
    }

    override fun clone(): RecipeChoice<Key> {
        return KeyedChoice(key.clone(), plugin)
    }

    override fun getItemKey(): Key {
        return key
    }

    /**
     * Gets the item from the choice if the item is custom. Otherwise return null
     */
    override fun getItem(): ItemStack {
        return plugin.items[key]!!
    }
}