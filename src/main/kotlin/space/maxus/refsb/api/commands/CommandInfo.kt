package space.maxus.refsb.api.commands

annotation class CommandInfo(
    val name: String, val permission: String = "",
    val playerOnly: Boolean, val configRequirement: String = "")
