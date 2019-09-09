import java.io.File;
import java.util.ArrayList;

public interface Importer {

    ArrayList<String> readAllFiles(File folder);

}
