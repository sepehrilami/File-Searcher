import java.util.Arrays;
import java.util.HashSet;

public class ExactTokenizer implements Tokenizer {

    @Override
    public HashSet<String> tokenize(String fileData) {
        return new HashSet<>(Arrays.asList(fileData.split(" ")));
    }
}
