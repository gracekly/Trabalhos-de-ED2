package TreeAVL;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;

public class AVLReader {
    private String filePath;

    private int min_character_length;
    private AVLTree<String> avlTree;


    public AVLReader(String filePath, int min_character_length, int max_height_difference) {
        this.filePath = filePath;

        this.min_character_length = min_character_length + 1;
        this.avlTree = new AVLTree<>(max_height_difference);

        readerAVL();
    }

    public AVLTree<String> readerAVL() {


        try (BufferedReader br = new BufferedReader(new FileReader(this.filePath))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    word = normalizeWord(word);
                    if (!word.isEmpty()) {
                        if (word.length() >= min_character_length) {
                            avlTree.insert(word, lineNumber);
                        }

                    }
                }
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return avlTree;
    }

    private String normalizeWord(String word) {
        //remove acentuacao grafica
        word = Normalizer.normalize(word, Normalizer.Form.NFD);
        word = word.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        //reemove pontuacao
        word = word.replaceAll("\\p{Punct}", "");

        word = word.toLowerCase();

        return word;
    }

    public void printAvlTree() {
        avlTree.preOrder();
    }

    public void searchKeyInAVL(String key) {
        avlTree.search(key);
    }

    public void removeKeyFromAVL(String key) {
        avlTree.remove(key);
    }

}


