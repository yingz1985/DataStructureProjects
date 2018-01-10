/**
 * The <code>EmptyTreeException</code> implements an exception called when the tree is empty
 * 
 * @author Ying Zhang 
 *
 */
public class EmptyTreeException extends Exception
{
	/**
	 * Creates an instance of <code>EmptyTreeException</code>
	 * <dt>Postcondition:
	 * 		<dd>error message is passed up to the superclass Exception
	 */
	public EmptyTreeException(String message) 
	{
			super(message);
	}

	

}
