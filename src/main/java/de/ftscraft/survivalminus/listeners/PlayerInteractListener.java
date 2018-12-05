package de.ftscraft.survivalminus.listeners;

import de.ftscraft.survivalminus.main.Survival;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;

public class PlayerInteractListener implements Listener {

    private Survival plugin;

    public PlayerInteractListener(Survival plugin)
    {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInteract(final PlayerInteractEvent event)
    {
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
                                public void run()
                                {
                                    event.getPlayer().setHealth(20);
                                }
                            }, 20 * 7);
                        }

                        if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("test")) {
                            FallingBlock fb = event.getPlayer().getWorld().spawnFallingBlock(event.getPlayer().getLocation(), new MaterialData(Material.COBWEB));
                            fb.setVelocity(event.getPlayer().getLocation().getDirection().multiply(2));
                        }

                    }
                } else if (event.getItem().getType() == Material.GOLDEN_AXE) {
                    if (event.getItem().getItemMeta().getDisplayName() != null) {
                        if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Hammer")) {

                            if (!event.getPlayer().hasPermission("survivalminus.item.hammer")) {
                                event.setCancelled(true);
                                event.getPlayer().sendMessage("§cDieses OP-Item ist nur für Richter!");
                                return;
                            }

                            Inventory inv = Bukkit.createInventory(null, 9 * 6, "§cRichter-Mute");
                            Collection<? extends Player> online = Bukkit.getOnlinePlayers();
                            for (int i = 0; i < online.size(); i++) {
                                Player p = (Player) online.toArray()[i];
                                ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
                                SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
                                skullMeta.setDisplayName("§5" + p.getName());
                                skullMeta.setOwningPlayer(p);
                                skull.setItemMeta(skullMeta);
                                inv.setItem(i, skull);
                            }

                            event.getPlayer().openInventory(inv);

                        }
                    }
                }
        }

    }

}
