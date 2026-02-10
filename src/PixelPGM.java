/**
 * @author Antony, Abdoulaye et Sèdrick
 */
public class PixelPGM extends Pixel {
    private int teinte;

    /**
     * Constructeur pour un pixel gris
     * @param ligne Position Y
     * @param colonne Position X
     * @param teinte Valeur numérique
     */
    PixelPGM(int ligne, int colonne, int teinte) {
        super(ligne, colonne);
        this.teinte = teinte;
    }

    public int getTeinte() {
        return teinte;
    }

    public void setTeinte(int teinte) {
        this.teinte = teinte;
    }
}
