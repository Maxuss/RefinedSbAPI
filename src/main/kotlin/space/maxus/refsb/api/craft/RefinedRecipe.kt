package space.maxus.refsb.api.craft

import org.apache.commons.lang.Validate
import org.bukkit.inventory.ItemStack
import org.jetbrains.annotations.NotNull
import space.maxus.refsb.api.Key

/**
 * This class is an implementation of Recipe interface
 */
class RefinedRecipe internal constructor(override val key: Key, override var result: ItemStack) : Recipe {
    override var choiceMap: MutableMap<Char?, RecipeChoice<*>?> = HashMap()
    override var rows: Array<String?> = emptyArray()
    override val matrix: String
        get() = rows.joinToString(separator = "\n")

    @Suppress("UNCHECKED_CAST")
    override fun shape(@NotNull vararg shape: String) : Recipe {
        Validate.notNull(shape, "Shape can not be null!")
        Validate.isTrue(shape.size in 1..3, "Shape must be 1, 2 or 3 chars long, not "+shape.size+"!")
        var lastLen = -1
        val var3: Array<String> = shape as Array<String>
        val var4 = shape.size
        var var5: Int
        run {
            var5 = 0
            while (var5 < var4) {
                val row = var3[var5]
                Validate.notNull(row, "Shape cannot have null rows")
                Validate.isTrue(
                    row.length in 1..3, "Crafting rows should be 1, 2, or 3 characters, not ",
                    row.length.toLong()
                )
                Validate.isTrue(lastLen == -1 || lastLen == row.length, "Crafting recipes must be rectangular")
                lastLen = row.length
                ++var5
            }
        }

        this.rows = arrayOfNulls(shape.size)

        val newIngredients: HashMap<Char?, RecipeChoice<*>?> = HashMap()
        val var14: Array<String> = shape
        var5 = shape.size

        for (var15 in 0 until var5) {
            val row = var14[var15]
            val var8 = row.toCharArray()
            val var9 = var8.size
            for (var10 in 0 until var9) {
                val c = var8[var10]
                newIngredients[c] = this.choiceMap[c]
            }
        }

        this.choiceMap = newIngredients
        return this
    }

    override fun setIngredient(char: Char, choice: RecipeChoice<*>?) : Recipe {
        Validate.isTrue(this.choiceMap.containsKey(char), "Symbol does not appear in the shape: ", key)
        this.choiceMap[char] = choice
        return this
    }
}