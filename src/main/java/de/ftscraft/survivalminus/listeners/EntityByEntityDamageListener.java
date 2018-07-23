package de.ftscraft.survivalminus.listeners;

import de.ftscraft.survivalminus.main.Survival;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class EntityByEntityDamageListener implements Listener {

    private Survival plugin;

    public EntityByEntityDamageListener(Survival plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler()
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        System.out.println(0);
        if ((event.getEntity() instanceof Player) && (event.getDamager() instanceof Player)) {
            Player a = (Player) event.getDamager();
            if (a.getInventory().getItemInMainHand().getItemMeta().getDisplayName() == null) {
                return;
            }
            if (a.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase("§cShiv")) {
                Vector attacker = event.getDamager().getLocation().getDirection();
                Vector victim = event.getEntity().getLocation().getDirection();
                //Hat er ihn nicht angeguckt
                if (attacker.dot(victim) > 0) {
                    //Von Hinten
                    event.setDamage(EntityDamageEvent.DamageModifier.ARMOR, event.getDamage(EntityDamageEvent.DamageModifier.ARMOR) + 2);
                }
                ((Player) event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 2 * 20, 1));
            }
        }
    }

}
