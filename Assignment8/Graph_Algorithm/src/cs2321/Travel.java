package cs2321;

import cs2321.Map.AdjListGraph;
import cs2321.ArrayList.ArrayList;
import cs2321.Map.HashMap;
import cs2321.PQ.HeapPQ;
import net.datastructures.*;

/**
 * @author Ruihong Zhang
 * Reference textbook R14.16 P14.81
 *
 */
public class Travel {

    //region Variables

    Graph<String, Integer> graph;
    Map<String, Vertex<String>> cities;
    Map<Vertex<String>, Boolean> visited;
    Map<Vertex<String>, Edge<Integer>> forest;

    //endregion

    //region Constructor

	/**
	 * @param routes: Array of routes between cities.
	 *                routes[i][0] and routes[i][1] represent the city names on both ends of the route.
	 *                routes[i][2] represents the cost in string type.
	 *                Hint: In Java, use Integer.valueOf to convert string to integer.
	 */
	public Travel(String[][] routes) {
		graph = new AdjListGraph<>();
		cities = new HashMap<>();
		visited = new HashMap<>();
		forest = new HashMap<>();

		for (String[] route : routes) {
		    Vertex<String> v1;
            Vertex<String> v2;
		    if (cities.get(route[0]) != null) {
		        v1 = cities.get(route[0]);
            } else {
		        v1 = graph.insertVertex(route[0]);
		        cities.put(route[0], v1);
            }

		    if (cities.get(route[1]) != null) {
		        v2 = cities.get(route[1]);
            } else {
		        v2 = graph.insertVertex(route[1]);
                cities.put(route[1], v2);
            }

		    graph.insertEdge(v1, v2, Integer.valueOf(route[2]));
        }
	}

	//endregion

    //region Depth First Search

	/**
	 * @param departure: the departure city name
	 * @param destination: the destination city name
	 * @return Return the path from departure city to destination using Depth First Search algorithm.
	 *         The path should be represented as ArrayList or DoublylinkedList of city names.
	 *         The order of city names in the list should match order of the city names in the path.
	 *
	 * @IMPORTANT_NOTE: The outgoing edges should be traversed by the order of the city names stored in
	 *                 the opposite vertices. For example, if V has 3 outgoing edges as in the picture below,
	 *                           V
	 *                        /  |  \
	 *                       /   |    \
	 *                      B    A     F
	 *              your algorithm below should visit the outgoing edges of V in the order of A,B,F.
	 *              This means you will need to create a helper function to sort the outgoing edges by
	 *              the opposite city names.
	 *
	 *              See the method sortedOutgoingEdges below.
	 */

    public Iterable<String> DFSRoute(String departure, String destination) {
        boolean exists = DFS(graph, cities.get(departure), cities.get(destination), visited, forest);   // Perform the DFS on the graph between the departure and destination vertex.
        return exists ? constructVertexPath(graph, cities.get(departure), cities.get(destination), forest) : null;  // If DFS cannot find a path, return nothing.
    }

    private boolean DFS(Graph<String, Integer> graph, Vertex<String> u, Vertex<String> v, Map<Vertex<String>, Boolean> visited, Map<Vertex<String>, Edge<Integer>> forest) {

        visited.put(u, true);                                   // Visit the starting vertex.

        if (u.equals(v)) {                                      // If we found the destination vertex, break out.
            return true;
        }

        for (Edge<Integer> e : sortedOutgoingEdges(graph, u)) {
            Vertex<String> w = graph.opposite(u, e);
            if (visited.get(w) == null) {                       // Test if the vertex is visited. If not:
                forest.put(w, e);                               // Put the edge and the opposing vertex into the forest and
                return DFS(graph, w, v, visited, forest);    // Recurse down the graph.
            }
        }
        return false;                                           // Otherwise, we did not find the destination vertex.
    }


    //endregion

    //region Breadth First Search

	/**
	 * @param departure: the departure city name
	 * @param destination: the destination city name
     * @return Return the path from departure city to destination using Breadth First Search algorithm.
	 *         The path should be represented as ArrayList or DoublylinkedList of city names.
	 *         The order of city names in the list should match order of the city names in the path.
	 *
	 * @IMPORTANT_NOTE: The outgoing edges should be traversed by the order of the city names stored in
	 *                 the opposite vertices. For example, if V has 3 outgoing edges as in the picture below,
	 *                           V
	 *                        /  |  \
	 *                       /   |    \
	 *                      B    A     F
	 *              your algorithm below should visit the outgoing edges of V in the order of A,B,F.
	 *              This means you will need to create a helper function to sort the outgoing edges by
	 *              the opposite city names.
	 *
	 *              See the method sortedOutgoingEdges below.
	 */

