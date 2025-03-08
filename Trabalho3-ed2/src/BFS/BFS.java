package BFS;

import java.util.*;

public class BFS {

    public static void bFSearch(Graph G, Vertex s) {

        for(Vertex u: G.vertices) {

            if(!u.equals(s))  {//se u for diferente do vertice de inicio
                u.color = "white";
                u.distance = Integer.MAX_VALUE;
                u.pi = null;
            }
        }

        s.color = "gray";
        s.distance = 0;
        s.pi = null;

        Queue<Vertex> queue = new LinkedList<>();
        queue.add(s);

        while(!queue.isEmpty()) {
            Vertex u = queue.poll(); //dequeue

            for (Vertex v: u.adjacents) {
                if(v.color.equals("white")) {
                    v.color = "gray";
                    v.distance = u.distance + 1;
                    v.pi = u;
                    queue.add(v);
                }
            }
            u.color = "black";
        }
    }


    public static int getNumberEdges(Graph G, Vertex origin, Vertex destination) {
        bFSearch(G, origin);

        if(destination.distance == Integer.MAX_VALUE) {
            return -1;
        }
        return destination.distance;
    }


    public static List<Vertex> printPath(Graph G, Vertex origin, Vertex destination) {
        bFSearch(G, origin);
        if(destination.distance == Integer.MAX_VALUE) {
            return null;
        }
         List<Vertex> path = new ArrayList<>();
        for (Vertex v = destination; v != null; v = v.pi) {
            path.add(v);
        }
        Collections.reverse(path);
        return path;
    }


    public static List<Vertex> getDistance(Graph G, Vertex origin, int distance) {
        bFSearch(G, origin);

        List<Vertex> verticesD = new ArrayList<>();
        for(Vertex v : G.vertices) {
            if (v.distance == distance) {
                verticesD.add(v);
            }
        }
        return verticesD;
    }
}
