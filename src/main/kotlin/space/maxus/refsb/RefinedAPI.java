package space.maxus.refsb;

import org.bukkit.plugin.java.JavaPlugin;
import space.maxus.refsb.api.commands.ExampleCommand;
import space.maxus.refsb.api.commands.SecondCommand;
import space.maxus.refsb.api.commands.ThirdCommand;
import space.maxus.refsb.api.listeners.BreakListener;
import space.maxus.refsb.api.listeners.DamageListener;
import space.maxus.refsb.api.listeners.InteractListener;
import space.maxus.refsb.api.listeners.InventoryListener;

import java.util.Objects;

public class RefinedAPI extends JavaPlugin {

    private static RefinedAPI instance;

    public static RefinedAPI getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new InteractListener(), this);
        getServer().getPluginManager().registerEvents(new DamageListener(), this);
        getServer().getPluginManager().registerEvents(new BreakListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
        instance = this;
        if(getConfig().getBoolean("show-examples")) {
            Objects.requireNonNull(getCommand("example")).setExecutor(new ExampleCommand());
            Objects.requireNonNull(getCommand("second")).setExecutor(new SecondCommand());
            Objects.requireNonNull(getCommand("third")).setExecutor(new ThirdCommand());
        }
        getLogger().info("Refined Skyblock API enabled!");
    }

    @Override
    public void onDisable() {
        instance = null;
        getLogger().info("Refined Skyblock API disabled!");
    }
}
