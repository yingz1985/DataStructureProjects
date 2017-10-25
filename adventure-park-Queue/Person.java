import java.util.ArrayList;

/**
 * The <code>Person</code> class defines a Person object with 
 * an integer id, the max amount of lines they can be on, 
 * an arraylist of the current lines that they are on, 
 * the current status of the person (an enum), and the number of rides they have been on. 
 * 
 * @author Ying Zhang
 * ID: 110864972
 * Homework # 4
 * CSE214-R02
 * TA: David s. Li
 * 
 */
public class Person 
{
	private int number;//integer id of the person
	private int maxLines;//the maximum amount of lines the person can be on
	private ArrayList<Ride> lines;//the current lines the person is on
	private Status status;//current status of the person 
	private int numRides;//the number of rides the person has been on
	
	
	

   
   
	/**
	 * 
	 * constructs an instance of <code>Person</code>.
     * 
     * @param number
	 *    The person's integer ID
	 * 
	 * <dt>Precondition:
	 *    <dd><code>number</code> must be greater than 0.
	 *     
	 * <dt>Postcondition:
	 *    <dd><code>number</code> is set asthe person ID, status is set to AVAILABLE,
	 *    arrayList containing  all the rides the person is on is initialized, 
	 *    number of rides the person has been on has been set to zero. 
	 *    	
	 * 
	 * @exception IllegalArgumentException
     *    Indicates that <code>number</code> is less than 0.
	 * 
	 * 
	 */
	public Person(int number)throws IllegalArgumentException
	{
		status = Status.AVAILABLE;
		lines = new ArrayList<Ride>() ;
		numRides = 0;
		if(number>0)
			this.number = number;
		else 
			throw new IllegalArgumentException("Did not enter valid number");
	}
	
	/**
	 * 
	 * increments the number of rides the <code>Person</code> object has ridden 
	 * <dt>Postcondition:
	 * 		<dd>numRides is incremented by one
	 */
	public void finishedARide()
	{
		numRides++;
		
	}
	
	/**
	 * returns the number of rides the person has been on 
	 * 
	 * @return returns person's attribute <code>numRides</code>
	 */
	public int getNumRides()
	{
		return numRides;
	}
	
	/**
	 * returns the <code>Person</code> object's status
	 * 
	 * @return returns person's attribute <code>
	 */
	public Status getStatus() 
	{
		return status;
	}

	/**
	 * sets Status for the <code>Person</code> object 
	 * @param status indicates whether or not a person is on ride, available, or on hold in line
	 * <dt>Postcondition:
	 * 		<dd> <code>status</code> is set as Person's status attribute 
	 */
	public void setStatus(Status status)
	{
		this.status = status;
	}

	/**
	 * returns <code>Person</code> attribute number;
	 * 
	 * @return returns person's integer ID
	 */
	public int getNumber() 
	{
		return number;
	}
	
	/**
	 * sets <code>Person</code> attribute number;
	 * @param number student integer id
	 * <dt>Postcondition:
	 * 		<dd> number is assigned to the Person's integer ID number
	 * 
	 * 
	 */
	public void setNumber(int number)
	{
		this.number = number;
	}
	
	/**
	 * returns Person attribute maxLines 
	 * @return returns the maximum number of lines the person can be on 
	 * 
	 */
	public int getMaxLines() 
	{
		return maxLines;
	}
	/**
	 * sets parameter maxLines to the Person attribute maxLines 
	 * 
	 * @param maxLines the maximum amount of lines the person can be on 
	 * <dt>Postcondition:
	 * 		<dd>maxLines is set as the Person attribute MaxLines 
	 */
	public void setMaxLines(int maxLines)
	{
		this.maxLines = maxLines;
	}
	/**
	 * adds an additional line to the person's list of lines
	 * 
	 * @param line the line the person is on
	 * <dt>Postcondition:
	 * 		<dd> line is added to an array lines 
	 */
	public void addLine(Ride line)
	{
		lines.add(line);
	}
	
	/**
	 * returns an array of lines the person is on
	 * @return returns the ArrayList lines object
	 * 
	 */
	public ArrayList<Ride> getLines()
	{
		return lines;
	}

	/**
	* Generates a string representation of the <code>Person</code> object
	* 
	* @return 
	*    returns a string representation of student's membership exclusivity and its ID number
	*/
	public String toString() 
	{
		String status = "";
		if(maxLines==1)
			status = "Regular ";
		if(maxLines==2)
			status = "Silver ";
		if(maxLines==3)
			status = "Gold ";
		return status + this.getNumber();
	
	}
	
	
}
