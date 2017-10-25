/**
* The <code>StudentLine</code> class implements an ArrayList-like
* data structure containing <code>Student</code> objects
* 
* @author Ying Zhang 
* 
* 
*/
public class StudentLine implements Cloneable
{
	private Student[] students; //An array holding student objects
	private int studentCount; //The number of students currently in the array
	final int CAPACITY = 20; //The maximum number of students the array can hold in a line

	/**
	* Constructs an instance of <code>StudentLine</code>.
 	* 
 	* <dt>Postcondition:
 	*    <dd><code>CAPACITY</code> has been set as array size
 	*    <dd><code>studentCount</code> has been initialized to 0 students
 	*/	  
	public StudentLine()
	{
		students = new Student[CAPACITY];
		studentCount = 0;
	}

	/**
	* returns the total number of students on the lunch line
	* 
	* @return
	*    returns current <code>studentCount</code>.
	*/
	public int numStudents() 
	{
		return studentCount;
	}

	/**
	* returns a <code>Student</code> object reference at a user-specified index
	* 
	* @param index
	*    the index of a specific <code>Student</code> in the array
	*     
	* <dt>Precondition:
	*    <dd>index must not be smaller than the array size, or greater than the student count
	*    <dd>index must refer to a <code>Student</code> object
	*     
	* @return
	*    returns <code>Student</code> if index corresponds to a specific student
	*     
	* <dt>Postcondition:
	*    <dd>The array remains unchanged.
	*     
	* @exception ArrayIndexOutOfBoundsException
	*    Indicates that entered parameter does not point to a valid index in the array
	*/
	public Student getStudent(int index) throws ArrayIndexOutOfBoundsException
	{
		if (index >= 0 && index < studentCount && students[index] != null)
			return students[index];
		else
			throw new ArrayIndexOutOfBoundsException("That is an invalid index");

	}

	/**
	* Gets the reference to the Student at the given index
	* 
	* @param index
	*    the index of a specific <code>Student</code> in the array
	*     
	* <dt>Precondition:
	*    <dd>The array must not be empty, or <code>studentCount</code> must not be zero
	*    <dd>index must not be smaller than the array size, or greater than the student count
	*    <dd>index must refer to a <code>Student</code> object
	*     
	* @return
	*    returns <code>Student</code> object to be removed at given valid index
	*     
	* <dt>Postcondition:
	*    <dd><code>Student</code> at given index is removed from the array
	*    <dd>Every <code>Student</code>behind the index moves up one space
	*     
	* @exception EmptyLineException
	*    Indicates there are no students on line to be removed.
	* @exception ArrayIndexOutOfBoundsException
	*    Indicates entered index does not correspond to a valid <code>Student</code> object.
	*/
	public Student removeStudent(int index) throws EmptyLineException, ArrayIndexOutOfBoundsException
	{
		if (studentCount == 0)
			throw new EmptyLineException();
		else 
		{
			Student temp = getStudent(index);
			
			for (int i = index; i < studentCount - 1; i++) 
				students[i] = students[i + 1];
			
			students[studentCount - 1] = null;
			studentCount--;
			return temp;

		}
	}
	
	/**
	* Adds a student to the end of the array
	* 
	* @param student
	*    new <code>Student</code> that should be added to the array
	*     
	* <dt>Precondition:
	*    <dd> The line must not exceed its <code>CAPACITY</code> of 20 students
	*     
	* @return
 	*    returns the <code>Student</code> object entered as parameter
 	*     
 	* <dt>Postcondition:
 	*    <dd>a new <code>Student</code> object has been added to the end of the array
 	* @exception DeanException
 	*    Indicates the array has reached its maximum capacity 
 	*/
	public Student addStudent(Student student) throws DeanException
	{
		if (studentCount == 20)
			throw new DeanException();
		
		students[studentCount] = student;
		studentCount++;
		return student;
	}

