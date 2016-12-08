/**
 * A BreadthFirstSearchNode object represents a node in a search tree, in which
 * breadth-first search is used.
 * 
 * @author John MacCormick, Dickinson College
 * @version September 2014
 */
public class BreadthFirstSearchNode extends SearchNode {
	// Keep track of the total number of nodes created, as this will be used for
	// assigning the cost of a node.
	private static int numCreated = 0;

	/**
	 * @param parent
	 * @param state
	 * @param action
	 */
	public BreadthFirstSearchNode(SearchNode parent, WorldState state,
			Action action) {
		super(parent, state, action);
		numCreated++;
		cost = numCreated;
	}

	@Override
	protected SearchNode createChild(WorldState childState, Action action) {
		return new BreadthFirstSearchNode(this, childState, action);
	}

}
