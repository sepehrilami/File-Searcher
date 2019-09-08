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

class TextFinder {

    private File folder = new File("/home/sepehr/Downloads/test_files");
    private ArrayList<String> paths = new ArrayList<>();
    private HashMap<String, HashSet<String>> data = new HashMap<>();

    HashMap<String, HashSet<String>> getData() {
        return data;
    }

    void preprocess(Importer importer, Tokenizer tokenizer) {
        System.out.println(System.currentTimeMillis());
        RecursiveFileImporter recursiveFileImporter = new RecursiveFileImporter();
        recursiveFileImporter.readAllFiles(folder, paths);
        tokenCreate(paths);
        System.out.println(System.currentTimeMillis());
    }

    private void tokenCreate(ArrayList<String> paths) {
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
