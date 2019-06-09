package me.rigbytheswag.swaghub.listeners.features;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class DoubleJumpListener implements Listener {

    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
        if (event.getPlayer().getGameMode() != GameMode.CREATIVE) {
            Player player = event.getPlayer();

            player.playSound(player.getLocation(), Sound.ENTITY_IRONGOLEM_ATTACK ,5, -10);
            player.setVelocity(player.getLocation().getDirection().multiply(1).setY(1));

            // TODO: maybe a cool particle?

            player.setAllowFlight(false);
            event.setCancelled(true);
        }
    }

    // now we're going to make sure the player can double jump again once they touch ground
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = (Player) event.getPlayer();

        if (!player.getAllowFlight()) {
            if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR) {
                player.setAllowFlight(true);
            }
        }
    }
}
