import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;

public class ExactTokenizer implements Tokenizer {

    @Override
    public void tokenize(TextFinder textFinder) {
        for (String path : textFinder.getPaths()) {
            File file = new File(path);
            try (BufferedReader ignored = new BufferedReader(new FileReader(file))) {

                String fileData = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);

                for (String string : fileData.split(" ")) {
                    textFinder.getData().putIfAbsent(string, new HashSet<>());
                    textFinder.getData().get(string).add(file.getName());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
