package space.maxus.refsb.api.items

import de.tr7zw.changeme.nbtapi.NBTItem
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import org.apache.commons.lang.StringUtils.capitalize
import org.bukkit.ChatColor
import org.bukkit.inventory.ItemStack
import org.reflections.ReflectionUtils
import space.maxus.refsb.RefinedAPI
import space.maxus.refsb.api.SkyblockFeature
import space.maxus.refsb.util.ItemHelper
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.lang.reflect.Modifier
import java.util.*
import java.util.function.Consumer

/**
 * This class should be inherited to create custom items
 */
abstract class SkyblockItem : SkyblockFeature {
    /**
     * Gets the configuration for item
     */
    abstract fun getConfig() : ItemConfiguration

    /**
     * Gets ID of item in format 'EXAMPLE_ITEM'
     */
    abstract override fun getId() : String

    /**
     * Called after initializing base of item
     */
    open fun postInit(base: ItemStack) { }

    private lateinit var item : ItemStack

    /**
     * Compiles the item into ItemStack
     */
    fun generate(): ItemStack {
        item = ItemStack(getConfig().origin)
        return inheritanceGeneration(item)
    }

    protected open fun inheritanceGeneration(item: ItemStack): ItemStack {
        val cfg: ItemConfiguration = getConfig()
        val abils: MutableList<ItemAbility> = cfg.abilities
        val name: Component = cfg.name
        val rar: SkyblockRarity = cfg.rarity
        val t: ItemType = cfg.type
        val m = item.itemMeta!!
        m.displayName(name)
        //#region lore
        val lore: MutableList<String> = ArrayList()
        if (t.isConsumable) {
            lore.add(ChatColor.DARK_GRAY.toString() + "Consumed on use")
        }
        val stats: ItemStats = cfg.stats
        val getters: Set<Method> = ReflectionUtils.getAllMethods(
            stats.javaClass,
            ReflectionUtils.withModifier(Modifier.PUBLIC), ReflectionUtils.withPrefix("get")
        )
        val d: HashMap<Int, Method> = HashMap()
        getters.forEach(Consumer {
                get: Method ->
                    val a: StatPosition = get.getAnnotation(StatPosition::class.java)
                    val v: Int = a.pos
                    d[v] = get
        })
        val nd: TreeMap<Int, Method> = TreeMap<Int, Method>(d)
        for (i in getters.indices) {
            val g: Method? = nd[i]
            try {
                if (i == 7) {
                    if (stats.hasRedStats() && stats.hasGreenStats()) {
                        lore.add(" ")
                    }
                } else {
                    val stat = g?.invoke(stats) ?: ""
                    if (stat != "") {
                        lore.add(stat as String)
                    }
                }
            } catch (e: IllegalAccessException) {
                RefinedAPI.getInstance().logger.severe("Could not set stats to object! ${e.stackTrace}")
            } catch (e: InvocationTargetException) {
                RefinedAPI.getInstance().logger.severe("Could not set stats to object! ${e.stackTrace}")
            }
        }
        if (abils.isNotEmpty()) {
            lore.add(" ")
            for (a in abils) {
                lore.addAll(a.generate().map { str -> str ?: "" })
                if (abils.indexOf(a) != abils.size - 1) {
                    lore.add(" ")
                }
            }
        }
        if (t.isReforgeable) {
            lore.add(" ")
            lore.add(ChatColor.DARK_GRAY.toString() + "This item can be reforged!")
        }
        lore.add(rar.displayColor.toString() + "" + ChatColor.BOLD + rar.unformattedName + " " + t.display)
        //#endregion lore
        m.lore(lore.map {
            s: String -> LegacyComponentSerializer.legacySection().deserialize(s)
        })
        ItemHelper.hideAllFlags(m)
        if (getConfig().glint) ItemHelper.applyGlint(m)

        m.isUnbreakable = true
        item.itemMeta = m

        this.item = addRarity(addSkyblockTag(item), rar.index)
        // finish the initialization by calling postInit
        postInit(item)

        return item
    }

    /**
     * Static data for the item
     */
    companion object Static {
        /**
         * Recombobulates item, upgrading it's rarity by 1
         */
        fun recombobulate(item: ItemStack): ItemStack {
            val m = item.itemMeta!!
            var it = NBTItem(item)
            if(!it.hasKey("skyblockData"))
                it = NBTItem(convertToSkyblock(item))

            var comp = it.getOrCreateCompound("skyblockData")
            if (comp.hasKey("recombobulated")) return item
            if (comp.hasKey("rarity")) {
                val rarity = comp.getInteger("rarity")!!
                val r: SkyblockRarity = SkyblockRarity.byIndex(rarity + 1)
                val sname: String = ChatColor.stripColor(m.displayName()
                    ?.let { it1 -> LegacyComponentSerializer.legacySection().serialize(it1) }) ?: "null"
                val name: String = ChatColor.RESET.toString() + "" + r.displayColor + sname
                val d = mutableListOf(ChatColor.stripColor(LegacyComponentSerializer.legacySection().serialize(m.lore()!![m.lore()!!.size]))?.split("\\s"))
                d.removeAt(0)
                val type: String = d.joinToString(separator = " ")
                val rdisp: String =
                    r.displayColor.toString() + "" + ChatColor.BOLD + "" + ChatColor.MAGIC + "a " + ChatColor.RESET + r.displayName + " " + type + ChatColor.BOLD + "" + ChatColor.MAGIC + " a"
                val lore = m.lore()
                lore?.set(lore.size - 1, LegacyComponentSerializer.legacySection().deserialize(rdisp))
                m.lore(lore)
                m.displayName(LegacyComponentSerializer.legacySection().deserialize(name))
                item.itemMeta = m

                val i = NBTItem(item)
                comp = i.getCompound("skyblockData")
                comp.setInteger("rarity", rarity+1)
                comp.setBoolean("recombobulated", true)
                return i.item
            }
            return item
        }

        /**
         * Converts vanilla native item into skyblock native item
         */
        fun convertToSkyblock(i: ItemStack): ItemStack {
            ItemHelper.getExtraStats(i)
            val m = i.itemMeta!!
            ItemHelper.hideAllFlags(m)
            val r: SkyblockRarity = ItemHelper.getRarity(i.type)
            val l: MutableList<Component>? = if (m.hasLore()) {
                m.lore()
            } else ArrayList()
            assert(l != null)
            l!!.add(LegacyComponentSerializer.legacySection().deserialize(r.displayName + " " + ItemHelper.getType(i.type).display))
            m.lore(l)
            val tn = i.type.toString().replace("_", " ").lowercase()
            val name = r.displayColor.toString() + if (m.hasDisplayName()) LegacyComponentSerializer.legacySection().serialize(m.displayName() ?: Component.text("null")) else capitalize(tn)
            m.displayName(LegacyComponentSerializer.legacySection().deserialize(name))
            i.itemMeta = m
            val nbt = NBTItem(i)
            val comp = nbt.getOrCreateCompound("skyblockData")
            comp.setInteger("rarity", r.index)
            return nbt.item
        }
    }
}