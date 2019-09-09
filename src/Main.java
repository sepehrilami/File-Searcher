import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Importer importer = null;
        Tokenizer tokenizer = null;
        TextFinder textFinder = new TextFinder(importer, tokenizer);
        textFinder.preprocess(new File("/home/sepehr/Downloads/test_files"));
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter your word:");
        String input = scanner.next();
        textFinder.searchBar(input);
    }
}