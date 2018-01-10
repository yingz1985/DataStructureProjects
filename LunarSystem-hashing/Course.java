package lunarSystem;

import java.io.Serializable;

/**
 * The <code>Course</code> class defines a course object with attributes designated department,
 * a three-digit course number, and a semester associated with it. 
 * Courses will be associated with the Students that are taking them
 * 
 * @author Ying Zhang 
 *
 *
 */
public class Course implements Serializable
{
	private String department;//department the course belongs to
	private int number;//course number
	private String semester;//the semester the course is offered
	/**
	 * constructs an instance of <code>Course</code>.
	 * 
	 * @param department the department the course belongs to
	 * @param number	the course number
	 * @param semester	the semester the course is offered
	 * 
	 * <dt>Postcondition:
	 * 		<dd>a Course object is created with attributed initialized to parameters
	 * 		<dd>parameter department has been assigned to the attribute department
	 * 		<dd>parameter number has been assigned to the attribute number
	 * 		<dd>parameter semester has beeen assigned to the attribute semester
	 */
	public Course(String department, int number, String semester)
	{
		this.department = department;
		this.number = number;
		this.semester = semester;
	}
	/**
	 * returns a string representation of the department the course belongs to 
	 * @return returns Course's attribute department
	 */
	public String getDepartment()
	{
		return department;
	}
	/**
	 * sets the department
	 * @param department the department the course belongs to
	 * <dt>Postcondition:
	 * 		<dd> parameter department has been assigned to the course attribute department
	 */
	public void setDepartment(String department)
	{
		this.department = department;
	}
	/**
	 * returns the course number 
	 * @return returns Course attribute number
	 */
	public int getNumber()
	{
		return number;
	}
	/**
	 * sets course number 
	 * @param number course number
	 * <dt>Postcondition:
	 * 		<dd>parameter number is assigned to data field number
	 */
	public void setNumber(int number)
	{
		this.number = number;
	}
	/**
	 * returns a string representation of the course semester
	 * @return returns Course attribute semester
	 */
	public String getSemester()
	{
		return semester;
	}
	/**
	 * sets course semester 
	 * @param semester	the semester the course is being offered
	 * <dt>Postcondition:
	 * 		<dd>parameter semester is assigned to attribute semester
	 */
	public void setSemester(String semester)
	{
		this.semester = semester;
	}
	/**
	 * returns a string representation of the course's department, course number, 
	 * and the semester it's offered
	 * @return	returns a string representation of all of the course's attributes
	 */
	public String toString()
	{
		return String.format("%-6s%-8s%-6s", department,number,semester);
	}
	
}
