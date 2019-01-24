package de.ftscraft.survivalminus.main;

import de.ftscraft.ftssystem.main.FtsSystem;
import de.ftscraft.survivalminus.commands.CMDchooseability;
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
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.TreeMap;

public class Survival extends JavaPlugin {

    private TreeMap<String, User> user;

    public ItemStacks itemStacks;

    private FtsSystem fts;

    private UserIO userIO;

    @Override
    public void onEnable() {
        super.onEnable();
        initUtils();
        initListeners();
        initCommands();
        for(Player a : Bukkit.getOnlinePlayers()) {
            User u = new User(this, a);
            u.getData();
        }

    }

    private void initCommands() {
        new CMDgesundheit(this);
        new CMDchooseability(this);
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
        super.onDisable();
    }

    private void initListeners() {
        new PlayerJoinListener(this);
        new PlayerQuitListener(this);
        //new PlayerInteractListener(this);
        new PlayerDrinkListener(this);
        new BowListener(this);
        new McMMOListener(this);
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
        return user.get(name);
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

    public ItemStacks getItemStacks()
    {
        return itemStacks;
    }
}
