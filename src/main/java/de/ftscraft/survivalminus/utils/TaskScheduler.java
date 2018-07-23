package de.ftscraft.survivalminus.utils;

import de.ftscraft.survivalminus.main.Survival;
import de.ftscraft.survivalminus.user.User;

public class TaskScheduler implements Runnable {

    private Survival plugin;
    private int timeSeconds = 0;

    public TaskScheduler(Survival plugin) {
        this.plugin = plugin;
        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, this, 1, 1);
    }

    public void run() {

        timeSeconds++;

        if(timeSeconds == 20) {
            for (User a : plugin.getAllUser()) {
                a.setSeconds_thirst(a.getSeconds_thirst() - 1);
            }
            timeSeconds = 0;
        }

    }

}
