package de.ftscraft.survivalminus.listeners;

import de.ftscraft.survivalminus.main.Survival;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.material.Cauldron;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.util.Arrays;

public class PlayerInteractListener implements Listener {

    private Survival plugin;

    public PlayerInteractListener(Survival plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {

            if (event.getClickedBlock().getType() == Material.CAULDRON) {
                Cauldron c = (Cauldron) event.getClickedBlock().getState().getData();

                if (!c.isEmpty()) {

                    Location blockUnder = event.getClickedBlock().getLocation().clone().subtract(0, 1, 0);

                    Block block = blockUnder.getBlock();

                    if (block.getType() == Material.FIRE) {

                        if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.GLASS_BOTTLE) {

                            event.setCancelled(true);

                            event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);
                            ItemStack is = new ItemStack(Material.POTION, 1);
                            PotionMeta im = (PotionMeta) is.getItemMeta();
                            im.setBasePotionData(new PotionData(PotionType.WATER));
                            im.setLore(Arrays.asList("§7Gereinigt"));
                            is.setItemMeta(im);

                            event.getPlayer().getInventory().addItem(is);

                            BlockState d = event.getClickedBlock().getState();
                            d.getData().setData((byte) (c.getData()-1));
                            d.update();

                        }

                    } else return;
                }

            }

        }
    }

}
