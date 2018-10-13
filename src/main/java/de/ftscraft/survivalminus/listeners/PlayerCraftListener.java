package de.ftscraft.survivalminus.listeners;

import de.ftscraft.survivalminus.main.Survival;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerCraftListener implements Listener {

    private Survival plugin;

    public PlayerCraftListener(Survival plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        ItemStack result = event.getInventory().getResult();
        if(result.getItemMeta().getDisplayName() != null) {
            if(result.getItemMeta().getDisplayName().equalsIgnoreCase("§cDolch") || result.getItemMeta().getDisplayName().equalsIgnoreCase("§4Robe")) {
                if(!event.getWhoClicked().hasPermission("ftsengine.raeuber")) {
                    event.setCancelled(true);
                }
            }
        }
    }

}