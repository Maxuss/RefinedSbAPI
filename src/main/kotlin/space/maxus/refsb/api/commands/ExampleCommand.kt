package space.maxus.refsb.api.commands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Material
import org.bukkit.entity.Player
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection

@CommandInfo(name = "example", playerOnly = true)
class ExampleCommand : ChatCommand() {
    override fun execute(player: Player, args: Array<out String>): Boolean {
        val it = player.inventory.itemInMainHand
        if(it.type == Material.AIR) {
            player.sendMessage(Component.text().color(TextColor.color(218, 50, 14)).append(
                Component.text("You don't hold any items!")))
            return true
        }
        val stringed = it.toString()
        val selection = StringSelection(stringed)
        val clip = Toolkit.getDefaultToolkit().systemClipboard
        clip.setContents(selection, selection)
        val msg = Component.text().color(TextColor.color(232, 146, 22)).append(
            Component.text("Copied to clipboard!"))
        player.sendMessage(msg)
        return true
    }
}