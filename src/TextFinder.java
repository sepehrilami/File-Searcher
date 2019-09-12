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
import java.util.Set;

class TextFinder implements SearchType {

    private HashMap<String, HashSet<String>> data = new HashMap<>();

    private ArrayList<String> paths;
    private Tokenizer tokenizer;
    private Tokenizer searchTokenizer;
    private Importer importer;

    HashMap<String, HashSet<String>> getData() {
        return data;
    }

    public Tokenizer getSearchTokenizer() {
        return searchTokenizer;
    }

    public ArrayList<String> getPaths() {
        return paths;
    }

    TextFinder(Importer importer, Tokenizer tokenizer, Tokenizer searchTokenizer) {
        this.importer = importer;
        this.tokenizer = tokenizer;
        this.searchTokenizer = searchTokenizer;
    }

    void preprocess() {
        System.out.println(System.currentTimeMillis());
        paths = importer.importData();
        for (String path : this.getPaths()) {
            File file = new File(path);
            try (BufferedReader ignored = new BufferedReader(new FileReader(file))) {

                String fileData = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);

                HashSet<String> tokens = tokenizer.tokenize(fileData);

                for (String string : tokens) {
                    data.putIfAbsent(string.toLowerCase(), new HashSet<>());
                    data.get(string.toLowerCase()).add(file.getName());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(System.currentTimeMillis());
    }

    @Override
    public Set<String> search(String input) {
        HashSet<String> words = searchTokenizer.tokenize(input);
        ArrayList<Set<String>> listOfListOfFiles = new ArrayList<>();
        for (String word : words) {
            System.out.println(word);
            if (data.get(word.toLowerCase()) != null) {
                System.out.println(data.get(word.toLowerCase()) + "\n");
                listOfListOfFiles.add(data.get(word.toLowerCase()));
            } else {
                System.out.println("Oops! We didn't find any file.\nTry something else!");
            }

        }
        if (listOfListOfFiles.size() > 0) {
            Set<String> intersection = listOfListOfFiles.get(0);
            for (Set<String> scan : listOfListOfFiles.subList(1, listOfListOfFiles.size())) {
                intersection.retainAll(scan);
            }
            //System.out.println("total:\n" + intersection);
            return intersection;
        }
        return null;
    }
}
