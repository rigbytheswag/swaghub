package me.rigbytheswag.swaghub.listeners;

import me.rigbytheswag.swaghub.HubPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WorldListener {

    private final HubPlugin plugin;

    public WorldListener(HubPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean getOption(String string) {
        return plugin.getConfig().getBoolean("disable." + string);
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if (getOption("creatureSpawn") && event.getSpawnReason() != CreatureSpawnEvent.SpawnReason.CUSTOM) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        if (getOption("weather")) {
            event.setCancelled(true);
        }
    }
}
