package de.ftscraft.survivalminus.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Food {

    //Name Kohlenhydrate, Proteine, Vitamine, Durst, Material - Material ist null bei speziellen Items

    /*OFENKARTOFFEL(1, 0, 0, 0, Material.BAKED_POTATO),
    KARTOFFEL(1, 0, 0, 0, Material.POTATO),
    SEETANG(0, -1, 0, 2, Material.DRIED_KELP),
    ROTE_BEETE(0, 0, 2, 0, Material.BEETROOT),
    KEKSE(0, 0, 0, 0, Material.COOKIE),
    GEBRATENER_KABELJAU(0, 1, 0, -1, Material.COOKED_SALMON),
    GEBRATENER_LACHS(0, 1, 0, -1, Material.COOKED_COD),
    MELONE(-1, 0, 1, 1, Material.MELON_SLICE),
    KAROTTE(0, 0, 1, 0, Material.CARROT),
    APFEL(0, 0, 4, 0, Material.APPLE),
    G_APFEL(8, 8, 8, 8, Material.GOLDEN_APPLE),
    OP_APFEL(20, 20, 20, 20, Material.ENCHANTED_GOLDEN_APPLE),
    BROT(2, 0, 0, 0, Material.BREAD),
    GEBRATENER_HASE(0, 2, 0, 0, Material.COOKED_RABBIT),
    ROTE_BEETE_SUPPE(3, 3, 3, 3, Material.BEETROOT_SOUP),
    HUEHNCHEN(0, 1, 0, 0, Material.COOKED_CHICKEN),
    PILZSUPPE(2, -2, 2, 2, Material.MUSHROOM_STEW),
    HAMMELFLEISCH(0, 1, 0, 0, Material.COOKED_MUTTON),
    G_KAROTTE(3, 3, 3, 3, Material.GOLDEN_CARROT),
    KUERBIS_KUCHEN(1, 0, 0, 0, Material.PUMPKIN_PIE),
    SCHEWINEFLEISCH(0, 2, 0, 0, Material.COOKED_PORKCHOP),
    STEAK(0, 1, 0, 0, Material.COOKED_BEEF),
    KANNICHEN_RAGOUT(3, 3, 3, 3, Material.RABBIT_STEW),
    UBERRESTE(0, 0, 0, 0, Material.ROTTEN_FLESH),
    KUCHEN(4, 4, 4, 4, Material.CAKE);*/

    OFENKARTOFFEL(1, 0, 0, 0, Material.BAKED_POTATO),
    KARTOFFEL(0, 0, 0, 0, Material.POTATO),
    SEETANG(0, 0, 0, 1, Material.DRIED_KELP),
    ROTE_BEETE(0, 0, 2, 0, Material.BEETROOT),
    KEKSE(1, 0, 0, 0, Material.COOKIE),
    GEBRATENER_KABELJAU(0, 1, 0, -1, Material.COOKED_SALMON),
    GEBRATENER_LACHS(0, 1, 0, -1, Material.COOKED_COD),
    MELONE(-1, 0, 1, 1, Material.MELON_SLICE),
    KAROTTE(0, 0, 1, 0, Material.CARROT),
    APFEL(0, 0, 4, 0, Material.APPLE),
    G_APFEL(8, 8, 8, 8, Material.GOLDEN_APPLE),
    OP_APFEL(20, 20, 20, 20, Material.ENCHANTED_GOLDEN_APPLE),
    BROT(2, 0, 0, 0, Material.BREAD),
    GEBRATENER_HASE(0, 1, 0, 0, Material.COOKED_RABBIT),
    ROTE_BEETE_SUPPE(0, 0, 15, 0, Material.BEETROOT_SOUP),
    HUEHNCHEN(0, 1, 0, 0, Material.COOKED_CHICKEN),
    PILZSUPPE(0, 0, 0, 0, Material.MUSHROOM_STEW),
    HAMMELFLEISCH(0, 1, 0, 0, Material.COOKED_MUTTON),
    G_KAROTTE(0, 0, 0, 0, Material.GOLDEN_CARROT),
    KUERBIS_KUCHEN(1, 0, 1, 0, Material.PUMPKIN_PIE),
    SCHEWINEFLEISCH(0, 3, 0, 0, Material.COOKED_PORKCHOP),
    STEAK(0, 1, 0, 0, Material.COOKED_BEEF),
    KANNICHEN_RAGOUT(0, 0, 0, 0, Material.RABBIT_STEW),
    UBERRESTE(0, 0, 0, 0, null),
    SUSSERFISCH(0, 2, 0, 0, null),
    HONIGMELONENSAFT(0, 0, 2, 2, null),
    MARMELADE(3, 0, 0, 0, null),
    KUCHEN(0, 0, 0, 0, Material.CAKE);

    private int
            kohlenhydrate,
            vitamine,
            proteine,
            durst;
    private Material material;

    Food(int kh, int protein, int vitamin, int durst, Material mat) {
        this.kohlenhydrate = kh;
        this.vitamine = vitamin;
        this.proteine = protein;
        this.durst = durst;
        this.material = mat;
    }

    public int getKohlenhydrate() {
        return kohlenhydrate;
    }

    public int getVitamine() {
        return vitamine;
    }

    public int getProteine() {
        return proteine;
    }

    public int getDurst() {
        return durst;
    }

    public Material getMaterial() {
        return material;
    }

    public static Food getFoodByMaterial(ItemStack is) {
        Material mat = is.getType();
        //Variable als Zwischenspeicher
        //Wenn er kein spezielles Food erkennt, wird er den Zwischenspeicher returnen
        Food food = null;
        for (Food a : Food.values()) {
            //Wenn das Food ein besonderes Essen ist (mat == null) wird geschaut, ob das Item damit übereinstimmt
            if (a.getMaterial() == null) {
                if (is.getItemMeta() != null) {
                    if (is.getItemMeta().getDisplayName() != null) {
                        switch (a) {
                            case UBERRESTE:
                                if (is.getItemMeta().getDisplayName().equalsIgnoreCase(Variables.ITEM_UBERRESTE_NAME)) {
                                    return a;
                                }
                                break;
                            case SUSSERFISCH:
                                if (is.getItemMeta().getDisplayName().equalsIgnoreCase(Variables.ITEM_SUSSERFISCH_NAME)) {
                                    return a;
                                }
                                break;
                            case HONIGMELONENSAFT:
                                if (is.getItemMeta().getDisplayName().equalsIgnoreCase(Variables.ITEM_HONIGMELONENSAFT_NAME)) {
                                    return a;
                                }
                                break;
                            case MARMELADE:
                                if (is.getItemMeta().getDisplayName().equalsIgnoreCase(Variables.ITEM_MARMELADE_NAME)) {
                                    return a;
                                }
                                break;

                        }
                    }
                }
                //wenn es nicht übereinstimmt, wird das nächste food überprüft
                continue;
            }
            if (a.getMaterial() == mat)
                food = a;
        }

        return food;
    }

}
