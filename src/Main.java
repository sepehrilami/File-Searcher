import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        TextFinder textFinder = new TextFinder();
        Importer importer = null;
        Tokenizer tokenizer = null;
        textFinder.preprocess(importer , tokenizer);
        System.out.println("enter your word:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        System.out.println("Files containing this word:");
        System.out.println(textFinder.getData().get(input));
    }
}