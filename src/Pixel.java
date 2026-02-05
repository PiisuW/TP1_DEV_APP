public abstract class Pixel {
    protected int ligne;
    protected int colonne;

    /**
     * Constructeur de Pixel avec argument
     * @param ligne
     * @param colonne
     */
    Pixel(int ligne, int colonne) {

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
