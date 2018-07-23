package de.ftscraft.survivalminus.listeners;

import de.ftscraft.survivalminus.main.Survival;
import de.ftscraft.survivalminus.user.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private Survival plugin;

    public PlayerJoinListener(Survival plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        User u = new User(plugin, event.getPlayer());
        u.getData();
    }

}
