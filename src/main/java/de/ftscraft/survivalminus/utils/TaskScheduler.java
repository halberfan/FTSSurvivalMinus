package de.ftscraft.survivalminus.utils;

import de.ftscraft.survivalminus.main.Survival;
import de.ftscraft.survivalminus.user.User;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TaskScheduler implements Runnable {

    private Survival plugin;
    private int timeSeconds = 0;

    public TaskScheduler(Survival plugin) {
        this.plugin = plugin;
        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, this, 1, 1);
    }

    public void run() {

        timeSeconds++;


        if (timeSeconds == 40) {
            for (User a : plugin.getAllUser()) {
                a.setSeconds_thirst(a.getSeconds_thirst() - 1);
                a.checkAciveValues();
                if(a.getPlayer().getGameMode() == GameMode.CREATIVE) {
                    a.setThirst(20);
                    a.setProteine(20);
                    a.setKohlenhydrate(20);
                }
            }
            timeSeconds = 0;
        }

        for (User a : plugin.getAllUser()) {
            if (a.getState() == 1) {
                a.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 41, 1), true);
                a.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 41, 1), true);
                a.getPlayer().setFoodLevel(3);
            } else if (a.getState() == 2) {
                a.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 41, 1), true);
                a.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 41, 2), true);
                a.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 41, 2), true);
                a.getPlayer().setFoodLevel(3);
            }

            Player p = a.getPlayer();

            if (p.getInventory().getChestplate() != null && p.getInventory().getChestplate().getType() != Material.AIR) {
                if (p.getInventory().getChestplate().getItemMeta().getDisplayName() != null) {
                    if (p.getInventory().getChestplate().getItemMeta().getDisplayName().equalsIgnoreCase("§4Robe")) {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 3 * 20, 2), true);
                    }
                }
            }

        }


    }

}
