import java.util.Comparator;

/**
 * 
 * <code>EdgeComparator</code> allows us to compare two Edges based on 
 * the cost by implementing the Comparator interface
 * 
 * @author Ying Zhang 
 * 
 */
public class EdgeComparator implements Comparator
{

	
	/**
	 * compares two objects and return 1 if the first parameter is "greater",
	 * -1 if "smaller", 0 if they are equal
	 * @param o1 first object to be compared
	 * @param 02 second object to be compared
	 * @return	returns an int result of the comparison
	 * <dt>Method implements the Comparator interface and overrides the compare method.
	 */
	public int compare(Object o1, Object o2)
	{
		if(o1 instanceof Edge && o2 instanceof Edge)
		{
			if(((Edge)o1).getCost()<((Edge)o2).getCost())
				return -1;
			if(((Edge)o1).getCost()>((Edge)o2).getCost())
				return 1;
			else return 0;
						
		}
		else
			return -2;
	}
}
