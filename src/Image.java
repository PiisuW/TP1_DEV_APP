import exceptions.ExceptionEcritureImage;
import exceptions.ExceptionImagesIdentiques;
import exceptions.ExceptionLectureImage;

/**
 * Classe abstraite bibliothèque de référence pour les fonctions de ImagePGM et ImagePPM
 * @author Antony, Abdoulaye et Sèdrick
 */
public abstract class Image {
    // Constante
    public static final int LIMITE_LIGNE_ASCII = 70;
    public static final int MAX_VALEUR_LIMITE = 65535;

    // Fichier
    /**
     * Charge les données d'un fichier PGM ou PPM dans l'objet Image
     * @param fichier le chemin vers le fichier à lire
     */
    public abstract void lire(String fichier) throws ExceptionLectureImage;

    /**
     * Sauvegarde l'image actuelle dans un fichier au format ASCII
     * @param fichier Le nom du fichier de destination
     */
    public abstract void ecrire(String fichier) throws ExceptionEcritureImage;

    // Traitement
    /**
     * Copie l'image pour en faire une deuxième identique à la première
     * Cree des nouveaux pixels a partir des valeurs des pixel de l'objet copie1 passe en parametre pour ensuite creer une nouvelle matrice selon le type d'image
     * @param copie1 L'objet {@link Image} source de la copie
     * @param copie2 L'objet {@link Image} destination de la copie
     */
    public static void copier(Image copie1, Image copie2)
            throws ExceptionImagesIdentiques {

        if (copie1.sont_identiques(copie2)) {
            throw  new ExceptionImagesIdentiques("les deux images sont identiques");
        }
        else if (copie1.getClass() != copie2.getClass()) {
            //throw new ExceptionImageClassIncompatible("Les images sont incompatibles") <-- a creer
        }
        else{

            int copie1Hauteur = copie1.getHauteur();
            int copie1Largeur = copie1.getLargeur();

            if (copie1.getClass() == ImagePGM.class) {
                PixelPGM[][] tempMatrice = new PixelPGM[copie1Hauteur][copie1Largeur];

                for (int i = 0; i < copie1Hauteur; i++) {
                    for (int j = 0; j < copie1Largeur; j++) {
                        tempMatrice[i][j] =
                                new PixelPGM(((ImagePGM) copie1).getMatrice()[i][j].getLigne(),
                                ((ImagePGM) copie1).getMatrice()[i][j].getColonne(),
                                ((ImagePGM) copie1).getMatrice()[i][j].getTeinte());
                    }
                }

                ((ImagePGM) copie2).setMatrice(tempMatrice);
            }
            else if (copie1.getClass() == ImagePPM.class) {
                PixelPPM[][] tempMatrice = new PixelPPM[copie1Hauteur][copie1Largeur];

                for (int i = 0; i < copie1Hauteur; i++) {
                    for (int j = 0; j < copie1Largeur; j++) {
                        tempMatrice[i][j] =
                                new PixelPPM(((ImagePPM) copie1).getMatrice()[i][j].getLigne(),
                                        ((ImagePPM) copie1).getMatrice()[i][j].getColonne(),
                                        ((ImagePPM) copie1).getMatrice()[i][j].getRouge(),
                                        ((ImagePPM) copie1).getMatrice()[i][j].getVert(),
                                        ((ImagePPM) copie1).getMatrice()[i][j].getBleu());
                    }
                }

                ((ImagePPM) copie2).setMatrice(tempMatrice);
            }
        }
    }

    /**
     * Modifie la luminosité : un v positif assombrit et v négatif éclaircit
     * @param v la valeur de modification
     */
    public abstract void eclaircir_noircir(int v);

    /**
     * Pivote une image à 90 degrés
     */
    public abstract void pivoter90();

    /**
     * Vérification de deux image s'ils sont identique ou non
     * @param autre la deuxième image a comparer
     * @return Un Vrai ou Faux, si identique
     */
    public abstract boolean sont_identiques(Image autre);

    /**
     * Extrait une sous-section de l'image entre deux points
     * @param p1 Ligne de départ
     * @param c1 Colonne de départ
     * @param p2 Ligne de fin
     * @param c2 Colonne de fin
     * @return Une nouvelle Image contenant la zone extraite
     */
    public abstract Image extraire(int p1, int c1, int p2, int c2);

    /**
     * Reduit la taille de l'image avec la moyenne des pixels à l'origine
     * @return Une nouvelle image réduite
     */
    public abstract Image reduire();

    /**
     * Indetifie le pixel (couleur ou gris) le plus présent dans l'image
     * @return Le pixel le plus prséent dans l'image
     */
    public abstract Pixel couleur_preponderante();

    // Accès
    public abstract int getLargeur();
    public abstract int getHauteur();
    public abstract int getValeurMax();
}
