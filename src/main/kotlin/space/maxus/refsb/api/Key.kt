package space.maxus.refsb.api

import org.bukkit.NamespacedKey
import org.jetbrains.annotations.Nullable

/**
 * This interface is used for creating keys, used for identification in Skyblock plugins
 */
interface Key {
    val namespace: String
    val key: String

    val full: String
        get() = "$namespace:$key"

    /**
     * Converts the key to bukkit's NamespacedKey
     */
    fun namespaced() : NamespacedKey? {
        return NamespacedKey.fromString(full)
    }

    companion object {
        /**
         * Constructs full simple key
         */
        @Deprecated("Does not allow converting to Bukkit's NamespacedKey", ReplaceWith("Key.key(SkyblockPlugin, String)", "space.maxus.refsb.api.Key"))
        fun key(namespace: String, key: String) : SimpleKey {
            return SimpleKey(namespace, key)
        }

        /**
         * Constructs full complex key
         */
        fun key(owner: SkyblockPlugin, key: String) : SkyblockKey {
            return SkyblockKey(owner, key)
        }

        /**
         * Constructs minecraft native key
         */
        fun minecraft(key: String) : Key {
            return SimpleKey("minecraft", key)
        }
    }
}