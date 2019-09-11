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
    public void tokenize(String path, HashMap<String, HashSet<String>> data) {
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
    }

    @Override
    public Set<String> tokenizeInput(String input ,HashMap<String, HashSet<String>> data ) {
        ArrayList<Set<String>> listOfListOfFiles = new ArrayList<>();
        String[] words = input.split(" ");
        for (String word : words) {
            System.out.println(word);
            if (data.get(word.toLowerCase()) != null) {
                System.out.println(data.get(word.toLowerCase()) + "\n");
                listOfListOfFiles.add(data.get(word.toLowerCase()));
            } else {
                System.out.println("Oops! We didn't find any file.\nTry something else!");
            }

        }
        if (listOfListOfFiles.size() > 0) {
            Set<String> intersection = listOfListOfFiles.get(0);
            for (Set<String> scan : listOfListOfFiles.subList(1, listOfListOfFiles.size())) {
                intersection.retainAll(scan);
            }
            //System.out.println("total:\n" + intersection);
            return intersection;
        }
        return null;
    }
}
