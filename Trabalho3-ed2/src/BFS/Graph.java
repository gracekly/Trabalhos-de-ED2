package BFS;

import java.util.*;

public class Graph {
    List<Vertex> vertices;

    public Graph() {
        this.vertices = new ArrayList<>();
    }


    public void addVertex(Vertex vertex){
        this.vertices.add(vertex);
    }


    public Vertex getVertex(String name) {
        for (Vertex v : vertices) {
            if(v.name.equals(name)){
                return v;
            }
        }
        return null;
    }
}
