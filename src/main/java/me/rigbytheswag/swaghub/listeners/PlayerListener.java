package me.rigbytheswag.swaghub.listeners;

import me.rigbytheswag.swaghub.HubPlugin;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    private final HubPlugin plugin;

    public PlayerListener(HubPlugin plugin) {
        this.plugin = plugin;
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

        player.getInventory().setItem(0, plugin.getManagerHandler().getItemHandler().getServerSelector());
    }
}
