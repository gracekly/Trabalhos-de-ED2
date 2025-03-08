package Dijkstra;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node implements Comparable<Node> {

    private String name;
    private Integer distance;
    private Integer distance2;
    private List<Node> shortestPath;
    private List<Node> shortestPath2;
    private Map<Node, Integer> adjacentNodes;

    public Node(String name) {
        this.name = name;
        this.distance = Integer.MAX_VALUE;
        this.distance2 = Integer.MAX_VALUE;
        this.shortestPath = new LinkedList<>(); //lista dos nós q compoem o caminho mais curto ate este no
        this.shortestPath2 = new LinkedList<>(); //segundo caminho mais curto
        this.adjacentNodes = new HashMap<>(); //guarda os nos adjacentes e os pesos das arestas
    }

    public void setAdjacentNodes(Node node, int weight) {
        adjacentNodes.put(node, weight);
    }

    @Override
    public int compareTo(Node node) { //define a ordem em que a priority queue armazena os elementos(nesse caso em ordem crescente)
        return Integer.compare(this.distance, node.getDistance());
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public int getDistance2() {
        return distance2;
    }

    public void setDistance2(Integer distance2) {
        this.distance2 = distance2;
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public List<Node> getShortestPath2() {
        return shortestPath2;
    }

    public void setShortestPath2(List<Node> shortestPath2) {
        this.shortestPath2 = shortestPath2;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
