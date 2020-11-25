package de.ftscraft.survivalminus.listeners;

import de.ftscraft.survivalminus.main.Survival;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerClickListener implements Listener {

    private Survival plugin;

    public PlayerClickListener(Survival plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        Player p = (Player) event.getWhoClicked();

        Inventory inv = event.getInventory();

        if(event.getView().getTitle().equalsIgnoreCase("§cRichter-Mute")) {
            event.setCancelled(true);
            ItemStack item = event.getCurrentItem();

            if(item == null)
                return;
            if(item.getItemMeta().getDisplayName() == null)
                return;

            String player = item.getItemMeta().getDisplayName().replace("§5", "");

            plugin.getFts().getPunishmentManager().addTempMute("§cDer Richter hat dich gemuted!", p.getName(), player, "§cDer Spieler wurde von dem Richter gemuted", "5m");

            p.sendMessage("§cDer Spieler " + player + " wurde gemuted für 5 Minuten!");

        }

    }

}
