package space.maxus.refsb.api


/**
 * Simple map for storing data with IDs
 */
interface ObjectMap<V> : MutableMap<Key, V>, Cloneable {
    fun keyByValue(value: V) : Key?
}