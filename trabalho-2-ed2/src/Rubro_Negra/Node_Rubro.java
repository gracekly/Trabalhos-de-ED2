package Rubro_Negra;

import java.util.ArrayList;

public class Node_Rubro<T extends Comparable<T>> {

    T data;
    Node_Rubro<T> left, right, parent;
    ArrayList <Integer> linhas =  new ArrayList<Integer>();
    boolean cor;

    //No da Arvore B Apenas modificado para armazenar uma Palavra, que é o valor que realmente usamos para inserir na arvore
    //E armazenamos os indices (linhhas) em que a palavra aparece
    public Node_Rubro(T data, int index) {
        this.data = data;
        this.cor = true;
        this.linhas.add(index);
    }

    public void addindex(int index) {
        this.linhas.add(index);
    }
}



