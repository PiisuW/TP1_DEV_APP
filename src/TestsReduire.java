
import exceptions.ExceptionEcritureImage;
import exceptions.ExceptionImagesIdentiques;
import exceptions.ExceptionLectureImage;

public class TestsReduire {
    public static void main(String[] args) {
        try {
            ImagePGM pgm = new ImagePGM(0, 0, 0);
            pgm.lire("Sherbrooke_COPIE_de_copie.pgm");
            System.out.println("Tests PGM");
            System.out.println(pgm.getHauteur() + " " + pgm.getLargeur());

            // Test Reduire
            ImagePGM pgm2 = new ImagePGM(0, 0, 0);
            Image.copier(pgm, pgm2);
            pgm2.reduire();
            pgm2.ecrire("testDelete.pgm");
            System.out.println("Reduction terminee");


        } catch (ExceptionLectureImage e) {
            System.err.println("ERREUR DE LECTURE : " + e.getMessage());
        } catch (ExceptionEcritureImage e) {
            System.err.println("Erreur lors de l'écriture : " + e.getMessage());
        } catch (ExceptionImagesIdentiques e) {
            throw new RuntimeException(e);
        }
    }

}
