package BFS;

import java.util.*;

public class Vertex {

    public String name;
    String color;
    int distance;
    Vertex pi;
    List<Vertex> adjacents; //lista de adjacencias


    public Vertex(String name) {
        this.name = name;
        this.color = "white";
        this.distance = Integer.MAX_VALUE;
        this.pi = null;
        this.adjacents = new ArrayList<>(); //lista de adjacencias
    }


    public void addAdjacent(Vertex vertex) {
        this.adjacents.add(vertex);
    }


}

