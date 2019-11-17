package de.ftscraft.survivalminus.listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import de.ftscraft.survivalminus.main.Survival;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.lang.reflect.InvocationTargetException;

public class PlayerMoveListener implements Listener {

    private Survival plugin;

    public PlayerMoveListener(Survival plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {

        Player p = event.getPlayer();

        if (p.getInventory().getChestplate() != null && p.getInventory().getChestplate().getType() != Material.AIR)
            if (p.getInventory().getChestplate().getItemMeta().getDisplayName() != null) {
                /*if(p.getInventory().getChestplate().getItemMeta().getDisplayName().equalsIgnoreCase("§4Robe")) {

                    ProtocolManager  pm = ProtocolLibrary.getProtocolManager();
                    PacketContainer packet = pm.createPacket(PacketType.Play.Client.FLYING);
                    packet.getBooleans().write(0, false);
                    try {
                        pm.sendServerPacket(p, packet);
                    } catch (InvocationTargetException e1) {
                        e1.printStackTrace();
                    }
                }*/
            }

    }

    @EventHandler
    public void onGlide(EntityToggleGlideEvent event){

        Player p  = (Player) event.getEntity();

        if (p.getInventory().getChestplate() != null && p.getInventory().getChestplate().getType() != Material.AIR)
            if (p.getInventory().getChestplate().getItemMeta().getDisplayName() != null) {
                if(p.getInventory().getChestplate().getItemMeta().getDisplayName().equalsIgnoreCase("§4Robe")) {
                    event.setCancelled(true);
                }
            }
    }

}
