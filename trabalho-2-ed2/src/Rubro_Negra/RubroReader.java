package Rubro_Negra;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;

//Usamos essa classe para ler do arquivo e ao mesmo tempo já montar a Arvore Rubro Negra com as Palavras e os Indices
//Ja unindo também as funções da Arvore Rubro Negra, para que precisemos chamr só a partir dessa
public class RubroReader {
    private String filePath;

    private int min_character_length;
    private Arvore_Rubro<String> Rubrotree;

    public RubroReader(String filePath, int min_character_length) {
        this.filePath = filePath;

        this.min_character_length = min_character_length + 1;
        this.Rubrotree = new Arvore_Rubro<>();

        readerRubro();
    }

    public void readerRubro() {


        try (BufferedReader br = new BufferedReader(new FileReader(this.filePath))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    word = normalizeWord(word);
                    if (!word.isEmpty()) {
                        if (word.length() >= min_character_length) {
                            Rubrotree.insert(word, lineNumber);
                        }

                    }
                }
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
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

    public void printRubro() {
        Rubrotree.preOrder(Rubrotree.getRoot());
    }

    public void search(String key) {
        Rubrotree.imprime_node(Rubrotree.search(key));
    }

    public void remove(String key) {
        Rubrotree.remove(key);
    }

    public void insert(String key, int index) {
        Rubrotree.insert(key, index);
    }

}