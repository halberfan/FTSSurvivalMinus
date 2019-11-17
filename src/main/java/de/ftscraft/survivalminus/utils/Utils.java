package de.ftscraft.survivalminus.utils;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;

import java.util.Random;

public class Utils {

    public static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }

    public static void abortEventCauseNoSkill(Event event) {

        if (event instanceof Cancellable) {
            ((Cancellable) event).setCancelled(true);
            if (event instanceof InventoryEvent)
                ((InventoryEvent) event).getViewers().get(0).sendMessage("§cDu kannst dieses Item nicht craften weil du es nicht gelernt hast. Erfahre mehr hier: §5https://ftscraft.de/mcmmo/");
        }
        if (event instanceof InventoryClickEvent) {
            InventoryClickEvent e = (InventoryClickEvent) event;
            if (e.getInventory() instanceof AnvilInventory) {
                int level = ((AnvilInventory) e.getInventory()).getRepairCost();
                ((Player) e.getWhoClicked()).giveExp(level);
            }
        }
    }

    public static int getRandomNumber(int max, int min) {

        return min + (int) (Math.random() * ((max - min) + 1));

    }

}
