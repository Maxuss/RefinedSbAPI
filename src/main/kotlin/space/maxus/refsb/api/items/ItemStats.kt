package space.maxus.refsb.api.items

import de.tr7zw.changeme.nbtapi.NBTCompound
import de.tr7zw.changeme.nbtapi.NBTItem
import net.minecraft.nbt.NBTTagCompound
import org.bukkit.ChatColor
import org.bukkit.inventory.ItemStack


/**
 * Class used for storing stats of item
 */
@Suppress("UNUSED")
class ItemStats {
    private var damage = 0
    private var strength = 0
    private var defense = 0
    private var health = 0
    private var abilityDamage = 0
    private var critChance = 0
    private var critDamage = 0
    private var attackSpeed = 0
    private var speed = 0
    private var trueDefense = 0
    private var seaCreatureChance = 0
    private var magicFind = 0
    private var petLuck = 0
    private var ferocity = 0
    private var soulflow = 0
    private var miningFortune = 0
    private var farmingFortune = 0
    private var foragingFortune = 0
    private var miningSpeed = 0
    private var intelligence = 0
    
    //#region setters
    fun setDamage(damage: Int): ItemStats {
        this.damage = damage
        return this
    }

    fun setDefense(defense: Int): ItemStats {
        this.defense = defense
        return this
    }

    fun setStrength(strength: Int): ItemStats {
        this.strength = strength
        return this
    }

    fun setHealth(health: Int): ItemStats {
        this.health = health
        return this
    }

    fun setAttackSpeed(attackSpeed: Int): ItemStats {
        this.attackSpeed = attackSpeed
        return this
    }

    fun setCritChance(critChance: Int): ItemStats {
        this.critChance = critChance
        return this
    }

    fun setAbilityDamage(abilityDamage: Int): ItemStats {
        this.abilityDamage = abilityDamage
        return this
    }

    fun setCritDamage(critDamage: Int): ItemStats {
        this.critDamage = critDamage
        return this
    }

    fun setFerocity(ferocity: Int): ItemStats {
        this.ferocity = ferocity
        return this
    }

    fun setMagicFind(magicFind: Int): ItemStats {
        this.magicFind = magicFind
        return this
    }

    fun setPetLuck(petLuck: Int): ItemStats {
        this.petLuck = petLuck
        return this
    }

    fun setSeaCreatureChance(seaCreatureChance: Int): ItemStats {
        this.seaCreatureChance = seaCreatureChance
        return this
    }

    fun setSpeed(speed: Int): ItemStats {
        this.speed = speed
        return this
    }

    fun setTrueDefense(trueDefense: Int): ItemStats {
        this.trueDefense = trueDefense
        return this
    }

    fun setSoulflow(soulflow: Int): ItemStats {
        this.soulflow = soulflow
        return this
    }

    fun setIntelligence(intelligence: Int): ItemStats {
        this.intelligence = intelligence
        return this
    }

    fun setMiningFortune(miningFortune: Int): ItemStats {
        this.miningFortune = miningFortune
        return this
    }

    fun setForagingFortune(foragingFortune: Int): ItemStats {
        this.foragingFortune = foragingFortune
        return this
    }

    fun setFarmingFortune(farmingFortune: Int): ItemStats {
        this.farmingFortune = farmingFortune
        return this
    }

    fun setMiningSpeed(miningSpeed: Int): ItemStats {
        this.miningSpeed = miningSpeed
        return this
    }

    //#endregion setters
    ///
    fun hasRedStats(): Boolean {
        val stats: List<Int> = mutableListOf(
            damage, strength, attackSpeed, critChance,
            critDamage, seaCreatureChance, abilityDamage
        )
        return stats.stream().anyMatch { i: Int -> i != 0 }
    }

    fun hasGreenStats(): Boolean {
        val stats: List<Int> = mutableListOf(
            health, defense, speed,
            magicFind, petLuck, trueDefense, ferocity, soulflow,
            miningFortune, miningSpeed, farmingFortune, 
            foragingFortune, intelligence
        )
        return stats.stream().anyMatch { i: Int -> i != 0 }
    }

