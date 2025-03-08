package Algorithms;

public class HeapSort<T extends Comparable<T>> {
	
	public void build_max_heap(T[] array) {
		int n = array.length;
		for(int i=n/2-1; i>=0; i--) {
			max_heapfy(array, i, n);
		}
	}
	
	public void max_heapfy(T[] array, int i, int n) {
		int left = 2*i;
		int right = 2*i+1;
		int largest = i;
		
		if(left < n && array[left].compareTo(array[largest]) > 0) {
			largest = left;
		}
		
		if(right < n && array[right].compareTo(array[largest]) > 0) {
			largest = right;
		}
		
		if(largest != i) {
			T aux = array[i];
			array[i] = array[largest];
			array[largest] = aux; 
			max_heapfy(array, largest, n);
		}
		
	}
	
	public void max_heap_sort(T[] array) {
		
		int n = array.length;
		build_max_heap(array);
		
		
		T aux = array[0];
		array[0] = array[n-1];
		array[n-1] = aux; 
		
		for(int i=n-2; i>0; i--) {
			min_heapfy(array, 0, i);
			aux = array[0];	
			array[0] = array[i];
			array[i] = aux; 
		}	
	}
	
	public void build_min_heap(T[] array) {
		int n = array.length;
		for(int i=n/2-1; i>=0; i--) {
			min_heapfy(array, i, n);
		}
		
	}
	
	public void min_heapfy(T[] array, int i, int n) {
		int left = 2*i;
		int right = 2*i+1;
		int minimum = i;
		
		if(left < n && array[minimum].compareTo(array[left]) > 0) {
			minimum = left;
		}
		
		if(right < n && array[minimum].compareTo(array[right]) > 0) {
			minimum = right;
		}
		
		if(minimum != i) {
			T aux = array[i];
			array[i] = array[minimum];
			array[minimum] = aux;
			min_heapfy(array, minimum, n);
		}
		
	}
	
	public void min_heap_sort(T[] array) {
		
		int n = array.length;
		build_min_heap(array);
	
		 
		T aux = array[0];
		array[0] = array[n-1];
		array[n-1] = aux; 
	
		for(int i=n-2; i>0; i--) {
			min_heapfy(array, 0, i);
			aux = array[0];	
			array[0] = array[i];
			array[i] = aux; 
		}
	
		
	}
}
	