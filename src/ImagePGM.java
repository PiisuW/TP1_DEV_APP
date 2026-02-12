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
        if (v > this.valeurMax / 2) v = this.valeurMax / 2;
        if (v < -(this.valeurMax / 2)) v = -(this.valeurMax / 2);

        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {

                int t = matrice[i][j].getTeinte();
                t = t - v;

                if (t < 0) t = 0;
                if (t > valeurMax) t = valeurMax;

                matrice[i][j].setTeinte(t);
            }
        }
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
        if (!(autre instanceof ImagePGM)) return false;

        ImagePGM img = (ImagePGM) autre;

        if (this.largeur != img.largeur) return false;
        if (this.hauteur != img.hauteur) return false;
        if (this.valeurMax != img.valeurMax) return false;

        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {

                PixelPGM p1 = this.matrice[i][j];
                PixelPGM p2 = img.matrice[i][j];

                if (p1 == null || p2 == null) {
                    if (p1 != p2) return false;
                    continue;
                }

                if (p1.getTeinte() != p2.getTeinte()) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image extraire(int p1, int c1, int p2, int c2){

        if (p2 >= hauteur) {
            p2 = hauteur -1;
        }
        if (c2 >= largeur) {
            c2 = largeur -1;
        }


        int newHauteur = p2 - p1 + 1;
        int newLargeur = c2 - c1 + 1;


        ImagePGM extraite = new ImagePGM(newLargeur, newHauteur, this.valeurMax);


        for (int i = 0; i < newHauteur; i++) {
            for (int j = 0; j < newLargeur; j++) {

                int teinte = this.matrice[p1 + i][c1 + j].getTeinte();


                extraite.getMatrice()[i][j] = new PixelPGM(i,j,teinte);
            }
        }
        return extraite;
    }

    /**
     * Réduit la taille de l'image en calculant la moyenne des teintes de gris
     * @return Une nouvelle ImagePGM réduite
     */
    @Override
    public Image reduire(){
        if (this.hauteur <= 2 || this.largeur <= 2) {
            //throw new ExpectionImageTooSmall;
        }

        int nouvelleHauteur = this.hauteur / 2;
        int nouvelleLargeur = this.largeur / 2;

        if (nouvelleHauteur % 2 != 0) { nouvelleHauteur--; }
        if (nouvelleLargeur % 2 != 0) { nouvelleLargeur--; }

//                +----++---+
//                |  1 |  2 |
//                |    |    |
//                +----+----+
//                |  3 | 4  |
//                |    |    |
//                +----+----+


        PixelPGM[][] tempMatrice = new  PixelPGM[nouvelleHauteur][nouvelleLargeur];
        for (int i = 0; i < this.hauteur; i += 2) { //parcours la matrice a bonds de 2 pour pouvoir calculer la moyenne d'un carree 2x2
            for (int j = 0; j < this.largeur; j += 2) {
                int moyenneTeinte = 0;
                int multiplicateur = 0;

                for (int k = i; k < i + 2; k++) {
                    for (int l = j; l < j + 2; l++) {
                        moyenneTeinte += this.matrice[k][l].getTeinte();
                        multiplicateur += 1;
                        System.out.println("Moyenne teinte: " + moyenneTeinte + " multiplicateur: " + multiplicateur);
                        System.out.println("PositionX: " + i + " PositionY: " + j); // debogage
                    }
                }
                moyenneTeinte = moyenneTeinte/multiplicateur;

                tempMatrice[i / 2][j / 2] = new PixelPGM(i, j, moyenneTeinte);

                System.out.println("Moyenne teinte: " + moyenneTeinte + " multiplicateur: " + multiplicateur);
            }
        }

        this.matrice = tempMatrice;
        this.hauteur = nouvelleHauteur;
        this.largeur = nouvelleLargeur;

        return  this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pixel couleur_preponderante(){
        int[] compteur = new int[valeurMax + 1];

        int maxCount = -1;
        int bestI = 0;
        int bestJ = 0;

        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {

                int t = matrice[i][j].getTeinte();
                compteur[t]++;

                if (compteur[t] > maxCount) {
                    maxCount = compteur[t];
                    bestI = i;
                    bestJ = j;
                }
            }
        }

        return matrice[bestI][bestJ];
    }

    public PixelPGM[][] getMatrice() {
        return this.matrice;
    }

    public void setMatrice(PixelPGM[][] matrice) {
        this.matrice = matrice;
    }
}
