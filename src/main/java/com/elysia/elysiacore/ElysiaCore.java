package com.elysia.elysiacore;

import com.elysia.elysiacore.listener.PlayerFoodListener;
import com.elysia.elysiacore.listener.PlayerJoinAndQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ElysiaCore extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new PlayerJoinAndQuitListener(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerFoodListener(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
