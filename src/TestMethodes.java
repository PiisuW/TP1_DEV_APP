import exceptions.ExceptionEcritureImage;
import exceptions.ExceptionImagesIdentiques;
import exceptions.ExceptionLectureImage;

public class TestMethodes {
    public static void main(String[] args) {

        Image img = new ImagePGM();

        img.lire("test.pgm");
    }
}
