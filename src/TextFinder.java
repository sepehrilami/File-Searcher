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

class TextFinder implements Tokenizer {

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
}
