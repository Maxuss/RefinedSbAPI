package space.maxus.refsb.api.listeners

import de.tr7zw.changeme.nbtapi.NBTItem
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import space.maxus.refsb.api.events.SkyblockInteractEvent

class InteractListener : Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    fun onInteract(e: PlayerInteractEvent) {
        val item = e.item ?: return
        val nbt = NBTItem(item)
        if(nbt.hasKey("skyblockData")) {
            val event = SkyblockInteractEvent(item, nbt.getCompound("skyblockData").getString("id"), e.action, e)

            Bukkit.getServer().pluginManager.callEvent(event)
        }
    }
}