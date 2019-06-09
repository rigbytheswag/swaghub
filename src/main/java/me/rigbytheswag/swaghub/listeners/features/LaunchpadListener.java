package me.rigbytheswag.swaghub.listeners.features;

import com.google.common.collect.Lists;
import me.rigbytheswag.swaghub.HubPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import java.util.List;

public class LaunchpadListener implements Listener {

    private List<Entity> deny = Lists.newArrayList();

    @EventHandler
    public void onPlayerLaunchpad(PlayerMoveEvent event) {
        if (event.getTo().getBlock().getType() == Material.GOLD_PLATE) {
            if (!deny.contains(event.getPlayer())) {
                LivingEntity entity = event.getPlayer();

                Vector vector = entity.getEyeLocation().getDirection().multiply(10.0).setY(1.0);
                entity.setVelocity(vector);
                deny.add(entity);

                Runnable remove = () -> deny.remove(entity);
                Bukkit.getScheduler().scheduleSyncDelayedTask(HubPlugin.getPlugin(), remove, 15);
            }
        }
    }
}
