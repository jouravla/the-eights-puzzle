/**
 * An EightsPuzzleAction object represents an action in a search tree for
 * 8-puzzle-like problems. The problem consists of a rectangular grid of tiles
 * with one gap, so the possible actions consist of moving a tile in one of the
 * four directions up, down, left, right.
 * 
 * @author John MacCormick, Dickinson College
 * @version September 2014
 */
public class EightsPuzzleAction extends Action {

	/**
	 * The directions in which tiles can be moved.
	 */
	public enum Direction {
		Up, Down, Left, Right
	};

	// The direction in which a tile is moved to perform this action.
	private Direction direction;

	/**
	 * Create a new EightsPuzzleAction representing a move in a given direction.
	 * 
	 * @param direction
	 *            The direction in which a tile is moved to perform this action.
	 */
	public EightsPuzzleAction(Direction direction) {
		super();
		this.direction = direction;
	}

	/** Returns the direction in which a tile is moved to perform this action.
	 * @return The direction in which a tile is moved to perform this action.
	 */
	public Direction getDirection() {
		return direction;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return direction.toString();
	}

}
