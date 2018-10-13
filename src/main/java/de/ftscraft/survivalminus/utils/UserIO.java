package de.ftscraft.survivalminus.utils;

import com.gmail.nossr50.datatypes.skills.SkillType;
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
                fatigue = cfg.getInt("health.fatigue"),
                mood = cfg.getInt("health.mood"),
                kohlenhydrate = cfg.getInt("health.kohlenhydrate"),
                protein = cfg.getInt("health.protein"),
                salt = cfg.getInt("health.salt"),
                fat = cfg.getInt("health.fat");

        long firstPlayed;
        if (cfg.contains("player.firstTimePlayed"))
            firstPlayed = cfg.getLong("player.firstTimePlayed");
        else {
            user.setFirstTimePlayed(System.currentTimeMillis());
            firstPlayed = 0;
        }

        List typeList = cfg.getList("skills");
        String[] types = null;
        if (typeList != null)
            types = (String[]) typeList.toArray(new String[typeList.size()]);

        user.setThirst(thirst);
        user.setFatigue(fatigue);
        user.setMood(mood);
        user.setKohlenhydrate(kohlenhydrate);
        user.setProteine(protein);
        user.setSalt(salt);
        user.setFat(fat);
        if (firstPlayed != 0)
            user.setFirstTimePlayed(firstPlayed);
        if (types != null)
            user.setSkills(types);
    }

    public void savePlayerData(User user) {
        File userFile = new File(folder + "//" + user.getUUID().toString() + ".yml");

        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(userFile);

        cfg.set("health.thirst", user.getThirst());
        cfg.set("health.fatigue", user.getFatigue());
        cfg.set("health.mood", user.getMood());
        cfg.set("health.kohlenhydrate", user.getKohlenhydrate());
        cfg.set("health.protein", user.getProteine());
        cfg.set("health.salt", user.getSalt());
        cfg.set("health.fat", user.getFat());

        cfg.set("player.firstTimePlayed", user.getFirstTimePlayed());

        List<String> b = new ArrayList<>();

        if (!user.getAbility().values().isEmpty())
            for (SkillType a : user.getAbility().values()) {
                b.add(a.getName());
            }

        cfg.set("skills", b.toArray());

        try {
            cfg.save(userFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
