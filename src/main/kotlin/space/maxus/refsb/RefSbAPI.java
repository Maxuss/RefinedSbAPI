package space.maxus.refsb;

import org.bukkit.plugin.java.JavaPlugin;

public class RefSbAPI extends JavaPlugin {

    private static RefSbAPI instance;

    public static RefSbAPI getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("Refined Skyblock API enabled!");
    }

    @Override
    public void onDisable() {
        instance = null;
        getLogger().info("Refined Skyblock API disabled!");
    }
}
