import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

class RecursiveFileImporter implements Importer {

    private ArrayList<String> paths = new ArrayList<>();
    private File file;

    RecursiveFileImporter(File file) {
        this.file = file;
    }


    @Override
    public ArrayList<String> readAllFiles() {
        for (File subFile : Objects.requireNonNull(file.listFiles())) {
            if (subFile.isFile()) {
                paths.add(subFile.getPath());
            } else if (subFile.isDirectory()) {
                this.file = subFile;
                readAllFiles();
            }
        }
        return paths;
    }

}
