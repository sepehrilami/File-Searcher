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

class TextFinder implements  SearchType {

    private HashMap<String, HashSet<String>> data = new HashMap<>();
    private ArrayList<String> paths;
    private Tokenizer tokenizer;
    private  Importer importer;

    HashMap<String, HashSet<String>> getData() {
        return data;
    }

    TextFinder( Importer importer , Tokenizer tokenizer) {
        this.importer = importer;
        this.tokenizer = tokenizer;
    }


    void preprocess(File folder) {
        System.out.println(System.currentTimeMillis());
        RecursiveFileImporter recursiveFileImporter = new RecursiveFileImporter();
        paths = recursiveFileImporter.readAllFiles(folder);
        tokenize();
        System.out.println(System.currentTimeMillis());
    }

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
    public void searchBar(String input) {
        System.out.println("Files containing this word:\n");
        if (this.getData().get(input.toLowerCase()) != null) {
            System.out.println(this.getData().get(input.toLowerCase()) + "\n");
        } else {
            System.out.println("Oops! We didn't find any file.\nTry something else!");
        }
    }
}
