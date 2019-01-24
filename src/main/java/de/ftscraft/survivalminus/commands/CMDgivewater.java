package de.ftscraft.survivalminus.commands;

import de.ftscraft.survivalminus.main.Survival;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CMDgivewater implements CommandExecutor {


    private Survival plugin;

    public CMDgivewater(Survival plugin)
    {
        this.plugin = plugin;
        plugin.getCommand("givewater").setExecutor(this::onCommand);
    }

    @Override
    public boolean onCommand(CommandSender cs, Command command, String label, String[] args)
    {

        if (cs.hasPermission("survivalminus.water")) {

            if (args.length == 1) {

                Player t = Bukkit.getPlayer(args[0]);

                if (t == null) {
                    cs.sendMessage("§cDer Spieler wurde nicht gefunden");
                    return true;
                }

                t.getInventory().addItem(plugin.getItemStacks().getWater());

                cs.sendMessage("§cDer Spieler hat das Wasser erhalten");

            }
            else if (args.length == 2) {

                Player t = Bukkit.getPlayer(args[0]);

                if (t == null) {
                    cs.sendMessage("§cDer Spieler wurde nicht gefunden");
                    return true;
                }

                int amount;

                try {
                    amount = Integer.valueOf(args[1]);
                }catch (NumberFormatException e) {
                    cs.sendMessage("§cKeine Richtige Nummer!");
                    return true;
                }

                ItemStack is = plugin.getItemStacks().getWater();
                is.setAmount(amount);

                t.getInventory().addItem(is);

                cs.sendMessage("§cDer Spieler hat das Wasser erhalten");


            }

        }

        return false;
    }
}
