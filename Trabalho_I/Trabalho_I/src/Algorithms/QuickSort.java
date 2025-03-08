package Algorithms;

public class QuickSort<T extends Comparable<T>> {
	
	BubbleSort<T> bubble = new BubbleSort<>();
	
	public void quick_sort(T[] array, int inicio, int fim, int L) {
		if(inicio < fim) {
			if(fim-inicio > L) {
				int posicao_pivo = particiona_vetor(array, inicio, fim);
				quick_sort(array, inicio, posicao_pivo-1, L);
				quick_sort(array, posicao_pivo+1, fim, L);
			} else {
				bubble.buble_sort(array, inicio, fim);
			}
		}
	}
	
	
	public int particiona_vetor(T[] array, int inicio, int fim) {
		quick_mediana(array, inicio, fim);
		T pivo = array[inicio];
		
		int i = inicio+1; int j = fim;
		
		while(i <= j) {
			if(pivo.compareTo(array[i]) >= 0) {
				i++;
			} else if(array[j].compareTo(pivo) > 0) {
				j--;
			} else {
				T aux = array[i];
				array[i] = array[j];
				array[j] = aux;
				i++;
				j--;
			}
		}
		array[inicio] = array[j];
		array[j] = pivo;
		return j;
	}
	
	public void quick_mediana(T[] array, int inicio, int fim) {
		int maior = inicio;
		
		if(array[fim].compareTo(array[maior]) > 0) {
			maior = fim;
		} else if(array[(inicio+fim)/2].compareTo(array[maior]) > 0){
			maior = (inicio+fim)/2;
		}
		
		T aux = array[inicio];
		array[inicio] = array[maior];
		array[maior] = aux;
	}
	
	public static void mostrar_vetor(Object[] vec) {
        System.out.print("[ ");
        for(int i=0; i<vec.length; i++){
            System.out.print(vec[i] + " ");
        }
        System.out.print("]");
        System.out.println();
	}

}
