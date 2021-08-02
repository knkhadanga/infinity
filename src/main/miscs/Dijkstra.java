package main.miscs;

public class Dijkstra {

    static final int INFINITY = Integer.MAX_VALUE;

    static class Graph {
        int vertices;
        int matrix[][];

        public Graph(int vertex) {
            this.vertices = vertex;
            matrix = new int[vertex][vertex];
        }

        public void addEdge(int source, int destination, int weight) {
            //add edge
            matrix[source][destination] = weight;

            //add back edge for undirected graph
            matrix[destination][source] = weight;
        }

        //get the vertex with minimum distance which is not visited
        int getVertexWithMinDistance(boolean[] visited, int[] distance) {
            int minKey = Integer.MAX_VALUE;
            int vertex = -1;
            for (int i = 0; i < vertices; i++) {
                if (!visited[i] && distance[i] < minKey) {
                    minKey = distance[i];
                    vertex = i;
                }
            }
            return vertex;
        }

        public void dijkstra_GetMinDistances(int sourceVertex) {
            boolean[] visited = new boolean[vertices];
            int[] distance = new int[vertices];


            //Initialize all the distance to infinity
            for (int i = 0; i < vertices; i++) {
                distance[i] = INFINITY;
            }

            //start from the vertex 0
            distance[sourceVertex] = 0;

            //create SPT
            for (int i = 0; i < vertices; i++) {

                //get the vertex with the minimum distance
                int currentVertex = getVertexWithMinDistance(visited, distance);

                //mark this vertex is visited
                visited[currentVertex] = true;

                //iterate through all the adjacent vertices of above vertex and update the keys
                for (int adjecent = 0; adjecent < vertices; adjecent++) {
                    //check of the edge weight between currentVertex and adjecentVertex
                    if (matrix[currentVertex][adjecent] > 0) {
                        //check if this adjecentVertex is not visited and
                        // if distance[adjecentVertex]!=Infinity

                        if ((!visited[adjecent]) && (matrix[currentVertex][adjecent] != INFINITY)) {
                            //check if distance needs an update or not
                            //means check total weight from source to currentVertex is less than
                            //the current distance value, if yes then update the distance

                            int newDistance = matrix[currentVertex][adjecent] + distance[currentVertex];
                            if (newDistance < distance[adjecent])
                                distance[adjecent] = newDistance;
                        }
                    }
                }
            }
            //print shortest path tree
            printDijkstra(sourceVertex, distance);
        }

        public void printDijkstra(int sourceVertex, int[] key) {
            System.out.println("Dijkstra Algorithm: (Adjacency Matrix)");
            for (int i = 0; i < vertices; i++) {
                System.out.println("Source Vertex: " + sourceVertex + " to vertex " + +i +
                        " distance: " + key[i]);
            }
        }
    }

    public static void main(String[] args) {
        int vertices = 6;
        Graph graph = new Graph(vertices);
        int sourceVertex = 0;
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 3, 4);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 5, 6);
        graph.dijkstra_GetMinDistances(sourceVertex);
    }
}