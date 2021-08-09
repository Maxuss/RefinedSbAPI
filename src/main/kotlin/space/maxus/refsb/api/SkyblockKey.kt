package space.maxus.refsb.api

import org.bukkit.NamespacedKey

class SkyblockKey internal constructor(val owner: SkyblockPlugin, override val key: String) : Key {
    override val namespace: String
        get() = owner.name.lowercase()

    override fun namespaced() : NamespacedKey {
        return NamespacedKey(owner, key)
    }
}