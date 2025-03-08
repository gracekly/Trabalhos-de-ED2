package Questoes;
import Algorithms.QuickSort;
import Algorithms.BubbleSort;

public class Questao_03<T extends Comparable<T>> {
	
	QuickSort<T> quick_sort = new QuickSort<>();
	BubbleSort<T> bubble_sort = new BubbleSort<>();
	
	public long quick_sort_part(T[] array, int inicio, int fim, int L) {
		
		//Variaveis para compararmos os Tempos
		long start;
		long time;
		
		//Iniciar e Finalizar a contagem do Tempo após a execucação do Algoritmo
		start = System.currentTimeMillis();
		quick_sort.quick_sort(array, inicio, fim, L);
		time = (System.currentTimeMillis()-start);
		
		//Retornar o tempo necessario para execução
		return time;

	}

}
