
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Questao_2 {

    class Aresta {
        int origem, destino, peso;

        Aresta(int origem, int destino, int peso) {
            this.origem = origem;
            this.destino = destino;
            this.peso = peso;
        }
    }

    class Grafo {
        int V, E;
        List<Aresta> arestas;

        Grafo(int V, int E) {
            this.V = V;
            this.E = E;
            arestas = new ArrayList<>(E);
        }

        void adicionarAresta(int origem, int destino, int peso) {
            arestas.add(new Aresta(origem, destino, peso));
        }

        int encontrar(int pai[], int i) {
            if (pai[i] != i)
                pai[i] = encontrar(pai, pai[i]);
            return pai[i];
        }

        void unir(int pai[], int rank[], int x, int y) {
            int raizX = encontrar(pai, x);
            int raizY = encontrar(pai, y);

            if (rank[raizX] < rank[raizY]) {
                pai[raizX] = raizY;
            } else if (rank[raizX] > rank[raizY]) {
                pai[raizY] = raizX;
            } else {
                pai[raizY] = raizX;
                rank[raizX]++;
            }
        }

        List<Aresta> reverseDeleteMST() {
            // Passo 2: Ordenar as arestas em ordem decrescente de peso
            Collections.sort(arestas, (a, b) -> b.peso - a.peso);

            // Inicializar subconjuntos para union-find
            int pai[] = new int[V];
            int rank[] = new int[V];

            for (int i = 0; i < V; i++) {
                pai[i] = i;
                rank[i] = 0;
            }

            List<Aresta> resultado = new ArrayList<>();

            // Inicialmente, incluir todas as arestas
            for (Aresta aresta : arestas) {
                resultado.add(aresta);
            }

            // Passo 3: Para cada aresta na ordem ordenada, verificar se pode ser removida
            for (Aresta aresta : arestas) {
                // Remover a aresta
                resultado.remove(aresta);

                // Verificar se o grafo ainda está conectado
                // Criar uma estrutura union-find para as arestas restantes
                int tempPai[] = new int[V];
                int tempRank[] = new int[V];
                for (int i = 0; i < V; i++) {
                    tempPai[i] = i;
                    tempRank[i] = 0;
                }

                for (Aresta e : resultado) {
                    int x = encontrar(tempPai, e.origem);
                    int y = encontrar(tempPai, e.destino);
                    if (x != y) {
                        unir(tempPai, tempRank, x, y);
                    }
                }

                // Verificar se o grafo está desconectado
                boolean estaConectado = true;
                int raiz = encontrar(tempPai, 0);
                for (int i = 1; i < V; i++) {
                    if (encontrar(tempPai, i) != raiz) {
                        estaConectado = false;
                        break;
                    }
                }

                // Se o grafo estiver desconectado, readicionar a aresta
                if (!estaConectado) {
                    resultado.add(aresta);
                }
            }

            return resultado;
        }

    }

    public void run() {
        int V = 4;
        int E = 5;
        Grafo grafo = new Grafo(V, E);

        grafo.adicionarAresta(0, 1, 10);
        grafo.adicionarAresta(0, 2, 3);
        grafo.adicionarAresta(0, 3, 5);
        grafo.adicionarAresta(1, 3, 15);
        grafo.adicionarAresta(2, 3, 6);

        List<Aresta> mst = grafo.reverseDeleteMST();

        System.out.println("Arestas na MST:");
        for (Aresta aresta : mst) {
            System.out.println(aresta.origem + " -- " + aresta.destino + " == " + aresta.peso);
        }
    }



}
