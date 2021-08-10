package space.maxus.refsb.util;

import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class provides several useful methods for your GUIs
 */
public class GuiHelper {
    /**
     * Gets the menu glass
     */
    public static ItemStack menuGlass() {
        ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta m = glass.getItemMeta();
        ItemHelper.hideAllFlags(m);
        m.displayName(TextUtils.legacy(" "));
        glass.setItemMeta(m);
        NBTItem nbt = new NBTItem(glass);
        nbt.addCompound("skyblockData");
        return nbt.getItem();
    }

    /**
     * Generates a simple menu item
     * @param name display name of item
     * @param lore lore of item
     * @param mat material of item
     * @return generated menu item
     */
    public static ItemStack menuItem(String name, List<String> lore, Material mat) {
        ItemStack n = new ItemStack(mat);
        ItemMeta m = n.getItemMeta();
        ItemHelper.hideAllFlags(m);
        m.displayName(TextUtils.legacy(name));
        m.lore(lore.stream().map(TextUtils::legacy).collect(Collectors.toList()));
        n.setItemMeta(m);
        NBTItem nbt = new NBTItem(n);
        nbt.addCompound("skyblockData");
        return nbt.getItem();
    }
}
