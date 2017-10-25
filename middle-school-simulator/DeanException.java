/**
* The <code>DeanException</code> implements an exception called when the array is full
* 
* @author Ying Zhang
* 
* 
*/
public class DeanException extends Exception 
{
	
	/**
	* Creates an instance of <code>DeanException</code>
	* 
	* <dt>Postcondition:
	*    <dd>error message is passed up to the superclass Exception
	*/
	public DeanException() 
	{
		super("You tried to add a student to a full lunch line."
		  + " Dean Mean has picked up the student and given them a healthy dose of detention. "
		  + "Therefore, they will not be added to the lunch line.");
	}
	
}