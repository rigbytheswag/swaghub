package me.rigbytheswag.swaghub.listeners;

import me.rigbytheswag.swaghub.HubPlugin;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

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

        plugin.getManagerHandler().getItemHandler().addItemsToInventory(player);

        // Double jump
        player.setAllowFlight(true);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        plugin.getPlayerMap().remove(player.getUniqueId());
    }
}
