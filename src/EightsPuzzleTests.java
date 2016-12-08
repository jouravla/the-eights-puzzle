import static org.junit.Assert.*;

import org.junit.Test;

public class EightsPuzzleTests {

	public static final int[][] START_BOARD = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 } };
	
	@Test
	public void ClassicalSearchTest() {
		// The puzzle board to be used as a goal.
		int[][] goal = { { 3, 1, 2 }, { 4, 5, 8 }, { 6, 7, 0 } };

		int maxNodes = 1;
		int maxDepth = -1;
		ClassicalSearch.SearchType searchType = ClassicalSearch.SearchType.Tree;
		EightsPuzzleWorldState initial_state = new EightsPuzzleWorldState(START_BOARD);
		SearchNode initial_node = null;
		
		EightsPuzzleWorldState goal_state = new EightsPuzzleWorldState(goal);

		initial_node = new BreadthFirstSearchNode(null, initial_state, null);
		
		/* ************************** Max Node Test ************************** */
		ClassicalSearch tester = new ClassicalSearch(initial_node, goal_state, maxNodes, maxDepth, searchType);
		tester.search();
		// check that the number of nodes generated is equal to the maximum number of generated nodes allowed in this case
		assertEquals(1, tester.getGeneratedNodes());
				
		maxNodes = -1;
		
		tester = new ClassicalSearch(initial_node, goal_state, maxNodes, maxDepth, searchType);
		tester.search();
		// check that the number of nodes generated is equal to 121 when no maximum limit is set in this case
		assertEquals(121, tester.getGeneratedNodes());
		
		maxNodes = 100;
		tester = new ClassicalSearch(initial_node, goal_state, maxNodes, maxDepth, searchType);
		tester.search();
		// check that the number of nodes generated is equal to the maximum number of generated nodes allowed in this case
		assertEquals(100, tester.getGeneratedNodes());
		
		/* ************************** Max Depth Test ************************** */
		maxNodes = -1;
		maxDepth = 1;
		
		tester = new ClassicalSearch(initial_node, goal_state, maxNodes, maxDepth, searchType);
		tester.search();
		
		// check that the test fails if maxDepth is set to 1
		org.junit.Assert.assertFalse(tester.search());
	}
}
