package de.ftscraft.survivalminus.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Food {

    //Name Kohlenhydrate, Proteine, Vitamine, Durst, Material - Material ist null bei speziellen Items

    OFENKARTOFFEL(8, 0, 0, 0, Material.BAKED_POTATO),
    KARTOFFEL(4, 0, 0, 0, Material.POTATO),
    SEETANG(0, 5, -2, 6, Material.DRIED_KELP),
    ROTE_BEETE(0, 0, 5, 0, Material.BEETROOT),
    KEKSE(6, 0, 0, 0, Material.COOKIE),
    GEBRATENER_KABELJAU(0, 8, 0, 0, Material.COOKED_SALMON),
    GEBRATENER_LACHS(0, 8, 0, 0, Material.COOKED_COD),
    MELONE(0, 0, 5, 6, Material.MELON_SLICE),
    KAROTTE(0, 0, 6, 0, Material.CARROT),
    APFEL(0, 0, 10, 0, Material.APPLE),
    G_APFEL(8, 8, 25, 4, Material.GOLDEN_APPLE),
    OP_APFEL(20, 20, 20, 20, Material.ENCHANTED_GOLDEN_APPLE), //
    BROT(12, 0, 0, -2, Material.BREAD),
    GEBRATENER_HASE(0, 12, 0, 0, Material.COOKED_RABBIT),
    ROTE_BEETE_SUPPE(0, 0, 25, 5, Material.BEETROOT_SOUP),
    HUEHNCHEN(0, 12, 0, 0, Material.COOKED_CHICKEN),
    PILZSUPPE(10, 0, 8, 8, Material.MUSHROOM_STEW),
    HAMMELFLEISCH(0, 12, 0, 0, Material.COOKED_MUTTON),
    G_KAROTTE(0, 0, 15, 0, Material.GOLDEN_CARROT),
    KUERBIS_KUCHEN(12, 0, 0, 0, Material.PUMPKIN_PIE),
    SCHEWINEFLEISCH(0, 16, 0, 0, Material.COOKED_PORKCHOP),
    STEAK(0, 10, 0, 0, Material.COOKED_BEEF),
    KANNICHEN_RAGOUT(8, 18, 8, 8, Material.RABBIT_STEW),
    CHORUSFRUCH(-4, 0, 5, -8, Material.CHORUS_FRUIT),
    GIFTIG_KARTOFFEN(4, 0, 0, 0, Material.POISONOUS_POTATO),
    HONIG(-2, 0, 0, 8, Material.HONEY_BOTTLE),
    LEUCHTBEERE(0, 0, 5, 0, Material.GLOW_BERRIES),
    SELTSAME_SUPPE(5, 5, 5, 5, Material.SUSPICIOUS_STEW),
    SUESSBEEREN(0, 0, 4, 0, Material.SWEET_BERRIES),
    SPINNENAUGE(-2, 3, -2, -2, Material.SPIDER_EYE),
    ZOMBIE_FLEISCH(-2, 3, -2, -2, Material.ROTTEN_FLESH),
    KUGELFISCH(0, 2, 0, 4, Material.PUFFERFISH),
    CLOWNFISCH(0, 2, 0, 0, Material.TROPICAL_FISH),
    R_KABELJAU(0, 4, 0, 0, Material.COD),
    R_LACHS(0, 4, 0, 0, Material.SALMON),
    R_HAMMEL(0, 4, 0, 0, Material.MUTTON),
    R_HASE(0, 4, 0, 0, Material.RABBIT),
    R_RIND(0, 4, 0, 0, Material.BEEF),
    R_SCHWEIN(0, 4, 0, 0, Material.PORKCHOP),
    //FTS-ESSEN
    UBERRESTE(0, 0, 0, 0, null),
    SUSSERFISCH(0, 12, 0, 0, null),
    HONIGMELONENSAFT(0, 0, 0, 20, null),
    MARMELADE(16, 0, 8, 0, null);

    private final int
            kohlenhydrate,
            vitamine,
            proteine,
            durst;
    private final Material material;

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
                //wenn es nicht übereinstimmt, wird das nächste food überprüft
                continue;
            }
            if (a.getMaterial() == mat)
                food = a;
        }

        return food;
    }

}
