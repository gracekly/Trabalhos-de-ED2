package Algorithms;

public class BubbleSort<T extends Comparable<T>> {
	
	public void buble_sort(T[] array, int inicio, int fim) {
		
		for(int i=0; i<fim; i++) {
			for(int j=0; j<fim-i; j++) {
				if(array[j].compareTo(array[j+1]) > 0) {
					T aux = array[j];
					array[j] = array[j+1];
					array[j+1] = aux;
					
				}
			}
		}
	}
	
	
	public void mostrar_vetor(T[] vec) {
        System.out.print("[ ");
        for(int i=0; i<vec.length; i++){
            System.out.print(vec[i] + " ");
        }
        System.out.print("]");
        System.out.println();
	}

}
