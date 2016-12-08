/**
 * An AStarManhattan object represents a node in a search tree, in which
 * A* search is used using the Manhattan (or city block) heuristic.
 * 
 * @author Sasha Jouravlev, Lam Nguyen
 * @version September 2016
 */
public class AStarManhattan extends AStarAbstract {

	/**
	 * @param parent
	 * @param state
	 * @param action
	 */
	public AStarManhattan(SearchNode parent, WorldState state, Action action, WorldState goal) {
		super(parent, state, action, goal);
		
		// if this isn't the root of the tree, set the number of moves to the parent's number plus 1
		if (parent!=null) {
			this.setNumMoves(((AStarManhattan)parent).getNumMoves() + 1);
		}
		
		// set the cost for this node: the number of moves it took to get to this state + the heuristic
		cost = this.getNumMoves() + this.findHeuristics(goal, state);
	}
	
	/**
	 * Returns the Manhattan heuristic for the current node which is the amount of moves it would take to 
	 * put every tile in its goal position.
	 */
	@Override
	public int findHeuristics(WorldState goal, WorldState current) {
		//the total number of moves 
		int sum = 0; 
		//the current state of the board saved in an array for easy searching
		int[][] currentBoard = ((EightsPuzzleWorldState) current).getBoard();
		//the goal state of the board saved in an array for easy searching
		int[][] goalBoard = ((EightsPuzzleWorldState) goal).getBoard();
		
		//the outer double loop is to select the current board tile
		for (int i = 0; i < currentBoard.length; i++) {
			for (int j = 0; j < currentBoard[0].length; j++) {
				//save the current tile for comparison
				int tile = currentBoard[i][j];
				
				//the number of moves for that specific tile
				int distance = 0;
				
				//if the tile isn't the blank space
				if (tile != 0) {
					// the inner double loop finds the position of the corresponding tile in the goal board
					for (int a = 0; a < goalBoard.length; a++) {
						for (int b = 0; b < goalBoard[0].length; b++) {
							// when the selected tile's spot is found on the goal board, find the number of 
							// moves it would take to reach that spot.
							if (goalBoard[a][b] == tile) {
								distance = Math.abs(a-i) + Math.abs(b-j);
							}
						}
					}
					// add the computed Manhattan distance to the overall sum
					sum += distance;
				}
			}
		}
		return sum;
	}

	/**
	 * Create a new node, which is a child of the calling node, and which has
	 * the given state and action.
	 * 
	 * Returns: The newly-constructed child node.
	 */
	@Override
	protected SearchNode createChild(WorldState childState, Action action) {
		return new AStarManhattan(this, childState, action, this.getGoalState());
	}

}
