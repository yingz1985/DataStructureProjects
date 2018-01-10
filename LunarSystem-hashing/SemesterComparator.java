package lunarSystem;

import java.util.Comparator;


/**
 * <code>SemesterComparator</code> allows us to compare two courses based on 
 * the semester they were offered by implementing the Comparator interface
 * 
 * @author Ying Zhang 
 *
 *
 */
public class SemesterComparator implements Comparator
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
		try
		{
			return compare((Course)o1,(Course)o2);
		}
		catch(Exception x)
		{
			return 2;//can only compare courses
		}
	}
	/**
	 * Compares two courses based on the semester they're being offered
	 * Should return: -1 if the left course’s semester is less than the right, 
	 * 0 if the semesters are equal, and 1 if the left’s semester is greater than the right.
	 * @param left the first course to be compared 
	 * @param right the second course to be compared
	 * @return 
	 * 		 Should return: -1 if the left course’s semester is less than the right, 
	 * 		 0 if the semesters are equal, and 1 if the left’s semester is greater than the right.
	 */
	public int compare(Course left, Course right) 
	{

		
		int leftNum = Integer.parseInt(left.getSemester().substring(1));
		int rightNum = Integer.parseInt(right.getSemester().substring(1));
		if(leftNum<rightNum)
			return -1;
		else if(leftNum==rightNum)
		{
			char left1 = left.getSemester().charAt(0);
			char right1 = right.getSemester().charAt(0);
			if(left1<right1)
				return 1;
			else if(left1>right1)
				return -1;
			else 
			{
				CourseNameComparator s = new CourseNameComparator();
				
				return s.compare(left, right);
			}
				
		}
		else
		{
			return 1;
		}
	}



}
