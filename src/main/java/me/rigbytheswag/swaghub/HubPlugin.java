package me.rigbytheswag.swaghub;

import com.google.common.collect.Maps;
import me.rigbytheswag.swaghub.listeners.PlayerListener;
import me.rigbytheswag.swaghub.manager.ManagerHandler;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.UUID;

public final class HubPlugin extends JavaPlugin {

    private Map<UUID, Player> playerMap = Maps.newHashMap();

    private ManagerHandler managerHandler;


    @Override
    public void onEnable() {
        saveDefaultConfig();
        registerManagers();
        registerListeners();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerManagers() {
        managerHandler = new ManagerHandler(this);

    }

    private void registerListeners() {
        new PlayerListener(this);
    }

    public Map<UUID, Player> getPlayerMap() {
        return playerMap;
    }

    public ManagerHandler getManagerHandler() {
        return managerHandler;
    }
}
