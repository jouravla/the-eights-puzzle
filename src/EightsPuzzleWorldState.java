import java.util.ArrayList;
import java.util.Arrays;

/**
 * An EightsPuzzleWorldState object represents a possible state for
 * 8-puzzle-like problems. The problem consists of a rectangular grid of
 * numbered tiles with one gap, so the state is specified by a rectangular array
 * of integers.
 * 
 * @author John MacCormick, Dickinson College
 * @author Some code copied from Prof. Grant Braught; used with permission.
 * @version September 2014
 */
public class EightsPuzzleWorldState extends WorldState {

	/**
	 * A fixed value used to represent the presence of the gap, or "hole", in
	 * the puzzle board.
	 */
	public static final int HOLE_VALUE = 0;

	// A two-dimensional array representing the numbers on
	// the tiles in the
	// puzzle board.
	private int[][] board;

	// The number of rows and columns in the puzzle board,
	// respectively.
	private int boardHeight;
	private int boardWidth;

	// The row and column of the current location of the
	// hole. Thus, we must
	// maintain the invariant that
	// board[holeRow][holeColumn] == HOLE_VALUE.
	private int holeRow;
	private int holeColumn;

	/**
	 * Create a new puzzle state representing the given puzzle board.
	 * 
	 * @param board An array representing the numbers on the tiles in the puzzle board.
	 */
	public EightsPuzzleWorldState(int[][] board) {
		this.board = board;
		this.boardHeight = board.length;
		this.boardWidth = board[0].length;
		
		// initialize the holeRow, holeColumn fields correctly
		computeHoleLocation();
	}

	// Set the values of the holeRow, holeColumn fields to maintain the
	// invariant that board[holeRow][holeColumn] == HOLE_VALUE.
	private void computeHoleLocation() {
		boolean foundHole = false;
		for (int i = 0; i < boardHeight; i++) {
			for (int j = 0; j < boardWidth; j++) {
				if (board[i][j] == HOLE_VALUE) {
					holeRow = i;
					holeColumn = j;
					foundHole = true;
				}
			}
		}
		if (!foundHole) {
			throw new RuntimeException("No hole found in the puzzle.");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see WorldState#hashCode()
	 */
	@Override
	public int hashCode() {
		// Arrays.deepHashCode() computes a hash of all elements in a 2-D array,
		// which is what we need here.
		return Arrays.deepHashCode(board);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see WorldState#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		EightsPuzzleWorldState other = (EightsPuzzleWorldState) obj;
		// Arrays.deepEquals() tests for the equality of all elements in a 2-D
		// array, which is what we need here.
		return Arrays.deepEquals(board, other.board);
	}

	/*
	 * This method checks the position of the hole on the board 
	 * to find all possible actions that can be applied to the current board
	 * 
	 * @see WorldState#getValidActions()
	 */
	@Override
	public ArrayList<Action> getValidActions() {
		ArrayList<Action> actions = new ArrayList<Action>();

		// Add the Up action if it is possible to slide up
		if (holeRow < boardHeight - 1) {
			actions.add(new EightsPuzzleAction(EightsPuzzleAction.Direction.Up));
		}
		
		// Add the Down action if it is possible to slide down
		if (holeRow > 0) {
			actions.add(new EightsPuzzleAction(EightsPuzzleAction.Direction.Down));
		}
		
		// Add the Left action if it is possible to slide left
		if (holeColumn < boardWidth - 1) {
			actions.add(new EightsPuzzleAction(EightsPuzzleAction.Direction.Left));
		}
		
		// Add the Right action if it is possible to slide right
		if (holeColumn > 0) {
			actions.add(new EightsPuzzleAction(EightsPuzzleAction.Direction.Right));
		}
		
		return actions;
	}

	/**
	 * Clones the puzzle board.
	 * 
	 * @return A new 2-D array containing a copy of the calling object's puzzle
	 *         board.
	 */
	public int[][] cloneBoard() {
		int[][] tmp = board.clone();
		for (int row = 0; row < board.length; row++) {
			tmp[row] = board[row].clone();
		}
		return tmp;
	}

	/* 
	 * This method applies 
	 * @see WorldState#apply(Action)
	 */
	@Override
	public WorldState apply(Action action) {
		EightsPuzzleAction theAction = (EightsPuzzleAction) action;
		int[][] newBoard = cloneBoard();
		int tileToMove = -1;
		int newHoleRow = holeRow;
		int newHoleColumn = holeColumn;

		// First: compute the new position of the hole based on the action applied
		switch (theAction.getDirection()) {
		case Up:
			newHoleRow++;
			break;
			
		case Down:
			newHoleRow--;
			break;
		
		case Left:
			newHoleColumn++;
			break;
			
		case Right:
			newHoleColumn--;
			break;
		}

		// Then: set the value of the board at the newly computed position to be HOLE_VALUE
		// and move the tile (that used to be at that position) over correspondingly.
		tileToMove = board[newHoleRow][newHoleColumn];
		newBoard[holeRow][holeColumn] = tileToMove;
		newBoard[newHoleRow][newHoleColumn] = HOLE_VALUE;

		return new EightsPuzzleWorldState(newBoard);
	}

	/* (non-Javadoc)
	 * @see WorldState#toString()
	 */
	@Override
	public String toString() {
		StringBuilder representation = new StringBuilder();
		for (int row = 0; row < boardHeight; row++) {
			for (int col = 0; col < boardWidth; col++) {
				if (board[row][col] == HOLE_VALUE) {
					representation.append("   ");
				} else {
					representation.append(" " + board[row][col] + " ");
				}
			}
			representation.append("\n");
		}
		return representation.toString();
	}

	/**
	 * Return a reference to the calling object's puzzle board.
	 * @return A reference to the calling object's puzzle board.
	 */
	public int[][] getBoard() {
		return board;
	}

}
