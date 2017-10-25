/**
 * The <code>Car</code> class defines a Car object with attributes car type and owner name
 * 
 * @author Ying Zhang 
 * 
 */
public class Car implements Comparable {
	private Make make; // car type
	private String owner; // name of car owner

	/**
	 * constructs an instance of <code>Car</code>.
	 * 
	 * @param make
	 *            car type
	 * @param owner
	 *            Car owner's name
	 *            <dt>Postcondition:
	 *            <dd><code>make</code> has been set to the car attribute car
	 *            type
	 *            <dd><code>owner</code> has been set to the car attribute name
	 *            of the owner
	 * 
	 */
	public Car(Make make, String owner) {
		this.make = make;
		this.owner = owner;
	}

	/**
	 * returns attribute car type
	 * 
	 * @return returns the make of the car
	 */
	public Make getMake() {
		return make;
	}

	/**
	 * sets car type for the Car object
	 * 
	 * @param make
	 *            the type of car it is
	 * 
	 *            <dt>Postcondition:
	 *            <dd>attribute <code>make</code> has been assigned to the Car
	 *            object
	 */
	public void setMake(Make make) {
		this.make = make;
	}

	/**
	 * returns the owner's name
	 * 
	 * @return a string representation of the car owner's name
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * sets passed string to the owner name of the Car object
	 * 
	 * @param owner
	 *            the name of the car owner
	 * 
	 *            <dt>Postcondition:
	 *            <dd>attribute <code>owner</code> has been assigned to the Car
	 *            object
	 *
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * Generates a string representation of the Car object
	 * 
	 * @return returns a string representation of Car's attributes
	 * 
	 */
	public String toString() {
		String format = String.format("%-15s%-15s", make, owner);
		return format;
	}

	/**
	 * compare this Car object to another Car object for sorting purposes
	 * 
	 * @param o
	 *            an object to be compared with
	 * 
	 *            <dt>Precondition:
	 *            <dd><code>o</code> must be of type Car
	 * 
	 * @return Compares the string representation of the car type return 1 if
	 *         <code>o</code> is less than this car type lexicographically.
	 *         return -1 if <code>o</code> is greater than this car type
	 *         lexicographically. return 0 if they are the same type of car
	 */
	public int compareTo(Object o) {
		if (o instanceof Car) {

			if (make.toString().compareTo(((Car) o).getMake().toString()) > 0)
				return 1;
			if (make.toString().compareTo(((Car) o).getMake().toString()) == 0)
				return 0;
			else
				return -1;
		}
		return -1;

	}

}
