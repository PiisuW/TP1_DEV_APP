import exceptions.ExceptionEcritureImage;
import exceptions.ExceptionLectureImage;

/**
 * @author Antony, Abdoulaye et Sèdrick
 */
public class Main {
    public static void main(String[] args) {


        // Test de lire et écrire en PPM et PGM
        try {
            ImagePGM image = new ImagePGM(0, 0, 0);

            System.out.println("Début de la lecture...");
            image.lire("image_test.pgm");

            if (image.getLargeur() > 0) {
                System.out.println("VÉRIFICATION RÉUSSITE :");
                System.out.println("Dimensions : " + image.getLargeur() + " x " + image.getHauteur());
                System.out.println("Valeur Max : " + image.getValeurMax());

                System.out.println("Écriture de la copie...");
                image.ecrire("Sherbrooke_COPIE_de_copie.pgm");

                System.out.println("Test terminé ! Vérifie le dossier de ton projet");
            } else {
                System.out.println("ATTENTION : L'image semble être vide ou les dimensions sont invalides.");
            }
        } catch (ExceptionLectureImage e) {
            System.err.println("ERREUR DE LECTURE : " + e.getMessage());
        } catch (ExceptionEcritureImage e) {
            System.err.println("Erreur lors de l'écriture : " + e.getMessage());
        }

        try {
            ImagePPM imageCouleur = new ImagePPM(0, 0, 0);

            System.out.println("Lecture de l'image PPM (Couleur)...");
            imageCouleur.lire("image_test.ppm");

            if (imageCouleur.getLargeur() > 0) {
                System.out.println("Succès ! Image de " + imageCouleur.getLargeur() + "x" + imageCouleur.getHauteur() + " chargée.");

                System.out.println("Écriture de la copie couleur...");
                imageCouleur.ecrire("Sherbrooke_COPIE_COULEUR.ppm");
                System.out.println("Fichier 'Sherbrooke_COPIE_COULEUR.ppm' créé !");
            } else {
                System.out.println("ATTENTION : L'image semble être vide ou les dimensions sont invalides.");
            }
        } catch (ExceptionLectureImage e) {
            System.err.println("ERREUR DE LECTURE : " + e.getMessage());
        } catch (ExceptionEcritureImage e) {
            System.err.println("Erreur lors de l'écriture : " + e.getMessage());
        }
    }
}