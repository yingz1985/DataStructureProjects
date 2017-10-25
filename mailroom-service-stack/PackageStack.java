package iringMailroom;
import java.util.ArrayList;
/**
 * The <code>PackageStack</code> class implements an stack-like
 * data structure containing <code>Package</code> objects contained in an arraylist
 * 
 * @author Ying Zhang
 * ID: 110864972
 * Homework # 3
 * CSE214-R02
 * TA: David s. Li
 * 
 *
 */ 
public class PackageStack {
	private final int CAPACITY = 7; //maximum number of packages in the stack
	private int countPackages; // number of packages in the stack
	ArrayList<Package> stack; 
	
	/**
	 * Constructs an instance of <code>PackageStack</code> with an empty stack of packages
	 * <dt>Postcondition:
	 * 		<dd>PackageStack has been initialized with an empty arraylist
	 * 		<dd>countPackages has been initialized to 0 packages 
	 */
	public PackageStack()
	{
		stack = new ArrayList();
		countPackages = 0; 
	}
	
	/**
	 * Pushes package x onto the top of the backing data structure
	 * 
	 * @param x
	 * 		Object Package ready to be added to the top of the stack
	 * <dt>Precondition:
	 * 		<dd>stack is not full.
	 * @throws FullStackException
	 * 		indicates there are already 7 packages (reached its capacity) 
	 * <dt>Postcondition:
	 * 		<dd>Package x added onto the top of the stack
	 */
	public void push(Package x) throws FullStackException
	{
		if(isFull())
		{
			throw new FullStackException();
		}
		else
		{
			stack.add(x);
			countPackages++;
		}
			
	}
	/**
	 * Removes the topmost package from the stack and returns it.
	 * 
	 * 
	 * @return
	 * 		returns the topmost package before being removed
	 *  <dt>Precondition:
	 * 		<dd>The stack is not empty
	 * 
	 * <dt>Postcondition:
	 * 		<dd>The topmost Package is removed from the stack
	 * 
	 * @throws EmptyStackException
	 * 		indicates there are no packages to be removed (empty stack)
	 * 
	 */
	public Package pop() throws EmptyStackException
	{
		if(isEmpty())
		{
			throw new EmptyStackException();
		}
		else 
		{
			Package ready = stack.get(countPackages-1);
			stack.remove(countPackages-1); 
			countPackages--;
			return ready;
		}
			
		
	}
	
	/**
	 * Returns the topmost Package from the stack without removing it. 
	 *
	 * @return
	 * 		returns the topmost Package in the stack
	 * <dt>Precondition:
	 * 		<dd>The stack is not empty
	 * @throws EmptyStackException
	 * 		indicates the stack is empty
	 */
	public Package peek() throws EmptyStackException
	{
		if(countPackages==0)
			throw new EmptyStackException();
		else
		{
			return stack.get(countPackages-1);
			
		}
	}
	
	/**
	 * Returns true if the stack is full, false otherwise.
	 * 
	 * @return
	 * 		returns true if there're currently 7 packages in the stack, false otherwise
	 */
	public boolean isFull()
	{
		if(countPackages==CAPACITY)
			return true;
		else 
			return false;
	}
	/**
	 * returns true if the stack is empty,false otherwise
	 * @return
	 * 		Returns true if there are no packages in the stack, false otherwise. 
	 */
	public boolean isEmpty()
	{
		if(countPackages==0)
			return true;
		else
			return false;
	}

	/**
	 * Generates a string representation of the PackageStack
	 * 
	 * @return
	 * 		returns a list of string representations of packages stacked in the arraylist
	 */
	public String toString() 
	{
		if(countPackages==0)
			return "empty.";
		String result = "";
		for(int i = 0; i<countPackages;i++)
		{
			if(i==0 || (i)%7!=0)
				result+=stack.get(i).toString();
			else 
				result+="\n"+stack.get(i).toString();
		}
		return result;
	}
	
	

}
