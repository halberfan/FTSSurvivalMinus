package de.ftscraft.survivalminus.utils;

import de.ftscraft.survivalminus.main.Survival;
import de.ftscraft.survivalminus.user.User;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserIO {

    private File folder;

    private Survival plugin;
    private int interactionToValueDown = 50;
    private long firstPlayed;

    public UserIO(Survival plugin) {
        this.plugin = plugin;
        this.folder = new File(plugin.getDataFolder() + "//user");
        if (!this.folder.exists()) {
            folder.mkdir();
        }
    }

    public void getPlayerData(User user) {
        File userFile = new File(folder + "//" + user.getUUID().toString() + ".yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(userFile);
        if (!userFile.exists()) {
            try {
                cfg.save(userFile);
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        int thirst = cfg.getInt("health.thirst"),
                kohlenhydrate = cfg.getInt("health.kohlenhydrate"),
                protein = cfg.getInt("health.protein"),
                vitamin = cfg.getInt("health.vitamin");

        if (cfg.contains("player.firstTimePlayed")) {
            user.setFirstTimePlayed(cfg.getLong("player.firstTimePlayed"));
        } else {
            user.setFirstTimePlayed(0);
        }

        List typeList = cfg.getList("skills");
        String[] types = null;
        if (typeList != null)
            types = (String[]) typeList.toArray(new String[typeList.size()]);

        user.setThirst(thirst);
        user.setKohlenhydrate(kohlenhydrate);
        user.setProteine(protein);
        user.setVitamine(vitamin);
    }

    public void savePlayerData(User user) {
        File userFile = new File(folder + "//" + user.getUUID().toString() + ".yml");

        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(userFile);

        cfg.set("health.thirst", user.getThirst());
        cfg.set("health.vitamin", user.getVitamine());
        cfg.set("health.kohlenhydrate", user.getKohlenhydrate());
        cfg.set("health.protein", user.getProteine());

        cfg.set("player.firstTimePlayed", user.getFirstTimePlayed());

        List<String> b = new ArrayList<>();


        cfg.set("skills", b.toArray());

        try {
            cfg.save(userFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
