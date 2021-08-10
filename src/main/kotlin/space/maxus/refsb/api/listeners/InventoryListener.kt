package space.maxus.refsb.api.listeners

import de.tr7zw.changeme.nbtapi.NBTItem
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import space.maxus.refsb.api.Key
import space.maxus.refsb.api.SkyblockPlugin
import space.maxus.refsb.api.craft.*
import space.maxus.refsb.util.TextUtils

class InventoryListener : Listener {
    @EventHandler
    fun onInventoryClick(e: InventoryClickEvent) {
        val inv = e.inventory
        val name = ChatColor.stripColor(TextUtils.toLegacy(e.view.title()))

        if(name?.lowercase().equals("crafting table")) {
            val recipes: MutableList<Recipe> = mutableListOf()

            for(plugin in Bukkit.getPluginManager().plugins) {
                val depends = plugin.description.depend
                if(depends.contains("RefinedSkyblockAPI")) {
                    val sb = plugin as SkyblockPlugin
                    recipes.addAll(sb.recipeManager.registeredRecipes.values)
                }
            }

            val contents : MutableList<MutableList<ItemStack?>> = mutableListOf(
                mutableListOf(inv.getItem(10), inv.getItem(11), inv.getItem(12)),
                mutableListOf(inv.getItem(19), inv.getItem(20), inv.getItem(21)),
                mutableListOf(inv.getItem(28), inv.getItem(29), inv.getItem(30))
            )

            for(recipe in recipes) {
                val matrix = recipe.rows
                val choices = recipe.choiceMap
                val charray : MutableList<MutableList<Char>> = mutableListOf()

                val choiceArray: MutableList<MutableList<RecipeChoice<*>?>?> = arrayOfNulls<MutableList<RecipeChoice<*>?>>(3).toMutableList()
                for(row in matrix) {
                    if(row == null) return
                    val index = matrix.indexOf(row)

                    charray.add(index, row.toCharArray().asList().toMutableList())
                }

                for(char in charray) {
                    val ind = charray.indexOf(char)
                    val tmp = mutableListOf<RecipeChoice<*>?>()
                    for(ch in char) {
                        val x = char.indexOf(ch)

                        tmp[x] = choices[ch]
                    }
                    choiceArray[ind] = tmp
                }

                val matches : MutableList<MutableList<Boolean?>> = mutableListOf(
                    mutableListOf(),
                    mutableListOf(),
                    mutableListOf()
                )

                for(row in contents) {
                    val index = contents.indexOf(row)
                    val choiceRow = choiceArray[index]
                    for(itemc in row) {
                        val i = row.indexOf(itemc)
                        matches[index][i] = false
                        val choice = choiceRow?.get(i) ?: continue
                        if(choice is KeyedChoice) {
                            val key = choice.key
                            val id = key.key.uppercase()
                            val nbt = NBTItem(itemc)
                            if(!nbt.hasKey("skyblockData")) continue
                            val sbid = nbt.getCompound("skyblockData").getString("id")
                            matches[index][i] = sbid.uppercase() == id
                        } else {
                            val comparable = choice.getItem()
                            matches[index][i] = comparable.isSimilar(itemc)
                        }
                    }
                }

                if(matches.all { bool -> bool.all { boole -> boole == true } }) {
                    val result = recipe.result
                    inv.setItem(23, result)
                    return
                }
            }
        }
    }
}

