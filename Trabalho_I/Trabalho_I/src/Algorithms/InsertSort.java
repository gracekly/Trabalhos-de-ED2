package Algorithms;
import Structs.Generics;

public class InsertSort {

    public static void insertSort(Generics<?, ?>[] vetor) {

        int i, j;
        Generics<?, ?> aux;

        for (i = 1; i < vetor.length; i++) {
            aux = vetor[i];
            j = i - 1;
            while (j >= 0 && vetor[j].compareTo(aux) > 0) { //se o resultado da comparação for maior q 0, significa que o elemento na posição v[j] é maior que aux
                vetor[j + 1] = vetor[j];
                j--;
            }
            vetor[j + 1] = aux;
        }
    }


}


