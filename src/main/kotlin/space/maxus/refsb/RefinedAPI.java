package space.maxus.refsb;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import space.maxus.refsb.api.SkyblockPlugin;
import space.maxus.refsb.api.commands.ExampleCommand;
import space.maxus.refsb.api.commands.FourthCommand;
import space.maxus.refsb.api.commands.SecondCommand;
import space.maxus.refsb.api.commands.ThirdCommand;
import space.maxus.refsb.api.craft.Recipe;
import space.maxus.refsb.api.listeners.BreakListener;
import space.maxus.refsb.api.listeners.DamageListener;
import space.maxus.refsb.api.listeners.InteractListener;
import space.maxus.refsb.api.listeners.InventoryListener;

import java.util.ArrayList;
import java.util.List;

/**
 * The API for Refined SB
 */
public class RefinedAPI extends JavaPlugin {

    public static RefinedAPI INSTANCE;

    public static RefinedAPI getInstance() {
        return INSTANCE;
    }

    public static List<Recipe> recipes = new ArrayList<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new InteractListener(), this);
        getServer().getPluginManager().registerEvents(new DamageListener(), this);
        getServer().getPluginManager().registerEvents(new BreakListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
        INSTANCE = this;
        if(getConfig().getBoolean("show-examples")) {
            new ExampleCommand(this);
            new SecondCommand(this);
            new ThirdCommand(this);
            new FourthCommand(this);
        }

        getLogger().info("Starting to process all recipes in plugins...");
        int amount = 0;
        for(Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
            getLogger().info("Processing plugin "+plugin.getName());
            List<String> depends = plugin.getDescription().getDepend();
            List<String> softDepend = plugin.getDescription().getSoftDepend();
            if(depends.contains("RefinedSkyblockAPI") || softDepend.contains("RefinedSkyblockAPI")) {
                getLogger().info("Found that plugin "+plugin.getName()+" depends on RefSB API! Checking recipes...");
                try {
                    SkyblockPlugin sb = (SkyblockPlugin) plugin;
                    amount += sb.recipeManager.getRegisteredRecipes().values().size();
                    recipes.addAll(sb.recipeManager.getRegisteredRecipes().values());
                    getLogger().info("Added all recipes from plugin "+plugin.getName()+" into recipe registry! Continuing...");
                } catch (ClassCastException e) {
                    getLogger().warning("Plugin's "+plugin.getName()+" main class does not extends SkyblockPlugin! Please contact plugin developers, and tell them to do so!");
                }
            }
        }

        if(amount > 0) getLogger().info("Successfully registered "+amount+" custom recipes!");

        getLogger().info("Refined Skyblock API fully enabled!");
    }

    @Override
    public void onDisable() {
        INSTANCE = null;
        getLogger().info("Refined Skyblock API disabled!");
    }
}
