/**
* 
* The <code>InvalidArgumentException</code> implements an exception called when the index is invalid
* 
* @author Ying Zhang 
* 
* 
*/
public class InvalidArgumentException extends Exception 
{
	/**
	* Creates an instance of <code>InvalidArgumentException
	* 
	* <dt>Postcondition:
	*    <dd>error message is passed up to the superclass Exception
	*/
	public InvalidArgumentException() 
	{
		super("Entered index creates space in the tree. Please try again.");
	}
	
}