package space.maxus.refsb.api.entities

import de.tr7zw.changeme.nbtapi.NBTEntity
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.inventory.EntityEquipment
import org.bukkit.persistence.PersistentDataType
import space.maxus.refsb.RefinedAPI
import space.maxus.refsb.api.Key
import space.maxus.refsb.api.SkyblockConstants
import space.maxus.refsb.util.TextUtils

/**
 * This class is used for creating skyblock entities
 */
abstract class SkyblockEntity {
    abstract fun getLocation(e: Entity?): Location
    abstract fun getEquipment(base: EntityEquipment?): EntityEquipment?
    abstract val type: EntityType
    abstract val name: String
    abstract val health: Double
    abstract val damage: Double
    abstract val defense: Double
    abstract val level: Int
    abstract val id: String

    /**
     * This function is called after initializng base entity
     */
    open fun postInit(entity: LivingEntity?, base: Entity?) {
        // should be used by entities inheriting this
    }

    /**
     * Whether the entity will drop loot
     */
    protected open fun dropsOwnLoot(): Boolean {
        return false
    }

    /**
     * Generates the entity into Bukkit's entity
     */
    fun generate(en: Entity): Entity {
        val e = en.world.spawnEntity(getLocation(en), type) as LivingEntity

        // set equipment
        getEquipment(e.equipment)

        // set attributes
        val cont = e.persistentDataContainer
        cont.set(Key.namespaced(RefinedAPI.getInstance(), "hp"), PersistentDataType.DOUBLE, health)
        cont.set(Key.namespaced(RefinedAPI.getInstance(), "damage"), PersistentDataType.DOUBLE, damage)
        cont.set(Key.namespaced(RefinedAPI.getInstance(), "defense"), PersistentDataType.DOUBLE, defense)
        cont.set(Key.namespaced(RefinedAPI.getInstance(), "level"), PersistentDataType.INTEGER, level)
        if(dropsOwnLoot())
            cont.set(Key.namespaced(RefinedAPI.getInstance(), "dropLoot"), PersistentDataType.BYTE, 0.toByte())
        cont.set(Key.namespaced(RefinedAPI.getInstance(), "id"), PersistentDataType.STRING, id)

        // set display name
        e.isCustomNameVisible = true
        e.customName(
            TextUtils.legacySection(
                ChatColor.DARK_GRAY.toString() + "[" + ChatColor.GRAY + "Lv " + level + ChatColor.DARK_GRAY + "]" + " "
                    + name + ChatColor.RESET + " " + ChatColor.GREEN + health * 5 + ChatColor.WHITE
                    + "/" + ChatColor.GREEN + health.toInt() * 5 + ChatColor.RED + " " + SkyblockConstants.HEALTH))

        // finish initialization by calling postInit
        postInit(e, en)
        return e
    }


    /**
     * Contains static methods for entities
     */
    companion object Static {
        /**
         * Converts vanilla-native entity to skyblock-native entity
         */
        fun toSkyblockEntity(e: LivingEntity) {
            val name: String? = if (e.customName == null) {
                ChatColor.RED.toString() + capitalize(e.type.toString().lowercase().replace("_", " "))
            } else e.customName
            val maxHp = e.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.baseValue ?: 20

            val lvl = (maxHp.toDouble() / 20).toInt()
            e.isCustomNameVisible = true
            e.customName(
                TextUtils.legacySection(ChatColor.DARK_GRAY.toString() + "[" + ChatColor.GRAY + "Lv " + lvl + ChatColor.DARK_GRAY + "]" + " "
                        + name + ChatColor.RESET + " " + ChatColor.GREEN + e.health.toInt() * 5 + ChatColor.WHITE
                        + "/" + ChatColor.GREEN + (maxHp.toDouble() * 5).toInt() + ChatColor.RED + "" + SkyblockConstants.HEALTH))

            val nbt = NBTEntity(e)
            val comp = nbt.getOrCreateCompound("skyblockData")
            comp.setString("name", name)
            comp.setInteger("level", lvl)
        }

        private fun capitalize(str: String): String {
            val words = str.split("\\s").toTypedArray()
            val cap = StringBuilder()
            for (w in words) {
                val first = w.substring(0, 1)
                val second = w.substring(1)
                cap.append(first.uppercase()).append(second).append(" ")
            }
            return cap.toString().trim { it <= ' ' }
        }
    }
}