package space.maxus.refsb.api.craft

class IdChoice(id: String) : RecipeChoice<String> {
    private val theId: String = id.uppercase()

    override fun test(that: String): Boolean {
        return that.uppercase() == theId
    }

    override fun clone(): RecipeChoice<String> {
        return IdChoice(theId)
    }

    override fun getItemId(): String {
        return theId
    }
}