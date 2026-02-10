import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Implémentation de la bibliothèque pour les images en couleurs
 * @author Antony, Abdoulaye et Sèdrick
 */
public class ImagePPM extends Image {

    private int largeur;
    private int hauteur;
    private int valeurMax;

    private PixelPPM[][] matrice;

    /**
     * Constructeur d'ImagePPM avec attribut;
     * @param largeur La largeur de l'image en pixels
     * @param hauteur La hauteur de l'image en pixels
     * @param valeurMax La valeur max autorisé pour un pixel (65535)
     */
    ImagePPM(int largeur, int hauteur, int valeurMax) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.valeurMax = valeurMax;
        this.matrice = new PixelPPM[hauteur][largeur];
    }

    @Override public int getLargeur() {
        return largeur;
    }

    @Override public int getHauteur() {
        return hauteur;
    }

    @Override public int getValeurMax() {
        return valeurMax;
    }

    //Fichier


    @Override
    public void lire(String fichier) {
        try {
            File f = new File(fichier);
            Scanner sc = new Scanner(f);

            String magic = "";
            int largeur;
            int hauteur;
            int max;

            while (sc.hasNext("#")) {
                sc.nextLine();
            }
            magic = sc.next();
            if (magic.equals("P3")) {
                this.largeur = sc.nextInt();
                this.hauteur = sc.nextInt();
                this.valeurMax = sc.nextInt();

                this.matrice = new PixelPPM[this.hauteur][this.largeur];
                for (int i = 0; i < this.hauteur; i++) {
                    for (int j = 0; j < this.largeur; j++) {
                        int r = sc.nextInt();
                        int g = sc.nextInt();
                        int b = sc.nextInt();

                        this.matrice[i][j] = new PixelPPM(i,j,r,g,b);

                    }
                }
            } else {
                throw new RuntimeException();
            }


            sc.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void ecrire(String fichier) {
        try {
            File f = new File(fichier);
            PrintWriter pw = new PrintWriter(f);


            pw.println("P3");
            pw.println(this.largeur + " " + this.hauteur);
            pw.println(this.valeurMax);

            int nbCaracteresLigne = 0;



            for (int i = 0; i < this.hauteur; i++) {
                for (int j = 0; j < this.largeur; j++) {

                    PixelPPM pixel = (PixelPPM) this.matrice[i][j];
                    int[] couleurs = {pixel.getRouge(), pixel.getVert(), pixel.getBleu()};

                    for (int valeur : couleurs) {
                        String s = valeur + " ";

                        if (nbCaracteresLigne + s.length() > 70) {
                            pw.print("\n");
                            nbCaracteresLigne = 0;
                        }

                        pw.print(s);
                        nbCaracteresLigne += s.length();
                    }






                }


            }

            pw.close();

        } catch (IOException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    @Override
    public void eclaircir_noircir(int v) {

    }

    @Override
    public void pivoter90(){

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sont_identiques(Image autre){
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image extraire(int p1, int c1, int p2, int c2){
        return null;
    }

    /**
     * Réduit l'image en calculant la moyenne de rouge, vert et bleu
     * @return Une nouvelle image réduite par la moyenne
     */
    @Override
    public Image reduire(){
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pixel couleur_preponderante(){
        return null;
    }

    public PixelPPM[][] getMatrice(){
        return matrice;
    }

    public void setMatrice(PixelPPM[][] matrice){
        this.matrice = matrice;
    }
}
