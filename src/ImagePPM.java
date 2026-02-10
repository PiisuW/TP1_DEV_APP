/**
 * Implémentation de la bibliothèque pour les images en couleurs
 * @author Antony, Abdoulaye et Sèdrick
 */
public class ImagePPM extends Image {

    private int largeur;
    private int hauteur;
    private int valeurMax;

    private PixelPPM[][] matrice;

    /**
     * Constructeur d'ImagePPM avec attribut;
     * @param largeur La largeur de l'image en pixels
     * @param hauteur La hauteur de l'image en pixels
     * @param valeurMax La valeur max autorisé pour un pixel (65535)
     */
    ImagePPM(int largeur, int hauteur, int valeurMax) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.valeurMax = valeurMax;
        this.matrice = new PixelPPM[hauteur][largeur];
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
    public void copier(Image copie) {

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
     * Réduit l'image en calculant la moyenne de rouge, vert et bleu
     * @return Une nouvelle image réduite par la moyenne
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
}
