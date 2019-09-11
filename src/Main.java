import java.io.File;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        RecursiveFileImporter recursiveFileImporter = new RecursiveFileImporter();
        ExactTokenizer exactTokenizer = new ExactTokenizer();
        InputTokenizer inputTokenizer = new InputTokenizer();
        TextFinder textFinder = new TextFinder(recursiveFileImporter, exactTokenizer, inputTokenizer);
        textFinder.preprocess(new File("/home/sepehr/Downloads/test_files"));
        String input = getString();
        Set<String> fileNames = textFinder.search(input);
        //for console mode:
        System.out.println(fileNames);
    }

    private static String getString() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter your word:");
        return scanner.nextLine();
    }
}