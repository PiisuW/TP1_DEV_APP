public class PixelPPM extends Pixel {
    private int teinte;


    /**
     * Constructeur de PixelPPM avec Argument
     * @param ligne
     * @param colonne
     * @param teinte
     */
    PixelPPM(int ligne, int colonne, int teinte) {
        super(ligne, colonne);
    }

    public void setTeinte(int teinte) {
        this.teinte = teinte;
    }

    public int getTeinte() {
        return teinte;
    }
}
