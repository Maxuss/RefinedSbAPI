package space.maxus.refsb.api.craft

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack
import space.maxus.refsb.api.gui.InventoryBase
import space.maxus.refsb.util.GuiHelper
import space.maxus.refsb.util.TextUtils

/**
 * This inventory is a pre-made crafting gui, that supports all recipes and should be called whenever you want in your plugin
 */
class CraftingInventory : InventoryBase() {
    /**
     * Title of the inventory
     */
    override val name: Component
        get() = TextUtils.legacy("Crafting Table")

    /**
     * Size of the inventory
     */
    override val size: Int
        get() = 54

    /**
     * Holder of the inventory
     */
    override fun getOwner(opener: Player): InventoryHolder {
        return opener
    }

    /**
     * Generate the contains of inventory
     */
    override fun generateContains(base: Inventory): Array<ItemStack> {
        val gls = GuiHelper.menuGlass()

        val red = GuiHelper.menuItem(" ", listOf(), Material.RED_STAINED_GLASS_PANE)
        val cls = GuiHelper.menuItem("&cClose", listOf(), Material.ARROW)
        val air = ItemStack(Material.AIR)

        return arrayOf(
            gls, gls, gls, gls, gls, gls, gls, gls, gls,
            gls, air, air, air, gls, gls, gls, gls, gls,
            gls, air, air, air, gls, air, gls, gls, gls,
            gls, air, air, air, gls, gls, gls, gls, gls,
            gls, gls, gls, gls, gls, gls, gls, gls, gls,
            red, red, red, red, cls, red, red, red, red
        )
    }

}