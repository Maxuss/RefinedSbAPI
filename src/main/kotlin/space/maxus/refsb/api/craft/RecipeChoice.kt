package space.maxus.refsb.api.craft

import org.bukkit.inventory.ItemStack
import space.maxus.refsb.api.Key
import java.util.function.Predicate

/**
 * This class is used for making recipe choices
 */
interface RecipeChoice<T> : Predicate<T>, Cloneable {
    override fun test(that: T): Boolean
    override fun clone(): RecipeChoice<T>

    /**
     * Gets the item from the choice if the item is custom. Otherwise return null
     */
    fun getItem() : ItemStack

    /**
     * Gets the id of custom item to be used. If custom item is not used returns null
     */
    fun getItemKey() : Key
}