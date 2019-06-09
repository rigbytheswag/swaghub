package me.rigbytheswag.swaghub.manager;

import me.rigbytheswag.swaghub.HubPlugin;
import me.rigbytheswag.swaghub.manager.handlers.ItemHandler;

public class ManagerHandler {

    private final HubPlugin plugin;

    private ItemHandler itemHandler;


    public ManagerHandler(HubPlugin plugin) {
        this.plugin = plugin;
        this.loadManagers();
    }

    private void loadManagers() {
        itemHandler = new ItemHandler(this);
    }

    public HubPlugin getPlugin() {
        return plugin;
    }

    public ItemHandler getItemHandler() {
        return itemHandler;
    }
}
