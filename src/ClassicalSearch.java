import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * A ClassicalSearch can perform various classical search algorithms using tree
 * search or graph search.
 * 
 * @author John MacCormick, Dickinson College
 * @author some code copied from Prof. Grant Braught; used with permission.
 * @version September 2014
 */
public class ClassicalSearch {
	private static final boolean VERBOSE = true;

	/**
	 * Specifies whether tree search or graph search is used.
	 */
	public enum SearchType {
		Tree, Graph
	};

	// the root node of the search tree
	private SearchNode initialNode;

	// the state that is the goal of the search
	private WorldState goalState;

	// the maximum number of nodes to be expanded before the search terminates
	// in failure (-1 means no maximum)
	private int maxNodes;

	// the maximum depth of nodes to be examined (-1 means no maximum)
	private int maxDepth;

	// the type of search to be performed
	private SearchType searchType;

	// a node in the search tree that represents a solution, or null if a
	// solution has not been found
	private SearchNode solutionNode = null;

	// the number of nodes that have been expanded so far in the search
	private int expandedNodes;

	// the number of nodes that have been generated so far in the search
	private int generatedNodes;

	/**
	 * Creates a new ClassicalSearch object in order to perform a classical
	 * search algorithm.
	 * 
	 * @param initialNode
	 *            The root node of the search tree.
	 * @param goalState
	 *            The state that is the goal of the search.
	 * @param maxNodes
	 *            The maximum number of nodes to be expanded before the search
	 *            terminates in failure, or -1 for no maximum
	 * @param maxDepth
	 *            The maximum depth of nodes to be examined, or -1 for no maximum
	 * @param searchType
	 *            The type of search to be performed
	 */
	public ClassicalSearch(SearchNode initialNode, WorldState goalState,
			int maxNodes, int maxDepth, SearchType searchType) {
		this.initialNode = initialNode;
		this.goalState = goalState;
		this.maxNodes = maxNodes;
		this.maxDepth = maxDepth;
		this.searchType = searchType;
		this.generatedNodes = 1;
		this.expandedNodes = 0;
	}

	/**
	 * Returns a node in the search tree that represents a solution.
	 * 
	 * @return A node in the search tree that represents a solution, or null if
	 *         a solution has not been found.
	 */
	public SearchNode getSolutionNode() {
		return solutionNode;
	}

	/**
	 * Returns the number of nodes that have been expanded.
	 * 
	 * @return The number of nodes that have been expanded.
	 */
	public int getExpandedNodes() {
		return expandedNodes;
	}

	/**
	 * Returns the number of nodes that have been generated.
	 * 
	 * @return The number of nodes that have been generated.
	 */
	public int getGeneratedNodes() {
		return generatedNodes;
	}

	/**
	 * Perform the classical state space search defined by this object.
	 * 
	 * @return true if a solution is found and false otherwise.
	 */
	public boolean search() {
		// Stores the nodes that have been generated, but not yet expanded. The
		// nodes are ordered according to the cost of their states.
		PriorityQueue<SearchNode> frontier = null;
		
		// This ArrayList keeps track of the states that have been added
		ArrayList<WorldState> addedStates = new ArrayList<WorldState>();

		// Place the initial node into the frontier.
		frontier = new PriorityQueue<SearchNode>();
		frontier.add(initialNode);
		addedStates.add(initialNode.state);

		// As long as we haven't found a solution and there are still nodes in
		// the frontier, continue the search.
		while (!frontier.isEmpty() && solutionNode == null) {

			// Get the lowest cost node from the priority queue.
			SearchNode currentNode = frontier.poll();

			if (VERBOSE) {
				System.out.println("Considering Node:");
				currentNode.print();
			}

			// If the node about to be expanded has the goal state then we have
			// found a solution, so break out of the loop.
			if (currentNode.getState().equals(goalState)) {
				solutionNode = currentNode;
				if (VERBOSE) {
					System.out.println("Solution found");
				}
				break;
			}

			// Expand the current node to get all of the child states that can
			// be reached by application of valid actions.
			Collection<SearchNode> childNodes = currentNode.expand();
			expandedNodes++;

			if (VERBOSE) {
				System.out.println("Expanding into ...");
			}

			// Put each of the child nodes into the frontier for consideration later.
			for (SearchNode child : childNodes) {
				// check if the maximum number of generated nodes is exceeded
				if (maxNodes != -1 && generatedNodes >= maxNodes) {
					return false;
				}
				
				// Compute the depth of this node
				// Add this node to the frontier if its depth does not exceed the maximum depth
				int depth = 0;
				SearchNode temp = child;
				while (!temp.equals(initialNode)) {
					temp = temp.parent;
					depth++;
				}
				
				// Only add the node to the frontier if tree search is used, or
				// if graph search is used and the state has not already been added to the ArrayList addedStates 
				if ((maxDepth == -1) || (maxDepth!= -1 && depth <= maxDepth)) {
					if (searchType.equals(ClassicalSearch.SearchType.Tree) ||
							(searchType.equals(ClassicalSearch.SearchType.Graph) && (!addedStates.contains(child.state)) )) {
						frontier.add(child);
						addedStates.add(child.state); // add this node's state to the ArrayList addedStates
						generatedNodes++;
						if (VERBOSE) {
							child.print();
							System.out.println("Added to frontier. (cost " + child.getCost() + ")\n\n");
						}
					}
				}
			}
		}

		return solutionNode != null;
	}

}
