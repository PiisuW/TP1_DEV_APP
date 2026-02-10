/**
 * @author Antony, Abdoulaye et Sèdrick
 */
public class Main {
    public static void main(String[] args) {

        ImagePGM image = new ImagePGM(0,0,0);

        System.out.println("Début de la lecture...");
        image.lire("Sherbrooke_COPIE.pgm");

        if (image.getLargeur() > 0) {
            System.out.println("VÉRIFICATION RÉUSSITE :");
            System.out.println("Dimensions : " + image.getLargeur() + " x " + image.getHauteur());
            System.out.println("Valeur Max : " + image.getValeurMax());

            System.out.println("Écriture de la copie...");
            image.ecrire("Sherbrooke_COPIE_de_copie.pgm");

            System.out.println("Test terminé ! Vérifie le dossier de ton projet");
        } else {
            System.out.println("ERREUR : L'image n'a pas été chargée");
        }
    }
}