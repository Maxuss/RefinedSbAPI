package space.maxus.refsb.api.commands

import space.maxus.refsb.api.SkyblockPlugin

internal class CommandRegisterer(plugin: SkyblockPlugin) {
    init {
        for(cmd in plugin.commands) {
            val n = cmd.commandInfo.name
            plugin.getCommand(n)?.setExecutor(cmd) ?: plugin.logger.severe("Could not register command $n")
            plugin.getCommand(n)?.setTabCompleter(cmd) ?: plugin.logger.severe("Could not register command $n")
        }
    }
}