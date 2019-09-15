import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class ExactTokenizer implements Tokenizer {

    @Override
    public Set<String> tokenize(String fileData) {
        return Arrays.stream(fileData.split(" ")).map(String::toLowerCase).collect(Collectors.toSet());
    }
}