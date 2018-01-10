import java.util.Comparator;

/**
 * 
 * <code>NodeComparator</code> allows us to compare two Nodes based on 
 * the distance by implementing the Comparator interface
 * 
 * @author Ying Zhang 
 * 
 */
public class NodeComparator implements Comparator
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
		if(o1 instanceof Node && o2 instanceof Node)
		{
			if(((Node)o1).getDistance()<((Node)o2).getDistance())
				return -1;
			if(((Node)o1).getDistance()>((Node)o2).getDistance())
				return 1;
			else return 0;
						
		}
		else
			return -2;
	}
}
