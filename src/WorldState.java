import java.util.Collection;

/**
 * A WorldState object represents a state in a search problem. For example, in
 * the well known missionaries-and-cannibals problem, the state would be the
 * number of missionaries and cannibals on each side of the river, together with
 * the location of the boat. WorldState is an abstract class; derived classes
 * can specify the state for a real search problem by implementing the abstract
 * methods.
 * 
 * @author John MacCormick, Dickinson College
 * @version September 2014
 */
public abstract class WorldState {

	/**
	 * This is the standard equals() method possessed by all Java objects. It is
	 * declared as abstract in this class to enforce the requirement that
	 * derived classes must implement a meaningful equals() method.
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public abstract boolean equals(Object other);

	/**
	 * This is the standard hashCode() method possessed by all Java objects. It
	 * is declared as abstract in this class to enforce the requirement that
	 * derived classes must implement a meaningful hashCode() method, thus
	 * ensuring that WorldState objects can be used correctly in HashSets,
	 * HashMaps, and the like.
	 * 
	 * @see java.lang.Object#hashCode(java.lang.Object)
	 */
	@Override
	public abstract int hashCode();

	/**
	 * This is the standard toString() method possessed by all Java objects. It
	 * is declared as abstract in this class to enforce the requirement that
	 * derived classes must implement a meaningful toString() method.
	 * 
	 * @see java.lang.Object#toString(java.lang.Object)
	 */
	@Override
	public abstract String toString();

	/**
	 * Returns a collection of all actions that can be applied to this state.
	 * 
	 * @return A collection of all actions that can be applied to this state.
	 */
	public abstract Collection<Action> getValidActions();

	/**
	 * Applies the given action to the calling WorldState object. The calling
	 * object is not altered; the result of the action is returned in a new
	 * object.
	 * 
	 * @param action
	 *            The action that is applied to the calling WorldState object.
	 * @return A new WorldState object, which is the result of applying the
	 *         given action to the calling WorldState object.
	 */
	public abstract WorldState apply(Action action);
}
