/**
* The <code>Student</code> class defines a student object with attributes name and money
* 
* 
* @author Ying Zhang
* 
*/
public class Student implements Cloneable

{
	private String name; //student name
	private double money;//amount of money student has
	
    /**
    * constructs an instance of <code>Student</code>.
    * 
    * @param name
	*    Student name
	* @param money
	* 	  Amount of money student has
	* 
	* <dt>Precondition:
	*    <dd><code>money</code> must be greater than 0.
	*     
	* <dt>Postcondition:
	*    <dd><code>name</code> and <code>name</code> has been assigned to student object
	* 
	* @exception IllegalArgumentException
    *    Indicates that <code>money</code> is less than 0.
    */
	public Student(String name, double money)
	{
		this.name = name;
		
		if(money>0)
			this.money = money;
		else 
			throw new IllegalArgumentException("You can't have debt in middle school.");
	}
	
    /**
	* returns Student name
	* 
	* @return
	*    returns Student name 
	*/
	
	public String getName() 
	{
		return name;
	}

    /**
	* returns the current amount of money this student holds
	* 
	* @return
	*    returns the amount of money student object has
	*/
	
	public double getMoney() 
	{
		return money;
	}
	
	/**
	* sets money amount for the student object
	* 
	* @param money 
	*    amount of money the student has
	*     
	* <dt>Precondition:
	*    <dd><code>money</code> must be greater than 0.
	*  	        
	* <dt>Postcondition:
	*    <dd><code>money</code>has been assigned to the student object
	*     
	* @exception IllegalArgumentException
	* 	  Indicates that <code>money</code> is less than 0.
	* 	   
	*/
	public void setMoney(double money)
	{
		if(money>0)
			this.money = money;
		
		else 
			throw new IllegalArgumentException("You can't have debt in middle school.");
	}

	/**
	* Compare this object with another for equality
	* 
	* <dt>Precondition:
	*    <dd><code>obj</code> has to be of type <code>Student</code>
	*    <dd><code>obj</code> has to have the same attributes as this <code>Student</code>
	*     
	* @param obj
	*    an object to be compared with
	* @return 
	* 	 returns true if <code>obj</code> has the same name and the same amount of money 
 	*/
	public boolean equals(Object obj)
	{
		
		if (obj instanceof Student) 
		{
			if ((this.name.equals(((Student) obj).getName()) &&
			  this.money == ((Student) obj).getMoney()))
				
				return true;
			
			else
				return false;
		}
		
		else
			return false;
	}

	/**
	*  Generates a deep copy of this Student object
	*  
	*  @return 
	*    returns an object with the same attributes but different reference  
	*     
	*/
	public Object clone() 
	{
		return new Student(name, money);

	}

	/**
	* Generates a string representation of the Student object
	* 
	* @return 
	*    returns a string representation of student's attributes
	*/
	public String toString()
	{
		String format = String.format("%-15s%10s", this.getName(),"$"+this.getMoney());
		return format;
	}
	

}
