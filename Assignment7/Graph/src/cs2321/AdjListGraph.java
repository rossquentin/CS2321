package cs2321;

import net.datastructures.*;

/*
 * Implement Graph interface. A graph can be declared as either directed or undirected.
 * In the case of an undirected graph, methods outgoingEdges and incomingEdges return the same collection,
 * and outDegree and inDegree return the same value.
 *
 * @author CS2321 Instructor
 */
public class AdjListGraph<V, E> implements Graph<V, E> {

    private class GraphVertex<V> implements Vertex<V> {
        private V element;                              // Element contained at vertex.
        private Position<Vertex<V>> pos;                // Position of vertex
        private final Map<Vertex<V>, Edge<E>> outgoing; // Outgoing edges from vertex
        private final Map<Vertex<V>, Edge<E>> incoming; // Incoming edges from vertex

        public GraphVertex(V element, boolean isDirected) {
            this.element = element;
            outgoing = new HashMap<>();
            if (isDirected) {
                incoming = new HashMap<>();
            } else {
                incoming = outgoing;
            }
        }

        @Override
        public V getElement() {
            return element;
        }

        public Position<Vertex<V>> getPosition() {
            return pos;
        }

        public Map<Vertex<V>, Edge<E>> getOutgoing() {
            return outgoing;
        }

        public Map<Vertex<V>, Edge<E>> getIncoming() {
            return incoming;
        }

        public void setElement(V element) {
            this.element = element;
        }

        public void setPosition(Position<Vertex<V>> pos) {
            this.pos = pos;
        }
    }

    private class GraphEdge<E> implements Edge<E> {
        private E element;                      // Element contained at edge
        private Position<Edge<E>> pos;          // Position of edge
        private final Vertex<V>[] endpoints;    // End vertices of the edge.

        public GraphEdge(Vertex<V> u, Vertex<V> v, E element) {
            this.element = element;
            endpoints = new Vertex[]{u, v};
        }

        @Override
        public E getElement() {
            return element;
        }

        public Vertex<V>[] getEndpoints() {
            return endpoints;
        }

        public Position<Edge<E>> getPosition() {
            return pos;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public void setPosition(Position<Edge<E>> pos) {
            this.pos = pos;
        }
    }

    private final boolean isDirected;
    private final PositionalList<Vertex<V>> vertices;
    private final PositionalList<Edge<E>> edges;

    /**
     * Constructor with parameter for specifying if the graph
     * is directed or not.
     *
     * @param directed True if graph is directed, false if graph is undirected.
     */
    public AdjListGraph(boolean directed) {
        isDirected = directed;
        edges = new DoublyLinkedList<>();
        vertices = new DoublyLinkedList<>();
    }

    /**
     * Default constructor. Assume the graph is undirected.
     */
    public AdjListGraph() {
        this(false);
    }


    /**
     * Validates that the given edge is a valid edge for the graph.
     * Returns a GraphEdge if the edge is valid.
     *
     * @param e The edge to validate
     * @return A GraphEdge of the edge e.
     */
    private GraphEdge<E> validate(Edge<E> e) {
        if (!(e instanceof GraphEdge))                  // Edge given is not an edge
            throw new IllegalArgumentException("Invalid edge");

        GraphEdge<E> edge = (GraphEdge<E>) e;           // Safe cast

        if (edge.getPosition() == null) return null;    // Edge is defunct
        return edge;
    }

    /**
     * Validates that the given vertex is a valid edge for the graph.
     * Returns a GraphVertex if the edge is valid.
     *
     * @param v The vertex to validate
     * @return A GraphVertex of the vertex v.
     */
    private GraphVertex<V> validate(Vertex<V> v) {
        if (!(v instanceof GraphVertex))                // Vertex given is not an edge
            throw new IllegalArgumentException("Invalid vertex");

        GraphVertex<V> vertex = (GraphVertex<V>) v;     // Safe cast

        if (vertex.getPosition() == null) return null;  // Vertex is defunct
        return vertex;
    }

    /* (non-Javadoc)
     * @see net.datastructures.Graph#edges()
     */
    public Iterable<Edge<E>> edges() {
        return edges;
    }

    /* (non-Javadoc)
     * @see net.datastructures.Graph#vertices()
     */
    public Iterable<Vertex<V>> vertices() {
        return vertices;
    }

    /* (non-Javadoc)
     * @see net.datastructures.Graph#numEdges()
     */
    public int numEdges() {
        return edges.size();
    }

    /* (non-Javadoc)
     * @see net.datastructures.Graph#numVertices()
     */
    public int numVertices() {
        return vertices.size();
    }

