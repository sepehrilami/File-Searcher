import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class Test {

    public static void main(String[] args) {
        File folder = new File("/home/sepehr/Downloads/");
        ArrayList<String> realFiles = new ArrayList<>();
        fileFinder(folder, realFiles);
        for (String realFile : realFiles) {
            System.out.println(realFile);
        }
        System.out.println(realFiles.size());
    }

    private static void fileFinder(File file, ArrayList<String> files) {
        for (File subFile : Objects.requireNonNull(file.listFiles())) {
            if (subFile.isFile()) {
                files.add(subFile.getName());
            } else if (subFile.isDirectory()) {
                System.out.println("subfile name is: " + subFile.getName() + "-----------------------");
                fileFinder(subFile, files);
            }
        }
    }
}