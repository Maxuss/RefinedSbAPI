package space.maxus.refsb.api.items

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.ChatColor
import org.bukkit.Material
import space.maxus.refsb.api.SComponent
import space.maxus.refsb.util.TextUtils.noItalic

internal class ExampleItem : SkyblockItem() {
    override fun getConfig(): ItemConfiguration {
        return ItemConfiguration(
            Component.text("").color(TextColor.color(225, 66, 31 ))
                .append(noItalic(Component.text("Example Item"))),
            ItemType.SWORD,
            ItemStats().setAbilityDamage(10).setMiningFortune(50)
                .setStrength(100).setSpeed(150).setDamage(25).setFerocity(42),
            Material.DIAMOND_SWORD,
            SkyblockRarity.LEGENDARY,
            lore = mutableListOf(
                SComponent.text("").color(TextColor.color(100, 100, 100)).append(
                    SComponent.text("Very cool example sword!"))
            ),
            abilities = mutableListOf(
                ItemAbility("Example Ability", AbilityType.RIGHT_CLICK,
                listOf(ChatColor.GRAY.toString()+"This is a very cool ability!",ChatColor.GRAY.toString()+"It does something ig"))
            )
        )
    }

    override fun getId(): String { return "EXAMPLE_ITEM" }
}