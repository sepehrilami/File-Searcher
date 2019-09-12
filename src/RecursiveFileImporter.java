import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

class RecursiveFileImporter implements Importer {

    private ArrayList<String> texts = new ArrayList<>();
    private File file;
    ArrayList<String> paths = new ArrayList<>();

    public RecursiveFileImporter(File file) {
        this.file = file;
        importData();
    }

    @Override
    public ArrayList<String> importData() {
        for (File subFile : Objects.requireNonNull(file.listFiles())) {
            if (subFile.isFile()) {
                paths.add(subFile.getPath());
            } else if (subFile.isDirectory()) {
                this.file = subFile;
                importData();
            }
        }
        return paths;
    }
}
