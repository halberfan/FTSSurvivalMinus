package de.ftscraft.survivalminus.main;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import de.ftscraft.ftssystem.main.FtsSystem;
import de.ftscraft.survivalminus.commands.CMDgesundheit;
import de.ftscraft.survivalminus.commands.CMDgivewater;
import de.ftscraft.survivalminus.listeners.*;
import de.ftscraft.survivalminus.user.User;
import de.ftscraft.survivalminus.utils.ItemStacks;
import de.ftscraft.survivalminus.utils.TaskScheduler;
import de.ftscraft.survivalminus.utils.UserIO;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.TreeMap;
import java.util.UUID;

public class Survival extends JavaPlugin {

    private TreeMap<String, User> user;

    public ItemStacks itemStacks;

    private FtsSystem fts;

    private UserIO userIO;

    ProtocolManager pm;

    @Override
    public void onEnable() {
        super.onEnable();
        initUtils();
        initListeners();
        initCommands();
        pm = ProtocolLibrary.getProtocolManager();
        for (Player a : Bukkit.getOnlinePlayers()) {
            User u = new User(this, a);
            u.getData();
        }

    }

    private void initCommands() {
        new CMDgesundheit(this);
        new CMDgivewater(this);
    }

    private void initUtils() {
        userIO = new UserIO(this);
        user = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        itemStacks = new ItemStacks(this);
        fts = (FtsSystem) Bukkit.getServer().getPluginManager().getPlugin("FTSSystem");
        new TaskScheduler(this);
    }

    @Override
    public void onDisable() {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            getUser(onlinePlayer.getName()).saveData();
            getUser(onlinePlayer.getName()).remove();
        }
        super.onDisable();
    }

    private void initListeners() {
        new PlayerJoinListener(this);
        new PlayerQuitListener(this);
        //new PlayerInteractListener(this);
        new PlayerDrinkListener(this);
        new BowListener(this);
        new PlayerDoThingsListener(this);
        new BlockLandListener(this);
        new ArrowLandListener(this);
        new PlayerMoveListener(this);
        new DeadListener(this);
        new PlayerCraftListener(this);
        new PlayerInteractListener(this);
        new PlayerClickListener(this);
    }

    public User getUser(String name) {
        User t = user.get(name);
        return t;
    }

    public User getUser(OfflinePlayer player) {
        return user.get(player.getName());
    }

    public Collection<User> getAllUser() {
        return user.values();
    }

    public void addUser(String name, User user) {
        this.user.put(name, user);
    }

    public UserIO getUserIO() {
        return userIO;
    }

    public void removeUser(String name) {
        this.user.remove(name);
    }

    public FtsSystem getFts() {
        return fts;
    }

    public ItemStacks getItemStacks() {
        return itemStacks;
    }

}
