import java.io.File;
import java.util.*;

class TextFinder implements SearchType {

    private HashMap<String, HashSet<String>> data = new HashMap<>();

    private ArrayList<String> paths;
    private Tokenizer tokenizer;
    private Importer importer;

    HashMap<String, HashSet<String>> getData() {
        return data;
    }

    public ArrayList<String> getPaths() {
        return paths;
    }

    TextFinder(Importer importer, Tokenizer exactTokenizer) {
        this.importer = importer;
        this.tokenizer = exactTokenizer;
    }


    void preprocess(File folder) {
        System.out.println(System.currentTimeMillis());
        paths = importer.importer(folder);
        tokenizer.tokenize(this);
        System.out.println(System.currentTimeMillis());
    }


    @Override
    public void searchBar(String input) {
        System.out.println("Files containing this word:\n");
        ArrayList<Set<String>> listOfListOfFiles = new ArrayList<>();
        String[] words = input.split(" ");
        for (String word : words) {
            System.out.println(word);
            if (this.getData().get(word.toLowerCase()) != null) {
                System.out.println(this.getData().get(word.toLowerCase()) + "\n");
                listOfListOfFiles.add(this.getData().get(word.toLowerCase()));
            } else {
                System.out.println("Oops! We didn't find any file.\nTry something else!");
            }

        }
        if (listOfListOfFiles.size() > 0) {
            Set<String> intersection = listOfListOfFiles.get(0);
            for (Set<String> scan : listOfListOfFiles.subList(1, listOfListOfFiles.size())) {
                intersection.retainAll(scan);
            }
            System.out.println("total:\n" + intersection);
        }
    }
}
