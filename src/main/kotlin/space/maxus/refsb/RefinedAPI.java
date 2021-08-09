package space.maxus.refsb;

import org.bukkit.plugin.java.JavaPlugin;
import space.maxus.refsb.api.commands.ExampleCommand;
import space.maxus.refsb.api.commands.SecondCommand;

import java.util.Objects;

public class RefinedAPI extends JavaPlugin {

    private static RefinedAPI instance;

    public static RefinedAPI getInstance() {
        return instance;
    }


    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        if(getConfig().getBoolean("show-examples")) {
            Objects.requireNonNull(getCommand("example")).setExecutor(new ExampleCommand());
            Objects.requireNonNull(getCommand("second")).setExecutor(new SecondCommand());
        }
        getLogger().info("Refined Skyblock API enabled!");
    }

    @Override
    public void onDisable() {
        instance = null;
        getLogger().info("Refined Skyblock API disabled!");
    }
}
