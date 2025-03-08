package Questoes;
import Algorithms.HeapSort;
public class Questao_04 <T extends Comparable<T>> {
	HeapSort<T> heapsort = new HeapSort<>();
	
	public void double_heap_sort(T[] array,T[]copy, T[]answer) {
		int n = array.length;
		
		//Variaveis para Controlar a Inserção no vetor de Resposta
		int end = n-1;
		int begin = 0;
		//Prepara os dois vetores, construindo um com MaxHeap e outro com MinHeap
		heapsort.build_max_heap(array);
		answer[end] = array[0];
		heapsort.build_min_heap(copy);
		answer[begin] = copy[0];
		
		//Primeiras trocas já com o resultado obtido do build_
		T aux = array[0];
		array[0] = array[end];
		array[end] = aux; 
		
		aux = copy[0];
		copy[0] = copy[end];
		copy[end] = aux; 
		
		end--;
		begin++;
		
		//Interando sobre metade do tamanhho do vetor e coordernando as inserções
		for(int i=n-2; i>n/2-1; i--) {
			heapsort.max_heapfy(array, 0, i);
			answer[end] = array[0];
			aux = array[0];
			array[0] = array[i];
			array[i] = aux;
			
			heapsort.min_heapfy(copy,  0,  i);
			answer[begin] = copy[0];
			aux = copy[0];
			copy[0] = copy[i];
			copy[i] = aux;
			
			end--;
			begin++;
		}
		
	}
	
}
