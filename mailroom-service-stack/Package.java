package iringMailroom;
/**
 *  The <code>Package</code> class defines a Package object with attributes recipient name,
 *  arrival data, and weight. 
 *  
 * @author Ying Zhang
 * 
 *
 */
public class Package
{
	private String recipient; 
	private int arrivalDate; 
	private double weight;
	/**
	 * constructs an instance of <code>Package</code>.
	 * @param recipient
	 * 		the receiver name of the package
	 * @param arrivalDate
	 * 		day the package arrived
	 * @param weight
	 * 		how much the package weighs 
	 * <dt>Postcondition:
	 * 		<dd><code>recipient</code> has been set to the car attribute recipient name
	 * 		<dd><code>arrivalDate</code> has been set to the car attribute arrivalDate
	 * 		<dd><code>weight</code> has been set to the car attribute weight if weight>0
	 * 
	 * @throws IllegalArgumentException 
	 * 		indicates input weight is negative
	 */
	public Package(String recipient,int arrivalDate,double weight)throws IllegalArgumentException
	{
		this.recipient = recipient;
		this.arrivalDate = arrivalDate;
		if(weight>0)
			this.weight = weight;	
		else 
			throw new IllegalArgumentException("Package weight can not be negative");
	}

	/**
	 * returns the recipient name
	 * 
	 * @return
	 * 		returns Package attribute <code>recipient</code>
	 */
	public String getRecipient() 
	{
		return recipient;
	}

	/**
	 * sets the recipient attribute of the package
	 * 
	 * @param recipient
	 * 		name of the recipient
	 * <dt>Postcondition:
	 * 		<dd><code>recipient</code> is set to the <code>Package</code> attribute recipient
	 */
	public void setRecipient(String recipient)
	{
		this.recipient = recipient;
	}

	/**
	 * returns the date of arrival of <code>Package</code>
	 * @return
	 * 		returns Package attribute arrivalDate
	 */
	public int getArrivalDate() 
	{
		return arrivalDate;
	}

	/**
	 * sets the arrivalDate of a Package object
	 * @param arrivalDate
	 * 		day the package arrived 
	 * <dt>Postcondition:
	 * 		<dd><code>arrivalDate</code> is set to the Package attribute <code>arrivalDate</code>
	 */
	public void setArrivalDate(int arrivalDate) 
	{
		this.arrivalDate = arrivalDate;
	}

	/**
	 * returns the package weight
	 * @return
	 * 		returns the Package attribute <code>weight</code>
	 */
	public double getWeight()
	{
		return weight;
	}

	/**
	 * sets the weight of the object Package
	 * 
	 * @param weight
	 * 		how much the package weighs
	 * <dt>Postcondition:
	 * 		<dd><code>weight</code> is set as the Package attribute weight
	 */
	public void setWeight(double weight) 
	{
		this.weight = weight;
	}

	/**
	 * Generates a string representation of the Package object
	 * 
	 * @return
	 * 		returns a string representation of a Package's attributes 
	 * 		recipient name and arrival date
	 */
	public String toString() {
		return "["+ recipient + " "+ arrivalDate+"]";
	}
	


}
