import BFS.Graph;
import BFS.Vertex;
import Dijkstra.Node;

import java.util.*;

import static BFS.BFS.*;
import static Dijkstra.Dijkstra.*;


public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Graph G = new Graph();
        Questao_1 q1 = new Questao_1();
        Questao_2 q2 = new Questao_2();


        //BFS---------------------------------------------------------------------------------------------
        Vertex A =new Vertex("A");
        Vertex B =new Vertex("B");
        Vertex C =new Vertex("C");
        Vertex D =new Vertex("D");
        Vertex E =new Vertex("E");
        Vertex F =new Vertex("F");

        A.addAdjacent(B);
        A.addAdjacent(C);
        A.addAdjacent(D);

        B.addAdjacent(A);
        B.addAdjacent(E);

        C.addAdjacent(A);
        C.addAdjacent(F);

        D.addAdjacent(A);

        E.addAdjacent(B);

        F.addAdjacent(C);

        G.addVertex(A);
        G.addVertex(B);
        G.addVertex(C);
        G.addVertex(D);
        G.addVertex(E);
        G.addVertex(F);



        //Dijkstra----------------------------------------------------------------------------------------------
        Node A1 = new Node("A");
        Node B1 = new Node("B");
        Node C1 = new Node("C");
        Node D1 = new Node("D");
        Node E1 = new Node("E");
        Node F1 = new Node("F");

        A1.setAdjacentNodes(B1, 2);
        A1.setAdjacentNodes(C1, 4);

        B1.setAdjacentNodes(C1, 3);
        B1.setAdjacentNodes(D1, 1);
        B1.setAdjacentNodes(E1, 5);

        C1.setAdjacentNodes(D1, 2);

        D1.setAdjacentNodes(E1, 1);
        D1.setAdjacentNodes(F1, 4);

        E1.setAdjacentNodes(F1, 2);

        List<Node> nodes = new ArrayList<>();
        nodes.add(A1);
        nodes.add(B1);
        nodes.add(C1);
        nodes.add(D1);
        nodes.add(E1);
        nodes.add(F1);



        //menu-------------------------------------------------------------------------------------------------
        boolean run = true;

        while (run) {

            System.out.println("--------        MENU       --------");

            System.out.println("[1] - Busca em Profundidade");
            System.out.println("[2] - Árvore Geradora Mínima");
            System.out.println("[3] - Caminho Mínimo - Dijkstra");
            System.out.println("[4] - Busca em Largura");

            System.out.println("[5] - Sair");
            System.out.println();
            System.out.print("Selecione uma opção: ");

            int escolha = 0;

            try {
                escolha = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Entrada Inválida");
                scanner.nextLine();
            }


            switch (escolha) {

                case 1:

                    q1.run();
                    break;

                case 2:
                    q2.run();
                    break;

                case 3:
                    scanner.nextLine();
                    System.out.println("Para retornar os dois menores caminhos entre um par de vértices");
                    System.out.println("Insira o vértice de Origem: ");
                    String origin_name4 = scanner.nextLine();
                    Node start_node = getNode(nodes, origin_name4);

                    System.out.println("Insira o vértice de Destino: ");
                    String destination_name4 = scanner.nextLine();
                    Node end_node = getNode(nodes, destination_name4);

                    if (start_node == null || end_node == null) {
                        System.out.println("Vértice inválido ou não há caminho.");
                    } else {

                        printTwoShortestPaths(start_node, end_node);
                    }


                    break;


                case 4:

                    boolean menu_interno1 = true;

                    while (menu_interno1) {
                        System.out.println("\n-----  BUSCA EM LARGURA -----\n");
                        System.out.println("[1] - Retornar número de arestas entre Origem e Destino:");
                        System.out.println("[2] - Retornar o caminho de Origem ao Destino:");
                        System.out.println("[3] - Retornar vérticecs a uma distância D:");
                        System.out.println("[4] - Voltar.");


                        int opcao = 0;
                        try {
                            opcao = scanner.nextInt();
                            scanner.nextLine();
                        } catch (Exception e) {
                            System.out.println("Entrada Inválida");
                            scanner.nextLine();
                        }
                        switch (opcao) {
                            case 1:

                                System.out.println("Insira o vértice de Origem: ");
                                String origin_name = scanner.nextLine();
                                Vertex origin = G.getVertex(origin_name);

                                System.out.println("Insira o vértice de Destino: ");
                                String destination_name = scanner.nextLine();
                                Vertex destination = G.getVertex(destination_name);

                                if (origin == null || destination == null) {
                                    System.out.println("Vértice inválido.");
                                    break;
                                }

                                int number_edges = getNumberEdges(G, origin, destination);

                                if (number_edges == -1) {
                                    System.out.println("O vértice " + destination_name + " não é alcançável a partir de " + origin_name);
                                } else {
                                    System.out.println("O número de arestas existentes entre " + origin_name + " e " + destination_name + " é: " + number_edges);
                                }

                                break;


                            case 2:

                                System.out.println("Insira o vértice de Origem: ");
                                String origin_name2 = scanner.nextLine();
                                Vertex origin2 = G.getVertex(origin_name2);

                                System.out.println("Insira o vértice de Destino: ");
                                String destination_name2 = scanner.nextLine();
                                Vertex destination2 = G.getVertex(destination_name2);

                                if (origin2 == null || destination2 == null) {
                                    System.out.println("Vértice inválido.");
                                    break;
                                }

                                List<Vertex> path = printPath(G, origin2, destination2);

                                if(path!=null) {
                                    System.out.println("Caminho de " + origin_name2 + " a " + destination_name2);
                                    for (Vertex v : path) {
                                        System.out.println(v.name + " ");
                                    }
                                }
                                else {
                                    System.out.println("O vértice " + destination_name2 + " não é alcançável a partir de " + origin_name2);
                                }

                                break;


                            case 3:
                                System.out.println("Insira o vértice de Origem: ");
                                String origin_name3 = scanner.nextLine();
                                Vertex origin3 = G.getVertex(origin_name3);

                                System.out.println("Insira a distância: ");
                                int distance = scanner.nextInt();

                                if(origin3 == null) {
                                    System.out.println("Vértice inválido");
                                    break;
                                }

                                List<Vertex> verticesD = getDistance(G, origin3, distance);
                                for(Vertex v: verticesD) {
                                    System.out.println(v.name + " ");
                                }
                                System.out.println();
                                break;

                            case 4:
                                menu_interno1 = false;
                                break;

                            default:
                                System.out.println("Opção inválida.");
                                break;
                        }
                    }

                    break;

                case 5:
                    System.out.println("Agradecemos por usar nossos Algoritmos!");
                    run = false;
                    break;
                default:
                    System.out.println();


            }
        }
    }

    private static Node getNode(List<Node> nodes, String node_name) {
        for (Node node : nodes) {
            if (node.getName().equals(node_name)) {
                return node;
            }
        }
        return null;
    }
}