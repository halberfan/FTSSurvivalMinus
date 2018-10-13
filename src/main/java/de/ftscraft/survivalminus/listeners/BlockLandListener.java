package de.ftscraft.survivalminus.listeners;

import de.ftscraft.survivalminus.main.Survival;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class BlockLandListener implements Listener {

    public BlockLandListener(Survival survival) {
        survival.getServer().getPluginManager().registerEvents(this, survival);
    }

    @EventHandler
    public void onBlockLand(EntityChangeBlockEvent event) {
        if(event.getEntity().getType() == EntityType.FALLING_BLOCK) {
            event.getBlock().setType(Material.COBWEB);
        }
    }

}
