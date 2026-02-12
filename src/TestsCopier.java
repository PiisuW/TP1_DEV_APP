import exceptions.ExceptionEcritureImage;
import exceptions.ExceptionImagesIdentiques;
import exceptions.ExceptionLectureImage;

public class TestsCopier {
    public static void main(String[] args) {
        try{
            //Tests PGM
            ImagePGM pgm = new ImagePGM(0, 0, 0);
            pgm.lire("Sherbrooke_COPIE_de_copie.pgm");
            System.out.println("Tests PGM");
            ImagePGM pgm2 = new ImagePGM(0, 0, 0);
            pgm.copier(pgm2);
            pgm2.ecrire("testCopier.pgm");
            System.out.println("Copie reussie et fin ecriture");

            //Tests PPM
            ImagePPM ppm = new ImagePPM(0, 0, 0);
            ppm.lire("Sherbrooke_COPIE_COULEUR.ppm");
            System.out.println("Tests PPM");

        } catch (ExceptionLectureImage e) {
            System.err.println("Erreur DE LECTURE : " + e.getMessage());
        } catch (ExceptionEcritureImage e) {
            System.err.println("Erreur lors de l'écriture : " + e.getMessage());
        } catch (ExceptionImagesIdentiques e) {
            System.err.println("Erreur lors de la copie : " + e.getMessage());
        }

    }
}
