package de.ftscraft.survivalminus.commands;

import de.ftscraft.survivalminus.main.Survival;
import de.ftscraft.survivalminus.user.User;
import de.ftscraft.survivalminus.utils.Variables;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDgesundheit implements CommandExecutor {

    private Survival plugin;

    public CMDgesundheit(Survival plugin) {
        this.plugin = plugin;
        plugin.getCommand("gesundheit").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        if(!(cs instanceof Player)) {
            return true;
        }

        User u = plugin.getUser(cs.getName());

        cs.sendMessage("§c---§6Deine Gesundheit§c---");
        cs.sendMessage("§bDurst: §c"+u.getThirst()+"§7/§c"+ Variables.MAX_THIRST);
        cs.sendMessage("§bKohlenhydrate: §c"+u.getKohlenhydrathe() + "§7/§c"+ Variables.MAX_KOHLENHYDRATHE);
        cs.sendMessage("§bZucker: §c"+u.getSuager()+"§7/§c"+Variables.MAX_SUGAR);
        cs.sendMessage("§bProteine: §c"+u.getProteine()+ "§7/§c"+Variables.MAX_PROTEIN);

        return false;
    }


}
