package Arvore_B;



import java.io.*;

public class Arvore_B<T extends Comparable<T>> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Node_B<T> root;
    private int t; // Grau mínimo
    private String filePath;


    public Arvore_B(int t, String filePath) {
        this.root = null;
        this.t = t;
        this.filePath = filePath;
    }


    //Função de Inserir seguindo a lógica correta
    //Chama as funções de um Nó B, quando necessário, Inserir realizando o Split e depois inserindo no No nao cheio gerado
    public void insert(T key) {
        if (root == null) {
            root = new Node_B<T>(t, true);
            root.keys.add(0, key);
            root.numKeys = 1;
        } else {
            if (root.numKeys == 2 * t - 1) {
                Node_B<T> s = new Node_B<T>(t, false);
                s.children.add(0, root);
                s.splitChild(0, root);

                int i = 0;
                if (s.keys.get(0).compareTo(key) < 0) {
                    i++;
                }
                s.children.get(i).insertNonFull(key);

                root = s;
            } else {
                root.insertNonFull(key);
            }
        }
    }


    //Salvar os dados para o arquivo
    //Aqui na verade só salvamos o Objeto Todo, que é a Arvore, serializando - o
    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Carregar a Arvore do Arquivo
    //Vai dar erro quando tentarmos carregar uma Arvore de um path que nao exista
    public static <T extends Comparable<T>> Arvore_B<T> loadFromFile(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Arvore_B<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    //Imprimir a Arvore chamand a função de imprimir cada Nó
    public void printTree() {
        if (root != null) {
            root.printNode(0);
        }
    }

    //Procurar uma chave na Arvore chama procurar uma chave independende em um Nó
    public T search(T key) {
        return (root == null) ? null : root.search(key);
    }

    //Remover da Arvore
    public void remove(T key) {
        if (root != null) {
            root.remove(key);
            if (root.numKeys == 0) {
                if (root.isLeaf) {
                    root = null;
                } else {
                    root = root.children.get(0);
                }
            }
        }
    }













}