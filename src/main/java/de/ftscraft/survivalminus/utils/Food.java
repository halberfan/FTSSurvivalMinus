package de.ftscraft.survivalminus.utils;

import org.bukkit.Material;

public enum Food {

    //Name (Kohlenhydrate, Proteine, Vitamine, Durst)

    OFENKARTOFFEL(3, 0, 0, 0, Material.BAKED_POTATO),
    KARTOFFEL(1,0,0,0, Material.POTATO),
    SEETANG(0, 0, 4, 0, Material.DRIED_KELP),
    ROTE_BEETE(0, 0, 2, 0, Material.BEETROOT),
    KEKSE(0, 0, 0, 0, Material.COOKIE),
    GEBRATENER_KABELJAU(0, 3, 0, 0, Material.COOKED_SALMON),
    GEBRATENER_LACHS(0, 3, 0, 0, Material.COOKED_COD),
    MELONE(0, 0, 1, 1, Material.MELON_SLICE),
    KAROTTE(0, 0, 3, 0, Material.CARROT),
    APFEL(0, 0, 3, 0, Material.APPLE),
    G_APFEL(8, 8, 8, 8, Material.GOLDEN_APPLE),
    OP_APFEL(20, 20, 20, 20, Material.ENCHANTED_GOLDEN_APPLE),
    BROT(2, 0, 0, 0, Material.BREAD),
    GEBRATENER_HASE(0, 3, 0, 0, Material.COOKED_RABBIT),
    ROTE_BEETE_SUPPE(3, 3, 3, 3, Material.BEETROOT_SOUP),
    HUEHNCHEN(0, 3, 0, 0, Material.COOKED_CHICKEN),
    PILZSUPPE(2, 2, 2, 2, Material.MUSHROOM_STEM),
    HAMMELFLEISCH(0, 3, 0, 0, Material.COOKED_MUTTON),
    G_KAROTTE(3, 3, 3, 3, Material.GOLDEN_CARROT),
    KUERBIS_KUCHEN(4, 4, 4, 4, Material.PUMPKIN_PIE),
    SCHEWINEFLEISCH(0, 3, 0, 0, Material.COOKED_PORKCHOP),
    STEAK(0, 3, 0, 0, Material.COOKED_BEEF),
    KANNICHEN_RAGOUT(3, 3, 3, 3, Material.RABBIT_STEW),
    KUCHEN(4, 4, 4, 4, Material.CAKE);

    private int
            kohlenhydrate,
            vitamine,
            proteine,
            durst;
    private Material material;

    Food(int kh, int protein, int vitamin, int durst, Material mat)
    {
        this.kohlenhydrate = kh;
        this.vitamine = vitamin;
        this.proteine = protein;
        this.durst = durst;
        this.material = mat;
    }

    public int getKohlenhydrate()
    {
        return kohlenhydrate;
    }

    public int getVitamine()
    {
        return vitamine;
    }

    public int getProteine()
    {
        return proteine;
    }

    public int getDurst()
    {
        return durst;
    }

    public Material getMaterial()
    {
        return material;
    }

    public static Food getFoodByMaterial(Material mat) {
        for(Food a : Food.values()) {
            if(a.getMaterial() == mat)
                return a;
        }
        return null;
    }

}
