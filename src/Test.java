import java.io.File;
import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {
        File folder = new File("/home/sepehr/Downloads/");
        ArrayList<String> realFiles = new ArrayList<>();
        ArrayList<String> paths = new ArrayList<>();
        System.out.println(System.currentTimeMillis());
        new RecursiveFileImporter(folder, realFiles, paths);
        System.out.println(System.currentTimeMillis());
        for (String realFile : paths) {
            System.out.println(realFile);
        }
        System.out.println(realFiles.size());
    }
}