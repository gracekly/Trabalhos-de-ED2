import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.*;

public class Questao_1 {

    class Grafo_DFS {
        private int vertices;
        private LinkedList<Integer>[] adjList;

        // Construtor
        public Grafo_DFS(int vertices) {
            this.vertices = vertices;
            adjList = new LinkedList[vertices];
            for (int i = 0; i < vertices; i++) {
                adjList[i] = new LinkedList<>();
            }
        }

        // Método para adicionar uma aresta
        public void adicionarAresta(int origem, int destino) {
            adjList[origem].add(destino);
        }

        // Método para realizar DFS e retornar o número de arestas entre origem e destino
        public int numeroDeArestas(int origem, int destino) {
            boolean[] visitado = new boolean[vertices];
            int[] distancia = new int[vertices];
            Arrays.fill(distancia, -1);

            Stack<Integer> pilha = new Stack<>();
            pilha.push(origem);
            distancia[origem] = 0;

            while (!pilha.isEmpty()) {
                int vertice = pilha.pop();
                if (!visitado[vertice]) {
                    visitado[vertice] = true;

                    for (int adj : adjList[vertice]) {
                        if (distancia[adj] == -1 || distancia[adj] > distancia[vertice] + 1) {
                            distancia[adj] = distancia[vertice] + 1;
                        }
                        pilha.push(adj);
                    }
                }
            }

            return distancia[destino];
        }

        // Método para retornar um caminho de origem a destino
        public List<Integer> caminhoDFS(int origem, int destino) {
            boolean[] visitado = new boolean[vertices];
            int[] pai = new int[vertices];
            Arrays.fill(pai, -1);

            Stack<Integer> pilha = new Stack<>();
            pilha.push(origem);
            visitado[origem] = true;

            while (!pilha.isEmpty()) {
                int vertice = pilha.pop();

                for (int adj : adjList[vertice]) {
                    if (!visitado[adj]) {
                        visitado[adj] = true;
                        pai[adj] = vertice;
                        pilha.push(adj);
                        if (adj == destino) {
                            break;
                        }
                    }
                }
            }

            List<Integer> caminho = new LinkedList<>();
            for (int v = destino; v != -1; v = pai[v]) {
                caminho.add(v);
            }
            Collections.reverse(caminho);
            return caminho;
        }

        // Método para imprimir todas as arestas do tipo retorno
        public void imprimirArestasRetorno() {
            boolean[] visitado = new boolean[vertices];
            boolean[] pilhaDeRecursao = new boolean[vertices];

            for (int i = 0; i < vertices; i++) {
                if (!visitado[i]) {
                    imprimirArestasRetornoUtil(i, visitado, pilhaDeRecursao);
                }
            }
        }

        private void imprimirArestasRetornoUtil(int vertice, boolean[] visitado, boolean[] pilhaDeRecursao) {
            visitado[vertice] = true;
            pilhaDeRecursao[vertice] = true;

            for (int adj : adjList[vertice]) {
                if (!visitado[adj]) {
                    imprimirArestasRetornoUtil(adj, visitado, pilhaDeRecursao);
                } else if (pilhaDeRecursao[adj]) {
                    System.out.println("Aresta de retorno: " + vertice + " -> " + adj);
                }
            }

            pilhaDeRecursao[vertice] = false;
        }

    }

    public void  run(){
        Grafo_DFS grafo = new Grafo_DFS(6);

        grafo.adicionarAresta(0, 1);
        grafo.adicionarAresta(1, 2);
        grafo.adicionarAresta(2, 0);
        grafo.adicionarAresta(2, 3);
        grafo.adicionarAresta(3, 4);
        grafo.adicionarAresta(4, 5);
        grafo.adicionarAresta(5, 3);

        int origem = 0;
        int destino = 4;

        System.out.println("Número de arestas de " + origem + " a " +destino + ": "+ grafo.numeroDeArestas(origem, destino));

        System.out.println("Caminho de 0 a 3: " + grafo.caminhoDFS(origem, destino));

        System.out.println("Arestas de retorno:");
        grafo.imprimirArestasRetorno();
    }

}
