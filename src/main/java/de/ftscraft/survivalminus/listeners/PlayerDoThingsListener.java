package de.ftscraft.survivalminus.listeners;

import com.gmail.nossr50.datatypes.skills.SkillType;
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
        u.addToCount(1);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        User u = plugin.getUser(event.getPlayer().getName());
        if (u.getAbility().get(0) != null) {
            Material m = event.getBlock().getType();
            if (m != null) {
                if (u.getAbility().get(0) == SkillType.MINING) {
                    if (m == Material.STONE) {
                        u.addToCount(1);
                    } else {
                        u.addToCount(2);
                    }
                } else if (u.getAbility().get(0) == SkillType.WOODCUTTING) {
                    boolean a = false;
                    for(Material mat : Material.values()) {
                        if (mat.toString().contains("WOOD")) {
                            a = true;
                            u.addToCount(1);
                            break;
                        }
                    }
                    if(!a)
                        u.addToCount(2);
                } else if(u.getAbility().get(0) == SkillType.MINING) {
                    boolean a = false;
                    for(Material mat : Material.values()) {
                        if (mat.toString().contains("STONE")) {
                            a = true;
                            u.addToCount(1);
                            break;
                        }
                    }
                    if(!a)
                        u.addToCount(2);
                }
            }
        } else {u.addToCount(2);}
        if (u.getState() == 1) {
            if (u.getBlocksToBreak() != 0) {
                u.setBlocksToBreak(u.getBlocksToBreak() - 1);
                event.setCancelled(true);
            } else if (u.getBlocksToBreak() == 0) {
                event.setCancelled(false);
                u.setBlocksToBreak(10);
            }
        } else if (u.getState() == 2) {
            if (u.getBlocksToBreak() != 0) {
                u.setBlocksToBreak(u.getBlocksToBreak() - 1);
                event.setCancelled(true);
            } else if (u.getBlocksToBreak() == 0) {
                event.setCancelled(false);
                u.setBlocksToBreak(20);
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        User u = plugin.getUser(event.getPlayer().getName());
        u.addToCount(2);
    }

    @EventHandler
    public void onEnchanting(EnchantItemEvent event) {
        User u = plugin.getUser(event.getEnchanter().getName());
        u.addToCount(2);
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
                                int id1 = item1.getType().getId();
                                int id2 = item2.getType().getId();

// if the player is repairing something the ids will be the same
                                if (id1 != 0 && id1 == id2) {
                                    // item in the result slot
                                    ItemStack item3 = e.getCurrentItem();

// check if there is an item in the result slot
                                    if (item3 != null) {
                                        u.addToCount(1);
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
