package de.ftscraft.survivalminus.user;

import com.gmail.nossr50.datatypes.skills.AbilityType;
import com.gmail.nossr50.datatypes.skills.SkillType;
import de.ftscraft.survivalminus.main.Survival;
import de.ftscraft.survivalminus.utils.Variables;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class User {

    private Survival plugin;
    private Player player;

    private int thirst = Variables.MAX_THIRST,
            fatigue = Variables.MAX_FATIGUE,
            mood = Variables.MAX_MOOD;

    private int seconds_thirst = Variables.TIME_THIRST;

    private int
            kohlenhydrathe = Variables.MAX_KOHLENHYDRATHE,
            proteine = Variables.MAX_PROTEIN,
            suager = Variables.MAX_SUGAR,
            salt = Variables.MAX_SALT,
            bodyFat = Variables.MAX_BODY_FAT,
            fat = Variables.MAX_FAT;

    private HashMap<Integer, SkillType> ability;

    public User(Survival survival, Player a) {
        this.plugin = survival;
        this.player = a;
        this.ability = new HashMap<>();
        plugin.addUser(a.getName(), this);
    }

    public void saveData() {
        plugin.getUserIO().savePlayerData(this);
    }

    public int getThirst() {
        return thirst;
    }

    public int getSeconds_thirst() {
        return seconds_thirst;
    }

    public void setSeconds_thirst(int seconds_thirst) {
        this.seconds_thirst = seconds_thirst;
        if (seconds_thirst == 0) {
            thirst = thirst - 1;
            this.seconds_thirst = Variables.TIME_THIRST;
        }
    }

    public void setThirst(int thirst) {
        this.thirst = thirst;
    }

    public int getKohlenhydrathe() {
        return kohlenhydrathe;
    }

    public int getProteine() {
        return proteine;
    }

    public int getFat() {
        return fat;
    }

    public int getSalt() {
        return salt;
    }

    public int getSuager() {
        return suager;
    }

    public HashMap<Integer, SkillType> getAbility() {
        return ability;
    }

    public int getFatigue() {
        return fatigue;
    }

    public int getMood() {
        return mood;
    }

    public UUID getUUID() {
        return player.getUniqueId();
    }

    public void setFatigue(int fatigue) {
        this.fatigue = fatigue;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public void setKohlenhydrathe(int kohlenhydrathe) {
        this.kohlenhydrathe = kohlenhydrathe;
    }

    public void setProteine(int proteine) {
        this.proteine = proteine;
    }

    public void setSuager(int suager) {
        this.suager = suager;
    }

    public void setSalt(int salt) {
        this.salt = salt;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getBodyFat() {
        return bodyFat;
    }

    public void setBodyFat(int bodyFat) {
        this.bodyFat = bodyFat;
    }

    public void remove() {
        plugin.removeUser(player.getName());
    }

    public void setSkills(String[] skills) {
        int i = 0;
        for (String a : skills) {
            ability.put(i, SkillType.valueOf(a.toUpperCase()));
            i++;
        }
    }

    public void getData() {
        plugin.getUserIO().getPlayerData(this);
    }

    public boolean hasSkill(SkillType skill) {
        for (SkillType a : ability.values()) {
            if (a.getName().equalsIgnoreCase(skill.getName()))
                return true;
        }
        return false;
    }

    public void skillRemove(SkillType skill) {
        for (Map.Entry<Integer, SkillType> entry : ability.entrySet()) {
            if (entry.getValue().equals(skill)) {

                int a = entry.getKey();
                System.out.println(a);
                for(int i = 0; i <= ability.size(); i++) {
                    System.out.println(i);
                    if(i >= a && !ability.get(i).getName().equals(skill.getName())) {
                        System.out.println(true);
                        player.sendMessage("§cDu hast durch den Levelverlust den Skill §e"+ability.get(i).getName()+" §cverloren");
                        ability.remove(i);
                    } else System.out.println(false);
                }

                break;
            }
        }
    }
}
