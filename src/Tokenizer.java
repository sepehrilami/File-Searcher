import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public interface Tokenizer {

    Set<String> tokenize(String path , HashMap<String, HashSet<String>> data);
}