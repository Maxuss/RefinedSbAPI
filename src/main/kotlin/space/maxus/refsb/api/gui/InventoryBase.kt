package space.maxus.refsb.api.gui

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

/**
 * This is a base abstract class for more complex inventories
 * @see GuiBuilder
 */
abstract class InventoryBase {
    /**
     * Title of the inventory
     */
    abstract val name: Component

    /**
     * Size of the inventory
     */
    abstract val size: Int

    /**
     * Holder of the inventory
     */
    abstract fun getOwner(opener: Player) : InventoryHolder

    /**
     * Generate the contains of inventory
     */
    abstract fun generateContains(base: Inventory) : Array<ItemStack>

    /**
     * Generates the inventory and compiles it into bukkit's inventory
     */
    fun generate(player: Player) : Inventory {
        val inv = Bukkit.createInventory(getOwner(player), size, name)
        inv.contents = generateContains(inv)

        return inv
    }
}