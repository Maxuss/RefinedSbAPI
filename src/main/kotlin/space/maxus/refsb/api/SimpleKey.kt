package space.maxus.refsb.api

class SimpleKey internal constructor(override val namespace: String, override val key: String) : Key {
    override fun toString(): String {
        return this.full
    }
}