import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class Test {

    public static void main(String[] args) {
        File folder = new File("/home/sepehr/Downloads");
        ArrayList<String> realFiles = new ArrayList<>();
        System.out.println(System.currentTimeMillis());
        fileFinder(folder, realFiles);
        System.out.println(System.currentTimeMillis());
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
                fileFinder(subFile, files);
            }
        }
    }
}