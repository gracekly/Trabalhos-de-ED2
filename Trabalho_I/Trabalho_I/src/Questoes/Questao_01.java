package Questoes;
import Algorithms.InsertSort;
import Structs.Generics;
public class Questao_01  {

    public static Generics<?, ?>[] modifiedMergeSort(Generics<?, ?>[] vetor){
        Generics<?, ?>[] vetor_aux = new Generics<?, ?>[vetor.length];

        return mergeMain(vetor, vetor_aux,0,vetor.length-1);
    }



    public static Generics<?, ?>[] mergeMain(Generics<?, ?>[] vetor, Generics<?, ?>[] vetor_aux, int inicio, int fim){

        int meio;

        if(inicio < fim){ //inicio e fim sao indices
            //se inicio<fim significa q o vetor tem mais de 1 elemento
            meio = (inicio + fim)/2;
            if(meio <= 15){
                InsertSort.insertSort(vetor);
                return vetor;
            }
            else {
                mergeMain(vetor, vetor_aux, inicio, meio);
                mergeMain(vetor, vetor_aux, meio + 1, fim);
                if(vetor[meio].compareTo(vetor[meio+1]) > 0) { //meio>meio+1 (nao esta em ordem)
                    Merge(vetor, vetor_aux, inicio, meio + 1, fim);
                }
            }
        }
        return vetor;
    }

    public static void Merge(Generics<?, ?>[] vetor, Generics<?, ?>[] vetor_aux, int esq_pos, int dir_pos, int dir_fim){
        //junta os elementos de forma ordenada
        int esq_fim = dir_pos - 1;
        int temp_pos = esq_pos;
        int numElem = dir_fim - esq_pos + 1;

        while(esq_pos <= esq_fim && dir_pos <= dir_fim){ //compara começo do vetor1 com final do vetor1 e começo do vetor2 com o fim do vetor2

            if(vetor[esq_pos].compareTo(vetor[dir_pos]) <= 0){ // if v1[inicio] < v2[inicio]
                vetor_aux[temp_pos++] = vetor[esq_pos++]; //vetor auxiliar recebe o menor elemento do v1 (vetor d esquerda)
            } else {
                //senao, recebe o menor elemento do v2 (vetor da direita)
                vetor_aux[temp_pos++] = vetor[dir_pos++];
            }
        }

        while(esq_pos <= esq_fim){ //se o vetor da direita acabou
            vetor_aux[temp_pos++] = vetor[esq_pos++]; //entao insere o resto dos elementos do vetor da esquerda no vetor aux
        }
        while (dir_pos <= dir_fim){ //se o vetor da esquerda acabou...
            vetor_aux[temp_pos++] = vetor[dir_pos++];
        }

        for(int i = 0; i<numElem; i++, dir_fim--){
            vetor[dir_fim] = vetor_aux[dir_fim];
        }
    }
}

