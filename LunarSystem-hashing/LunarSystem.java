package lunarSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

/**
 * <code>LunarSystem</code> allows the user to interact with a database of Students. 
 * Provides the user with a menu-based interface that allows them to add, remove, and
 * edit Students, as well as their courses. 
 *  
 * @author Ying Zhang 
 *
 *
 */
public class LunarSystem
{

	private static HashMap<String, Student> database;
	
	public static void main(String[] args)
	{
		database = new HashMap<String, Student>(); //initialize hashmap 
		
		System.out.println("Welcome to the Lunar System, a second place "
		  + "course registration system for second rate courses at a second class school.\n");

		checkSaved();//try to load previous data, if not found, create a new hashmap 
		
		
		String option = "";
		do
		{
			System.out.println("\nMenu:\n\tL)Login\n\tX)Save state and quit\n\t"
					  + "Q)Quit without saving state.\n");
			Scanner user = new Scanner(System.in);
			System.out.print("Please select an option:");
			option = user.next().toUpperCase();
			
			if(option.equals("L"))
			{
				Scanner input = new Scanner(System.in);
				System.out.print("Please enter webid: ");
				String id = input.nextLine();
				
				
				if(id.equals("REGISTRAR") || id.equals("WebID"))
				{
					System.out.println("\nWelcome "+id+"\n");
					Scanner admin = new Scanner(System.in);
					String choice ="";
					displayAdminOption();
					do
					{
						
						System.out.print("Please select an option:");
						choice = admin.next().toUpperCase();
						
						if(choice.equals("R"))
						{
							Scanner register = new Scanner(System.in);
							System.out.print("Please enter a webid for the new student: ");
							String name = register.nextLine();
							Student newStudent = new Student(name);
							try
							{
								addStudent(name,newStudent);
							}
							catch(Exception x)
							{
								System.out.println(x.getMessage());
							}
						}
						if(choice.equals("D"))
						{
							Scanner register = new Scanner(System.in);
							System.out.print("Please enter a webid for the student to be deregistered: ");
							String name = register.nextLine();

							try
							{
								removeStudent(name);
							}
							catch(Exception x)
							{
								System.out.println(x.getLocalizedMessage());
							}
						}
						if(choice.equals("E"))
						{
							Scanner register = new Scanner(System.in);
							System.out.print("Please enter course: ");
							String course = register.nextLine().toUpperCase();
							
							String department = course.split(" ")[0];
							int courseNum = Integer.parseInt(course.split(" ")[1]);
							System.out.println(findStudentInCourse(department,courseNum));
							
							
						}
						
						
						
					}
					while(!choice.equals("L"));
					System.out.println(id+" logged out.");
				
						
					
				}
				else
				{
					Student student;
					try
					{
						
						//try to find the student in the database
						//if student does not exist, throw an exception
						 student = getStudent(id);
					}
					catch(Exception x)
					{
						System.out.println(x.getLocalizedMessage());
						continue;
					}
					System.out.println("\nWelcome "+id+"\n");
					displayStudentOption();
				
					Scanner students = new Scanner(System.in);
					String choice;
					
					do
					{
						 System.out.print("Please select an option:");
						 choice = students.next().toUpperCase();
						 //allows a student to add a course
						 if(choice.equals("A"))
						 {
							 Scanner stdin = new Scanner(System.in);
							 System.out.print("Please enter course name: ");
							 String courseName = stdin.nextLine().toUpperCase();
							 
							 String department = courseName.split(" ")[0];
							 int courseNum = Integer.parseInt(courseName.split(" ")[1]);
							 System.out.print("Please select a semester:");
							 
							 String semester = stdin.nextLine().toUpperCase();
							 addCourse(department,courseNum,semester,student);
						 }
						 //allows a student to drop one of its courses
						 if(choice.equals("D"))
						 {
							 Scanner stdin = new Scanner(System.in);
							 System.out.print("Please enter course name: ");
							 String courseName = stdin.nextLine().toUpperCase();
							 
							 String department = courseName.split(" ")[0];
							 int courseNum = Integer.parseInt(courseName.split(" ")[1]);

							 dropCourse(department,courseNum,student);
							 
						 }
						 //arranges and prints the list of courses the student is enrolled in
						 //sorting based on the course name course number
						 if(choice.equals("C"))
						 {
							 CourseNameComparator s = new CourseNameComparator();
							 Collections.sort(student.getCourses(),s);
							 System.out.print(student);
						 }
						 //arranges and prints the list of courses the student is enrolled in
						 //Sorting based on the semester the course is being offered
						 if(choice.equals("S"))
						 {
							 SemesterComparator s = new SemesterComparator();
							 Collections.sort(student.getCourses(),s);
							 System.out.print(student);
						 }
					}
					
					while(!choice.equals("L"));
					
				}
			}
			//saves the database to be read the next time system runs 
			if(option.equals("X"))
			{
				save();
			}
			
			
		}
		while(!option.equals("Q"));
		System.out.println("Good bye, please pick the right SUNY next time!\n");
		File n = new File("Lunar.ser");
    	n.deleteOnExit();
    	//When the system exists without saving, all previous data is lost
    	
		System.exit(0);
		
	}
	
