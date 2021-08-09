package space.maxus.refsb.api.listeners

import de.tr7zw.changeme.nbtapi.NBTItem
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import space.maxus.refsb.api.events.SkyblockDamageEvent

class DamageListener : Listener {
    fun onPlayerDamageEntity(e: EntityDamageByEntityEvent) {
        if(e.damager is Player) {
            val item = (e.damager as Player).inventory.itemInMainHand
            val nbt = NBTItem(item)
            if(!nbt.hasKey("skyblockData")) return
            val event = SkyblockDamageEvent(e.entity, item, nbt.getCompound("skyblockData").getString("id"), e)

            Bukkit.getPluginManager().callEvent(event)
        }
    }
}