import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Main {

    public static void preprocess(Importer importer, Tokenizer tokenizer) {
        File folder = new File("/home/sepehr/Downloads/test_files");
        ArrayList<String> realFiles;
        ArrayList<String> paths = new ArrayList<>();
        System.out.println(System.currentTimeMillis());
        TextFinder textFinder = new TextFinder();
        RecursiveFileImporter recursiveFileImporter = new RecursiveFileImporter();
        realFiles = recursiveFileImporter.readAllFiles(folder , paths);
        for (String path : paths) {
            File file = new File(path);
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String fileData = bufferedReader.readLine();
                for (String string : fileData.split(" ")) {
                    //System.out.println(string);
                    textFinder.getData().putIfAbsent(string, new HashSet<>());
                    textFinder.getData().get(string).add(file.getName());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(System.currentTimeMillis());
        System.out.println(textFinder.getData().values().size());
        System.out.println(realFiles.size());
    }

    public static void main(String[] args) {
        Importer importer = null;
        Tokenizer tokenizer = null;
        preprocess(importer , tokenizer);
    }

}