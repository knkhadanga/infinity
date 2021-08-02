package main.miscs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * This class has Graph specific traversal
 */
public class Graph {

    //no of vertex in graph
    int vertices;

    //each list in array represents the list of adjecent vertex
    List<Integer>[] adjecency;

    Graph(int noOfVertices) {
        this.vertices = noOfVertices;
        adjecency = new LinkedList[vertices];

        //each list holds adjecency information about it's own vertices
        for (int i = 0; i < vertices; i++) {
            adjecency[i] = new LinkedList<>();
        }
    }

    void addEdge(int vertex, int adjecent) {
        adjecency[vertex].add(adjecent);
    }

    void breadthFirstTraversal(int startingVertex) {
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();

        visited[startingVertex] = true; //mark the vertex visited
        queue.add(startingVertex);

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();

            System.out.print(" " + currentVertex);

            List<Integer> neighbors = adjecency[currentVertex];

            for (int neighbor : neighbors) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }

    void dfs(int vertex) {
        boolean[] visited = new boolean[vertices];
        depthFirstTraversal(vertex, visited);
    }

    void depthFirstTraversal(int vertex, boolean[] visited) {
        visited[vertex] = true;
        System.out.print(" " + vertex);

        List<Integer> neighbors = adjecency[vertex];

        for (int currentVertex : neighbors) {
            if (visited[currentVertex] != true) {
                depthFirstTraversal(currentVertex, visited);
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(6);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);


        System.out.println("Following is Breadth First Traversal starting from vertex 2)");

        g.breadthFirstTraversal(2);

        System.out.println("\nFollowing is Depth First Traversal starting from vertex 2)");
        g.dfs(2);
    }

}
