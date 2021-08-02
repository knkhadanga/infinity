package main.miscs;

public class DijkstraNew {

    int noOfVertices;
    int[][] adjecencyList;

    DijkstraNew(int vertex){
        this.noOfVertices = vertex;
        adjecencyList = new int[noOfVertices][noOfVertices];
    }

    void addEdge (int source, int destination, int weight){
        //add weight from source to destination
        adjecencyList[source][destination] = weight;

        //add weight from destination to source
        adjecencyList[destination][source] = weight;
    }

    void shortestPath (int sourceVertex){
        int INFINITY = Integer.MAX_VALUE;
        //boolean array to store if the vertex is already visited or not
        boolean[] visited = new boolean[noOfVertices];
        int[] distance = new int[noOfVertices];

        //initialize the distance with infinity
        for (int i = 0; i < noOfVertices; i++){
            distance[i] = Integer.MAX_VALUE;
        }

        //set distance of source vertex to 0
        distance[sourceVertex] = 0;

        for (int i = 0; i < noOfVertices; i++){
            //get the visited with minimum distance
            int currentVertex = getVertexWithMinDistance(distance, visited);

            //mark it visited
            visited[currentVertex]  = true;

            for (int adjecent = 0; adjecent < noOfVertices; adjecent++){
                if (adjecencyList[currentVertex][adjecent] > 0){
                    if (adjecencyList[currentVertex][adjecent] != INFINITY && !visited[adjecent]){
                        int newDistance = adjecencyList[currentVertex][adjecent] + distance[currentVertex];
                        if (distance[adjecent] < newDistance ){
                            distance[adjecent] = newDistance;
                        }
                    }
                }
            }
        }
    }

    /**
     * method to find vertex with minimum distance which has not been visited
     */
    int getVertexWithMinDistance (int[] distance, boolean[] visited){
        int minDistance = Integer.MAX_VALUE;
        int vertex = -1;

        for (int i = 0; i < noOfVertices; i++){
            if (!visited[i] && distance[i] < minDistance){
                minDistance = distance[i];
                vertex = i;
            }
        }

        return vertex;
    }
}
