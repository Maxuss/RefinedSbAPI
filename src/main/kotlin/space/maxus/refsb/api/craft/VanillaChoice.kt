package space.maxus.refsb.api.craft

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class VanillaChoice(private val mat: Material) : RecipeChoice<Material> {
    override fun test(that: Material): Boolean {
        return that == mat
    }

    override fun clone(): RecipeChoice<Material> {
        return VanillaChoice(mat)
    }

    override fun getMaterial(): Material {
        return mat
    }
}