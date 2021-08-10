package space.maxus.refsb;

import org.bukkit.plugin.java.JavaPlugin;
import space.maxus.refsb.api.commands.ExampleCommand;
import space.maxus.refsb.api.commands.FourthCommand;
import space.maxus.refsb.api.commands.SecondCommand;
import space.maxus.refsb.api.commands.ThirdCommand;
import space.maxus.refsb.api.listeners.BreakListener;
import space.maxus.refsb.api.listeners.DamageListener;
import space.maxus.refsb.api.listeners.InteractListener;
import space.maxus.refsb.api.listeners.InventoryListener;

import java.util.Objects;

/**
 * The API for Refined SB
 */
public class RefinedAPI extends JavaPlugin {

    public static RefinedAPI INSTANCE;

    public static RefinedAPI getInstance() {
        return INSTANCE;
    }

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
        getLogger().info("Refined Skyblock API enabled!");
    }

    @Override
    public void onDisable() {
        INSTANCE = null;
        getLogger().info("Refined Skyblock API disabled!");
    }
}
