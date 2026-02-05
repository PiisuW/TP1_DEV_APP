public class PixelPGM extends Pixel {
    private int rouge;
    private int vert;
    private int bleu;


    /**
     * Constructeur de PixelPGM avec Attribut
     * @param ligne
     * @param colonne
     * @param rouge
     * @param vert
     * @param bleu
     */
    PixelPGM(int ligne, int colonne, int rouge, int vert, int bleu) {
        super(ligne, colonne);
    }

    public int getRouge() {
        return rouge;
    }

    public int getVert() {
        return vert;
    }

    public int getBleu() {
        return bleu;
    }

    public void setRouge(int rouge) {
        this.rouge = rouge;
    }

    public void setVert(int vert) {
        this.vert = vert;
    }

    public void setBleu(int bleu) {
        this.bleu = bleu;
    }
}
