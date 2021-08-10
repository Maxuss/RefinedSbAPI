package space.maxus.refsb.api.gui

import net.kyori.adventure.text.Component
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

/**
 * This interface is used for building bukkit inventories
 */
interface GuiBuilder {

    /**
     * Generated inventory
     */
    var inventory: Inventory

    /**
     * Owner of the inventory
     */
    var owner: InventoryHolder?

    /**
     * Title of the inventory
     */
    var name: Component?

    /**
     * Contents of the inventory
     */
    var contents: Array<ItemStack>

    /**
     * Size of the inventory
     */
    var size: Int

    /**
     * Constructs new inventory with given size
     */
    fun new(size: Int) : GuiBuilder

    /**
     * Sets the owner of the inventory
     */
    fun <T : InventoryHolder> setOwner(owner: T) : GuiBuilder

    /**
     * Sets the name of the inventory
     */
    fun setName(name: Component) : GuiBuilder

    /**
     * Sets the legacy section sign decorated name of inventory
     */
    fun setName(name: String) : GuiBuilder

    /**
     * Sets the contents of the inventory
     */
    fun setContents(content: Array<ItemStack>) : GuiBuilder

    /**
     * Builds the inventory
     */
    fun build() : Inventory

    companion object {
        /**
         * Creates a default builder for use
         */
        fun getDefault() : GuiBuilder {
            return GuiBuilderImpl()
        }
    }
}