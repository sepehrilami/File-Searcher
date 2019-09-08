import java.io.File;

public class Main {

    public static void main(String[] args) {
        TextFinder textFinder = new TextFinder(new File("/home/sepehr/Downloads/test_files"));
        textFinder.preprocess();
        textFinder.searchBar();
    }
}