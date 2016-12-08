/**
 * A DepthFirstSearchNode object represents a node in a search tree, in which
 * depth-first search is used.
 * 
 * @author Lam Nguyen, Sasha Jouravlev, Dickinson College
 * @version September 2016
 */
public class DepthFirstSearchNode extends SearchNode {
	// Keep track of the total number of nodes created, as this will be used for
	// assigning the cost of a node.
	private static int numCreated = 0;

	/**
	 * @param parent
	 * @param state
	 * @param action
	 */
	public DepthFirstSearchNode(SearchNode parent, WorldState state,
			Action action) {
		super(parent, state, action);
		// This will allow for a Last-In, First-out style queue
		// as we only explore the node with the most negative cost, which is the node generated most recently
		numCreated--;
		cost = numCreated;
	}
	
	@Override
	protected SearchNode createChild(WorldState childState, Action action) {
		return new DepthFirstSearchNode(this, childState, action);
	}
}
