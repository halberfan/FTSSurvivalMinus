package de.ftscraft.survivalminus.user;

import com.gmail.nossr50.datatypes.skills.SkillType;
import de.ftscraft.survivalminus.main.Survival;
import de.ftscraft.survivalminus.utils.Food;
import de.ftscraft.survivalminus.utils.Variables;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class User {

    private Survival plugin;
    private Player player;

    private int thirst = Variables.MAX_THIRST;

    private int seconds_thirst = Variables.TIME_THIRST;

    private int
            kohlenhydrathe = Variables.MAX_KOHLENHYDRATHE,
            proteine = Variables.MAX_PROTEIN,
            vitamine = Variables.MAX_VITAMINE;

    private boolean kohlen_active = false,
                    protein_active = false,
                    salt_active = false,
                    fat_active = false;

    private int seconds_down = Variables.DOWN;

    private HashMap<Integer, SkillType> ability;

    private int blocksToBreak = 5;

    private long firstTimePlayed;

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
            proteine = proteine - 1;
            vitamine = vitamine - 1;
            kohlenhydrathe = kohlenhydrathe - 1;
            this.seconds_thirst = Variables.TIME_THIRST;
        }
    }

    public void setThirst(int thirst) {
        this.thirst = thirst;
        if(this.thirst < 0)
            this.thirst = 0;
    }

    public int getKohlenhydrate() {
        return kohlenhydrathe;
    }

    public int getProteine() {
        return proteine;
    }

    public int getVitamine() {
        return vitamine;
    }

    public HashMap<Integer, SkillType> getAbility() {
        return ability;
    }

    public UUID getUUID() {
        return player.getUniqueId();
    }

    public void setKohlenhydrate(int kohlenhydrathe) {
        this.kohlenhydrathe = kohlenhydrathe;
        if(this.kohlenhydrathe < 0)
            this.kohlenhydrathe = 0;
    }

    public void setProteine(int proteine) {
        this.proteine = proteine;
        if(this.proteine < 0)
            this.proteine = 0;
}

    public void setVitamine(int salt) {
        this.vitamine = salt;
        if(this.vitamine < 0)
            this.vitamine = 0;
    }

    public void remove() {
        plugin.removeUser(player.getName());
    }

    public void setSkills(String[] skills) {
        int i = 0;
        for (String a : skills) {
            ability.put(i, SkillType.getSkill(a.toUpperCase()));
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
                for (int i = 0; i <= ability.size(); i++) {
                    if (i >= a && !ability.get(i).getName().equals(skill.getName())) {
                        player.sendMessage("§cDu hast durch den Levelverlust den Skill §e" + ability.get(i).getName() + " §cverloren");
                        ability.remove(i);
                    }
                }

                break;
            }
        }
    }

    public long getFirstTimePlayed() {
        return firstTimePlayed;
    }

    public void setFirstTimePlayed(long firstTimePlayed) {
        this.firstTimePlayed = firstTimePlayed;
    }

    public void addValuesByFood(Food food) {
        int thirst = food.getDurst();
        int kohlenhydrate = food.getKohlenhydrate();
        int proteine = food.getProteine();
        int vitamine = food.getVitamine();
        //Set Durst
        if (getThirst() + thirst > Variables.MAX_THIRST) {
            setThirst(Variables.MAX_THIRST);
        } else setThirst(getThirst() + thirst);
        //Set Kohlenhydrate
        if (getKohlenhydrate() + kohlenhydrate > Variables.MAX_KOHLENHYDRATHE) {
            setKohlenhydrate(Variables.MAX_KOHLENHYDRATHE);
        } else setKohlenhydrate(getKohlenhydrate() + kohlenhydrate);

        if (getProteine() + proteine > Variables.MAX_PROTEIN) {
            setProteine(Variables.MAX_PROTEIN);
        } else setProteine(getProteine() + proteine);
        if (getVitamine() + vitamine > Variables.MAX_VITAMINE) {
            setVitamine(Variables.MAX_VITAMINE);
        } else setVitamine(getVitamine() + vitamine);

        if(food.getMaterial() == Material.COOKIE) {
            if(player.getWalkSpeed() == (float)0.2) {
                player.setWalkSpeed((float) 0.3);
                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                    player.setWalkSpeed((float) 0.2);
                }, 20 * 10);
            }
        } else if(food.getMaterial() == Material.CAKE) {
            if(player.getWalkSpeed() == (float)0.2) {
                player.setWalkSpeed((float) 0.35);
                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                    player.setWalkSpeed((float) 0.2);
                }, 20 * 10);
            }
        }

    }

    public void addToCount(int i) {
        seconds_down = seconds_down - i;
        if (seconds_down <= 0) {
            thirst -= 1;
            kohlenhydrathe -= 1;
            proteine -= 1;
            vitamine -= 1;
            seconds_down = Variables.DOWN;
        }
    }

    public int getState() {
        if(player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR) {
            return 0;
        }
        if (getThirst() <= 5) {
            if (getThirst() == 0)
                return 2;
            else return 1;
        }
        if(getVitamine() <= 5) {
            if(getVitamine() == 0)
                return 2;
            else return 1;
        }
        if(getProteine() <= 5) {
            if(getProteine() == 0)
                return 2;
            else return 1;
        }
        if(getKohlenhydrate() <= 5) {
            if(getKohlenhydrate() == 0)
                return 2;
            else return 1;
        }
        return 0;
    }

    public Player getPlayer() {
        return player;
    }

    public int getBlocksToBreak() {
        return blocksToBreak;
    }

    public void setBlocksToBreak(int blocksToBreak) {
        this.blocksToBreak = blocksToBreak;
    }

    public boolean isFat_active() {
        return fat_active;
    }

    public boolean isKohlen_active() {
        return kohlen_active;
    }

    public boolean isProtein_active() {
        return protein_active;
    }

    public boolean isSalt_active() {
        return salt_active;
    }

    public void setFat_active(boolean fat_active) {
        this.fat_active = fat_active;
    }

    public void setKohlen_active(boolean kohlen_active) {
        this.kohlen_active = kohlen_active;
    }

    public void setSalt_active(boolean salt_active) {
        this.salt_active = salt_active;
    }

    public void setProtein_active(boolean protein_active) {
        this.protein_active = protein_active;
    }

    public void checkAciveValues() {
        if(!isKohlen_active()) {
            if(firstTimePlayed >= Variables.MILLIS_TO_KOHLE) {
                setKohlen_active(true);
            }
        }
        if(!isFat_active()) {
            if(firstTimePlayed >= Variables.MILLIS_TO_FAT) {
                setFat_active(true);
            }
        }
        if(!isProtein_active()) {
            if(firstTimePlayed >= Variables.MILLIS_TO_PROTEIN) {
                setProtein_active(true);
            }
        }
        if(!isSalt_active()) {
            if(firstTimePlayed >= Variables.MILLIS_TO_SALT)
                setSalt_active(true);
        }

    }

    public void fillAll()
    {
        setVitamine(20);
        setThirst(20);
        setProteine(20);
        setKohlenhydrate(20);
    }
}
