import exceptions.ExceptionEcritureImage;
import exceptions.ExceptionImagesIdentiques;
import exceptions.ExceptionLectureImage;

public class TestsPivoter90 {
    public static void main(String[] args) {

        try {
            // Test PGM
            ImagePGM pgm = new ImagePGM();
            pgm.lire("Sherbrooke_COPIE_de_copie.pgm");
            System.out.println("Tests PGM");

            // Test pivotement
            ImagePGM pgmCopie = new ImagePGM(pgm.getLargeur(), pgm.getHauteur(), pgm.getValeurMax());
            pgm.copier(pgmCopie);
            pgmCopie.pivoter90();
            pgmCopie.ecrire("Sherbrooke_Pivoter90.pgm");
            System.out.println("Rotation de 90 terminee");


            // Test PPM
            ImagePPM ppm = new ImagePPM();
            ppm.lire("Sherbrooke_COPIE_COULEUR.ppm");
            System.out.println("Tests PPM");

            // Test pivotement
            ImagePPM ppmCopie = new ImagePPM(ppm.getLargeur(), ppm.getHauteur(), pgm.getValeurMax());
            ppm.copier(ppmCopie);
            ppmCopie.pivoter90();
            ppmCopie.ecrire("Sherbrooke_Pivoter90.ppm");
            System.out.println("Rotation de 90 terminee");



        } catch (ExceptionLectureImage e) {
            System.err.println("Erreur de lecture : " + e.getMessage());
        } catch (ExceptionEcritureImage e) {
            System.err.println("Erreur d'écriture : " + e.getMessage());
        } catch (ExceptionImagesIdentiques e) {
            System.err.println("Erreur de copie : " + e.getMessage());
        }


    }
}
