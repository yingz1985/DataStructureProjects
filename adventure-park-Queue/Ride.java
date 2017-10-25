/**
 * The <code>Ride</code> class contains the duration of the ride in minutes, an instance of the virtualLine Object, 
 * an instance of the HoldingQueue Object, and another queue of all the people on the ride 
 * 
 * @author Ying Zhang
 * 
 *
 */
public class Ride 
{
	private int duration;//how many time steps the ride takes up
	private int timeLeft;//how many minutes are left until the end of the ride cycle
	private String name;//the name of the ride
	VirtualLine virtualLine;//the queue of people waiting for the ride
	HoldingQueue holdingQueue;//the queue of people who are next for the ride
	HoldingQueue peopleOnRide;//a list of people on ride, changes person's status to "onRide" and "Available"
							  //has a capacity specified at the start of the simulation 
	
	private int passengers;//number of passengers that have been on the ride 
	
	/**
	 * Constructs an instance of <code>Ride</code>
	 * @param name the name of the ride 
	 * 
	 * <dt>Postcondition:
	 * 		<dd> <code>name</code> is set as the ride name, 
	 * 		<dd> the virtualLine, holdingQueue, and the line that contains passengers are initialized 
	 * 		<dd> the number of passengers is set to 0
	 */
	public Ride(String name)
	{
		this.name = name;
		virtualLine = new VirtualLine();
		holdingQueue = new HoldingQueue();
		peopleOnRide = new HoldingQueue();
		passengers = 0;
	}
	
	/**
	 * increments passenger everytime a passenger is dequeued from the ride 
	 * <dt>Postcondition:
	 * 		<dd> Ride attribute passengers is incremented by 1 
	 */
	public void addPerson()
	{
		passengers++;
	}
	/**
	 * returns the number of people that have been on the ride 
	 * @return returns the Ride attribute passengers
	 */
	public int numOfPeopleRidden()
	{
		return passengers;
	}
	/**
	 * returns the time steps the ride takes up 
	 * @return returns the Ride attribute duration
	 */
	public int getDuration() 
	{
		return duration;
	}
	/**
	 * sets the Ride attribute duration 
	 * @param duration the time steps the ride takes up 
	 * <dt>Postcondition:
	 * 		<dd>duration is set as Ride attribute duration
	 */
	public void setDuration(int duration) 
	{
		this.duration = duration;
	}
	
	/**
	 * returns how many time steps are left til completion 
	 * @return returns Ride attribute timeLeft
	 */
	public int getTimeLeft() 
	{
		return timeLeft;
	}
	
	/**
	 * sets the Ride attribute timeLeft
	 * @param timeLeft how many times until completion 
	 * <dt>Postcondition:
	 * 		<dd>Ride attribute timeLeft is set to passed in parametr 
	 */
	public void setTimeLeft(int timeLeft)
	{
		this.timeLeft = timeLeft;
	}
	/**
	 * returns the ride's name
	 * 
	 * @return returns the Ride attribute name
	 */
	public String getName() 
	{
		return name;
	}
	/**
	 * sets the Ride name
	 * @param name the Ride's name 
	 * <dt>Postcondition:
	 * 		<dd> the Ride's name has been set to the parameter name
	 */
	public void setName(String name) 
	{
		this.name = name;
	}
	/**
	 * returns the virtualLine object from Ride 
	 * @return returns the virtualLine of the line 
	 * <dt>Note:
	 * 		<dd> virtualLine does not need a setter as it is already initialized in the constructor
	 * 		<dd> virtualLine has no capacity
	 */
	public VirtualLine getVirtualLine() 
	{
		return virtualLine;
	}
	/**
	 * returns the holdingQueue object from Ride 
	 * @return returns the holdingQueue of the line 
	 */
	public HoldingQueue getHoldingQueue()
	{
		return holdingQueue;
	}
	/**
	 * sets capacity of the holdingQueue
	 * @param capacity the capacity of the holdingQueue 
	 * <dt>Postcondition:
	 * 		<dd>the maximum size of the holdingQueue is set as the capacity 
	 * 
	 */
	public void setHoldingQueue(int capacity) 
	{
		holdingQueue.setMaxSize(capacity);
	}
	/**
	 * returns a queue of everyone currently on the ride 
	 * @return returns <code>peopleOnRide</code>
	 */
	public HoldingQueue getPeopleOnRide() 
	{
		return peopleOnRide;
	}
	
	/**
	 * sets the capacity of the max number of people that can be on ride 
	 * @param capacity the capacity of peopleOnRide 
	 * <dt>Postcondition:
	 * 		<dd> peopleOnRide has been assigned a capacity 
	 * 
	 */
	public void setPeopleOnRide(int capacity) 
	{
		peopleOnRide.setMaxSize(capacity);;
	}
	
	
	/**
	 * 	
	 * Generates a string representation of the <code>Ride</code> object
	 * 
	 * @return 
	 *    returns a string representation of the Ride name and all its queues 
	 *    
	 */
	public String toString()
	{
		String name = this.getName();
		if(name.equals("BSOD"))
			name = "Blue Scream of Death";
		else if(name.equals("KK"))
			name = "Kingda Knuth";
		else if(name.equals("TOT"))
			name = "i386 Tower of Terror";
		else
			name = "GeForce";
		String result = name +" - Time remaining: "+this.getTimeLeft()+" mins\n";
		result+="OnRide: "+peopleOnRide.toString();
		result+="\n"+"Holding Queue: "+holdingQueue.toString();
		result+="\n"+"Virtual Queue: "+virtualLine.toString();
		return result;
		
	}
	
}
