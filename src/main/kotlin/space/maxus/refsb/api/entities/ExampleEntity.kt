package space.maxus.refsb.api.entities

import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.inventory.EntityEquipment
import space.maxus.refsb.util.LegacyColor

internal class ExampleEntity : SkyblockEntity() {
    override fun getLocation(e: Entity?): Location {
        return e?.location!!
    }

    override fun getEquipment(base: EntityEquipment?): EntityEquipment? {
        return null
    }

    override val type: EntityType
        get() = EntityType.ZOMBIE
    override val name: String
        get() = LegacyColor.RED+"Example name"
    override val health: Double
        get() = 150.toDouble()
    override val damage: Double
        get() = 10.toDouble()
    override val defense: Double
        get() = 0.0
    override val level: Int
        get() = 12
    override val id: String
        get() = "EXAMPLE_ENTITY"

}