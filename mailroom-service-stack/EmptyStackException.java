package iringMailroom;
/**
 * The <code>EmptyStackException</code> implements an exception called 
 * when the pop() method is called on an empty stack
 * 
 * @author Ying Zhang
 * 
 *
 */

public class EmptyStackException extends Exception{
	
	/**
	 *
	 * Creates an instance of <code>EmptyStackException</code>
	 * 
	 * <dt>Postcondition:
	 * 		<dd>error messaged is passed up to the superclass Exception
	 * 
	 */

	public EmptyStackException()
	{
		super("There are no packages in this stack");
	}

}