	/**
	* Adds a student at given index, moving all other students back one index
	* 
	* @param index
	*    index of <code>Student</code> to be placed at
	* @param student
	*    <code>Student</code> to be added into the array
	*     
	* <dt>Precondition:
	*    <dd>The <code>studentCount</code> must be smaller than the <code>CAPACITY</code>
	*    <dd>index must not be smaller than the array size
	*    <dd>index must also not be greater than <code>stduentCount</code>+1
 	*    <dd>index must refer to a <code>Student</code> object
 	*    
 	* @return
 	*    returns the <code>Student</code> object enter as parameter 
 	*    if student is added successfully
 	* 
 	* <dt>Postcondition: 
 	* 	  <dd><code>Student</code> starting from index is moved one index back
 	* 	  <dd><code>Student</code> entered as parameter is added to given index.
 	* 
 	* @exception DeanException
 	*     Indicates the array has reached its capacity of 20 students
 	* @exception InvalidArgumentException
 	* 	  Indicates the entered index does not correspond to a valid <code>Student</code> object
 	*/
	public Student addStudent(int index, Student student) throws DeanException, InvalidArgumentException
	{
		if (index > studentCount + 1 || index < 0)
			throw new InvalidArgumentException();
		
		if (studentCount == 20)
			throw new DeanException();
		
		else 
		{
			for (int i = studentCount + 1; i > index; i--)
				students[i] = students[i - 1];
			
			
			students[index] = student;
			studentCount++;
		
		}
		return student;

	}

	/**
	* Change student positions at index1 and index2
	* 
	* <dt>Precondition:
	*    <dd> both indices must be greater than -1 and less than or equal to <code>studentCount</code>
	*     
	* @param index1
	*    first index of <code>Student</code> to be swapped
	* @param index2
	*    second index of <code>Student</code> to be swapped 
	*     
	* <dt>Postcondition:
 	*    <dd><code>Student</code> object at index1 and index2 has swapped positions 
 	*     
 	* @throws ArrayIndexOutOfBoundsException
 	*    Indicates either indices are invalid
 	*/
	public void swapStudents(int index1, int index2) throws ArrayIndexOutOfBoundsException 
	{
		if (index1 < 0 || index1 >= studentCount || index2 < 0 || index2 >= studentCount) 
			throw new ArrayIndexOutOfBoundsException("oops, your entered indices seen to be off. swap failed");
		
		else
		{
			Student temp = students[index1];
			students[index1] = students[index2];
			students[index2] = temp;
		}
	}

	/**
	* Creates a deep copy of this <code>StudentLine</code> object
	* 
	* @return
	*    returns a copy of with the same attributes but different reference
	*/
	public Object clone()
	{
		StudentLine newLine = new StudentLine();
		for (int i = 0; i < studentCount; i++)
		{
			try 
			{
				newLine.addStudent((Student) (students[i]).clone());
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		return newLine;
	}

	/**
	* Checks if this student line is equal to another object
	* <dt>Precondition:
	* 	 <dd><code>o</code> must have the same <code>studentCount</code> as this <code>StudentLine</code>
	*    <dd><code>o</code> has to be of type <code>StudentLine</code>
	*    <dd><code>o</code> has to have the same attributes as this <code>StudentLine</code>
	*    and in the same order in the array
 	*     
 	* @param obj
 	*    an object to be compared with
 	*  
 	* @return 
 	*    returns true if precondition is satisfied, else false.
 	*     
 	*/
	public boolean equals(Object o) 
	{
		if (o instanceof StudentLine) 
		{

			if (studentCount == 0 && ((StudentLine) o).numStudents() == 0)
				return true;
			
			if (studentCount != ((StudentLine) o).numStudents())
				return false;

			for (int i = 0; i < ((StudentLine) o).numStudents(); i++)
			{
				if (!((StudentLine) o).getStudent(i).getName().equals(this.getStudent(i).getName())
				  || ((StudentLine) o).getStudent(i).getMoney() != this.getStudent(i).getMoney())
					return false;
			}
			return true;
		}
		return false;
	}


	/**
	* Generates a string representation of <code>StudentLine</code>
	* 
	* @return 
	*    returns string representations of <code>Student</code> in the array
	*/
	public String toString() 
	{
		String result = "";
		for (int i = 0; i < studentCount; i++) 
		{

			result += i + 1 + "." + students[i].toString() + "\n";

		}
		return result;
	}
	
	

}
