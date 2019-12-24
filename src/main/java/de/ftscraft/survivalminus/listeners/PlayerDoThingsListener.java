package de.ftscraft.survivalminus.listeners;

import de.ftscraft.survivalminus.main.Survival;
import de.ftscraft.survivalminus.user.User;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class PlayerDoThingsListener implements Listener {

    private Survival plugin;

    public PlayerDoThingsListener(Survival plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        User u = plugin.getUser(event.getWhoClicked().getName());
        u.addToCount(70);
    }


    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        User u = plugin.getUser(event.getPlayer().getName());
        u.addToCount(2);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        User u = plugin.getUser(event.getPlayer().getName());
        u.addToCount(1);
    }

    @EventHandler
    public void onEnchanting(EnchantItemEvent event) {
        User u = plugin.getUser(event.getEnchanter().getName());
        u.addToCount(100);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        User u = plugin.getUser(e.getWhoClicked().getName());

        if (!e.isCancelled()) {
            HumanEntity ent = e.getWhoClicked();

// not really necessary
            if (ent instanceof Player) {
                Player player = (Player) ent;
                Inventory inv = e.getInventory();

// see if we are talking about an anvil here
                if (inv instanceof AnvilInventory) {
                    AnvilInventory anvil = (AnvilInventory) inv;
                    InventoryView view = e.getView();
                    int rawSlot = e.getRawSlot();

// compare raw slot to the inventory view to make sure we are in the upper inventory
                    if (rawSlot == view.convertSlot(rawSlot)) {
                        // 2 = result slot
                        if (rawSlot == 2) {
                            // all three items in the anvil inventory
                            ItemStack[] items = anvil.getContents();

// item in the left slot
                            ItemStack item1 = items[0];

// item in the right slot
                            ItemStack item2 = items[1];

// I do not know if this is necessary
                            if (item1 != null && item2 != null) {
                                Material id1 = item1.getType();
                                Material id2 = item2.getType();

// if the player is repairing something the ids will be the same
                                if (id1 == id2) {
                                    // item in the result slot
                                    ItemStack item3 = e.getCurrentItem();

// check if there is an item in the result slot
                                    if (item3 != null) {
                                        u.addToCount(100);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


    }

}
