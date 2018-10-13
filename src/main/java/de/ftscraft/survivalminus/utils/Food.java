package de.ftscraft.survivalminus.utils;

public enum Food {

    //Name (Kohlenhydrate, Fette, Proteine, Salze, Durst)

    CLOWNFISH(0, 2, 2, 0, 0),
    OFENKARTOFFEL(3, 0, 0, 0, 0),
    SEETANG(1, 1, 0, 0, 1),
    ROTE_BEETE(3, 0, 0, 0, 0),
    KEKSE(2, 0, 0, 0, 0),
    GEBRATENER_KABELJAU(2, 0, 0, 2, 0),
    GEBRATENER_LACHS(2, 0, 0, 2, 0),
    MELONE(2, 0, 0, 0, 2),
    KAROTTE(2, 0, 0, 0, 0),
    APFEL(2, 0, 0, 0, 2),
    G_APFEL(1, 1, 1, 1, 1),
    OP_APFEL(3, 3, 3, 3, 3),
    BROT(2, 0, 2, 0, 0),
    GEBRATENER_HASE(0, 2, 2, 0, 0),
    ROTE_BEETE_SUPPE(1, 1, 1, 0, 1),
    HUENCHEN(0, 0, 3, 0, 0),
    PILZSUPPE(0, 1, 1, 0, 1),
    HAMMELFLEISCH(0, 0, 3, 0, 0),
    G_KAROTTE(2, 2, 2, 0, 0),
    KUERBIS_KUCHEN(1, 1, 0, 0, 0),
    SCHEWINEFLEISCH(0, 2, 0, 0, 0),
    STEAK(0, 0, 2, 0, 0),
    KANNICHEN_RAGOUT(1, 1, 1, 0, 1),
    KUCHEN(1, 1, 1, 0, 0);  

    private int
            kohlenhydrate,
            fette,
            proteine,
            salz,
            durst;

    Food(int kh, int fett, int protein, int salz, int durst) {
        this.kohlenhydrate = kh;
        this.fette = fett;
        this.proteine = protein;
        this.salz = salz;
        this.durst = durst;
    }

    public int getKohlenhydrate() {
        return kohlenhydrate;
    }

    public int getFette() {
        return fette;
    }

    public int getProteine() {
        return proteine;
    }

    public int getSalz() {
        return salz;
    }

    public int getDurst() {
        return durst;
    }

}
