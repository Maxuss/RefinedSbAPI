package space.maxus.refsb.api.events

import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

/**
 * This event is called when player interacts with skyblock item in main hand
 */
class SkyblockInteractEvent(
        val item: ItemStack,
        val itemId: String,
        val action: Action,
        val base: PlayerInteractEvent): Event(), Cancellable {
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