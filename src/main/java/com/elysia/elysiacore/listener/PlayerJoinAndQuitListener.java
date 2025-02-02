package com.elysia.elysiacore.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinAndQuitListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        event.setJoinMessage("§a[登录系统] 玩家 " + event.getPlayer().getName() + " 进入了沧海彼岸...");
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        event.setQuitMessage("§c[登录系统] 玩家 " + event.getPlayer().getName() + " 离开了沧海彼岸...");
    }
}
