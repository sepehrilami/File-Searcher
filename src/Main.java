public class Main {

    public static void main(String[] args) {
        TextFinder textFinder = new TextFinder();
        Importer importer = null;
        Tokenizer tokenizer = null;
        textFinder.preprocess(importer , tokenizer);
    }
}