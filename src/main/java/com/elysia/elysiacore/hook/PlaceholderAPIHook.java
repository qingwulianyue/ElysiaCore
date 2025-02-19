package com.elysia.elysiacore.hook;

import com.elysia.elysiacore.ElysiaCore;
import ink.ptms.adyeshach.core.Adyeshach;
import ink.ptms.adyeshach.core.entity.EntityInstance;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.serverct.ersha.dungeon.DungeonPlus;

import java.util.Objects;
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
        if (params.equals("teamSize")) return getTeamSize(player);
        String[] param = params.split("_");
        if (param[0].equals("name")) return getUUIDName(param[1], player);
        if (param[0].equals("teamHealth")) return getTeamHealth(param[1], player);
        if (param[0].equals("teamMaxHealth")) return getTeamMaxHealth(param[1], player);
        if (param[0].equals("teamName")) return getTeamName(param[1], player);
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
    private String getTeamSize(Player player){
        if (DungeonPlus.INSTANCE.getTeamManager().getTeam(player) == null)
            return "0";
        return String.valueOf(Objects.requireNonNull(DungeonPlus.INSTANCE.getTeamManager().getTeam(player)).getPlayers().size());
    }
    private String getTeamHealth(String param, Player player){
        UUID uuid = player.getUniqueId();
        int tag = Integer.parseInt(param);
        int i = 0;
        UUID result = null;
        for (UUID uid : Objects.requireNonNull(DungeonPlus.INSTANCE.getTeamManager().getTeam(player)).getPlayers()){
            if (uid == uuid) continue;
            if (i == tag){
                result = uid;
                break;
            }
            i++;
        }
        return String.valueOf(Bukkit.getPlayer(result).getHealth());
    }
    private String getTeamMaxHealth(String param, Player player){
        UUID uuid = player.getUniqueId();
        int tag = Integer.parseInt(param);
        int i = 0;
        UUID result = null;
        for (UUID uid : Objects.requireNonNull(DungeonPlus.INSTANCE.getTeamManager().getTeam(player)).getPlayers()){
            if (uid == uuid) continue;
            if (i == tag){
                result = uid;
                break;
            }
            i++;
        }
        return String.valueOf(Bukkit.getPlayer(result).getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
    }
    private String getTeamName(String param, Player player){
        UUID uuid = player.getUniqueId();
        int tag = Integer.parseInt(param);
        int i = 0;
        UUID result = null;
        for (UUID uid : Objects.requireNonNull(DungeonPlus.INSTANCE.getTeamManager().getTeam(player)).getPlayers()){
            if (uid == uuid) continue;
            if (i == tag){
                result = uid;
                break;
            }
            i++;
        }
        return String.valueOf(Bukkit.getPlayer(result).getName());
    }
}
