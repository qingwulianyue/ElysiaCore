package com.elysia.elysiacore;

import com.elysia.elysiacore.hook.PlaceholderAPIHook;
import com.elysia.elysiacore.listener.PlayerFoodListener;
import com.elysia.elysiacore.listener.PlayerJoinAndQuitListener;
import com.elysia.elysiacore.listener.ServerMotdListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ElysiaCore extends JavaPlugin {
    private static ElysiaCore instance;
    public static ElysiaCore getInstance() {
        return instance;
    }
    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new PlayerJoinAndQuitListener(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerFoodListener(),this);
        Bukkit.getPluginManager().registerEvents(new ServerMotdListener(),this);
        checkDepend();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    private void checkDepend(){
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
            getLogger().info("§a检测到 PlaceholderAPI，正在注册变量");
            new PlaceholderAPIHook().register();
        } else {
            getLogger().warning("§c未检测到 PlaceholderAPI，变量将无法使用");
        }
    }
}
