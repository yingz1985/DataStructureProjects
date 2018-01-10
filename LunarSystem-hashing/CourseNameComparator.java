package lunarSystem;

import java.util.Comparator;

/**
 * <code>CourseNameComparator</code> allows us to compare two course names with the following priority:
 *  department and then number by implementing the Comparator interface
 * 
 * @author Ying Zhang 
 *
 *
 */
public class CourseNameComparator implements Comparator 
{


	/**
	 * 
	 * compares two objects and return 1 if the first parameter is "greater",
	 * -1 if "smaller", 0 if they are equal
	 * @param o1 first object to be compared
	 * @param 02 second object to be compared
	 * @return	returns an int result of the comparison
	 * <dt>Method implements the Comparator interface and overrides the compare method.
	 */

	public int compare(Object o1, Object o2)
	{
		try
		{
			
			return compare((Course)o1,(Course)o2);
		}
		catch(Exception x)
		{
			return 2;
		}

	}
	
	/**
	 * 
	 * Compares two courses based on their department and course number
	 * should return: -1 if the left course name is “less” than the right course name,
	 * 0 if they are equal, and 1 if the left course name is “greater” than the right course.
	 * @param left the first course to be compared 
	 * @param right the second course to be compared
	 * @return 
	 * 		return: -1 if the left course name is “less” than the right course name,
	 * 		0 if they are equal, and 1 if the left course name is “greater” than the right course.
	 */
	 
	public int compare(Course left, Course right)
	{
		int result = left.getDepartment().compareTo(right.getDepartment());
		if(result<0)
			return -1;
		else if(result==0)
		{
			if(left.getNumber()>right.getNumber())
				return 1;
			else if(left.getNumber()==right.getNumber())
				return 0;
			else
				return -1;
		}
		else 
			return 1;

	}
	


}
