package iringMailroom;
/**
 * The <code>FullStackException</code> implements an exception called 
 * when the push() method is called on a full stack (7 packages)
 * 
 * @author Ying Zhang
 *
 */
public class FullStackException extends Exception{
	
	/**
	 *
	 * Creates an instance of <code>FullStackException</code>
	 * 
	 * <dt>Postcondition:
	 * 		<dd>error messaged is passed up to the superclass Exception
	 * 
	 */
	public FullStackException()
	{
		super("This stack has reached its capacity, "
	      + "no more packages can be added!");
	}

}
