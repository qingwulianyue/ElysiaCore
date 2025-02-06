package com.elysia.elysiacore.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerMotdListener implements Listener {
    @EventHandler
    public void onServerPing(ServerListPingEvent event){
        event.setMotd("§9欢迎来到沧海彼岸 §e服务器官方群:701312028");
    }
}
