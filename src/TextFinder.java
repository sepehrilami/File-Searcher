import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

class TextFinder implements  SearchType {

    private HashMap<String, HashSet<String>> data = new HashMap<>();

    private ArrayList<String> paths;
    private ExactTokenizer exactTokenizer;
    private  RecursiveFileImporter recursiveFileImporter;

    HashMap<String, HashSet<String>> getData() {
        return data;
    }

    public ArrayList<String> getPaths() {
        return paths;
    }

    TextFinder( RecursiveFileImporter recursiveFileImporter , ExactTokenizer exactTokenizer) {
        this.recursiveFileImporter = recursiveFileImporter;
        this.exactTokenizer = exactTokenizer;
    }


    void preprocess(File folder) {
        System.out.println(System.currentTimeMillis());
        paths = recursiveFileImporter.readAllFiles(folder);
        exactTokenizer.tokenize(this);
        System.out.println(System.currentTimeMillis());
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
