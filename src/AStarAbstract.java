import java.util.Collection;

/**
 * An abstract class that is extended by AStarNumTiles and AStarManhattan.
 * This class declares the findHeuristics method that will be implemented in both AStarNumTiles and AStarManhattan.
 * 
 * @author Lam Nguyen, Sasha Jouravlev
 * @version September 2016
 */
public abstract class AStarAbstract extends SearchNode {
	private int numMoves; // the number of moves to get to the current state
	private WorldState goalState; // the goal state which we are trying to base the heuristic off
	
	/**
	 * Creates a new node with the given parent, state, and action.
	 * 
	 * @param parent
	 *            The parent of this node in the search tree, or null if this
	 *            node is the root of the tree.
	 * @param state
	 *            The state of the world that this node represents.
	 * @param action
	 *            The action that was used to transition from the parent node to
	 *            this node, or null if this node is the root of the tree.
	 * @param goal
	 * 			  The goal state that is used to compare against the current state
	 * 			  in order to calculate the heuristic.
	 */
	public AStarAbstract(SearchNode parent, WorldState state, Action action, WorldState goal) {
		super(parent, state, action);
		goalState = goal;
	}
	
	/**
	 * Returns the number of moves that it took to get to the current state.
	 */
	public int getNumMoves() {
		return numMoves;
	}
	
	/**
	 * Returns the goalState of the node.
	 */
	public WorldState getGoalState() {
		return goalState;
	}
	
	/**
	 * Sets the number of moves that it took to get to the current state.
	 */
	public void setNumMoves(int moves) {
		numMoves = moves;
	}
	
	/**
	 * Returns the value of the heuristic function computed for this node.
	 */
	protected abstract int findHeuristics(WorldState goal, WorldState current);
	
	//protected abstract SearchNode createChild(WorldState childState, Action action, WorldState goal);
}
