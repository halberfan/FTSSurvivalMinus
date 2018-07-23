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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        int thirst = cfg.getInt("health.thirst"),
                fatigue = cfg.getInt("health.fatigue"),
                mood = cfg.getInt("health.mood"),
                kohlenhydrate = cfg.getInt("health.kohlenhydrate"),
                protein = cfg.getInt("health.protein"),
                sugar = cfg.getInt("health.sugar"),
                salt = cfg.getInt("health.salt"),
                fat = cfg.getInt("health.fat");

        List typeList = cfg.getList("skills");
        String[] types = null;
        if(typeList != null)
            types = (String[]) typeList.toArray(new String[typeList.size()]);

        user.setThirst(thirst);
        user.setFatigue(fatigue);
        user.setMood(mood);
        user.setKohlenhydrathe(kohlenhydrate);
        user.setProteine(protein);
        user.setSuager(sugar);
        user.setSalt(salt);
        user.setFat(fat);
        if(types != null)
            user.setSkills(types);
    }

    public void savePlayerData(User user) {
        File userFile = new File(folder + "//" + user.getUUID().toString() + ".yml");

        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(userFile);

        cfg.set("health.thirst", user.getThirst());
        cfg.set("health.fatigue", user.getFatigue());
        cfg.set("health.mood", user.getMood());
        cfg.set("health.kohlenhydrate", user.getKohlenhydrathe());
        cfg.set("health.protein", user.getProteine());
        cfg.set("health.sugar", user.getSuager());
        cfg.set("health.salt", user.getSalt());
        cfg.set("health.fat", user.getFat());

        List<String> b = new ArrayList<>();

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
