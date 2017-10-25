/**
 * 
 * The <code>HoldingQueue</code> class models a Queue, and extends <code>VirtualLine</code>, 
 * except in a holdingQueue there is a user-defined capacity
 * 
 * @author Ying Zhang
 * 
 * 
 */
public class HoldingQueue extends VirtualLine
{
	private int maxSize;//the maximum objects that can be stored in holdingQueue 

	
	/**
	 * returns the max size of the HoldingQueue 
	 * 
	 * @return returns HoldingQueue attribute <code>maxSize</code>;
	 */
	public int getMaxSize() 
	{
		return maxSize;
	}
	/**
	 * sets the capacity of the holdingQueue 
	 * @param maxSize the capacity of the holdingQueue 
	 * <dt>Postcondition: 
	 * 		<dd> maxSize has been set as the capacity of the holdingQueue 
	 */
	public void setMaxSize(int maxSize) 
	{
		this.maxSize = maxSize;
	}
	/**
	 * returns true if the holdingQueue has reached its maximum capacity 
	 * return returns true if the size is equal to its defined capacity 
	 */
	public boolean isFull()
	{
		if(super.size()==getMaxSize())
			return true;
		return false;
	}
	
	
	

}
