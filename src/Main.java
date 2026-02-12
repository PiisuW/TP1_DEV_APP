import exceptions.ExceptionEcritureImage;
import exceptions.ExceptionImagesIdentiques;
import exceptions.ExceptionLectureImage;

/**
 * @author Antony, Abdoulaye et Sèdrick
 */
public class Main {
    public static void main(String[] args) {

        try {
            // Test PGM
            ImagePGM pgm = new ImagePGM(0, 0, 0);
            pgm.lire("Sherbrooke_COPIE_de_copie.pgm");
            System.out.println("Tests PGM");

            // Test couleur prépondérante
            Pixel pMaxiGris = pgm.couleur_preponderante();
            System.out.println("Teinte la plus fréquente : " + ((PixelPGM)pMaxiGris).getTeinte());

            // Test pivotement
            pgm.pivoter90();
            pgm.ecrire("Sherbrooke_COPIE_de_copie.pgm");
            System.out.println("Rotation de 90 terminee");

            // Test luminosité (Éclaircir : on passe une valeur négative)
//            ImagePGM pgmCopie = new ImagePGM(pgm.getLargeur(), pgm.getHauteur(), pgm.getValeurMax());
//            Image.copier(pgm, pgmCopie);
//            pgmCopie.eclaircir_noircir(-100);
//            pgmCopie.ecrire("Sherbrooke_Version_MODIFIER.pgm");
//            System.out.println("Image eclaircie terminer");

            // TEST PPM
            ImagePPM ppm = new ImagePPM(0, 0, 0);
            ppm.lire("Sherbrooke_COPIE_COULEUR.ppm");
            System.out.println("TEST PPM");

            // Test sont_identiques avec une copie
//            ImagePPM ppmCopie = new ImagePPM(ppm.getLargeur(), ppm.getHauteur(), ppm.getValeurMax());
//            Image.copier(ppm, ppmCopie);
//            System.out.println("Les images sont identiques ? " + ppm.sont_identiques(ppmCopie));

            // Test couleur prépondérante
            Pixel pMaxCoul = ppm.couleur_preponderante();
            PixelPPM pcc = (PixelPPM) pMaxCoul;
            System.out.println("Couleur dominante : Rouge=" + pcc.getRouge() + " Vert=" + pcc.getVert() + " Bleu=" + pcc.getBleu());

        } catch (ExceptionLectureImage e) {
            System.err.println("ERREUR DE LECTURE : " + e.getMessage());
        } catch (ExceptionEcritureImage e) {
            System.err.println("Erreur lors de l'écriture : " + e.getMessage());
//        } catch (ExceptionImagesIdentiques e) {
//            System.err.println("Erreur lors d'identification : " + e.getMessage());
       }


    }
}