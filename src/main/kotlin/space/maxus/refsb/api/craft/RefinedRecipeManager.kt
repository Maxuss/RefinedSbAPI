package space.maxus.refsb.api.craft

import org.apache.commons.lang.Validate
import org.bukkit.inventory.ItemStack
import space.maxus.refsb.RefinedAPI
import space.maxus.refsb.api.Key
import space.maxus.refsb.api.SkyblockPlugin

/**
 * This class is used for managing your custom recipes
 */
@Suppress("UNUSED")
class RefinedRecipeManager(plugin: SkyblockPlugin, api: RefinedAPI) {
    /**
     * All registered recipes for your plugin
     */
    val registeredRecipes: MutableMap<Key, Recipe> = HashMap()

    /**
     * Registers a single created recipe to the manager
     */
    fun addRecipe(recipe: Recipe) {
        Validate.isTrue(!checkRecipe(recipe.key), "Can not register recipe with id: ${recipe.key} because it already exists! Skipping...")
        registeredRecipes[recipe.key] = recipe
    }

    /**
     * Creates a single recipe implementation for use
     */
    fun createRecipe(key: Key, result: ItemStack) : Recipe {
        Validate.isTrue(!checkRecipe(key), "Can not create recipe with id: $key because that key already exists! Skipping...")
        return RefinedRecipe(key, result)
    }

    private fun checkRecipe(key: Key) : Boolean {
        return registeredRecipes.containsKey(key)
    }
}