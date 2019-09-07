import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

class RecursiveFileImporter implements Importer {

    private ArrayList<String> fileNames = new ArrayList<>();

    ArrayList<String> readAllFiles(File file, ArrayList<String> paths) {
        for (File subFile : Objects.requireNonNull(file.listFiles())) {
            if (subFile.isFile()) {
                fileNames.add(subFile.getName());
                paths.add(subFile.getPath());
            } else if (subFile.isDirectory()) {
                readAllFiles(subFile , paths);
            }
        }
        return fileNames;
    }
}
