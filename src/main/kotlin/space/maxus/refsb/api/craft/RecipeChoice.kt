package space.maxus.refsb.api.craft

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
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
    fun getMaterial() : Material? {
        return null
    }

    /**
     * Gets the id of custom item to be used. If custom item is not used returns null
     */
    fun getItemId() : String? {
        return null
    }
}