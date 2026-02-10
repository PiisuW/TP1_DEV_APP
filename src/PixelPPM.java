/**
 * @author Antony, Abdoulaye et Sèdrick
 */
public class PixelPPM extends Pixel {
    private int rouge;
    private int vert;
    private int bleu;


    /**
     * Constructeur pour un pixel couleur
     * @param ligne Position Y
     * @param colonne Position X
     * @param rouge Intensité du rouge
     * @param vert Intensité du vert
     * @param bleu Intensité du bleu
     */
    PixelPPM(int ligne, int colonne, int rouge, int vert, int bleu) {
        super(ligne, colonne);
        this.rouge = rouge;
        this.vert = vert;
        this.bleu = bleu;
    }

    public int getRouge() {
        return rouge;
    }

    public void setRouge(int rouge) {
        this.rouge = rouge;
    }

    public int getVert() {
        return vert;
    }

    public void setVert(int vert) {
        this.vert = vert;
    }

    public int getBleu() {
        return bleu;
    }

    public void setBleu(int bleu) {
        this.bleu = bleu;
    }
}
