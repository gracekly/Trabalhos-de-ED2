package Dijkstra;

import java.util.*;

public class Dijkstra {

    public static void calculateShortestPaths(Node start_node) {
        if (start_node == null) {
            throw new IllegalArgumentException("Vértice inválido!");
        }

        start_node.setDistance(0);
        Set<Node> settledNodes = new HashSet<>(); //A
        Queue<Node> unsettledNodes = new PriorityQueue<>(Collections.singleton(start_node)); //Q - fila de prioridade

        while (!unsettledNodes.isEmpty()) { //while Q!= null
            Node currentNode = unsettledNodes.poll(); //extrai minimo

            for (Map.Entry<Node, Integer> entry : currentNode.getAdjacentNodes().entrySet()) { //iterando sobre os nós adjacentes do nó atual
                Node adjacentNode = entry.getKey();
                int edgeWeight = entry.getValue();

                if (!settledNodes.contains(adjacentNode)) { //se o no adjacente do no atual n estiver no conjunto solução

                    relax(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }

            }
            settledNodes.add(currentNode);
        }

        //relax2
        for (Node node : settledNodes) {
            for (Map.Entry<Node, Integer> entry : node.getAdjacentNodes().entrySet()) {
                Node adjacentNode = entry.getKey();
                int edgeWeight = entry.getValue();
                relax2(adjacentNode, edgeWeight, node);
            }
        }
    }

    private static void relax(Node adjacent_node, Integer edge_weight, Node source_node) { //verifica se ha um caminho mais curto para o no adjacente
        Integer new_distance = source_node.getDistance() + edge_weight;

        if (new_distance < adjacent_node.getDistance()) {
            adjacent_node.setDistance2(adjacent_node.getDistance());
            adjacent_node.setShortestPath2(new LinkedList<>(adjacent_node.getShortestPath()));

            adjacent_node.setDistance(new_distance);

            List<Node> newShortestPath = new LinkedList<>(source_node.getShortestPath());
            newShortestPath.add(source_node);

            adjacent_node.setShortestPath(newShortestPath);
        }
    }

    private static void relax2(Node adjacent_node, Integer edge_weight, Node source_node) {
        //System.out.println("relax 2");
        Integer new_distance = source_node.getDistance() + edge_weight;

        if (new_distance < adjacent_node.getDistance2() && new_distance > adjacent_node.getDistance()) {
            adjacent_node.setDistance2(new_distance);

            List<Node> newSecondShortestPath = new LinkedList<>(source_node.getShortestPath());
            newSecondShortestPath.add(source_node);

            adjacent_node.setShortestPath2(newSecondShortestPath);
        }
    }

    public static void printTwoShortestPaths(Node origin, Node destination) {

        calculateShortestPaths(origin);

        //menor caminho
        List<Node> shortestPath = destination.getShortestPath();
        StringBuilder pathBuilder = new StringBuilder();

        for (Node path : shortestPath) {
            pathBuilder.append(path.getName()).append(" -> ");
        }

        pathBuilder.append(destination.getName());
        System.out.println(String.format("Menor caminho: %s : %d", pathBuilder.toString(), destination.getDistance()));

        //segundo menor
        List<Node> secondShortestPath = destination.getShortestPath2();
        pathBuilder = new StringBuilder();

        for (Node path : secondShortestPath) {
            pathBuilder.append(path.getName()).append(" -> ");
        }

        pathBuilder.append(destination.getName());
        System.out.println(String.format("Segundo menor caminho: %s : %d", pathBuilder.toString(), destination.getDistance2()));
    }
}
