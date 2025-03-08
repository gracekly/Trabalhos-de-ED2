package Hash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;
import java.text.Normalizer;

public class Reader {

    private String filePath;
    private int min_character_length;
    private ProbingType probing;
    private HashTable<String, List<Integer>> hashTable;

    public Reader(String filePath, int min_character_length, HashTable hashtable ) {
        this.filePath = filePath;
        this.min_character_length = min_character_length + 1;
        this.hashTable = hashtable;
    }

    public void readStoreWords() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int line_number = 1;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    word = normalizeWord(word); //"formata" as palavras antes de inserir
                    if (word.length() >= min_character_length) {
                        if (hashTable.get(word) == null) {
                            hashTable.put(word, new ArrayList<>());
                        }
                        hashTable.get(word).add(line_number);
                    }
                }
                line_number++;
            }
        }
    }

    public void printHashTable() {
        List<String> sortedKeys = new ArrayList<>(hashTable.keys());
        Collections.sort(sortedKeys); // ordena as chaves em ordem alfabética

        for (String key : sortedKeys) {
            System.out.println("Palavra: " + key);
            List<Integer> lines = hashTable.get(key);
            for (Integer line : lines) {
                System.out.println("  Linha: " + line);
            }
        }
    }

    public void getWord(String word) {
        List<Integer> lines = hashTable.getPassos(word);
        if (lines != null) {
            System.out.println("Palavra '" + word + "' encontrada nas seguintes linhas:");
            for (Integer line : lines) {
                System.out.println("Linha: " + line);
            }
        } else {
            System.out.println("Palavra '" + word + "' não encontrada.");
        }
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
}