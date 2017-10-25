package iringMailroom;
/**
 * The <code>FloorStack</code> class implements an stack-like
 * data structure containing <code>Package</code> objects contained in an arraylist
 * 
 * @author Ying Zhang
 * 
 * 
 *
 */ 
public class FloorStack extends PackageStack{
	
	/**
	 * Constructs an instance of <code>PackageStack</code> with an empty stack of packages
	 * <dt>Postcondition:
	 * 		<dd>PackageStack has been initialized with an empty arraylist
	 * 		<dd>countPackages has been initialized to 0 packages 
	 */
	public FloorStack()
	{
		super();
	}
	
	/**
	 * returns false so the floor stack never catches a FullStackException
	 * @return
	 * 		returns false since floor stack should never be full
	 */
	public boolean isFull()
	{
		return false;
	}
	
	

}
