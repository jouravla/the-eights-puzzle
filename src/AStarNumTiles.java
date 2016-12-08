/**
 * An AStarNumTiles object represents a node in a search tree, in which
 * A* search is used using the h_1 heuristic.
 * 
 * @author Sasha Jouravlev, Lam Nguyen
 * @version September 2016
 */
public class AStarNumTiles extends AStarAbstract {

	/**
	 * @param parent
	 * @param state
	 * @param action
	 */
	public AStarNumTiles(SearchNode parent, WorldState state,
			Action action, WorldState goal) {
		super(parent, state, action, goal);
		
		// if this isn't the root of the tree, set the number of moves to the parent's number plus 1
		if (parent!=null) {
			this.setNumMoves(((AStarNumTiles)parent).getNumMoves() + 1);
		}
		
		// set the cost for this node: the number of moves it took to get to this state + the heuristic
		cost = this.getNumMoves() + this.findHeuristics(goal, state);
	}
	
	/**
	 * Returns the heuristic for the current node which is the number of displaced tiles from the goal state.
	 */
	@Override
	public int findHeuristics(WorldState goal, WorldState current) {
		// keep track of the number of displaced tiles
		int count = 0;
		// the current state of the board saved in an array for easy searching
		int[][] currentBoard = ((EightsPuzzleWorldState) current).getBoard();
		// the goal state of the board saved in an array for easy searching
		int[][] goalBoard = ((EightsPuzzleWorldState) goal).getBoard();
		
		for (int i = 0; i < currentBoard.length; i++) {
			for (int j = 0; j < currentBoard[0].length; j++) {
				// if the selected tile isn't in the correct spot on the goalBoard, add 1 to the count
				if (currentBoard[i][j] != 0 && currentBoard[i][j] != goalBoard[i][j]) {
					count++;
				}
			}
		}
		return count;
	}
 
	/**
	 * Create a new node, which is a child of the calling node, and which has
	 * the given state and action.
	 * 
	 * Returns: The newly-constructed child node.
	 */
	@Override
	protected SearchNode createChild(WorldState childState, Action action) {
		return new AStarNumTiles(this, childState, action, this.getGoalState());
	}

}