	/**
	 * returns a string representation of all student objects registered in the course
	 * @param department the department the course belongs to
	 * @param courseNum	the number of the course
	 * @return returns a string of all students in the course
	 */
	public static String findStudentInCourse(String department, int courseNum)
	{
		String result = "\nStudent    Semester\n-------------------\n";

		for(Student n : database.values())
		{
			for(int i = 0; i<n.getCourses().size();i++)
			{
				Course current = n.getCourses().get(i);
				if(current.getDepartment().equals(department) && current.getNumber()==courseNum)
				{
					String add = String.format("%-11s%-12s",n.getWebID(),current.getSemester());
					result+=add+"\n";
				}
			}
		}
		return result;

		
		
		
	}
	
	/**
	 * drops a course in the student arraylist of courses
	 * @param department the department the course belongs to
	 * @param courseNum	the number of the course
	 * @param semester the semester the course is offered
	 * @param student	the student taking the course
	 * <dt>Postcondition:
	 * 		<dd>The course is removed from the student arraylist of courses
	 */
	public static void dropCourse(String department, int courseNum, Student student)
	{
		Course newCourse = new Course(department,courseNum,"");

		CourseNameComparator n = new CourseNameComparator();
		for(int i = 0; i<student.getCourses().size();i++)
		{
			Course compare = student.getCourses().get(i);
		
			int courseNameCompared = n.compare(newCourse, compare);
			
			if(courseNameCompared==0)
			{
				student.getCourses().remove(i);

				System.out.println(department+" "+courseNum+" removed"+"\n");
			
				
				return;
			}
			
		}
	}
	
