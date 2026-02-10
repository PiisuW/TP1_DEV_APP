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
    public abstract void lire(String fichier);

    /**
     * Sauvegarde l'image actuelle dans un fichier au format ASCII
     * @param fichier Le nom du fichier de destination
     */
    public abstract void ecrire(String fichier);

    // Traitement
    /**
     * Copie l'image pour en faire une deuxième identique à la première
     * @param copie L'image copier
     */
    public abstract void copier(Image copie);

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
