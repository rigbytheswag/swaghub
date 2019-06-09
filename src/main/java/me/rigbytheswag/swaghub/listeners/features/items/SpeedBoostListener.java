package me.rigbytheswag.swaghub.listeners.features.items;

import com.google.common.collect.Lists;
import me.rigbytheswag.swaghub.HubPlugin;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class SpeedBoostListener implements Listener {

    private final HubPlugin plugin;

    private List<Player> deny = Lists.newArrayList();
    private int taskId;

    public SpeedBoostListener(HubPlugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        for (Player player : plugin.getPlayerMap().values()) {
            player.removePotionEffect(PotionEffectType.SPEED);
        }
        deny.clear();
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getAction() != Action.PHYSICAL) {
            if (player.getItemInHand().equals(plugin.getManagerHandler().getItemHandler().getSpeedBoostItem())) {
                if (!deny.contains(player)) {
                    if (player.hasPotionEffect(PotionEffectType.SPEED)) {
                        player.removePotionEffect(PotionEffectType.SPEED);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.speed-boost.disabled")));
                    } else {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.speed-boost.enabled")));
                    }

                    Runnable runnable = () -> deny.remove(player);
                    plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, runnable,20*5);
                    deny.add(player);

                    event.setCancelled(true);
                }
            }
        }
    }

    // just in case our server owners set the speed boost to something eatable
    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        if (event.getItem() == plugin.getManagerHandler().getItemHandler().getSpeedBoostItem()) {
            event.setCancelled(true);
        }
    }
}
