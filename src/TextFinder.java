import java.io.File;
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

    void preprocess(File folder) {
        System.out.println(System.currentTimeMillis());
        paths = importer.importData(folder);
        for (String path : this.getPaths()) {
            tokenizer.tokenize(path, this.getData());
        }
        System.out.println(System.currentTimeMillis());
    }

    @Override
    public Set<String> search(String input) {
        return searchTokenizer.tokenize(input, this.getData());
    }
}