    @Override
    public int outDegree(Vertex<V> v) throws IllegalArgumentException {
        GraphVertex<V> vertex = validate(v);
        return vertex.getOutgoing().size();
    }

    @Override
    public int inDegree(Vertex<V> v) throws IllegalArgumentException {
        GraphVertex<V> vertex = validate(v);
        return vertex.getIncoming().size();
    }

    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> v) throws IllegalArgumentException {
        GraphVertex<V> vertex = validate(v);
        return vertex.getOutgoing().values();
    }

    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> v) throws IllegalArgumentException {
        GraphVertex<V> vertex = validate(v);
        return vertex.getIncoming().values();
    }

    @Override
    public Edge<E> getEdge(Vertex<V> u, Vertex<V> v) throws IllegalArgumentException {
        GraphVertex<V> vertex = validate(u);
        return vertex.getOutgoing().get(v);
    }

    /* (non-Javadoc)
     * @see net.datastructures.Graph#endVertices(net.datastructures.Edge)
     */
    public Vertex<V>[] endVertices(Edge<E> e) throws IllegalArgumentException {
        GraphEdge<E> edge = validate(e);
        return edge.getEndpoints();
    }


    /* (non-Javadoc)
     * @see net.datastructures.Graph#insertEdge(net.datastructures.Vertex, net.datastructures.Vertex, java.lang.Object)
     */
    public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E o) throws IllegalArgumentException {
        if (getEdge(u, v) == null) {                         // Tests if an edge exists
            GraphEdge<E> edge = new GraphEdge<>(u, v, o);   // If edge doesn't exists, create a new GraphEdge.
            edge.setPosition(edges.addLast(edge));          // Set it's position to the end of the edges list.
            GraphVertex<V> origin = validate(u);            // Assume u is the origin vertex, and v is the destination vertex.
            GraphVertex<V> dest = validate(v);
            origin.getOutgoing().put(v, edge);              // Puts the incoming and outgoing direction for u and v
            dest.getIncoming().put(u, edge);                // Into their corresponding maps.
            return edge;
        } else {                                            // If edge exists, throw IllegalArgumentException
            throw new IllegalArgumentException("Edge from u to v exists");
        }
    }

    /* (non-Javadoc)
     * @see net.datastructures.Graph#insertVertex(java.lang.Object)
     */
    public Vertex<V> insertVertex(V o) {
        GraphVertex<V> vertex = new GraphVertex<>(o, isDirected);   // Creates a new GraphVertex
        vertex.setPosition(vertices.addLast(vertex));               // Set it's position to the end of the vertices list.
        return vertex;
    }

    /* (non-Javadoc)
     * @see net.datastructures.Graph#opposite(net.datastructures.Vertex, net.datastructures.Edge)
     */
    public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws IllegalArgumentException {
        GraphEdge<E> edge = validate(e);
        GraphVertex<V> vertex = validate(v);
        Vertex<V>[] endpoints = edge.getEndpoints();
        if (endpoints[0].equals(v)) {                   // Finds the other vertex on the given edge.
            return endpoints[1];
        } else if (endpoints[1].equals(v)) {
            return endpoints[0];
        } else {                                        // If the vertex is not incident to the edge, throw an IllegalArgumentException
            throw new IllegalArgumentException("v is not incident to edge");
        }
    }

    /* (non-Javadoc)
     * @see net.datastructures.Graph#removeEdge(net.datastructures.Edge)
     */
    public void removeEdge(Edge<E> e) throws IllegalArgumentException {
        GraphEdge<E> edge = validate(e);
        edges.remove(edge.getPosition());
    }

    /* (non-Javadoc)
     * @see net.datastructures.Graph#removeVertex(net.datastructures.Vertex)
     */
    public void removeVertex(Vertex<V> v) throws IllegalArgumentException {
        GraphVertex<V> vertex = validate(v);
        for (Edge<E> e : vertex.getIncoming().values()) {   // Remove all edges incoming to the vertex
            removeEdge(e);
        }
        for (Edge<E> e : vertex.getOutgoing().values()) {   // Remove all edges outgoing from the vertex.
            removeEdge(e);
        }
        vertices.remove(vertex.getPosition());              // Remove the vertex's position
    }

    /*
     * replace the element in edge object, return the old element
     */
    public E replace(Edge<E> e, E o) throws IllegalArgumentException {
        GraphEdge<E> edge = validate(e);
        E oldE = edge.getElement();
        edge.setElement(o);
        return oldE;
    }

    /*
     * replace the element in vertex object, return the old element
     */
    public V replace(Vertex<V> v, V o) throws IllegalArgumentException {
        GraphVertex<V> vertex = validate(v);
        V oldV = vertex.getElement();
        vertex.setElement(o);
        return oldV;
    }
}
