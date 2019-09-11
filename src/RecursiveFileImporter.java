import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

class RecursiveFileImporter implements Importer {

    private ArrayList<String> paths = new ArrayList<>();

    @Override
    public ArrayList<String> importData(File file) {
        for (File subFile : Objects.requireNonNull(file.listFiles())) {
            if (subFile.isFile()) {
                paths.add(subFile.getPath());
            } else if (subFile.isDirectory()) {
                importData(subFile);
            }
        }
        return paths;
    }
}
