import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public interface Tokenizer {

    void tokenize(String path , HashMap<String, HashSet<String>> data);

    Set<String> tokenizeInput(String input , HashMap<String, HashSet<String>> data);
}