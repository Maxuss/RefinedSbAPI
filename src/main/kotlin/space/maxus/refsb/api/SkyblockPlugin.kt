package space.maxus.refsb.api

import org.bukkit.plugin.java.JavaPlugin
import space.maxus.refsb.api.commands.ChatCommand
import space.maxus.refsb.api.commands.CommandRegisterer

/**
 * Main class used for inheritance with skyblock related plugins
 */
abstract class SkyblockPlugin : JavaPlugin() {
    /**
     * All commands that will be registered inside your plugin
     */
    var commands : MutableList<ChatCommand> = mutableListOf()

    /**
     * All items that should be registered inside your plugin. Only used for convenience
     */
    var items : Enum<*>? = null

    /**
     * All entities that should be registered inside your plugin. Only used for convenience
     */
    var entities : Enum<*>? = null

    final override fun onEnable() {
        enable()
        CommandRegisterer(this)
    }

    final override fun onDisable() {
        disable()
    }

    /**
     * This function is called upon enabling the plugin
     */
    open fun enable() {

    }

    /**
     * This function is called upon disabling the plugin
     */
    open fun disable() {

    }
}