package de.ftscraft.survivalminus.listeners;

import de.ftscraft.survivalminus.main.Survival;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.PlayerInventory;

public class BowListener implements Listener {

    private Survival plugin;

    public BowListener(Survival plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBow(EntityShootBowEvent event) {
        if(event.getEntity() instanceof Player) {

            Player p = (Player)event.getEntity();

            PlayerInventory inv = p.getInventory();

            if(inv.getItemInOffHand().getType() != Material.ARROW) {
                event.getProjectile().remove();

                p.sendMessage("§cDu musst den Pfeil in der Off-Hand haben!");
            }

        }
    }

}
