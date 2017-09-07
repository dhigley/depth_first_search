/**
 *   Project UnweightedGraph.java
 *
 *   Description: !!!!!!!!!!! FILL IN!!!!!!!!!!
 *
 *   Daniel Higley, higleyd
 *   CSC 364, Spring 2016
 *   04/24/2016
 *
 */
import java.util.*;

public class UnweightedGraphWithNonrecursiveDFS<V> extends UnweightedGraph<V> {
  /** Construct an empty graph */
  public UnweightedGraphWithNonrecursiveDFS() {
  }

  /** Construct a graph from vertices and edges stored in arrays */
  public UnweightedGraphWithNonrecursiveDFS(V[] vertices, int[][] edges) {
    super(vertices, edges);
  }

  /** Construct a graph from vertices and edges stored in List */
  public UnweightedGraphWithNonrecursiveDFS(List<V> vertices, List<Edge> edges) {
    /* super( edges, vertices); */
    super(vertices, edges);
  }

  /** Construct a graph for integer vertices 0, 1, 2 and edge list */
  public UnweightedGraphWithNonrecursiveDFS(List<Edge> edges, int numberOfVertices) {
    /* super( numberOfVertices, edges); */
    super(edges, numberOfVertices);
  }

  /** Construct a graph from integer vertices 0, 1, and edge array */
  public UnweightedGraphWithNonrecursiveDFS(int[][] edges, int numberOfVertices) {
    super(edges, numberOfVertices);
  }

  /*
   * Pseudo-code:
   *
   * Create a stack to hold the index numbers of each vertices neighbors.
   *
   * Push the value v onto the stack.
   * While the stack is not empty
   *   pop v off of the stack
   *   and for every neighbor of v (starting at the end and working back)
   *     get the index number of the neighbor
   *     if the neighbor has not already been visited
   *       set the neighbors parent to v
   *       and push the neighbor onto the stack
   *
   * Return a new tree object with v as the root, a list of each node's parent,
   * and the search order performed by the algorithm above.
   */
  @Override /** A non-recursive method for performing a depth-first search */
  public Tree dfs(int v) {
    if (neighbors.size() == 0 && vertices.size() == 0) {
      return null;
    }
    else if (v < 0 || v > vertices.size() - 1)
      throw new IndexOutOfBoundsException();
    else {
      List<Integer> searchOrder = new ArrayList<Integer>();
      int[] parent = new int[vertices.size()];
      for (int i = 0; i < parent.length; i++)
        parent[i] = -1; // Initialize parent[i] to -1

      boolean[] isVisited = new boolean[vertices.size()]; // Mark visited vertices
      Stack<Integer> stack = new Stack<>(); // A stack of children to find more children from

      // Start
      stack.push(v);

      while (!stack.isEmpty()) {
        v = stack.pop();
        if (!isVisited[v]) {
          searchOrder.add(v); // Store the visited vertex
          isVisited[v] = true; // Vertex v is visted
          for (int i = neighbors.get(v).size() - 1; i >= 0; i--) {
            int w = neighbors.get(v).get(i);
            if (!isVisited[w]) {
              parent[w] = v; // The parent of vertex i is v
              stack.push(w);
            }
          }
        }
      }

      return new Tree(v, parent, searchOrder);
    }
  }

}
