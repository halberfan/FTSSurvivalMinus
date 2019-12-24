package de.ftscraft.survivalminus.utils;

import de.ftscraft.survivalminus.main.Survival;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.time.temporal.ValueRange;
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
            robe = new ItemStack(Material.LEATHER_CHESTPLATE, 1),
            hammer = new ItemStack(Material.GOLDEN_AXE, 1),
            ablassbrief = new ItemStack(Material.PAPER, 1),
            ueberreste = new ItemStack(Material.ROTTEN_FLESH, 1),
            water = new ItemStack(Material.POTION, 1);

    private void initItems() {

        ItemMeta shivMeta = shiv.getItemMeta();
        shivMeta.setDisplayName("§cDolch");
        shivMeta.setLore(Arrays.asList("§7Töte einen Spieler ohne bemerkt zu werden!", "§7(Keine Todesnachricht im Chat)"));
        shivMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
        shiv.setItemMeta(shivMeta);

        PotionMeta waterm = (PotionMeta) water.getItemMeta();
        waterm.setBasePotionData(new PotionData(PotionType.WATER));
        waterm.setLore(Collections.singletonList("§7Gereinigt"));
        water.setItemMeta(waterm);

        ItemMeta robeMeta = robe.getItemMeta();
        robeMeta.setDisplayName("§4Robe");
        robeMeta.setLore(Arrays.asList("Diese Robe macht dich unsichtbar"));
        robeMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
        robe.setItemMeta(robeMeta);

        ItemMeta hammerMeta = hammer.getItemMeta();
        hammerMeta.setDisplayName("§6Hammer");
        hammerMeta.setLore(Arrays.asList("§cDer Richter kann damit einen Spieler für 5 Minuten muten"));
        hammer.setItemMeta(hammerMeta);

        ItemMeta ablassMeta = ablassbrief.getItemMeta();
        ablassMeta.setDisplayName("§cAblassbrief");
        ablassMeta.setLore(Arrays.asList("§7Dieser Brief wurde vom Papst ausgestellt und vergibt einer Person eine Sünde"));
        ablassbrief.setItemMeta(ablassMeta);

        ItemMeta ueberresteMeta = ueberreste.getItemMeta();
        ueberresteMeta.setDisplayName(Variables.ITEM_UBERRESTE_NAME);
        ueberreste.setItemMeta(ueberresteMeta);
    }

    private void initCrafting() {

        ShapedRecipe shivr = new ShapedRecipe(new NamespacedKey(plugin, "SurvivalMinus-SHIV"), shiv);
        shivr.shape("AIA", "DDD", "ESE");
        shivr.setIngredient('A', Material.AIR);
        shivr.setIngredient('I', Material.IRON_SWORD);
        shivr.setIngredient('D', Material.DIAMOND);
        shivr.setIngredient('E', Material.EMERALD);
        shivr.setIngredient('S', Material.STICK);
        plugin.getServer().addRecipe(shivr);

        ShapelessRecipe waterr = new ShapelessRecipe(new NamespacedKey(plugin, "SurvivalMinus-WATER"), water);
        waterr.addIngredient(Material.POTION);
        waterr.addIngredient(Material.REDSTONE);
        plugin.getServer().addRecipe(waterr);

        ShapedRecipe rober = new ShapedRecipe(new NamespacedKey(plugin, "SurvivalMinus-ROBE"), robe);
        rober.shape("LEL", "WEW", "LEL");
        rober.setIngredient('E', Material.EMERALD);
        rober.setIngredient('L', Material.LEATHER);
        rober.setIngredient('W', Material.WHITE_WOOL);
        plugin.getServer().addRecipe(rober);

        ShapedRecipe hammerr = new ShapedRecipe(new NamespacedKey(plugin, "SurvivalMinus-HAMMER"), hammer);
        hammerr.shape("BBA", "BCA", "ACA");
        hammerr.setIngredient('A', Material.AIR);
        hammerr.setIngredient('B', Material.GOLD_NUGGET);
        hammerr.setIngredient('C', Material.BLAZE_ROD);
        plugin.getServer().addRecipe(hammerr);

        ShapedRecipe ablassr = new ShapedRecipe(new NamespacedKey(plugin, "SurvivalMinus-ABLASS"), ablassbrief);
        ablassr.shape("ABA","BCB","ABA");
        ablassr.setIngredient('A', Material.EMERALD);
        ablassr.setIngredient('B', Material.GOLD_NUGGET);
        ablassr.setIngredient('C', Material.PAPER);
        plugin.getServer().addRecipe(ablassr);

        ShapelessRecipe ueberrester = new ShapelessRecipe(new NamespacedKey(plugin, "SurvivalMinus-UBERRESTE"), ueberreste);
        ueberrester.addIngredient(9, Material.ROTTEN_FLESH);
        plugin.getServer().addRecipe(ueberrester);

    }

    public ItemStack getWater()
    {
        return water.clone();
    }
}
