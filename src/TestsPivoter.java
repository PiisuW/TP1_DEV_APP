import exceptions.ExceptionEcritureImage;
import exceptions.ExceptionLectureImage;

public class TestsPivoter {
    public static void main(String[] args) throws Exception {
        try {
            ImagePGM pgm = new ImagePGM(0, 0, 0);
            pgm.lire("testPivoter.pgm");
            System.out.println("Tests PGM");
            System.out.println("Hauteur iniciale: " + pgm.getHauteur() + " ||  Largeur iniciale: " + pgm.getLargeur());

            pgm.pivoter90();
            pgm.ecrire("testPivoter.pgm");
            System.out.println("Hauteur finale: " + pgm.getHauteur() + " ||  Largeur finale: " + pgm.getLargeur());
            System.out.println("Pivoter PGM terminee");

        } catch (ExceptionLectureImage e) {
            System.err.println("Erreur DE LECTURE : " + e.getMessage());
        } catch (ExceptionEcritureImage e) {
            System.err.println("Erreur lors de l'écriture : " + e.getMessage());
        }
    }
}
