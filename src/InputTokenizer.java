import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class InputTokenizer implements Tokenizer {

    @Override
    public Set<String> tokenize(String input, HashMap<String, HashSet<String>> data) {
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
