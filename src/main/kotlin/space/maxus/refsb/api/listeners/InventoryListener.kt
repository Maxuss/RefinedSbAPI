package space.maxus.refsb.api.listeners

import de.tr7zw.changeme.nbtapi.NBTItem
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import space.maxus.refsb.RefinedAPI
import space.maxus.refsb.api.craft.KeyedChoice
import space.maxus.refsb.api.craft.Recipe
import space.maxus.refsb.api.craft.RecipeChoice
import space.maxus.refsb.util.TextUtils

class InventoryListener : Listener {
    @EventHandler
    fun onInventoryClick(e: InventoryClickEvent) {
        val inv = e.inventory
        val name = ChatColor.stripColor(TextUtils.toLegacy(e.view.title()))

        if(name?.lowercase().equals("crafting table")) {
            val cur = e.currentItem
            val indexx = inv.indexOf(cur)
            if(indexx == 23 && cur != null && !cur.type.isEmpty) {
                val data = operateRecipes(inv)
                if(data == null) { e.whoClicked.closeInventory(InventoryCloseEvent.Reason.CANT_USE); return }
                val itemdata = data.first
                for(i in 10..12) {
                    operateItemRemoval(9, inv, itemdata, i, 0)
                }
                for(i in 19..21) {
                    operateItemRemoval(18, inv, itemdata, i, 1)
                }
                for(i in 28..30) {
                    operateItemRemoval(27, inv, itemdata, i, 2)
                }
            }
            if(cur?.type == Material.GRAY_STAINED_GLASS_PANE
                || cur?.type == Material.ARROW
                || cur?.type == Material.RED_STAINED_GLASS_PANE) e.isCancelled = true
            val hasMeta = cur?.hasItemMeta() == true
            if(hasMeta) {
                val itemname = ChatColor.stripColor(TextUtils.toLegacy(cur?.itemMeta?.displayName()))
                if(itemname?.contains("Close") == true) {
                    e.whoClicked.closeInventory(InventoryCloseEvent.Reason.PLUGIN)
                }
            }
            val pair = operateRecipes(inv)

            if(pair != null) {
                val result = pair.second.result
                inv.setItem(23, result)
                return
            }
        }
    }

    private fun operateItemRemoval(removeAmount: Int, inv: Inventory, itemdata: MutableList<MutableList<ItemStack?>>, position: Int, iteration: Int) {
        val indexed = inv.getItem(position)
        val curi = try {
            itemdata[iteration][position - removeAmount]
        } catch(e: IndexOutOfBoundsException) {
            null
        }
        if(curi == null || indexed == null || indexed.type.isEmpty) { inv.setItem(position, ItemStack(Material.AIR)); return }
        val amountToBeRemoved = curi.amount
        val amount = indexed.amount - amountToBeRemoved
        indexed.amount = if(amount < 0) 0 else amount
        inv.setItem(position, indexed)
    }

    private fun operateRecipes(inv: Inventory) : Pair<MutableList<MutableList<ItemStack?>>, Recipe>? {
        val recipes: MutableList<Recipe> = mutableListOf()

        val logger = RefinedAPI.INSTANCE.logger

        val contents : MutableList<MutableList<ItemStack?>> = mutableListOf(
            mutableListOf(inv.getItem(10), inv.getItem(11), inv.getItem(12)),
            mutableListOf(inv.getItem(19), inv.getItem(20), inv.getItem(21)),
            mutableListOf(inv.getItem(28), inv.getItem(29), inv.getItem(30))
        )

        for(recipe in recipes) {
            val matrix = recipe.rows
            val choices = recipe.choiceMap
            val charray: MutableList<MutableList<Char>> = mutableListOf()

            val choiceArray: MutableList<MutableList<RecipeChoice<*>?>?> =
                arrayOfNulls<MutableList<RecipeChoice<*>?>>(3).toMutableList()
            logger.info(matrix.toString())

            for (row in matrix) {
                if (row == null) continue
                val index = matrix.indexOf(row)
                charray.add(index, row.toCharArray().asList().toMutableList())
            }

            for (char in charray) {
                val ind = charray.indexOf(char)
                val tmp = mutableListOf<RecipeChoice<*>?>()
                for (ch in char) {
                    tmp.add(choices[ch])
                }
                choiceArray[ind] = tmp
            }

            val matches: MutableList<MutableList<Boolean?>> = mutableListOf(
                mutableListOf(),
                mutableListOf(),
                mutableListOf()
            )

            val citems: MutableList<MutableList<ItemStack?>> = mutableListOf(
                mutableListOf(),
                mutableListOf(),
                mutableListOf()
            )

            logger.info("Starting to process contents")

            for (row in contents) {
                val index = contents.indexOf(row)
                val choiceRow = choiceArray[index]
                for (itemc in row) {
                    val i = row.indexOf(itemc)
                    matches[index].add(i, false)
                    citems[index].add(i, null)
                    val choice = choiceRow?.get(i) ?: continue
                    if (choice is KeyedChoice) {
                        val key = choice.key
                        val id = key.key.uppercase()
                        if (itemc == null || itemc.type.isEmpty) continue
                        val nbt = NBTItem(itemc)
                        if (!nbt.hasKey("skyblockData")) continue
                        val sbid = nbt.getCompound("skyblockData").getString("id")
                        matches[index][i] = sbid.uppercase() == id
                    } else {
                        val comparable = choice.getItem().clone()
                        val zero = itemc?.clone() ?: ItemStack(Material.AIR)

                        val rem = zero.amount - comparable.amount

                        zero.amount = 1
                        comparable.amount = 1

                        matches[index][i] = comparable.isSimilar(zero) && rem > 0
                        citems[index][i] = choice.getItem()
                    }
                }
            }

            if(matches.all { list -> list.all { bool -> bool == true }}) {
                return pairOf(citems, recipe)
            }
        }

        return null
    }

    private fun <A, B> pairOf(a:A,b:B) : Pair<A, B> {
        return Pair(a, b)
    }
}

