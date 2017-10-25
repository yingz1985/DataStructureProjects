/**
* The <code>EmptyLineException</code> implements an exception called when the array is empty
* 
* @author Ying Zhang
* 
* 
*/

public class EmptyLineException extends Exception 
{
	
	/**
	* Creates an instance of <code>EmptyLineException</code>
	*  
	* <dt>Postcondition:
	*    <dd>error message is passed up to the superclass Exception
	*/
	public EmptyLineException() 
	{
		super("The line is empty, please attempt to add students first");
	}

}



