package Questoes;

public class Questao_02 {

    public static <T extends Comparable> T[] ModifiedSelectSort(T[] vetor) {

        int menor = 0;
        int maior = 0;
        int i;
        int j;
        int m;
        int n;

        int meio = vetor.length / 2;
        int tamanho = vetor.length;

        T aux_min;
        T aux_max;

        for (i = 0, j = tamanho - 1; i < meio; i++, j--) {//for "fixo"

            menor = i;//lidam com indices
            maior = j;

            for (m = i + 1, n = j - 1; m < tamanho; m++, n--) {//for q itera sobre todos os elementos
                if (vetor[m].compareTo(vetor[menor]) < 0) { //vetor[m]<vetor[menor]
                    menor = m;
                }

                if (vetor[n].compareTo(vetor[maior]) > 0) {
                    maior = n;
                }

            }
            //armazena os menores valores nos auxiliares
            aux_min = vetor[menor];
            aux_max = vetor[maior];

            //realiza as trocas para o menor
            vetor[menor] = vetor[i];
            vetor[i] = aux_min;


            if (maior == i) { // atualiza seu índice para a posição do elemento mínimo
                maior = menor; //caso o maior elemento esteja na posiçao correta do menor, ele recebe a antiga posiçao do menor
            }

            //realiza as trocas para o maior
            vetor[maior] = vetor[j];
            vetor[j] = aux_max;

        }

        return vetor;
    }
}