package space.maxus.refsb.api

import org.bukkit.plugin.java.JavaPlugin
import space.maxus.refsb.api.commands.ChatCommand
import space.maxus.refsb.api.commands.CommandRegisterer

/**
 * Main class used for inheritance with skyblock related plugins
 */
abstract class SkyblockPlugin : JavaPlugin() {
    var commands : MutableList<ChatCommand> = mutableListOf()
    var items : Enum<*>? = null

    final override fun onEnable() {
        enable()
        CommandRegisterer(this)
    }

    final override fun onDisable() {
        disable()
    }

    open fun enable() {

    }

    open fun disable() {

    }
}