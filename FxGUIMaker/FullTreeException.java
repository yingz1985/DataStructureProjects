/**
 * The <code>FullTreeException</code> implements an exception called when the node has 10 children already 
 * 
 * @author Ying Zhang 
 */
public class FullTreeException extends Exception
{
	/**
	 * Creates an instance of <code>FullTreeExceptionn</code>
	 * <dt>Postcondition:
	 * 		<dd>error message is passed up to the superclass Exception
	 */
	public FullTreeException() 
	{
			super("This branch is full, please try another node perhaps?");
	}

	

}