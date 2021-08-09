package space.maxus.refsb.api.commands

import net.kyori.adventure.text.TextComponent
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import space.maxus.refsb.RefinedAPI
import space.maxus.refsb.util.TextUtils

/**
 * This abstract class is used for creating
 * simple chat commands. All commands should
 * be registered by yourself. All classes extending
 * this class should have CommandInfo annotation!
 * @see CommandInfo
 */
abstract class ChatCommand : CommandExecutor, TabCompleter {
    var commandInfo : CommandInfo =
        this::class.java.getAnnotation(CommandInfo::class.java)

    final override fun onCommand(
        sender: CommandSender,
        command: Command,
        data: String,
        args: Array<out String>): Boolean {
        if(commandInfo.configRequirement.isNotEmpty() &&
            !RefinedAPI
                .getInstance()
                .config.getBoolean(commandInfo.configRequirement)) {
            val comp = TextUtils.legacy("&cThis command is disabled on your server!")
            sender.sendMessage(comp)
            return true
        }

        if(commandInfo.playerOnly && sender !is Player) {
            val comp = TextUtils.legacy("&cThis command is only for players!")
            sender.sendMessage(comp)
            return true
        }

        if(commandInfo.permission.isNotEmpty() &&
                !sender.hasPermission(commandInfo.permission)) {
            val comp = TextUtils.legacy("&cYou don't have sufficient permissions to execute this command!")
            sender.sendMessage(comp)
            return true
        }

        if(commandInfo.playerOnly) return execute(sender as Player, args)
        return execute(sender, args)
    }

    final override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        data: String,
        args: Array<out String>
    ): MutableList<String> {
        return tabComplete(sender, args)
    }

    /**
     * This function is called on execute if in command info
     * specified that the command is for players only
     */
    open fun execute(player: Player, args: Array<out String>) : Boolean {
        return false
    }

    /**
     * This function is called on execute
     */
    open fun execute(sender: CommandSender, args: Array<out String>) : Boolean {
        return false
    }

    /**
     * This function is called on tab completion
     */
    open fun tabComplete(sender: CommandSender, args: Array<out String>) : MutableList<String> {
        return mutableListOf()
    }
}