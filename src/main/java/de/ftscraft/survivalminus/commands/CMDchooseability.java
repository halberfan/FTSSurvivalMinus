package de.ftscraft.survivalminus.commands;

import com.gmail.nossr50.api.ExperienceAPI;
import com.gmail.nossr50.datatypes.skills.AbilityType;
import com.gmail.nossr50.datatypes.skills.SkillType;
import com.gmail.nossr50.mcMMO;
import de.ftscraft.survivalminus.main.Survival;
import de.ftscraft.survivalminus.user.User;
import de.ftscraft.survivalminus.utils.Variables;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDchooseability implements CommandExecutor {

    private Survival plugin;

    public CMDchooseability(Survival plugin) {
        this.plugin = plugin;
        plugin.getCommand("chooseability").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        if (!(cs instanceof Player)) {
            cs.sendMessage("§cDieser Befehl ist nur für Spieler");
            return true;
        }

        Player p = (Player) cs;

        User user = plugin.getUser(p.getName());

        if (user.getAbility().size() == 0) {
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase(Variables.password)) {
                    SkillType t;
                    try {
                        t = SkillType.valueOf(args[1].toUpperCase());
                    } catch (Exception e) {
                        return true;
                    }

                    if (t == SkillType.WOODCUTTING || t == SkillType.MINING || t == SkillType.EXCAVATION || t == SkillType.HERBALISM || t == SkillType.FISHING) {
                        user.getAbility().put(0, t);
                        cs.sendMessage("§cDu hast erfolgreich " + t.getName() + " genommen");
                    } else {
                        cs.sendMessage("§cDu musst eine Sammelfähigkeit nutzen (Holzfäller, Mining, Graben, Ernten oder Fischen)");
                    }

                }
            }
        } else {
            if (args.length == 2) {
                if (!args[0].equalsIgnoreCase(Variables.password)) {
                    return true;
                }

                SkillType a = user.getAbility().get(user.getAbility().size() - 1);
                int alevel = ExperienceAPI.getLevel(p, a.getName());
                if (alevel >= 150) {
                    SkillType b;
                    try {
                        b = SkillType.valueOf(args[1].toUpperCase());
                    } catch (Exception e) {
                        return true;
                    }

                    if(b.getName().equalsIgnoreCase(a.getName())) {
                        cs.sendMessage("§cDiese Fähigkeit hast du schon mal ausgewählt");
                        return true;
                    }

                    user.getAbility().put(user.getAbility().size(), b);
                    cs.sendMessage("§cDu hast erfolgreich "+ b.getName() + " genommen");

                } else cs.sendMessage("§cDu musst bei dem Level was du davor ausgewählt hast ("
                        +user.getAbility().get(user.getAbility().size() - 1).getName()+") Level 150 erreichen");
            }
        }

        return false;
    }

}
