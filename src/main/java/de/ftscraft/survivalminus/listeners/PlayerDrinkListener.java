package de.ftscraft.survivalminus.listeners;

import de.ftscraft.survivalminus.main.Survival;
import de.ftscraft.survivalminus.user.User;
import de.ftscraft.survivalminus.utils.Food;
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
        User u = plugin.getUser(event.getPlayer().getName());
        if(event.getItem().getType() == Material.POTION){
            ItemStack is = event.getItem();
            PotionMeta im = (PotionMeta) is.getItemMeta();
            if(im.getBasePotionData().getType() == PotionType.WATER) {
                if(im.getLore() == null) {
                    event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20 * 3, 2));
                } else {
                    u.setThirst(u.getThirst() + Variables.BOTTLE_THIRST);
                    if(u.getThirst() > Variables.MAX_THIRST)
                        u.setThirst(Variables.MAX_THIRST);
                }
            }
        } else if(event.getItem().getType() == Material.COOKIE) {
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Variables.COOKIE_SPEED_TIME * 20, 1));
            u.addValuesByFood(Food.KEKSE);
        } else if(event.getItem().getType() == Material.TROPICAL_FISH && event.getItem().getData().getData() == 2) {
            u.addValuesByFood(Food.CLOWNFISH);
        } else if(event.getItem().getType() == Material.BAKED_POTATO) {
            u.addValuesByFood(Food.OFENKARTOFFEL);
        } else if(event.getItem().getType() == Material.BEETROOT) {
            u.addValuesByFood(Food.ROTE_BEETE);
        } else if(event.getItem().getType() == Material.COOKED_SALMON) {
            u.addValuesByFood(Food.GEBRATENER_LACHS);
        } else if(event.getItem().getType() == Material.MELON) {
            u.addValuesByFood(Food.MELONE);
        } else if(event.getItem().getType() == Material.CARROT) {
            u.addValuesByFood(Food.KAROTTE);
        } else if(event.getItem().getType() == Material.APPLE) {
            u.addValuesByFood(Food.APFEL);
        } else if(event.getItem().getType() == Material.GOLDEN_APPLE) {
            if(event.getItem().getData().getData() == 0)
                u.addValuesByFood(Food.G_APFEL);
            else if(event.getItem().getData().getData() == 1)
                u.addValuesByFood(Food.OP_APFEL);
        } else if(event.getItem().getType() == Material.BREAD) {
            u.addValuesByFood(Food.BROT);
        } else if(event.getItem().getType() == Material.COOKED_RABBIT) {
            u.addValuesByFood(Food.GEBRATENER_HASE);
        } else if(event.getItem().getType() == Material.BEETROOT_SOUP) {
            u.addValuesByFood(Food.ROTE_BEETE_SUPPE);
        } else if(event.getItem().getType() == Material.COOKED_CHICKEN) {
            u.addValuesByFood(Food.HUENCHEN);
        } else if(event.getItem().getType() == Material.MUSHROOM_STEW) {
            u.addValuesByFood(Food.PILZSUPPE);
        } else if(event.getItem().getType() == Material.COOKED_MUTTON) {
            u.addValuesByFood(Food.HAMMELFLEISCH);
        } else if(event.getItem().getType() == Material.GOLDEN_CARROT) {
            u.addValuesByFood(Food.G_KAROTTE);
        } else if(event.getItem().getType() == Material.PUMPKIN_PIE) {
            u.addValuesByFood(Food.KUERBIS_KUCHEN);
        } else if(event.getItem().getType() == Material.COOKED_PORKCHOP) {
            u.addValuesByFood(Food.SCHEWINEFLEISCH);
        } else if(event.getItem().getType() == Material.COOKED_BEEF) {
            u.addValuesByFood(Food.STEAK);
        } else if(event.getItem().getType() == Material.RABBIT_STEW) {
            u.addValuesByFood(Food.KANNICHEN_RAGOUT);
        } else if(event.getItem().getType() == Material.CAKE) {
            u.addValuesByFood(Food.KUCHEN);
        }
    }

}
