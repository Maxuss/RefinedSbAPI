package space.maxus.refsb.api

import de.tr7zw.changeme.nbtapi.NBTItem
import org.bukkit.inventory.ItemStack

interface SkyblockFeature {
    fun getId() : String

    fun addSkyblockTag(item : ItemStack) : ItemStack {
        val nbt = NBTItem(item)
        val comp = nbt.getOrCreateCompound("skyblockData")
        comp.setString("id", getId())
        return nbt.item
    }

    fun addRarity(item: ItemStack, rarity: Int) : ItemStack {
        val nbt = NBTItem(item)
        val comp = nbt.getOrCreateCompound("skyblockData")
        comp.setInteger("rarity", rarity)
        return nbt.item
    }
}