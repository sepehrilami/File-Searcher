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

    private HashMap<String, HashSet<String>> data = new HashMap<>();

    HashMap<String, HashSet<String>> getData() {
        return data;
    }

    void preprocess(Importer importer, Tokenizer tokenizer) {
        File folder = new File("/home/sepehr/Downloads/test_files");
        ArrayList<String> realFiles;
        ArrayList<String> paths = new ArrayList<>();
        System.out.println(System.currentTimeMillis());
        RecursiveFileImporter recursiveFileImporter = new RecursiveFileImporter();
        realFiles = recursiveFileImporter.readAllFiles(folder, paths);
        tokenCreate(paths);
        System.out.println(System.currentTimeMillis());
        System.out.println(this.getData().values().size());
        System.out.println(realFiles.size());
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
            } catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
