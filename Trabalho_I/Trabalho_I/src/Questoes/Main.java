package Questoes;
import java.util.Scanner;
import java.util.Random;
import Algorithms.Generic_Functions;
import Structs.Generics;

public class Main <T extends Comparable<T>> {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		
		
		boolean run = true;
		
		//Menu
		while(run) {
			System.out.println("--------  MENU  --------");
			System.out.println("[1] - Resolver Questão 1");
			System.out.println("[2] - Resolver Questão 2");
			System.out.println("[3] - Resolver Questão 3");
			System.out.println("[4] - Resolver Questão 4");
			System.out.println("[5] - Sair");
			System.out.println();
			System.out.print("Selecione uma opção: ");
			
			int escolha = 0;
			
			try {
				escolha = scanner.nextInt();
			} 
			catch(Exception e){
				System.out.println("Entrada Inválida");
				scanner.nextLine();
			}
			
			switch(escolha) { 
				case 1:
					questao_01();
					break;
				case 2:
					questao_02();
					break;
				case 3:
					questao_03();
					break;
				case 4:
					questao_04();
					break;
				case 5:
					System.out.println("Agradecemos por usar nossos Algoritmos!");
					run = false;
					break;
				default:
					System.out.println();
			}
			
		}
		
		scanner.close();
	}
	
	//Questao 01
	public static void questao_01() {

		System.out.println("Vetor original: ");
		Generics<?,?>[] vetor = Generic_Functions.imprimeVetorRandomGenerics(15, 0, 1000);

		System.out.println();

		System.out.println("Vetor após MergeSort modificado:");
		Questao_01.modifiedMergeSort(vetor);
		Generic_Functions.imprimeVetorGenerics(vetor);

	}
	
	//Questão 02
	public static void questao_02() {

		Generic_Functions<Integer> generic_functions =  new Generic_Functions<>();

		System.out.println("Vetor original: ");
		Integer[] vetor = criar_vetor(15);
		generic_functions.mostrar_vetor(vetor);

		System.out.println();

		System.out.println("Vetor após SelectSort modificado:");
		Questao_02.ModifiedSelectSort(vetor);
		generic_functions.mostrar_vetor(vetor);


	}
	
	//Questão 03
	public static void questao_03() {
		Generic_Functions<Integer> generic_functions =  new Generic_Functions<>();
		
		Questao_03<Integer> resp = new Questao_03<>();
		
		//Variavel para armazenar o Tempo de "Execução"
		long time;
		
		//Queremos um vetor com N elementos aleatorios (Nesse caso inteiros entre 0 e 1000) e temos dois valores de L para ir testando
		int n = 10000;
		int L1 = 0;
		int L2 = 5000;
		
		//Vetor Não aleatorio para Teste - Comentado por padrao
		//Integer[] vetor = {3,2,4,10,9,1,12,5,3,2,4,10,9,1,12,5,3,2,4,10,9,1,12,5,3,2,4,10,9,1,12,5,3,2,4,10,9,1,12,5,3,2,4,10,9,1,12,5};
		
		//Criamos o vetor e uma copia para analisarmos oa valores de L para o mesmo vetor aleatorio gerado
		Integer vetor[] = criar_vetor(n);
		Integer vetor_copy[] = vetor.clone();
		
		//Imprimir o Vetor e Verificar se está ordenado
		//Essa parte só a titulo de informação, para não precisarmos verificarmos elemento por elemento
		
		//System.out.println("Vetor Original:");
		//generic_functions.mostrar_vetor(vetor);
		generic_functions.verifica_ordenado(vetor);
		
		time = resp.quick_sort_part(vetor, 0, vetor.length-1, L1);
		
		//Imprimir o Vetor e o Tempo e Verificar se está ordenado
		
		//System.out.println("Vetor Resposta:");
		//generic_functions.mostrar_vetor(vetor);
		generic_functions.verifica_ordenado(vetor);
		System.out.println("O tempo em milisegundos foi de: " + time);
		
		//Mesma Lógica anterior repetida para o Vetor de Copia
		
		//System.out.println("Vetor Original:");
		//generic_functions.mostrar_vetor(vetor_copy);
		generic_functions.verifica_ordenado(vetor_copy);
		
		time = resp.quick_sort_part(vetor_copy, 0, vetor_copy.length-1, L2);
		
		//System.out.println("Vetor Resposta:");
		//generic_functions.mostrar_vetor(vetor_copy);
		generic_functions.verifica_ordenado(vetor_copy);
		System.out.println("O tempo em milisegundos foi de: " + time);

	}
	
	//Questão 04
	public static void questao_04() {
		
		Generic_Functions<Integer> generic_functions =  new Generic_Functions<>();
		
		Questao_04<Integer> resp = new Questao_04<>();
		
		//Criando o vetor e sua copia para servirem de HeapMax e HeapMin e o Vetor Resposta
		Integer[] vetor_max = {12,2,4,10,9,1,3,5};
		Integer[] vetor_min = vetor_max.clone();
		Integer[] answer = new Integer[vetor_max.length];
		
		//Imprime o Vetor e Verifica se está ordenado
		System.out.println("Vetor Original:");
		generic_functions.mostrar_vetor(vetor_max);
		generic_functions.verifica_ordenado(vetor_max);
	
		resp.double_heap_sort(vetor_max, vetor_min, answer);
		
		//Imprime o Vetor e Verifica se está ordenado
		System.out.println("Vetor Resposta:");
		generic_functions.mostrar_vetor(answer);
		generic_functions.verifica_ordenado(answer);
				
	}
	
	//Função de criar Inteiros randomicamente para gerarmos vetores maiores de maneira mais rapida
	public static Integer[] criar_vetor(int n) {
		Random generator = new Random();
		
		Integer[] array = new Integer[n];
		for(int i=0; i<n; i++) {
			array[i] = generator.nextInt(1001);
		}
		
		return array;
	}
	
}
