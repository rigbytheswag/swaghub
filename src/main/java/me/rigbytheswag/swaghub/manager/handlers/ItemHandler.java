package me.rigbytheswag.swaghub.manager.handlers;

import com.google.common.collect.Lists;
import me.rigbytheswag.swaghub.manager.Manager;
import me.rigbytheswag.swaghub.manager.ManagerHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemHandler extends Manager {

    public ItemHandler(ManagerHandler managerHandler) {
        super(managerHandler);
    }

    public ItemStack SERVER_SELECTOR = build(getMaterialFromConfigString("serverSelector"), getItemNameFromConfig("serverSelector"));
    public ItemStack SERVER_STORE = build(getMaterialFromConfigString("serverStore"), getItemNameFromConfig("serverStore"));
    public ItemStack SPEED_BOOST = build(getMaterialFromConfigString("speedBoost"), getItemNameFromConfig("speedBoost"));
    public ItemStack SETTINGS = build(getMaterialFromConfigString("settings"), getItemNameFromConfig("settings"));

    public ItemStack getServerSelectorItem() {
        return SERVER_SELECTOR;
    }

    public ItemStack getServerStoreItem() {
        return SERVER_STORE;
    }

    public ItemStack getSpeedBoostItem() {
        return SPEED_BOOST;
    }

    public ItemStack getSettingsItem() {
        return SETTINGS;
    }

    public Material getMaterialFromConfigString(String string) {
        return Material.getMaterial(managerHandler.getPlugin().getConfig().getString("items." + string + ".item"));
    }

    public String getItemNameFromConfig(String string) {
        return ChatColor.translateAlternateColorCodes('&', managerHandler.getPlugin().getConfig().getString("items." + string + ".name"));
    }

    public ItemStack build(Material type, String displayName, String... lore) {
        return build(type, (byte) 0, displayName, lore);
    }

    public ItemStack build(Material type, byte data, String displayName, String... lore) {
        ItemStack stack = new ItemStack(type, 1, (short)1, data);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));

        if (lore.length > 0) {
            List<String> l = Lists.newArrayList();
            for (String s : lore) {
                l.add(ChatColor.translateAlternateColorCodes('&', s));
            }
            meta.setLore(l);
        }
        stack.setItemMeta(meta);
        return stack;
    }
}



