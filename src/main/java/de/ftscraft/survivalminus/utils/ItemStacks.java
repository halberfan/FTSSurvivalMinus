package de.ftscraft.survivalminus.utils;

import de.ftscraft.survivalminus.main.Survival;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemStacks {

    Survival plugin;

    public ItemStacks(Survival plugin) {
        this.plugin = plugin;
        initItems();
        initCrafting();
    }

    public ItemStack shiv = new ItemStack(Material.GOLD_HOE);

    private void initItems() {

        ItemMeta shivMeta = shiv.getItemMeta();
        shivMeta.setDisplayName("§cShiv");
        shivMeta.setLore(Arrays.asList("§7Ist in Gift getaucht (Opfer erleidet Gifteffekt)", "§7Macht mehr schaden wenn von hinten angegriffen"));
        shiv.setItemMeta(shivMeta);
        shiv.setDurability((short) 1);

    }

    private void initCrafting() {

        ShapedRecipe shivr = new ShapedRecipe(new NamespacedKey(plugin, "SurvivalMinus-SHIV"), shiv);
        shivr.shape("AAA", "AIA", "ASA");
        shivr.setIngredient('A', Material.AIR);
        shivr.setIngredient('I', Material.IRON_INGOT);
        shivr.setIngredient('S', Material.STICK);
        plugin.getServer().addRecipe(shivr);

    }

}