    fun applyStats(item: ItemStack) : ItemStack {
        if(hasGreenStats() || hasRedStats()) {
            val nbti = NBTItem(item)
            val sb: NBTCompound = nbti.getOrCreateCompound("skyblockData")
            val stats = sb.getOrCreateCompound("stats")
            for(f in this.javaClass.declaredFields) {
                val amount = f.get(this)
                if(amount is Int && amount != 0)
                    stats.setInteger(f.name, amount)
            }
            return nbti.item
        }
        return item
    }

    //#region red
    @StatPosition(0)
    fun getDamage(): String {
        return proccessStat("Damage", damage * 5, r, false)
    }

    @StatPosition(1)
    fun getStrength(): String {
        return proccessStat("Strength", strength, r, false)
    }

    @StatPosition(2)
    fun getAttackSpeed(): String {
        return proccessStat("Bonus Attack Speed", attackSpeed, r, false)
    }

    @StatPosition(3)
    fun getCritChance(): String {
        return proccessStat("Crit Chance", critChance, r, true)
    }

    @StatPosition(4)
    fun getCritDamage(): String {
        return proccessStat("Crit Damage", critDamage, r, true)
    }

    @StatPosition(5)
    fun getSeaCreatureChance(): String {
        return proccessStat("Sea Creature Chance", seaCreatureChance, r, true)
    }

    @StatPosition(6)
    fun getAbilityDamage(): String {
        return proccessStat("Ability Damage", abilityDamage, r, true)
    }

    //#endregion red
    @StatPosition(7)
    fun reflectEmpty() : String {
        return " "
    }
    @StatPosition(8)
    fun getHealth(): String {
        return proccessStat("Health", health, g, false)
    }

    @StatPosition(9)
    fun getDefense(): String {
        return proccessStat("Defense", defense, g, false)
    }

    @StatPosition(10)
    fun getSpeed(): String {
        return proccessStat("Speed", speed, g, false)
    }
    
    @StatPosition(11)
    fun getMagicFind(): String {
        return proccessStat("Magic Find", magicFind, g, false)
    }

    @StatPosition(12)
    fun getPetLuck(): String {
        return proccessStat("Pet Luck", petLuck, g, false)
    }

    @StatPosition(13)
    fun getTrueDefense(): String {
        return proccessStat("True Defense", trueDefense, g, false)
    }

    @StatPosition(14)
    fun getFerocity(): String {
        return proccessStat("Ferocity", ferocity, g, false)
    }

    @StatPosition(15)
    fun getSoulflow(): String {
        return proccessStat("Soulflow", soulflow, b, false)
    }

    @StatPosition(16)
    fun getMiningFortune(): String {
        return proccessStat("Mining Fortune", miningFortune, g, false)
    }

    @StatPosition(17)
    fun getMiningSpeed(): String {
        return proccessStat("Mining Speed", miningSpeed, g, false)
    }

    @StatPosition(18)
    fun getForagingFortune(): String {
        return proccessStat("Foraging Fortune", foragingFortune, g, false)
    }

    @StatPosition(19)
    fun getFarmingFortune(): String {
        return proccessStat("Farming Fortune", farmingFortune, g, false)
    }

    @StatPosition(20)
    fun getIntelligence(): String {
        return proccessStat("Intelligence", intelligence, g, false)
    }

    private fun proccessStat(name: String, amount: Int, color: ChatColor, percented: Boolean): String {
        return if (amount != 0) ChatColor.GRAY.toString() + name + ": " + color + (if (amount < 0) amount else "+$amount") + (if (percented) "%" else "") else ""
    }

    companion object {
        private val r: ChatColor = ChatColor.RED
        private val g: ChatColor = ChatColor.GREEN
        private val b: ChatColor = ChatColor.DARK_AQUA
    }
}