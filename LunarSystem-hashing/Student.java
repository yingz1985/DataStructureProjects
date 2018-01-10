package lunarSystem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The <code>Student</code> class defines a student object with attributes webID 
 * and an arraylist of all the courses the student is currently taking. 
 * The class will serve as the stored element of the Lunar System database
 * 
 * @author Ying Zhang 
 *
 *
 */
public class Student implements Serializable
{
	private String webID; //student's webID used to sign in the Lunar system
	private ArrayList<Course> courses;//a list of all the courses the student is taking
	
    /**
    * constructs an instance of <code>Student</code>.
    * 
    * @param ID   student's webID
	*     
	* <dt>Postcondition:
	*    <dd><code>ID</code> has been assigned to student object
	* 
    */
	public Student(String ID)
	{
		this.webID = ID;
		courses = new ArrayList();
	}
	
	/**
	 * returns a string representation of Student object's attribute webID
	 * 
	 * @return returns webID
	 */
	public String getWebID()
	{
		return webID;
	}
	
	/**
	 * sets webID for the student
	 * @param webID student's ID
	 * <dt>postcondition:
	 * 		<dd> parameter webID is assigned to the class data field webID
	 */
	public void setWebID(String webID)
	{
		this.webID = webID;
	}
	/**
	 * returns an arrayList of all the courses the student is taking
	 * @return	returns Student attribute courses
	 * 
	 */
	public ArrayList<Course> getCourses()
	{
		return courses;
	}
	
	/**
	 * sets courses for the student 
	 * 
	 * @param courses	an arrayList of courses
	 * <dt>Postcondition:
	 * 		<dd> parameter courses has been assigned to the student attribute courses
	 * 
	 */
	public void setCourses(ArrayList<Course> courses)
	{
		this.courses = courses;
	}
	/**
	 * returns a string representation of all the courses the student is enrolled in
	 * @return	returns a string representation of all the courses contained in the arraylist
	 */
	public String toString()
	{
		String result = "\nDept. Course Semester\n-------------------------------\n";
		for(int i = 0;i<courses.size();i++)
		{
			result+= courses.get(i)+"\n";
		}
		return result+"\n";
	}
	
}
