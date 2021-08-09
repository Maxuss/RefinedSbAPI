package space.maxus.refsb.api.commands

import org.bukkit.entity.Player
import space.maxus.refsb.api.items.ExampleItem

@CommandInfo("second", playerOnly = true)
class SecondCommand : ChatCommand() {
    override fun execute(player: Player, args: Array<out String>): Boolean {
        player.inventory.addItem(ExampleItem().generate())
        return true
    }
}