package space.maxus.refsb.api.events

import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.inventory.ItemStack

class SkyblockDamageEvent(
    val entity: Entity,
    val item: ItemStack?,
    val itemId: String,
    val base: EntityDamageByEntityEvent
) : Event(), Cancellable {
    private val handlers = HandlerList()
    private var cancelled = false

    override fun getHandlers(): HandlerList {
        return handlers
    }

    fun getHandlerList(): HandlerList {
        return handlers
    }

    override fun isCancelled(): Boolean {
        return cancelled
    }

    override fun setCancelled(cancel: Boolean) {
        cancelled = cancel
        base.isCancelled = cancel
    }
}