package me.rigbytheswag.swaghub;

import com.google.common.collect.Maps;
import me.rigbytheswag.swaghub.manager.ManagerHandler;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.UUID;

public final class HubPlugin extends JavaPlugin {

    private Map<UUID, Player> userMap = Maps.newHashMap();

    private ManagerHandler managerHandler;


    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerManagers() {
        managerHandler = new ManagerHandler(this);

    }

    public ManagerHandler getManagerHandler() {
        return managerHandler;
    }
}
