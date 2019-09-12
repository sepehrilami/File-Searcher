import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

class RecursiveFileImporter implements Importer {

    HashMap<String, String> titleAndTexts = new HashMap<>();
    private File file;
    ArrayList<String> paths = new ArrayList<>();

    public RecursiveFileImporter(File file) {
        this.file = file;
        importData();
    }

    @Override
    public HashMap<String, String> importData() {
        for (File subFile : Objects.requireNonNull(file.listFiles())) {
            if (subFile.isFile()) {
                paths.add(subFile.getPath());
                try (BufferedReader ignored = new BufferedReader(new FileReader(subFile))) {
                    String fileData = new String(Files.readAllBytes(Paths.get(subFile.getPath())), StandardCharsets.UTF_8);
                    titleAndTexts.putIfAbsent(subFile.getName(), fileData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (subFile.isDirectory()) {
                this.file = subFile;
                importData();
            }
        }
        return titleAndTexts;
    }
}
