package de.ftscraft.survivalminus.listeners;

import com.gmail.nossr50.datatypes.skills.SkillType;
import com.gmail.nossr50.events.experience.McMMOPlayerLevelDownEvent;
import com.gmail.nossr50.events.experience.McMMOPlayerLevelUpEvent;
import com.gmail.nossr50.events.experience.McMMOPlayerXpGainEvent;
import com.gmail.nossr50.events.party.McMMOPartyXpGainEvent;
import com.gmail.nossr50.events.skills.abilities.McMMOPlayerAbilityActivateEvent;
import com.gmail.nossr50.events.skills.abilities.McMMOPlayerAbilityEvent;
import de.ftscraft.survivalminus.main.Survival;
import de.ftscraft.survivalminus.user.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class McMMOListener implements Listener {

    private Survival plugin;

    public McMMOListener(Survival plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onXpGain(McMMOPlayerXpGainEvent event) {
        User user = plugin.getUser(event.getPlayer().getName());
        boolean b = false;
        for (SkillType a : user.getAbility().values()) {
            if (a.getName().equals(event.getSkill().getName())) {
                b = true;
            }
        }
        if (!b) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onAbilityUse(McMMOPlayerAbilityActivateEvent event) {
        User user = plugin.getUser(event.getPlayer().getName());
        boolean b = false;
        for (SkillType a : user.getAbility().values()) {
            if (a.getName().equals(event.getSkill().getName())) {
                b = true;
            }
        }
        if (!b) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onLevelDown(McMMOPlayerLevelDownEvent event) {
        if(event.getSkillLevel() < 150) {
            User u = plugin.getUser(event.getPlayer());
            u.skillRemove(event.getSkill());
        }
    }

    @EventHandler
    public void onLevelUp(McMMOPlayerLevelUpEvent event) {
        if (event.getSkillLevel() >= 150) {
            User u = plugin.getUser(event.getPlayer().getName());
            if (u.hasSkill(event.getSkill()))
                event.getPlayer().sendMessage("§cDu kannst in Nordwall ein neuen Skill erlernen");
        }
    }

}
