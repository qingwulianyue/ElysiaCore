package com.elysia.elysiacore.hook;

import com.elysia.elysiacore.ElysiaCore;
import ink.ptms.adyeshach.core.Adyeshach;
import ink.ptms.adyeshach.core.entity.EntityInstance;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class PlaceholderAPIHook extends PlaceholderExpansion {
    /**
     * %ElysiaCore_name_uuid%:指定uuid生物的名称
     **/
    @Override
    public @org.jetbrains.annotations.NotNull String getIdentifier() {
        return "ElysiaCore";
    }

    @Override
    public @org.jetbrains.annotations.NotNull String getAuthor() {
        return "Elysia";
    }

    @Override
    public @org.jetbrains.annotations.NotNull String getVersion() {
        return ElysiaCore.getInstance().getDescription().getVersion();
    }
    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {
        if (player == null || params.isEmpty()) return null;
        String[] param = params.split("_");
        if (param[0].equals("name")) return getUUIDName(param[1], player);
        return null;
    }
    private String getUUIDName(String param, Player player){
        UUID uuid = UUID.fromString(param);
        Entity entity = Bukkit.getEntity(uuid);
        if (entity == null){
            EntityInstance entityInstance = Adyeshach.INSTANCE.api().getEntityFinder().getEntityFromUniqueId(param, player);
            if (entityInstance == null) return null;
            return entityInstance.getDisplayName();
        }
        return entity.getName();
    }
}
