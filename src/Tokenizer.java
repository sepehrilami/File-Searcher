import java.util.HashSet;

public interface Tokenizer {

    HashSet<String> tokenize(String fileData);
}