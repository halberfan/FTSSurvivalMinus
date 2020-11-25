package de.ftscraft.survivalminus.listeners;

import de.ftscraft.survivalminus.main.Survival;
import de.ftscraft.survivalminus.user.User;
import de.ftscraft.survivalminus.utils.Food;
import de.ftscraft.survivalminus.utils.Utils;
import de.ftscraft.survivalminus.utils.Variables;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
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

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDrink(PlayerItemConsumeEvent event) {
        User u = plugin.getUser(event.getPlayer().getName());
        if (isItGereinigtesWater(event.getItem())) {

            u.setThirst(u.getThirst() + Variables.BOTTLE_THIRST);
            if (u.getThirst() > Variables.MAX_THIRST)
                u.setThirst(Variables.MAX_THIRST);



            int rdm = Utils.getRandomNumber(49, 0);
            if (rdm == 0 && isItGereinigtesWater(event.getItem())) {
                Player p = event.getPlayer();
                event.setItem(new ItemStack(Material.AIR));
                p.playSound(p.getLocation(), Sound.ENTITY_SPLASH_POTION_BREAK, 1, 1);
                p.sendMessage("§eDu hast deine Flasche fallen lassen! Sie ist in tausend Einzelteile zersprungen.");
            }

        }

        Food f = Food.getFoodByMaterial(event.getItem());

        if (f != null) {
            u.addValuesByFood(f);
        }

    }

    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent event) {

        if(event.getItem() == null)
            return;

        if(isItGereinigtesWater(event.getItem())) {
            event.setCancelled(true);
        }

    }


    private boolean isItGereinigtesWater(ItemStack itemStack) {

        return itemStack.isSimilar(plugin.getItemStacks().getWater());

    }

}
