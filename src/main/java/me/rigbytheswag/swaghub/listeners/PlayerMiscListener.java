package me.rigbytheswag.swaghub.listeners;

import me.rigbytheswag.swaghub.HubPlugin;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerMiscListener implements Listener {

    private final HubPlugin plugin;

    public PlayerMiscListener(HubPlugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public boolean getPlayerOption(String string) {
        return plugin.getConfig().getBoolean("disable.player." + string);
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if (getPlayerOption("drop-items") && !event.getPlayer().hasPermission("hub.bypass.dropitems")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (getPlayerOption("damage") && !event.getEntity().hasPermission("hub.bypass.damage")) {
            event.setCancelled(true);

            if (event.getCause() == EntityDamageEvent.DamageCause.VOID) {
                event.getEntity().teleport(event.getEntity().getWorld().getSpawnLocation());
            }
        }
    }

    @EventHandler
    public void onPlayerHunger(FoodLevelChangeEvent event) {
        if (getPlayerOption("hunger") && !event.getEntity().hasPermission("hub.bypass.hunger")) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryClick(InventoryClickEvent event) {
        if (getPlayerOption("inventory-use") && !event.getWhoClicked().hasPermission("hub.bypass.inventoryUse")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (getPlayerOption("place-blocks") && !event.getPlayer().hasPermission("hub.bypass.placeBlocks") ||
                event.getPlayer().getGameMode() != GameMode.CREATIVE) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (getPlayerOption("break-blocks") && !event.getPlayer().hasPermission("hub.bypass.breakBlocks") ||
                event.getPlayer().getGameMode() != GameMode.CREATIVE) {
            event.setCancelled(true);
        }
    }
}
