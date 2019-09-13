import java.util.Arrays;
import java.util.HashSet;

public class InputTokenizer implements Tokenizer {

    @Override
    public HashSet<String> tokenize(String input) {
        return new HashSet<>(Arrays.asList(input.split(" ")));
    }

}
