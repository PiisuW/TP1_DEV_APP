import exceptions.ExceptionImagesIdentiques;

/**
 * Implémentation de la bibliothèque pour image avec tons de gris
 * @author Antony, Abdoulaye et Sèdrick
 */
public class ImagePGM extends Image {

    private int largeur;
    private int hauteur;
    private int valeurMax;

    private PixelPGM[][] matrice;

    /**
     * Constructeur d'ImagePGM avec attribut;
     * @param largeur La largeur de l'image en pixels
     * @param hauteur La hauteur de l'image en pixels
     * @param valeurMax La valeur max autorisé pour un pixel (65535)
     */
    ImagePGM(int largeur, int hauteur, int valeurMax) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.valeurMax = valeurMax;
        this.matrice = new PixelPGM[hauteur][largeur];
    }

   @Override public int getLargeur() {
        return largeur;
    }

   @Override public int getHauteur() {
        return hauteur;
    }

   @Override public int getValeurMax() {
        return valeurMax;
    }

    //Fichier

    @Override
    public void lire(String fichier) {

    }

    @Override
    public void ecrire(String fichier) {

    }

    @Override
    public void copier(Image copie1, Image copie2)
            throws ExceptionImagesIdentiques {


    }

    @Override
    public void eclaircir_noircir(int v) {

    }

    @Override
    public void pivoter90(){

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sont_identiques(Image autre){
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image extraire(int p1, int c1, int p2, int c2){
        return null;
    }

    /**
     * Réduit la taille de l'image en calculant moyenne des teintes de gris
     * @return Une nouvelle ImagePGM réduite
     */
    @Override
    public Image reduire(){
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pixel couleur_preponderante(){
        return null;
    }

    public PixelPGM[][] getMatrice() {
        return matrice;
    }

    public void setMatrice(PixelPGM[][] matrice) {
        this.matrice = matrice;
    }
}
