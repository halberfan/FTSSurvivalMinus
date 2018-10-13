package de.ftscraft.survivalminus.utils;

import de.ftscraft.survivalminus.main.Survival;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.util.Arrays;
import java.util.Collections;

public class ItemStacks {

    Survival plugin;

    public ItemStacks(Survival plugin) {
        this.plugin = plugin;
        initItems();
        initCrafting();
    }

    private ItemStack shiv = new ItemStack(Material.IRON_SWORD),
            medikit = new ItemStack(Material.GOLDEN_HOE),
            robe = new ItemStack(Material.ELYTRA, 1),
            water = new ItemStack(Material.POTION, 1);

    private void initItems() {

        ItemMeta shivMeta = shiv.getItemMeta();
        shivMeta.setDisplayName("§cDolch");
        //shivMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        shiv.setItemMeta(shivMeta);
        //shiv.setDurability((short) 1);

        ItemMeta medMeta = medikit.getItemMeta();
        medMeta.setDisplayName("§cMedikit");
        medMeta.setLore(Arrays.asList("§7Heilt dich innerhalb von 7 Sekunden.", "§7Macht dich während der Zeit langsamer"));
        medMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        medMeta.setUnbreakable(true);
        medikit.setItemMeta(medMeta);
        medikit.setDurability(((short) 2));

        PotionMeta waterm = (PotionMeta) water.getItemMeta();
        waterm.setBasePotionData(new PotionData(PotionType.WATER));
        waterm.setLore(Collections.singletonList("§7Gereinigt"));
        water.setItemMeta(waterm);

        ItemMeta robeMeta = robe.getItemMeta();
        robeMeta.setDisplayName("§4Robe");
        robeMeta.setLore(Arrays.asList("Diese Robe macht dich unsichtbar"));
        robe.setItemMeta(robeMeta);
    }

    private void initCrafting() {

        ShapedRecipe shivr = new ShapedRecipe(new NamespacedKey(plugin, "SurvivalMinus-SHIV"), shiv);
        shivr.shape("ESE", "IIA", "AAI");
        shivr.setIngredient('A', Material.AIR);
        shivr.setIngredient('I', Material.IRON_INGOT);
        shivr.setIngredient('S', Material.STICK);
        shivr.setIngredient('E', Material.EMERALD);
        plugin.getServer().addRecipe(shivr);

        ShapedRecipe medikitr = new ShapedRecipe(new NamespacedKey(plugin, "SurvivalMinus-MEDIKIT"), medikit);
        medikitr.shape("GGG", "GAG", "III");
        medikitr.setIngredient('G', Material.GOLD_INGOT);
        medikitr.setIngredient('I', Material.IRON_INGOT);
        medikitr.setIngredient('A', Material.APPLE);
        plugin.getServer().addRecipe(medikitr);

        ShapelessRecipe waterr = new ShapelessRecipe(new NamespacedKey(plugin, "SurvivalMinus-WATER"), water);
        waterr.addIngredient(Material.POTION);
        waterr.addIngredient(Material.REDSTONE);
        plugin.getServer().addRecipe(waterr);

        ShapedRecipe rober = new ShapedRecipe(new NamespacedKey(plugin, "SurvivalMinus-ROBE"), robe);
        rober.shape("LEL", "LYL", "LEL");
        rober.setIngredient('L', Material.LEATHER);
        rober.setIngredient('E', Material.EMERALD);
        rober.setIngredient('Y', Material.ELYTRA);
        plugin.getServer().addRecipe(rober);

    }

}
