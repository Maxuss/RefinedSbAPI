package space.maxus.refsb.api.craft

import org.bukkit.inventory.ItemStack
import org.jetbrains.annotations.NotNull
import space.maxus.refsb.api.Key

/**
 * This interface is used to create custom Skyblock Recipes
 */
interface Recipe {
    val key: Key
    var result: ItemStack

    var choiceMap: MutableMap<Char?, RecipeChoice<*>?>
    var rows: Array<String?>
    val matrix: String

    fun shape(@NotNull vararg shape: String) : Recipe
    fun setIngredient(char: Char, choice: RecipeChoice<*>?) : Recipe
}