import java.util.LinkedList;
/**
 * 
 * The <code>VirtualQueue</code> class models a Queue, and extends <code>LinkedList</code>
 * 
 * @author Ying Zhang
 * 
 */
public class VirtualLine extends LinkedList<Person>{
	
	/**
	 * enqueues a person to the end of the virtualLine 
	 * 
	 * @param p person to be enqueued into the virtualLine 
	 * 
	 * <dt>Postcondition:
	 * 		<dd> Person p is added to the end of the line 
	 */
	public void enqueue(Person p)
	{
		if(!isFull())
			super.addLast(p);
		
	}
	
	/**
	 * removes the front of the virtualLine and return the front 
	 * 
	 * @return returns the head of the linkedList before removing it 
	 * <dt>Postcondition:
	 * 		<dd> removes the head of the linkedList in which the virtualLine is extended from 
	 */
	public Person dequeue()
	{

			return (Person) super.remove(0);
	}
	

	/**
	 * returns the person in front of the virtualline
	 */
    public Person peek() 
    {

            return (Person) super.get(0);
        
    }
    /**
     * returns true if the virtualLine is empty 
     */
    public boolean isEmpty()
    {
		return super.isEmpty();
	}
    
    /**
     * return false since there is no maximum capacity 
     * @return false 
     */
    public boolean isFull()
    {
    	return false;
    }
    /**
     * returns a string representation of all the Person objects stored in the virtualLine 
     */
    public String toString() 
    { 
    	
        String result = "";


        if (this.size() == 0) {
            return (result = "empty");
        }

        for (int i = 0; i < this.size(); i++) 
        {

        	if(i==this.size()-1)
        		 result += ((Person)this.get(i)).toString();
        	else 
        		result += ((Person)this.get(i)).toString()+", ";
        }

        return result;
    }

    
    
    
	

}
