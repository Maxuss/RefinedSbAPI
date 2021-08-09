package space.maxus.refsb.util;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import space.maxus.refsb.api.items.ItemType;
import space.maxus.refsb.api.items.SkyblockRarity;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This is an utility class for item related manipulations
 */
public class ItemHelper {
    /**
     * Hides all flags in item meta
     * @param m item meta to be affected
     */
    public static void hideAllFlags(ItemMeta m) {
        m.addItemFlags(
                ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_DYE,
                ItemFlag.HIDE_ENCHANTS,
                ItemFlag.HIDE_PLACED_ON,
                ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_POTION_EFFECTS,
                ItemFlag.HIDE_DESTROYS
        );
    }

    /**
     * Hides all flags for item
     * @param in item to be affected
     */
    public static void hideAllFlags(ItemStack in) {
        ItemMeta m = in.getItemMeta();
        hideAllFlags(m);
        in.setItemMeta(m);
    }

    /**
     * Gets skyblock stats from vanilla-native item
     * @param base item to be affected
     */
    public static void getExtraStats(@NotNull ItemStack base) {
        if(!base.hasItemMeta()) return;
        assert base.getItemMeta() != null;

        if(base.getType().isBlock()) return;
        ItemMeta m = base.getItemMeta();
        NBTItem nbt = new NBTItem(base);
        NBTCompound comp = nbt.getOrCreateCompound("skyblockData");
        HashMap<String, Integer> statValues = new HashMap<>();
        if(m.getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE) != null) {
            int amount = (int) Math.round(new ArrayList<>(Objects.requireNonNull(m.getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE))).get(0).getAmount());
            statValues.put(ChatColor.GRAY+"Damage: "+ ChatColor.RED, amount);
            m.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
            comp.setInteger("damage", amount);
        }
        if(m.getAttributeModifiers(Attribute.GENERIC_ATTACK_SPEED) != null) {
            int amount = (int) Math.round(new ArrayList<>(Objects.requireNonNull(m.getAttributeModifiers(Attribute.GENERIC_ATTACK_SPEED))).get(0).getAmount());
            statValues.put(ChatColor.GRAY+"Bonus Attack Speed: "+ChatColor.RED, amount);
            m.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
            comp.setInteger("attackSpeed", amount);
        }
        statValues.put(" ",0);
        if(m.getAttributeModifiers(Attribute.GENERIC_ARMOR) != null) {
            int amount = (int) Math.round(new ArrayList<>(Objects.requireNonNull(m.getAttributeModifiers(Attribute.GENERIC_ARMOR))).get(0).getAmount());
            statValues.put(ChatColor.GRAY+"Defence: "+ChatColor.GREEN, amount);
            m.removeAttributeModifier(Attribute.GENERIC_ARMOR);
            comp.setInteger("defence", amount);
        }
        if(m.getAttributeModifiers(Attribute.GENERIC_MOVEMENT_SPEED) != null) {
            int amount = (int) Math.round(new ArrayList<>(Objects.requireNonNull(m.getAttributeModifiers(Attribute.GENERIC_MOVEMENT_SPEED))).get(0).getAmount());
            statValues.put(ChatColor.GRAY+"Speed: "+ChatColor.GREEN, amount);
            m.removeAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED);
            comp.setInteger("speed", amount);
        }
        List<String> lore = new ArrayList<>();
        for (Map.Entry<String, Integer> entry: statValues.entrySet()) {
            String val = entry.getValue() <= 0 ? "" : entry.getValue().toString();
            lore.add(entry.getKey()+val);
        }
        m.lore(lore.stream().map(it -> LegacyComponentSerializer.legacySection().deserialize(it)).collect(Collectors.toList()));
        base.setItemMeta(m);
    }

    public static void applyGlint(ItemMeta m) {
        m.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
    }

    public static @NotNull ItemType getType(@NotNull Material mat) {
        String name = mat.name();
        if(name.endsWith("_SWORD")) return ItemType.SWORD;
        else if(name.endsWith("BOW")) return ItemType.BOW;
        else if(name.endsWith("CHESTPLATE")) return ItemType.CHESTPLATE;
        else if(name.endsWith("LEGGINGS")) return ItemType.LEGGINGS;
        else if(name.endsWith("BOOTS")) return ItemType.BOOTS;
        else if(name.endsWith("HELMET")) return ItemType.HELMET;
        else if(name.endsWith("_HOE")) return ItemType.HOE;
        else if(name.endsWith("FISHING_ROD")) return ItemType.FISHING_ROD;
        else return ItemType.OTHER_NONCONSUMABLE;
    }

    // migrated from SkyblockD
    // https://github.com/Maxuss/SkyblockD/blob/08e5fc464efcd11b19862ddc4747fab9ea78293c/src/main/java/space/maxus/skyblockd/helpers/ItemHelper.java#L115
    public static @NotNull SkyblockRarity getRarity(@NotNull Material m){
        if(m.name().startsWith("WARPED_") || m.name().startsWith("CRIMSON_")) return SkyblockRarity.UNCOMMON;
        else if(m.name().startsWith("DIAMOND") || m.name().startsWith("EMERALD")) return SkyblockRarity.UNCOMMON;
        return switch (m) {
            // uncommon
            case TROPICAL_FISH_BUCKET, TROPICAL_FISH, BLAZE_POWDER, BLAZE_ROD, MAGMA_CREAM, GHAST_TEAR, SOUL_TORCH, SOUL_CAMPFIRE, SOUL_LANTERN, SOUL_SOIL, SOUL_SAND, BONE_BLOCK, SHULKER_SHELL, TUBE_CORAL, HORN_CORAL, FIRE_CORAL, BUBBLE_CORAL, BRAIN_CORAL, FIREWORK_ROCKET, GLOWSTONE_DUST, GLOWSTONE, BAMBOO, NAME_TAG, SPLASH_POTION, POTION, SKELETON_SKULL, ZOMBIE_HEAD, END_STONE, CHIPPED_ANVIL, ANVIL, OBSIDIAN, ENDER_PEARL, IRON_BLOCK, GOLD_BLOCK, EMERALD, EMERALD_ORE, REDSTONE_BLOCK, DIAMOND_SWORD, DIAMOND_SHOVEL, DIAMOND, DIAMOND_AXE, DIAMOND_ORE, DIAMOND_BOOTS, DIAMOND_CHESTPLATE, DIAMOND_HELMET, DIAMOND_HOE, DIAMOND_HORSE_ARMOR, DIAMOND_LEGGINGS, DIAMOND_PICKAXE -> SkyblockRarity.UNCOMMON;

            // rare
            case WET_SPONGE, SPONGE, HEART_OF_THE_SEA, WITHER_ROSE, YELLOW_SHULKER_BOX, WHITE_SHULKER_BOX, RED_SHULKER_BOX, PURPLE_SHULKER_BOX, PINK_SHULKER_BOX, ORANGE_SHULKER_BOX, MAGENTA_SHULKER_BOX, LIME_SHULKER_BOX, LIGHT_GRAY_SHULKER_BOX, LIGHT_BLUE_SHULKER_BOX, GREEN_SHULKER_BOX, GRAY_SHULKER_BOX, CYAN_SHULKER_BOX, BROWN_SHULKER_BOX, BLUE_SHULKER_BOX, BLACK_SHULKER_BOX, SHULKER_BOX, LINGERING_POTION, DRAGON_BREATH, TURTLE_HELMET, CREEPER_HEAD, END_ROD, END_STONE_BRICKS, END_STONE_BRICK_WALL, END_STONE_BRICK_STAIRS, END_STONE_BRICK_SLAB, NETHERITE_SCRAP, ANCIENT_DEBRIS, EMERALD_BLOCK, DIAMOND_BLOCK, ENCHANTING_TABLE, ENCHANTED_BOOK, ENDER_EYE, PLAYER_HEAD, ENDER_CHEST -> SkyblockRarity.RARE;

            // epic
            case CONDUIT, NETHER_STAR, TOTEM_OF_UNDYING, ELYTRA, DRAGON_HEAD, WITHER_SKELETON_SKULL, END_CRYSTAL, NETHERITE_PICKAXE, NETHERITE_LEGGINGS, NETHERITE_SWORD, NETHERITE_SHOVEL, NETHERITE_INGOT, NETHERITE_AXE, NETHERITE_BOOTS, NETHERITE_CHESTPLATE, NETHERITE_HELMET, NETHERITE_HOE -> SkyblockRarity.EPIC;

            // legendary
            case NETHERITE_BLOCK, BEACON, DRAGON_EGG -> SkyblockRarity.LEGENDARY;

            // special
            case SPAWNER, BARRIER -> SkyblockRarity.SPECIAL;
            default -> SkyblockRarity.COMMON;
        };
    }
}
