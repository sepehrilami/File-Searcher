import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

class TextFinder implements Tokenizer, SearchType {

    private HashMap<String, HashSet<String>> data = new HashMap<>();
    private ArrayList<String> paths;

    HashMap<String, HashSet<String>> getData() {
        return data;
    }

    TextFinder(File folder) {
        RecursiveFileImporter recursiveFileImporter = new RecursiveFileImporter(folder);
        paths = recursiveFileImporter.readAllFiles();
    }


    void preprocess() {
        System.out.println(System.currentTimeMillis());
        tokenize();
        System.out.println(System.currentTimeMillis());
    }

    @Override
    public void tokenize() {
        for (String path : paths) {
            File file = new File(path);
            try (BufferedReader ignored = new BufferedReader(new FileReader(file))) {

                String fileData = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);

                for (String string : fileData.split(" ")) {
                    this.getData().putIfAbsent(string, new HashSet<>());
                    this.getData().get(string).add(file.getName());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void searchBar() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("enter your word:");
            String input = scanner.next();
            if (input.equalsIgnoreCase("exittt")) {
                System.out.println("FINISHED!");
                break;
            }
            System.out.println("Files containing this word:\n");
            if (this.getData().get(input.toLowerCase()) != null) {
                System.out.println(this.getData().get(input.toLowerCase()) + "\n");
            } else {
                System.out.println("Oops! We didn't find any file.\nTry something else!");
            }
        }
    }
}
