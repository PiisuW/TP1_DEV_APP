import exceptions.ExceptionEcritureImage;
import exceptions.ExceptionImagesIdentiques;
import exceptions.ExceptionLectureImage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.HashMap;

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
    public void lire(String fichier) throws ExceptionLectureImage {
        try {
            File f = new File(fichier);
            Scanner sc = new Scanner(f);

            String magic = "";

            while (sc.hasNext("#.*")) {
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
                throw new ExceptionLectureImage("Le fichier n'est pas au format P3 (format lu : " + magic + ")");
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
            throw new ExceptionEcritureImage("Impossible d'écrire dans le fichier : " + e.getMessage());
        }
    }

    /**
     * Copie l'objet {@link Image} pour en faire une deuxième identique à la première
     * @param copie objet {@link Image} resultat de la copie
     */
    @Override
    public void copier(Image copie) throws ExceptionImagesIdentiques {
        if (this.sont_identiques(copie)) {
            throw  new ExceptionImagesIdentiques("les deux images sont identiques");
        }
        else if (this.getClass() != copie.getClass()) {
            //throw new ExceptionImageClassIncompatible("Les images sont incompatibles") <-- a creer
        }
        else{

            PixelPPM[][] tempMatrice = new PixelPPM[this.hauteur][this.largeur];

            for (int i = 0; i < this.hauteur; i++) {
                for (int j = 0; j < this.largeur; j++) {
                    tempMatrice[i][j] =
                            new PixelPPM(this.getMatrice()[i][j].getLigne(),
                                    this.getMatrice()[i][j].getColonne(),
                                    this.getMatrice()[i][j].getRouge(),
                                    this.getMatrice()[i][j].getVert(),
                                    this.getMatrice()[i][j].getBleu());
                }
            }
            ((ImagePPM)copie).setMatrice(tempMatrice);
        }
    }

    @Override
    public void eclaircir_noircir(int v) {
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {

                PixelPPM p = matrice[i][j];
                int r = p.getRouge() - v;
                int g = p.getVert() - v;
                int b = p.getBleu() - v;

                if (r < 0) r = 0;
                if (r > valeurMax) r = valeurMax;
                if (g < 0) g = 0;
                if (g > valeurMax) g = valeurMax;
                if (b < 0) b = 0;
                if (b > valeurMax) b = valeurMax;

                p.setRouge(r);
                p.setVert(g);
                p.setBleu(b);

            }
        }
    }

    /**
     * Cree une nouvelle matrice temporaire 2 dimensions de {@link PixelPPM} en inversant hauteur/largeur
     * Insere ensuite les donnees de la matrice actuelle de l'image vers la matrice temporaire en modifiant la position des pixels
     * La matrice temporaire devient donc une nouvelle matrice identique a l'ancienne, mais ayant subit une rotation de 90 degres vers la gauche
     * La matrice temporaire devient la nouvelle matrice de l'image de l'objet
     */
    @Override
    public void pivoter90() {
        int nouvelleLargeur = this.hauteur;
        int nouvelleHauteur = this.largeur;

        PixelPPM[][] tempMatrice = new PixelPPM[nouvelleHauteur][nouvelleLargeur];
        for (int i = 0; i < nouvelleHauteur; i++) {
            for (int j = 0; j < nouvelleLargeur; j++) {
                tempMatrice[i][j] =
                        new PixelPPM(   //Accede la position du pixel correspondant dans l'ancienne matrice
                                i,
                                j,
                                this.matrice[j][this.largeur - 1 - i].getRouge(),
                                this.matrice[j][this.largeur - 1 - i].getVert(),
                                this.matrice[j][this.largeur - 1 - i].getBleu());
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
    public boolean sont_identiques(Image autre) {
        if (!(autre instanceof ImagePPM)) return false;

        ImagePPM img = (ImagePPM) autre;

        if (this.largeur != img.largeur) return false;
        if (this.hauteur != img.hauteur) return false;
        if (this.valeurMax != img.valeurMax) return false;

        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {

                PixelPPM p1 = this.matrice[i][j];
                PixelPPM p2 = img.matrice[i][j];

                if (p1 == null || p2 == null) {
                    if (p1 != p2) {
                        return false;
                    }
                    continue;
                }

                if (p1.getRouge() != p2.getRouge()) return false;
                if (p1.getVert() != p2.getVert()) return false;
                if (p1.getBleu() != p2.getBleu()) return false;
            }
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image extraire(int p1, int c1, int p2, int c2) {
        return null;
    }

    /**
     * Réduit l'image en calculant la moyenne de rouge, vert et bleu
     *
     * @return Une nouvelle image réduite par la moyenne
     */
    @Override
    public Image reduire() {

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


        PixelPPM[][] tempMatrice = new  PixelPPM[nouvelleHauteur][nouvelleLargeur];
        for (int i = 0; i < this.hauteur; i += 2) { //parcours la matrice a bonds de 2 pour pouvoir calculer la moyenne d'un carree 2x2
            for (int j = 0; j < this.largeur; j += 2) {
                int moyenneRouge = 0;
                int moyenneVert = 0;
                int moyenneBleu = 0;

                int multiplicateur = 0;

                for (int k = i; k < i + 2; k++) {
                    for (int l = j; l < j + 2; l++) {
                        moyenneRouge += this.matrice[k][l].getRouge();
                        moyenneVert += this.matrice[k][l].getVert();
                        moyenneBleu += this.matrice[k][l].getBleu();

                        multiplicateur += 1;

                    }
                }

                moyenneRouge = moyenneRouge / multiplicateur;
                moyenneVert = moyenneVert / multiplicateur;
                moyenneBleu = moyenneBleu / multiplicateur;

                tempMatrice[i / 2][j / 2] = new PixelPPM(i, j, moyenneRouge, moyenneVert, moyenneBleu);
            }
        }

        this.matrice = tempMatrice;
        this.hauteur = nouvelleHauteur;
        this.largeur = nouvelleLargeur;

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pixel couleur_preponderante() {
        HashMap<String, Integer> compteur = new HashMap<>();

        int maxCount = -1;
        int bestI = 0;
        int bestJ = 0;

        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {

                PixelPPM p = matrice[i][j];

                String cle = p.getRouge() + "-" +
                        p.getVert() + "-" +
                        p.getBleu();

                int count = compteur.getOrDefault(cle, 0) + 1;
                compteur.put(cle, count);

                if (count > maxCount) {
                    maxCount = count;
                    bestI = i;
                    bestJ = j;
                }
            }
        }

        return matrice[bestI][bestJ];
    }

    public PixelPPM[][] getMatrice() {
        return matrice;
    }

    public void setMatrice(PixelPPM[][] matrice) {
        this.matrice = matrice;
    }
}


