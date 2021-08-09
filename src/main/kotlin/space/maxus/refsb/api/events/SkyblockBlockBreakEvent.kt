package space.maxus.refsb.api.events

import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.inventory.ItemStack

/**
 * This event is called when player breaks block with skyblock item in main hand
 */
class SkyblockBlockBreakEvent(
    val player: Player,
    val item: ItemStack,
    var itemId: String,
    var base: BlockBreakEvent
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