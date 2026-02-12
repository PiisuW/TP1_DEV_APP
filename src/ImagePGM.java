import exceptions.ExceptionEcritureImage;
import exceptions.ExceptionImagesIdentiques;
import exceptions.ExceptionLectureImage;

/**
 * Implémentation de la bibliothèque pour image avec tons de gris
 * @author Antony, Abdoulaye et Sèdrick
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

public class ImagePGM extends Image {

    private int largeur;
    private int hauteur;
    private int valeurMax;

    private PixelPGM[][] matrice;

    /**
     * Constructeur d'ImagePGM avec attribut;
     * @param largeur La largeur de l'image en pixels
     * @param hauteur La hauteur de l'image en pixels
     * @param valeurMax La valeur max autorisé pour un pixel (65535)
     */
    ImagePGM(int largeur, int hauteur, int valeurMax) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.valeurMax = valeurMax;
        this.matrice = new PixelPGM[hauteur][largeur];
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
    public void lire(String fichier) throws ExceptionLectureImage {
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
            if (magic.equals("P2")) {
                this.largeur = sc.nextInt();
                this.hauteur = sc.nextInt();
                this.valeurMax = sc.nextInt();

                this.matrice = new PixelPGM[this.hauteur][this.largeur];
                for (int i = 0; i < this.hauteur; i++) {
                    for (int j = 0; j < this.largeur; j++) {
                        int teinteLue = sc.nextInt();

                        this.matrice[i][j] = new PixelPGM(i,j,teinteLue);

                    }
                }
            } else {
                throw new ExceptionLectureImage("Le fichier n'est pas au format P2 (format lu : " + magic + ")");
            }


            sc.close();

        } catch (IOException e) {
            throw new ExceptionLectureImage("Impossible de lire dans le fichier : " + e.getMessage());
        }


    }

    @Override
    public void ecrire(String fichier) throws ExceptionEcritureImage {
        try {
            File f = new File(fichier);
            PrintWriter pw = new PrintWriter(f);


            pw.println("P2");
            pw.println(this.largeur + " " + this.hauteur);
            pw.println(this.valeurMax);

            int nbCaracteresLigne = 0;



            for (int i = 0; i < this.hauteur; i++) {
                for (int j = 0; j < this.largeur; j++) {

                    String s = this.matrice[i][j].getTeinte() + " ";

                    if (nbCaracteresLigne + s.length() > 70) {
                        pw.print("\n");
                        nbCaracteresLigne = 0;
                    }

                    pw.print(s);
                    nbCaracteresLigne += s.length();
                }


            }

            pw.close();

        } catch (IOException e) {
            throw new ExceptionEcritureImage("Impossible d'écrire' dans le fichier : " + e.getMessage());
        }

    }

    @Override
    public void eclaircir_noircir(int v) {

    }

    @Override
    public void pivoter90(){
        int nouvelleLargeur = this.hauteur;
        int nouvelleHauteur = this.largeur;

        PixelPGM[][] tempMatrice = new PixelPGM[nouvelleHauteur][nouvelleLargeur];
        for (int i = 0; i < nouvelleHauteur; i++) {
            for (int j = 0; j < nouvelleLargeur; j++) {
                tempMatrice[i][j] =         //accede a la position dans l'ancienne matrice
                        new PixelPGM(i, j, this.matrice[j][this.largeur - 1 - i].getTeinte());
            }
        }

        this.matrice = tempMatrice;

        this.hauteur = nouvelleHauteur;
        this.largeur = nouvelleLargeur;
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
     * Réduit la taille de l'image en calculant moyenne des teintes de gris
     * @return Une nouvelle ImagePGM réduite
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

    public PixelPGM[][] getMatrice() {
        return this.matrice;
    }

    public void setMatrice(PixelPGM[][] matrice) {
        this.matrice = matrice;
    }
}
