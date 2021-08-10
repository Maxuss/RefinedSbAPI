package space.maxus.refsb.api.commands

import de.tr7zw.changeme.nbtapi.NBTItem
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import space.maxus.refsb.api.SkyblockPlugin
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection

@CommandInfo(name = "example", playerOnly = true)
internal class ExampleCommand(instance: JavaPlugin) : ChatCommand(instance) {
    override fun execute(player: Player, args: Array<out String>): Boolean {
        val it = player.inventory.itemInMainHand
        if(it.type == Material.AIR) {
            player.sendMessage(Component.text().color(TextColor.color(218, 50, 14)).append(
                Component.text("You don't hold any items!")))
            return true
        }
        val stringed = NBTItem(it).toString()
        val selection = StringSelection(stringed)
        val clip = Toolkit.getDefaultToolkit().systemClipboard
        clip.setContents(selection, selection)
        val msg = Component.text().color(TextColor.color(232, 146, 22)).append(
            Component.text("Copied to clipboard!"))
        player.sendMessage(msg)
        return true
    }
}