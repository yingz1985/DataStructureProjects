/**
 * The <code>EndOfListException</code> implements an exception called when the
 * user tries to access a null reference in the linked list
 * 
 * @author Ying Zhang 
 *
 */
public class EndOfListException extends Exception {

	/**
	 * Creates an instance of <code>EndOfListException
	 * 
	 * <dt>Postcondition:
	 * <dd>error messaged is passed up to the superclassException
	 * 
	 */

	public EndOfListException() {
		super("You have reached the end of the list");
	}

}
