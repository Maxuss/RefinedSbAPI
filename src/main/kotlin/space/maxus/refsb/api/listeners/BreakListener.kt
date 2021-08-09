package space.maxus.refsb.api.listeners

import de.tr7zw.changeme.nbtapi.NBTItem
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import space.maxus.refsb.api.events.SkyblockBlockBreakEvent

class BreakListener : Listener {
    @EventHandler
    fun onBreak(e: BlockBreakEvent) {
        val p = e.player
        val item = p.inventory.itemInMainHand
        if(item.type.isEmpty) return
        val nbt = NBTItem(item)
        if(nbt.hasKey("skyblockData")) {
            val comp = nbt.getCompound("skyblockData")
            val event = SkyblockBlockBreakEvent(p, item, comp.getString("id"), e)

            Bukkit.getPluginManager().callEvent(event)
        }
    }
}