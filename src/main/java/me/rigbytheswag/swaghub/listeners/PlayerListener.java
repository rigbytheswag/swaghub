package me.rigbytheswag.swaghub.listeners;

import me.rigbytheswag.swaghub.HubPlugin;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    private final HubPlugin plugin;

    private FileConfiguration config;

    public PlayerListener(HubPlugin plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (player.getGameMode() != GameMode.SURVIVAL) {
            player.setGameMode(GameMode.SURVIVAL);
        }

        plugin.getPlayerMap().put(player.getUniqueId(), player);
        player.getInventory().clear();

        this.addItemsToInventory(player);
    }

    void addItemsToInventory(Player player) {
        if (config.getBoolean(getItemBoolean("serverSelector"))) {
            player.getInventory().setItem(0, plugin.getManagerHandler().getItemHandler().getServerSelectorItem());
        }

        if (config.getBoolean(getItemBoolean("serverStore"))) {
            player.getInventory().setItem(1, plugin.getManagerHandler().getItemHandler().getServerStoreItem());
        }

        if (config.getBoolean(getItemBoolean("speedBoost"))) {
            player.getInventory().setItem(2, plugin.getManagerHandler().getItemHandler().getSpeedBoostItem());
        }
    }

    String getItemBoolean(String string) {
        return "items." + string + ".enabled";
    }
}
