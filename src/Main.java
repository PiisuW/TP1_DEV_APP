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
            ImagePGM pgm = new ImagePGM();
            pgm.lire("Sherbrooke_COPIE_de_copie.pgm");
            System.out.println("Tests PGM");


            // Test couleur prépondérante
            Pixel pMaxiGris = pgm.couleur_preponderante();
            System.out.println("Teinte la plus fréquente : " + ((PixelPGM)pMaxiGris).getTeinte());
            // Résultat attendue : Teinte la plus fréquente : 112



            // Test luminosité (Éclaircir : on passe une valeur négative)
            ImagePGM pgmCopie = new ImagePGM(pgm.getLargeur(), pgm.getHauteur(), pgm.getValeurMax());
            Image.copier(pgm, pgmCopie);
            pgmCopie.eclaircir_noircir(100);
            pgmCopie.ecrire("Sherbrooke_LUMIÈRE_MODIFIER.pgm");
            System.out.println("Image eclaircie terminer");
            // Résultat attendue : Créer le fichier : Sherbrooke_LUMIÈRE_MODIFIER.pgm plus foncée


            // TEST PPM
            ImagePPM ppm = new ImagePPM();
            ppm.lire("Sherbrooke_COPIE_COULEUR.ppm");
            System.out.println("TEST PPM");


            // Test sont_identiques avec une copie identique
            ImagePPM ppmCopie = new ImagePPM(ppm.getLargeur(), ppm.getHauteur(), ppm.getValeurMax());
            Image.copier(ppm, ppmCopie);
            System.out.println(ppm.sont_identiques(ppmCopie) ? "Oui les images sont identiques" : "Non les images ne sont pas identiques");
            // Résultat attendue : Oui les images sont identiques

            // Test sont_identiques avec non identique
            ImagePPM ppmDiff = new ImagePPM(1,2,3);
            System.out.println(ppm.sont_identiques(ppmDiff) ? "Oui les images sont identiques" : "Non les images ne sont pas identiques");
            // Résultat attendue : Non les images ne sont pas identiques



            // Test couleur prépondérante
            Pixel pMaxCoul = ppm.couleur_preponderante();
            PixelPPM pcc = (PixelPPM) pMaxCoul;
            System.out.println("Couleur dominante : Rouge=" + pcc.getRouge() + " Vert=" + pcc.getVert() + " Bleu=" + pcc.getBleu());
            // Résultat attendue : Couleur dominante : Rouge=43 Vert=122 Bleu=214


        } catch (ExceptionLectureImage e) {
            System.err.println("ERREUR DE LECTURE : " + e.getMessage());
        } catch (ExceptionEcritureImage e) {
            System.err.println("Erreur lors de l'écriture : " + e.getMessage());
        } catch (ExceptionImagesIdentiques e) {
            System.err.println("Erreur lors d'identification : " + e.getMessage());
        }


    }
}