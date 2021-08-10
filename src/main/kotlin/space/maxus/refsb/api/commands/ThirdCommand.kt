package space.maxus.refsb.api.commands

import org.bukkit.entity.Player
import space.maxus.refsb.api.entities.ExampleEntity

@CommandInfo("third", playerOnly = true)
class ThirdCommand : ChatCommand() {
    override fun execute(player: Player, args: Array<out String>): Boolean {
        val entity = ExampleEntity()
        entity.generate(player)
        return true
    }
}