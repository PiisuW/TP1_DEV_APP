import exceptions.ExceptionEcritureImage;
import exceptions.ExceptionImagesIdentiques;
import exceptions.ExceptionLectureImage;

public class TestExtraire {
    public static void main(String[] args) {
        try {
            // Extraction PGM
            ImagePGM pgm = new ImagePGM(0,0,0);
            pgm.lire("Sherbrooke_COPIE_de_copie.pgm");
            System.out.println("Dimension original: " + pgm.getLargeur() + "x" + pgm.getHauteur());


            int startRow = 50;
            int startCol = 50;
            int endRow = 150;
            int endCol = 150;

            Image pgmExtraite = pgm.extraire(startRow, startCol, endRow, endCol);

            pgmExtraite.ecrire("Sherbrooke_EXTRAIT_TEST.pgm");
            System.out.println("Extraction sauvegarder dans 'Sherbrooke_EXTRAIT_TEST.pgm");

            // Extraction PPM
            ImagePPM ppm = new ImagePPM(0,0,0);
            ppm.lire("Sherbrooke_COPIE_COULEUR.ppm");
            System.out.println("Dimension original: " + ppm.getLargeur() + "x" + ppm.getHauteur());

            int startRowPPM = 50;
            int startColPPM = 50;
            int endRowPPM = 150;
            int endColPPM = 150;

            Image ppmExtraite = ppm.extraire(startRowPPM, startColPPM, endRowPPM, endColPPM);

            ppmExtraite.ecrire("Sherbrooke_EXTRAIT_TEST.ppm");
            System.out.println("Extraction sauvgarder dans 'Sherbrooke_EXTRAIT_TEST.ppm");



        } catch (ExceptionLectureImage e) {
            System.err.println("ERREUR DE LECTURE : " + e.getMessage());
        } catch (ExceptionEcritureImage e) {
            System.err.println("Erreur lors de l'écriture : " + e.getMessage());
        }
    }
}

