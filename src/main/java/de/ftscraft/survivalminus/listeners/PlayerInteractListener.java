package de.ftscraft.survivalminus.listeners;

import de.ftscraft.survivalminus.main.Survival;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.material.Cauldron;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.util.Vector;

import java.util.Arrays;

public class PlayerInteractListener implements Listener {

    private Survival plugin;

    public PlayerInteractListener(Survival plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInteract(final PlayerInteractEvent event) {
        /*
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
                            d.getData().setData((byte) (c.getData() - 1));
                            d.update();

                        }

                    } else return;
                }

            }

        }
        */

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getItem() != null)
                if (event.getItem().getType() == Material.GOLDEN_HOE) {
                    if (event.getItem().getItemMeta().getDisplayName() != null) {
                        if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cMedikit")) {
                            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 7 * 20, 2));
                            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                                @Override
                                public void run() {
                                    event.getPlayer().setHealth(20);
                                }
                            }, 20 * 7);
                        }

                        if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("test")) {
                            FallingBlock fb = event.getPlayer().getWorld().spawnFallingBlock(event.getPlayer().getLocation(), new MaterialData(Material.COBWEB));
                            fb.setVelocity(event.getPlayer().getLocation().getDirection().multiply(2));
                        }

                    }
                }
        }

    }

}
