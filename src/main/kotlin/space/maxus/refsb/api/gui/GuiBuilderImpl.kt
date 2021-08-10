package space.maxus.refsb.api.gui

import net.kyori.adventure.text.Component
import org.apache.commons.lang.Validate
import org.bukkit.Bukkit
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack
import space.maxus.refsb.util.TextUtils

internal class GuiBuilderImpl : GuiBuilder {
    override lateinit var inventory: Inventory
    override var owner: InventoryHolder? = null
    override var name: Component? = null
    override lateinit var contents: Array<ItemStack>
    override var size: Int = -1

    override fun new(size: Int): GuiBuilder {
        Validate.isTrue(size in 1..54, "The inventory size must be more or equal to 1 and less or equal to 54! Found $size instead!")
        this.size = size
        return this
    }

    override fun <T : InventoryHolder> setOwner(owner: T): GuiBuilder {
        this.owner = owner
        return this
    }

    override fun setName(name: Component): GuiBuilder {
        this.name = name
        return this
    }

    override fun setName(name: String): GuiBuilder {
        this.name = TextUtils.legacySection(name)
        return this
    }

    override fun setContents(content: Array<ItemStack>): GuiBuilder {
        this.contents = content
        return this
    }

    override fun build(): Inventory {
        Validate.isTrue(size != -1, "You have not yet initialized the builder!")
        this.inventory = Bukkit.createInventory(owner, size, name ?: TextUtils.legacy("null"))
        this.inventory.contents = contents
        return this.inventory
    }
}