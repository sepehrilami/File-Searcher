import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

class RecursiveFileImporter implements Importer {

    void readAllFiles(File file, ArrayList<String> paths) {
        for (File subFile : Objects.requireNonNull(file.listFiles())) {
            if (subFile.isFile()) {
                paths.add(subFile.getPath());
            } else if (subFile.isDirectory()) {
                readAllFiles(subFile , paths);
            }
        }
    }
}
