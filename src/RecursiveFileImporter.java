import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

class RecursiveFileImporter {

    RecursiveFileImporter(File file, ArrayList<String> fileNames, ArrayList<String> paths) {
        readAllFiles(file, fileNames, paths);
    }

    private void readAllFiles(File file, ArrayList<String> fileNames, ArrayList<String> paths) {
        for (File subFile : Objects.requireNonNull(file.listFiles())) {
            if (subFile.isFile()) {
                fileNames.add(subFile.getName());
                paths.add(subFile.getPath());
            } else if (subFile.isDirectory()) {
                new RecursiveFileImporter(subFile, fileNames, paths);
            }
        }
    }
}
