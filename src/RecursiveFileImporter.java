import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Objects;

class RecursiveFileImporter implements Importer {

    private HashMap<String, String> titleAndTexts = new HashMap<>();
    private File file;

    public RecursiveFileImporter(File file) {
        this.file = file;
    }

    @Override
    public HashMap<String, String> importData() {
        for (File subFile : Objects.requireNonNull(file.listFiles())) {
            if (subFile.isFile()) {
                String fileData = null;
                try {
                    fileData = new String(Files.readAllBytes(Paths.get(subFile.getPath())), StandardCharsets.UTF_8);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                titleAndTexts.putIfAbsent(subFile.getName(), fileData);
            } else if (subFile.isDirectory()) {
                this.file = subFile;
                importData();
            }
        }
        return titleAndTexts;
    }
}
