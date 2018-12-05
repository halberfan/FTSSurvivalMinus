package de.ftscraft.survivalminus.listeners;

import de.ftscraft.survivalminus.main.Survival;
import de.ftscraft.survivalminus.user.User;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeadListener implements Listener {


    private Survival plugin;

    public DeadListener(Survival plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player killed = event.getEntity();
        Player killer = event.getEntity().getKiller();
        if(killer == null)
            return;
        if(killer.getInventory().getItemInMainHand() != null && killer.getInventory().getItemInMainHand().getType() != Material.AIR) {
            if(killer.getInventory().getItemInMainHand().getItemMeta().getDisplayName() != null) {
                if(killer.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase("§cDolch")) {
                    event.setDeathMessage("");
                }
            }
        }

        User user = plugin.getUser(killed);
        user.fillAll();
    }

}

