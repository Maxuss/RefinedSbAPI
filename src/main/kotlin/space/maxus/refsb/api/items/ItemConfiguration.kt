package space.maxus.refsb.api.items

import net.kyori.adventure.text.Component
import org.bukkit.Material

/**
 * This class is used for storing item configuration data
 */
data class ItemConfiguration(
    var name: Component,
    var type: ItemType,
    var stats: ItemStats,
    var origin: Material,
    var rarity: SkyblockRarity = SkyblockRarity.COMMON,
    var glint: Boolean = false,
    var abilities: MutableList<ItemAbility> = mutableListOf(),
    var lore: MutableList<Component> = mutableListOf(),
)