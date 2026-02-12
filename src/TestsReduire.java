import exceptions.ExceptionEcritureImage;
import exceptions.ExceptionLectureImage;

public class TestsReduire {
    public static void main(String[] args) {
        try {
            ImagePGM pgm = new ImagePGM(0, 0, 0);
            pgm.lire("Sherbrooke_COPIE_de_copie.pgm");
            System.out.println("Tests PGM");
            System.out.println("Hauteur iniciale: " + pgm.getHauteur() + " ||  Largeur iniciale: " + pgm.getLargeur());

            // Test Reduire ----> OK
            pgm.reduire();
            pgm.ecrire("testReduire.pgm");
            System.out.println("Hauteur finale: " + pgm.getHauteur() + " ||  Largeur finale: " + pgm.getLargeur());
            System.out.println("Reduction PGM terminee");

            //Tests PPM
            ImagePPM ppm = new ImagePPM(0, 0, 0);
            ppm.lire("Sherbrooke_COPIE_COULEUR.ppm");
            System.out.println("Tests PPM");
            System.out.println("Hauteur iniciale: " + ppm.getHauteur() + " ||  Largeur iniciale: " + ppm.getLargeur());

            // Test Reduire ---> OK
            ppm.reduire();
            ppm.reduire();
            ppm.ecrire("testReduire.ppm");
            System.out.println("Hauteur finale: " + ppm.getHauteur() + " ||  Largeur finale: " + ppm.getLargeur());
            System.out.println("Reduction terminee");


        } catch (ExceptionLectureImage e) {
            System.err.println("Erreur DE LECTURE : " + e.getMessage());
        } catch (ExceptionEcritureImage e) {
            System.err.println("Erreur lors de l'écriture : " + e.getMessage());
        }
    }

}
