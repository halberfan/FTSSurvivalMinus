package de.ftscraft.survivalminus.listeners;

import de.ftscraft.survivalminus.main.Survival;
import de.ftscraft.survivalminus.user.User;
import de.ftscraft.survivalminus.utils.Variables;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class PlayerDrinkListener implements Listener {

    private Survival plugin;

    public PlayerDrinkListener(Survival plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onDrink(PlayerItemConsumeEvent event) {
        if(event.getItem().getType() == Material.POTION){
            ItemStack is = event.getItem();
            PotionMeta im = (PotionMeta) is.getItemMeta();
            if(im.getBasePotionData().getType() == PotionType.WATER) {
                if(im.getLore() == null) {
                    event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20 * 3, 2));
                } else {
                    User u = plugin.getUser(event.getPlayer());
                    u.setThirst(u.getThirst() + Variables.BOTTLE_THIRST);
                    if(u.getThirst() > Variables.MAX_THIRST)
                        u.setThirst(Variables.MAX_THIRST);
                }
            }
        } else if(event.getItem().getType() == Material.COOKIE) {
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Variables.COOKIE_SPEED_TIME * 20, 1));
        }
    }

}
