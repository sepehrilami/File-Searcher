import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
        realFiles = recursiveFileImporter.readAllFiles(folder , paths);
        for (String path : paths) {
            File file = new File(path);
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String fileData;
                while((fileData = bufferedReader.readLine()) != null) {
                    for (String string : fileData.split(" ")) {
                        //System.out.println(string);
                        this.getData().putIfAbsent(string, new HashSet<>());
                        this.getData().get(string).add(file.getName());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(System.currentTimeMillis());
        System.out.println(this.getData().values().size());
        System.out.println(realFiles.size());
    }
}
