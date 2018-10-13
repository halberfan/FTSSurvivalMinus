package de.ftscraft.survivalminus.listeners;

import de.ftscraft.survivalminus.main.Survival;
import net.minecraft.server.v1_13_R2.EntityPlayer;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    private Survival plugin;

    public PlayerMoveListener(Survival plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {

        Player p = event.getPlayer();

        EntityPlayer e = ((CraftPlayer)p).getHandle();

        if (p.getInventory().getChestplate() != null && p.getInventory().getChestplate().getType() != Material.AIR)
            if (p.getInventory().getChestplate().getItemMeta().getDisplayName() != null) {
                if(p.getInventory().getChestplate().getItemMeta().getDisplayName().equalsIgnoreCase("§4Robe")) {
                    e.setFlag(7, false);
                }
            }

    }

}
