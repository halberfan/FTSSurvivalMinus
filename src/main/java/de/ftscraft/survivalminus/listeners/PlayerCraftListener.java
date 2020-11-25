package de.ftscraft.survivalminus.listeners;

import de.ftscraft.survivalminus.main.Survival;
import de.ftscraft.survivalminus.user.User;
import de.ftscraft.survivalminus.utils.Utils;
import net.sf.cglib.asm.$Attribute;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
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
        Player p = (Player) event.getWhoClicked();
        if (result.getItemMeta().getDisplayName() != null) {
            if (result.getItemMeta().getDisplayName().equalsIgnoreCase("§cDolch") || result.getItemMeta().getDisplayName().equalsIgnoreCase("§4Robe")) {
                if (!event.getWhoClicked().hasPermission("survivalminus.item.dieb")) {
                    event.setCancelled(true);
                }
            } else if (result.getItemMeta().getDisplayName().equalsIgnoreCase("§6Hammer")) {
                if (!event.getWhoClicked().hasPermission("survivalminus.item.hammer")) {
                    event.setCancelled(true);
                }
            } else if (result.getItemMeta().getDisplayName().equalsIgnoreCase("§cAblassbrief")) {
                if (!event.getWhoClicked().hasPermission("survivalminus.item.ablassbrief")) {
                    event.setCancelled(true);
                }
            }

        }




    }

    @EventHandler
    public void onItemPrepareCraft(PrepareItemCraftEvent event) {

        for (ItemStack matrix : event.getInventory().getMatrix()) {
            if(matrix == null)
                continue;
            if(matrix.isSimilar(plugin.getItemStacks().getWater())) {
                event.getInventory().setResult(new ItemStack(Material.AIR));
                break;
            }
        }

    }

}
