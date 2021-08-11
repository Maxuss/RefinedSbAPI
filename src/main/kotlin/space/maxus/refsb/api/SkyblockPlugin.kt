package space.maxus.refsb.api

import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import space.maxus.refsb.RefinedAPI
import space.maxus.refsb.api.commands.ChatCommand
import space.maxus.refsb.api.commands.CommandRegisterer
import space.maxus.refsb.api.craft.RefinedRecipeManager
import space.maxus.refsb.api.entities.SkyblockEntity
import java.util.logging.Logger

/**
 * Main class used for inheritance with skyblock related plugins
 */
abstract class SkyblockPlugin : JavaPlugin() {
    /**
     * All commands that will be registered inside your plugin
     */
    var commands : MutableList<ChatCommand> = mutableListOf()

    /**
     * All items that should be registered inside your plugin
     */
    var items : ObjectMap<ItemStack> = SkyblockItemMap()

    /**
     * All entities that should be registered inside your plugin
     */
    var entities : ObjectMap<SkyblockEntity> = SkyblockEntityMap()

    /**
     * All inventories that should be registered inside your plugin
     */
    var inventories : ObjectMap<Inventory> = SkyblockInventoryMap()

    /**
     * A recipe manager for work with recipes
     */
    val recipeManager: RefinedRecipeManager = RefinedRecipeManager(this, RefinedAPI.getInstance())

    private val rsbLogger: Logger = Logger.getLogger("RSB API")

    final override fun onEnable() {
        enable()
        CommandRegisterer(this)
        rsbLogger.info("Enabled '$name' Skyblock Plugin!")
    }

    final override fun onDisable() {
        disable()
        rsbLogger.info("Disabled '$name' Skyblock Plugin!")
    }

    /**
     * This function is called upon enabling the plugin
     */
    open fun enable() {
        // Should be used by plugins on enable
    }

    /**
     * This function is called upon disabling the plugin
     */
    open fun disable() {
        // Should be used by plugins on enable
    }
}