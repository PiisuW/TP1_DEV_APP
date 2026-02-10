/**
 * Classe abstraite représentant un point/pixel dans une matrice d'image
 * @author Antony, Abdoulaye et Sèdrick
 */
public abstract class Pixel {
    protected int ligne;
    protected int colonne;

    /**
     * Constructeur de Pixel avec argument
     * @param ligne Position verticale
     * @param colonne Position horizontale
     */
    Pixel(int ligne, int colonne) {
        this.colonne = colonne;
        this.ligne = ligne;
    }


    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }
}
