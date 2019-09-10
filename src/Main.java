import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        RecursiveFileImporter recursiveFileImporter = new RecursiveFileImporter();
        ExactTokenizer exactTokenizer = new ExactTokenizer();
        TextFinder textFinder = new TextFinder(recursiveFileImporter, exactTokenizer);
        textFinder.preprocess(new File("/home/sepehr/Downloads/test_files"));
        String input = getString();
        textFinder.searchBar(input);
    }

    private static String getString() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter your word:");
        return scanner.next();
    }
}