package space.maxus.refsb.api

import org.bukkit.plugin.java.JavaPlugin
import space.maxus.refsb.api.commands.ChatCommand
import space.maxus.refsb.api.commands.CommandRegisterer

/**
 * Main class used for importing
 */
abstract class SkyblockPlugin : JavaPlugin() {
    var commands : MutableList<ChatCommand> = mutableListOf()

    final override fun onEnable() {
        CommandRegisterer(this)
        enable()
    }

    final override fun onDisable() {
        disable()
    }

    open fun enable() {

    }

    open fun disable() {

    }
}