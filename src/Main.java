import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        File folder = new File("/home/sepehr/Downloads/test_files");
        ArrayList<String> realFiles = new ArrayList<>();
        ArrayList<String> paths = new ArrayList<>();
        System.out.println(System.currentTimeMillis());
        TextFinder textFinder = new TextFinder();
        new RecursiveFileImporter(folder, realFiles, paths);
        for (String path : paths) {
            File file = new File(path);
            System.out.println("file is: " + path);
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String fileData = bufferedReader.readLine();
                for (String string : fileData.split(" ")) {
                    System.out.println(string);
                    textFinder.data.putIfAbsent(string, new ArrayList());
                    textFinder.data.get(string).add(file.getName());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(System.currentTimeMillis());
        /*for (String realFile : paths) {
            System.out.println(realFile);
        }*/
        System.out.println();
        System.out.println(textFinder.data.values());
        System.out.println(realFiles.size());
    }

}