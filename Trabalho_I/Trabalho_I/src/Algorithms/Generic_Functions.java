package Algorithms;
import Structs.Generics;

import java.util.Random;

public class Generic_Functions<T extends Comparable<T>>{
	
	public void mostrar_vetor(T[] vec) {
        System.out.print("[ ");
        for(int i=0; i<vec.length; i++){
            System.out.print(vec[i] + " ");
        }
        System.out.print("]");
        System.out.println();
	}
	
	
	
	
	public void verifica_ordenado(T[] array) {
		boolean esta_ordenado = true;
		for(int i=1; i<array.length; i++) {
			if(array[i-1].compareTo(array[i]) > 0) {
				esta_ordenado = false;
				break;
			}
		}
		
		if(esta_ordenado) {
			System.out.println("O vetor ESTÁ Ordenado");
			System.out.println();
		} else {
			System.out.println("O vetor NÃO ESTÁ está Ordenado");
			System.out.println();
		}
		
	}



	public static Generics<?, ?>[] vetorRandomGenerics(int size, int min, int max) {
		Generics<?, ?>[] vetor = new Generics<?, ?>[size];

		Random random = new Random();

		for (int i = 0; i < vetor.length; i++) {
			Integer ordem = i; // numeros em ordem crescente
			Integer aleatorio = random.nextInt(max - min + 1) + min; // numeros aleatorios
			vetor[i] = new Generics<>(ordem, aleatorio);
		}
		return vetor;
	}



	public static Generics<?,?> []imprimeVetorRandomGenerics(int tamanho, int inicio, int fim) {
		Generics<?,?>[]vetor = vetorRandomGenerics(tamanho, inicio, fim);
		for (Generics<?, ?> elementos : vetor) {
			System.out.println(elementos);
		}
		return vetor;
	}


	public static void imprimeVetorGenerics(Generics<?,?> []vetor) {
		for (Generics<?, ?> elementos : vetor) {
			System.out.println(elementos);
		}
	}
}




