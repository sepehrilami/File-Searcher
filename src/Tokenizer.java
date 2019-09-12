import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public interface Tokenizer {

    HashSet<String> tokenize(String fileData);
}