package space.maxus.refsb.api

import org.apache.commons.lang.Validate
import org.bukkit.NamespacedKey
import org.jetbrains.annotations.NotNull
import java.util.function.Predicate

/**
 * This interface is used for creating keys, used for identification in Skyblock plugins
 */
interface Key : Cloneable, Predicate<Key> {
    /**
     * The namespace part of the key
     */
    val namespace: String

    /**
     * The key part of the key
     */
    val key: String

    /**
     * Full string representation of the key
     */
    val full: String
        get() = "$namespace:$key"

    /**
     * Converts the key to bukkit's NamespacedKey
     */
    fun namespaced() : NamespacedKey? {
        return NamespacedKey.fromString(full)
    }

    operator fun plus(other: String) : String {
        return this.full+other
    }

    override fun test(t: Key): Boolean {
        return t == this || t.full == full || (t.namespace == namespace && t.key == key)
    }

    public override fun clone(): Key {
        return fromString(this.full)
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

        /**
         * Constructrs RefinedSB native key
         */
        fun refined(key: String) : Key {
            return SimpleKey("refined", key)
        }

        /**
         * Converts string representation of key into key instance
         */
        fun fromString(key: String) : Key {
            Validate.isTrue(key.contains(':'), "Invalid string key format!")
            Validate.isTrue(!key.contains(' '), "Key can not contain spaces!")
            val split = key.split(':')
            return SimpleKey(split[0], split[1])
        }

        /**
         * Converts namespaced key into normal key representation
         */
        fun fromNamespaced(@NotNull key: NamespacedKey) : Key {
            Validate.notNull(key, "Key can not be null!")
            return SimpleKey(key.namespace, key.key)
        }
    }
}