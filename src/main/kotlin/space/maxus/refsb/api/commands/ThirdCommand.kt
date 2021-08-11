package space.maxus.refsb.api.commands

import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import space.maxus.refsb.api.entities.ExampleEntity

@CommandInfo("third", playerOnly = true)
class ThirdCommand(instance: JavaPlugin) : ChatCommand(instance) {
    override fun execute(player: Player, args: Array<out String>): Boolean {
        val entity = ExampleEntity()
        entity.generate(player)
        return true
    }
}