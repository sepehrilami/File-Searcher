import java.io.File;
import java.util.Scanner;

public class Main implements SearchType  {

    public static void main(String[] args){
        TextFinder textFinder = new TextFinder(new File("/home/sepehr/Downloads/test_files"));
        textFinder.preprocess();
        searchBar(textFinder);
    }

    private static void searchBar(TextFinder textFinder) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("enter your word:");
            String input = scanner.next();
            if (input.equalsIgnoreCase("exittt")) {
                System.out.println("FINISHED!");
                break;
            }
            System.out.println("Files containing this word:\n");
            if (textFinder.getData().get(input.toLowerCase()) != null) {
                System.out.println(textFinder.getData().get(input.toLowerCase()) + "\n");
            } else {
                System.out.println("Oops! We didn't find any file.\nTry something else!");
            }
        }
    }
}