    public Iterable<String> BFSRoute(String departure, String destination) {
        return BFS(graph, cities.get(departure), cities.get(destination), forest);
    }

    private Iterable<String> BFS(Graph<String, Integer> graph, Vertex<String> s, Vertex<String> z, Map<Vertex<String>, Edge<Integer>> forest) {
        DoublyLinkedList<Vertex<String>> level = new DoublyLinkedList<>();  // This works in place of a queue.

        level.addLast(s);
        visited.put(s, true);                                               // Visit the starting vertex.

        boolean found = false;
        while (!level.isEmpty()) {

            DoublyLinkedList<Vertex<String>> nextLevel = new DoublyLinkedList<>();

            for (Vertex<String> u : level) {

                for (Edge<Integer> edge : sortedOutgoingEdges(graph, u)) {  // Loop through all edges incident to the vertex u.
                    Vertex<String> v = graph.opposite(u, edge);             // Test the opposite vertex

                    if (visited.get(v) == null) {                           // If we have yet to visit it:
                        visited.put(v, true);                               // Visit it
                        forest.put(v, edge);                                // Push to the forest
                        nextLevel.addLast(v);                               // And add the vertex v to the next level
                        if(v.equals(z)) {                                   // If we found the end vertex, break out of the loops.
                            found = true;
                            break;
                        }
                    }
                }
            }
            if (found) break;                                               // See above.

            level = nextLevel;                                              // Move to the next set of adjacent vertices..
        }
        return constructVertexPath(graph, s, z, forest);              // Construct the path between the two vertices. If one doesn't exist, the list will be empty.
    }

	//endregion

    //region Dijkstra's Algorithm

	/**
	 * @param departure: the departure city name
	 * @param destination: the destination city name
	 * @param itinerary: an empty DoublyLinkedList object will be passed in to the method.
	 * 	       When a shorted path is found, the city names in the path should be added to the list in the order.
	 * @return return the cost of the shortest path from departure to destination.
	 *
	 * @IMPORTANT_NOTE: The outgoing edges should be traversed by the order of the city names stored in
	 *                 the opposite vertices. For example, if V has 3 outgoing edges as in the picture below,
	 *                           V
	 *                        /  |  \
	 *                       /   |    \
	 *                      B    A     F
	 *              your algorithm below should visit the outgoing edges of V in the order of A,B,F.
	 *              This means you will need to create a helper function to sort the outgoing edges by
	 *              the opposite city names.
	 *
	 *              See the method sortedOutgoingEdges below.
	 */

	public int DijkstraRoute(String departure, String destination, DoublyLinkedList<String> itinerary ) {
        PositionalList<Edge<Integer>> edges = Dijkstra(graph, cities.get(departure), cities.get(destination), forest);

        int sum = 0;
        for(Edge<Integer> edge : edges) {
            sum += edge.getElement();                                                   // Finds the distance between the two vertices.
        }

        if (edges.size() == 0) {
            return sum;                                                                 // If the edge list is empty, there is no path.
        }

//        itinerary.addFirst(destination);                                                // Otherwise, convert between the iterable edge collection
//        Vertex<String> walk = cities.get(destination);                                  // To an DoublyLinkedList of strings.
//
//        for (int i = edges.size() - 1; i >= 0 ; i--) {
//            itinerary.addFirst(graph.opposite(walk, forest.get(walk)).getElement());
//            walk = graph.opposite(walk, forest.get(walk));
//        }

        constructVertexPath(graph, cities.get(departure), cities.get(destination), forest, itinerary);

        return sum;
	}

	private PositionalList<Edge<Integer>> Dijkstra(Graph<String, Integer> graph, Vertex<String> s, Vertex<String> z, Map<Vertex<String>, Edge<Integer>> forest) {
        Map<Vertex<String>, Integer> d = new HashMap<>();
        AdaptablePriorityQueue<Integer, Vertex<String>> pq = new HeapPQ<>();
        Map<Vertex<String>, Entry<Integer, Vertex<String>>> pqTokens = new HashMap<>();
        Map<Vertex<String>, Integer> cloud = new HashMap<>();

        for (Vertex<String> u : graph.vertices()) {
            if (u.equals(s)) {                                              // If the vertex is the starting one, set the distance to 0.
                d.put(u, 0);
            } else {                                                        // Otherwise, set the distance to as high as we can.
                d.put(u, Integer.MAX_VALUE);
            }
            pqTokens.put(u, pq.insert(d.get(u), u));                        // Insert the distance and the vertex to the pq.
        }

        while (!pq.isEmpty()) {
            Entry<Integer, Vertex<String>> entry = pq.removeMin();
            Integer distance = entry.getKey();
            Vertex<String> u = entry.getValue();

            if (u.equals(z)) break;                                         // If we found the end vertex, break out.

            cloud.put(u, distance);                                         // Put the minimum vertex in the cloud. This is guaranteed to be the shortest distance.
            pqTokens.remove(u);                                             // Remove from pq.

            for (Edge<Integer> edge : sortedOutgoingEdges(graph, u)) {
                Vertex<String> v = graph.opposite(u, edge);
                if (cloud.get(v) == null) {                                 // If the vertex opposing u is not in the cloud:
                    Integer newDistance = distance + edge.getElement();     // Get the distance to the opposing vertex v.

                    if (newDistance < d.get(v)) {                           // If the new distance is smaller:
                        d.put(v, newDistance);                              // Reassign the distance of v.
                        forest.put(v, edge);                                // Put the vertex v in the forest.
                        pq.replaceKey(pqTokens.get(v), newDistance);        // And replace the key of the vertex in the pq.
                    }
                }
            }
        }
        return constructEdgePath(graph, s, z, forest);                // Return the path between the two vertices. If a path doesn't exist, the list will be empty.
    }