	/**
	 * adds a course in the student arraylist of courses
	 * <dt>Precondition:
	 * 		The student cannot be already in the course
	 * @param department the department the course belongs to
	 * @param courseNum	the number of the course
	 * @param semester the semester the course is offered
	 * @param student	the student taking the course
	 * <dt>Postcondition:
	 * 		<dd>student is registered into the course 
	 */
	public static void addCourse(String department, int courseNum, String semester, Student student)
	{
		Course newCourse = new Course(department,courseNum,semester);
		SemesterComparator s = new SemesterComparator();
		CourseNameComparator n = new CourseNameComparator();
		for(int i = 0; i<student.getCourses().size();i++)
		{
			Course compare = student.getCourses().get(i);
			int semesterCompared = s.compare(newCourse, compare);
			int courseNameCompared = n.compare(newCourse, compare);
			
			if(semesterCompared == 0 && courseNameCompared==0)
			{
				System.out.println("Student is already enrolled in the course\n");
				return;
			}
			
		}
		char sem = semester.charAt(0);
		String m ="";
		if(sem=='F')
		{
			m = "Fall";
		}
		else
			m = "Spring";
		student.getCourses().add(newCourse);
		System.out.println(department+" "+courseNum+" added in "+m+" "+semester.substring(1)+"\n");
	}
	/**
	 * prints the menu when registrar is logged in
	 */
	public static void displayAdminOption()
	{
		System.out.println("Options:\n\tR) Register a student\n\tD) De-register a student"
	      + "\n\tE) View course enrollment\n\tL) Logout\n");
	}
	/**
	 * adds a student into the database
	 * <dt>Precondition:
	 * 		<dd>student must not already exist in the database (has to be a new student)
	 * @param webID student's webID
	 * @param student student to be inserted into the database
	 * @throws IllegalArgumentException
	 * 		indicates the student already exists in the database
	 * <dt>Postcondition:
	 * 		<dd>Student with webID is added to the hashmap database
	 */
	public static void addStudent(String webID,Student student)throws IllegalArgumentException
	{
		if(database.get(webID) != null)
			throw new IllegalArgumentException(webID+ " is already registered.\n");
		database.put(webID, student);
		System.out.println(webID+" registered.\n");
	}
	/**
	 * removes the student in the database
	 * @param webID student's webID
	 * <dd>Precondition:
	 * 		<dd> webID is not null and is a valid webID
	 * <dt>Postcondition:
	 * 		<dd> student with the webID is removed from the database
	 * @throws IllegalArgumentException 
	 * 		 indicates the webID doesn't map to a student in the database
	 */
	public static void removeStudent(String webID)
	{
		if(webID == null)
			throw new IllegalArgumentException("Student name is empty");
		
		if(database.get(webID)==null)
			throw new IllegalArgumentException(webID+" is not enrolled in the system");
		
		database.remove(webID);
		System.out.println(webID+" deregistered\n");
	}
	/**
	 * returns Student is the student is registered in the system
	 * @param webID student's webID
	 * @return returns <code>Student</code> if it's in the database
	 * @throws IllegalArgumentException - indicates the student is not in the database
	 */
	public static Student getStudent(String webID)
	{
		Student student = database.get(webID);
		if(student==null)
			throw new IllegalArgumentException(webID+" is not registered in the system");
		return student;
		
	}
	/**
	 * prints menu when a student is logged into the system
	 */
	public static void displayStudentOption()
	{
		System.out.println("Options:\n\tA)Add a class\n\tD)Drop a class"
		  + "\n\tC)View your classes sorted by course name/department"
		  + "\n\tS)View your courses sorted by semester\n");
	}
	/**
	 * 
     * Checks if the file exists in the working directory. If it exists,
     * read the object file and then load the data into the program
     * If it doesn't exist, a new empty hashmap database is created
     *  @throws Exception - indicates the file cannot be read 
     *  <dt>Postcondition:
     *  	<dd>if "Lunar.ser" exists then it is loaded and treated as the database for the system
     *  	<dd>if "Lunar.ser" does not exist then a new hashMap is created and used
     */
	 
	public static void checkSaved()
	{
		File f = new File("Lunar.ser");
		if(f.exists()&& !f.isDirectory())
		{
			try
			{
				FileInputStream file = new FileInputStream("Lunar.ser");
				ObjectInputStream inStream = new ObjectInputStream(file);
				database = (HashMap) inStream.readObject();
				inStream.close();
				System.out.println("Previous data loaded");
			}
			catch(Exception e)
			{
				System.out.println("error: read failure");
				database = new HashMap<String, Student> ();
			}
		}
		else
		{
			System.out.println("No previous data found");
			database = new HashMap<String, Student> ();
		}
	}
	
    /**
     * Saves the class data in the file called "Lunar.ser". 
     * 
     * @throws Exception indicates the file cannot be saved
     * <dt>Postcondition:
     * 		<dd>A file called "Lunar.ser" is created in the system with hashmap data
     * 		<dd> program is terminated
     * 
     */
    public static void save() 
    {

        try 
        {
        	
        	FileOutputStream file = new FileOutputStream("Lunar.ser");
        	ObjectOutputStream outStream = new ObjectOutputStream (file);

            outStream.writeObject(database); 
        	outStream.close();
        	
        } 
        catch (Exception e)
        {
            System.out.println("Error: Unable to write data to file.");
        }
        System.out.println("System state saved, system shut down for maintenance.");
        System.exit(0);
    }
    

    
	

}
