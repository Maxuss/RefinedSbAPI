package space.maxus.refsb.api

/**
 * This class provides a simple skeleton for ItemMap and EntityMap
 */
abstract class AbstractObjectMap<V> : ObjectMap<V>, HashMap<Key, V>() {
    override fun clone() : AbstractObjectMap<V> {
        val map = HashMap<Key, V>()
        map.putAll(this)
        return map as AbstractObjectMap<V>
    }

    override fun keyByValue(value: V): Key? {
        val set = entries
        for(entry in set) {
            if(entry.value == value)
                return entry.key
        }
        return null
    }
}