	//endregion

    //region Helper Methods

    /**
     * I strongly recommend you to implement this method to return sorted outgoing edges for vertex V
     * You may use any sorting algorithms, such as insert sort, selection sort, etc.
     *
     * @param v: vertex v
     * @return a list of edges ordered by edge's name
     */

    private Iterable<Edge<Integer>> sortedOutgoingEdges(Graph<String, Integer> graph, Vertex<String> v) {

        //TODO: sort the outgoing edges and return the sorted list
        Iterable<Edge<Integer>> outgoingEdges = graph.outgoingEdges(v);
        ArrayList<Edge<Integer>> edges = new ArrayList<>();

        for (Edge<Integer> edge : outgoingEdges) {
            edges.addLast(edge);
        }

        for (int i = 1; i < edges.size(); i++) {
            for (int j = i; j > 0; j--) {
                String v_j = graph.opposite(v, edges.get(j)).getElement();
                String v_j_prev = graph.opposite(v, edges.get(j - 1)).getElement();
                if (v_j.compareTo(v_j_prev) < 0) {
                    Edge<Integer> temp = edges.get(j);
                    edges.set(j, edges.get(j - 1));
                    edges.set(j - 1, temp);
                }
            }
        }

        return edges;
    }

    /**
     * Constructs a path from a given starting vertex u to a given ending vertex v with the given forest map.
     * @param graph     The graph to use
     * @param u         The starting vertex
     * @param v         The ending vertex
     * @param forest    The forest map
     * @return          An iterable collection of the elements of the vertexes of the path
     */
    private DoublyLinkedList<String> constructVertexPath(Graph<String, Integer> graph, Vertex<String> u,
                                                         Vertex<String> v, Map<Vertex<String>, Edge<Integer>> forest) {

        return getPath(graph, u, v, forest, new DoublyLinkedList<>());
    }

    private DoublyLinkedList<String> constructVertexPath(Graph<String, Integer> graph, Vertex<String> u,
                                                                      Vertex<String> v, Map<Vertex<String>, Edge<Integer>> forest,
                                                                      DoublyLinkedList<String> itinerary) {

        return getPath(graph, u, v, forest, itinerary);
    }

    private DoublyLinkedList<String> getPath(Graph<String, Integer> graph, Vertex<String> u, Vertex<String> v, Map<Vertex<String>, Edge<Integer>> forest, DoublyLinkedList<String> path) {

        if (forest.get(v) != null) {
            Vertex<String> walk = v;
            path.addFirst(walk.getElement());
            while (!walk.equals(u)) {
                Edge<Integer> edge = forest.get(walk);
                walk = graph.opposite(walk, edge);
                path.addFirst(walk.getElement());
            }
        }
        return path;
    }

    /**
     * Constructs a path from a given starting vertex u to a given ending vertex v with the given forest map.
     * @param graph     The graph to use
     * @param u         The starting vertex
     * @param v         The ending vertex
     * @param forest    The forest map
     * @return          An iterable collection of the elements of the vertexes of the path
     */
    private DoublyLinkedList<Edge<Integer>> constructEdgePath(Graph<String, Integer> graph, Vertex<String> u, Vertex<String> v, Map<Vertex<String>, Edge<Integer>> forest) {
        DoublyLinkedList<Edge<Integer>> path = new DoublyLinkedList<>();

        if (forest.get(v) != null) {
            Vertex<String> walk = v;
            while (!walk.equals(u)) {
                Edge<Integer> edge = forest.get(walk);
                path.addFirst(edge);
                walk = graph.opposite(walk, edge);
            }
        }
        return path;
    }

    //endregion

}
