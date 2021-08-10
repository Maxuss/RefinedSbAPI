package space.maxus.refsb.api.commands

import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import space.maxus.refsb.api.SkyblockPlugin
import space.maxus.refsb.api.items.ExampleItem

@CommandInfo("second", playerOnly = true)
internal class SecondCommand(instance: JavaPlugin) : ChatCommand(instance) {
    override fun execute(player: Player, args: Array<out String>): Boolean {
        player.inventory.addItem(ExampleItem().generate())
        return true
    }
}