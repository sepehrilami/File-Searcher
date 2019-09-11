import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ExactTokenizer implements Tokenizer {

    @Override
    public Set<String> tokenize(String path, HashMap<String, HashSet<String>> data) {
        File file = new File(path);
        try (BufferedReader ignored = new BufferedReader(new FileReader(file))) {

            String fileData = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);

            for (String string : fileData.split(" ")) {
                data.putIfAbsent(string.toLowerCase(), new HashSet<>());
                data.get(string.toLowerCase()).add(file.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.keySet();
    }

}
