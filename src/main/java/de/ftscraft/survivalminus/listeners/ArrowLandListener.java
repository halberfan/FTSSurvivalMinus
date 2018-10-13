package de.ftscraft.survivalminus.listeners;

import de.ftscraft.survivalminus.main.Survival;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class ArrowLandListener implements Listener {


    public ArrowLandListener(Survival plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onArrow(ProjectileHitEvent event) {
        if (event.getHitBlock() != null) {
            if (event.getEntity().getName().equalsIgnoreCase("§cSplashArrow")) {
                for (Entity e : event.getHitBlock().getWorld().getNearbyEntities(event.getHitBlock().getLocation(), 3, 3, 3)) {
                    if(e instanceof LivingEntity)
                        ((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.HARM, 1, 0));
                }
            }
        }
    }

}
