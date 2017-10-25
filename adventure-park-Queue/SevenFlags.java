import java.util.Scanner;
/**
 * The <code>Ride</code> driver class runs the simulation of four rides 
 * and prints a minute-by-minute breakdown of the simulation
 * @author Ying Zhang
 * 
 *
 */
public class SevenFlags
{	
	private static Ride BSOD = new Ride("BSOD");//Blue Scream of Death ride 
	private static Ride TOT = new Ride("TOT");//I386 Tower of terror ride 
	private static Ride GF = new Ride("GF");//GeForce ride 
	private static Ride KK = new Ride("KK");//Kingda Knuth ride 
	private static Ride[] SevenFlags = {BSOD,TOT,GF,KK};//an array of all the rides 
	public static void main(String[]args)
	{
		
		Scanner user = new Scanner(System.in);
		System.out.println("Welcome to Seven Flags!\n");
		System.out.print("Please enter the number of regular customers: ");
		int numRegular = user.nextInt();
		System.out.print("Please enter the number of silver customers: ");
		int numSilver = user.nextInt();
		System.out.print("Please enter the number of gold customers: ");
		int numGold = user.nextInt();

		Scanner input = new Scanner(System.in);
		System.out.print("Please enter simulation length: ");
		int time = input.nextInt();
		
		Person[] regular = createPeople(numRegular,1); //array of regular customers 
		Person[] silver = createPeople(numSilver,2);//array of silver customers 
		Person[] gold = createPeople(numGold,3);//array of goldcustomers 
		
		Person[][] allPersons= {gold,silver,regular};//a 2-dimensional array of all the customers 
		
		int duration = 0;
		int lineCapacity = 0;
		int holdCapacity = 0;
		
		System.out.print("\nPlease enter the duration of Blue Scream of Death (minutes):");
		duration = user.nextInt();
		BSOD.setDuration(duration);
		BSOD.setTimeLeft(duration);
		System.out.print("Please enter the capacity of Blue Scream of Death: ");
		lineCapacity = user.nextInt();
		BSOD.setPeopleOnRide(lineCapacity);
		System.out.print("Please enter the holding queue size for Blue Scream of Death: ");
		holdCapacity = user.nextInt();
		BSOD.setHoldingQueue(holdCapacity);
		
		
		System.out.print("\nPlease enter the duration of Kingda Knuth (minutes):");
		duration = user.nextInt();
		KK.setDuration(duration);
		KK.setTimeLeft(duration);
		System.out.print("Please enter the capacity of Kingda Knuth: ");
		lineCapacity = user.nextInt();
		KK.setPeopleOnRide(lineCapacity);
		System.out.print("Please enter the holding queue size for Kingda Knuth: ");
		holdCapacity = user.nextInt();
		KK.setHoldingQueue(holdCapacity);
		
		System.out.print("\nPlease enter the duration of i386 Tower of Terror (minutes):");
		duration = user.nextInt();
		TOT.setDuration(duration);
		TOT.setTimeLeft(duration);
		System.out.print("Please enter the capacity of i386 Tower of Terror: ");
		lineCapacity = user.nextInt();
		TOT.setPeopleOnRide(lineCapacity);
		System.out.print("Please enter the holding queue size for i386 Tower of Terror: ");
		holdCapacity = user.nextInt();
		TOT.setHoldingQueue(holdCapacity);
		
		System.out.print("\nPlease enter the duration of GeForce (minutes):");
		duration = user.nextInt();
		GF.setDuration(duration);
		GF.setTimeLeft(duration);
		System.out.print("Please enter the capacity of GeForce: ");
		lineCapacity = user.nextInt();
		GF.setPeopleOnRide(lineCapacity);
		System.out.print("Please enter the holding queue size for GeForce: ");
		holdCapacity = user.nextInt();
		GF.setHoldingQueue(holdCapacity);
		
		System.out.print("\nWould you like to input the probability of choosing each ride? (y/n):");
		String answer = user.next().toUpperCase();
		double[] probability = {0.25,0.25,0.25,0.25};
		double[] defaultP = {0.25,0.25,0.25,0.25}; 
		if(answer.equals("Y"))
		{
			Scanner select = new Scanner(System.in);
			
			System.out.print("Please enter the probability of selecting Blue Scream of Death ride:(0-1)");
			probability[0] = select.nextDouble();
			System.out.print("Please enter the probability of selcting i386 Tower of Terror:(0-1)");
			probability[1] = select.nextDouble();
			System.out.print("Please enter the probability of selcting GeForce:(0-1)");
			probability[2] = select.nextDouble();
			System.out.print("Please enter the probability of selcting Kingda Knuth:(0-1)");
			probability[3] = select.nextDouble();

			try
			{
				addToLine(allPersons,probability);
			}
			catch(Exception e)
			{
				probability = defaultP;
				System.out.print("\nYou did not enter valid probabilities, default equal probability will be used instead\n");
				addToLine(allPersons,probability);
			}
			
			
			
		}
		else
		{
			System.out.println("\nRides are selected with equal probability\n");
			addToLine(allPersons,probability);
		}
		System.out.println("----------------\nAt Time 0:\n");
		print(allPersons);
		
		
		int currentTime = 0;
		
		/**
		 * starting from time 0 up until the desired time, the while loop decrements all rides' timeLeft value
		 * if the value is zero after decrementing, 
		 * remove all customers, assign them a new ride, 
		 * dequeue from the holdLine and enqueue them to the ride, change their status to onRide 
		 * Move available customers from the virtual line to the holdLine to be added to the ride 
		 * if a customer is not available, then add it to the back of the virtual line
		 * 
		 */
		while(currentTime<time)
		{

			for(int i = 0;i<SevenFlags.length;i++)
			{

				SevenFlags[i].setTimeLeft(SevenFlags[i].getTimeLeft()-1);
				if(SevenFlags[i].getTimeLeft()==0)
				{
					SevenFlags[i].setTimeLeft(SevenFlags[i].getDuration());

					while(!(SevenFlags[i].getPeopleOnRide().isEmpty()))
					{

						Person person = SevenFlags[i].getPeopleOnRide().dequeue();
						person.finishedARide();
						person.getLines().remove(SevenFlags[i]);
						person.setStatus(Status.AVAILABLE);
						addToLine(person,probability);
						SevenFlags[i].addPerson();
						
					}
					
					while((!SevenFlags[i].getPeopleOnRide().isFull())&&
							(!SevenFlags[i].getHoldingQueue().isEmpty()))
					{

						Person person = SevenFlags[i].getHoldingQueue().dequeue();
						if(person.getStatus().toString().equals("HOLDING"))
						{
							SevenFlags[i].getPeopleOnRide().enqueue(person);
							person.setStatus(Status.ONRIDE);
							
						}
					}
						int count = 0;
						int numPeopleOnVirtualLine = SevenFlags[i].getVirtualLine().size();
					while((!SevenFlags[i].getHoldingQueue().isFull())
							&&(count<numPeopleOnVirtualLine))
					{
						count++;
						Person virtualPerson = SevenFlags[i].getVirtualLine().dequeue();
						if(virtualPerson.getStatus().toString().equals("HOLDING")
						  ||virtualPerson.getStatus().toString().equals("ONRIDE"))
						{
							//If person dequeued if currently waiting in another line, 
							//or the person is currently on a ride, place it to the back
							//of the virtual list
							
							SevenFlags[i].getVirtualLine().enqueue(virtualPerson);
						}
						else
						{
							SevenFlags[i].getHoldingQueue().enqueue(virtualPerson);
							virtualPerson.setStatus(Status.HOLDING);
						}
					}

					
				}
			}
			currentTime++;
			System.out.println("------------\nAt time "+currentTime);
			print(allPersons);
			
			System.out.println("");
			
			
			
			
		}

		System.out.println("\n\nOn average, Gold customers have taken " +findAverage(gold)+" rides");
		System.out.println("\n\nOn average, Silver customers have taken " +findAverage(silver)+" rides");
		System.out.println("\n\nOn average, Regular customers have taken " +findAverage(regular)+" rides\n");
		
		for(int i = 0; i<SevenFlags.length;i++)
			System.out.println(SevenFlags[i].getName()+" has completed rides for "
			  +SevenFlags[i].numOfPeopleRidden()+" people");
		
	}
	/**
	 * returns the average ride a type of customer has taken 
	 * @param person an array of customers, could be regular, silver or gold 
	 * 
	 * @return returns the average ride all customers of the same type have ridden 
	 */
	public static double findAverage(Person[] person)
	{
		double average = 0;
		for(int i = 0;i<person.length;i++)
		{
			average += person[i].getNumRides();
		}
		average = (int)(average/person.length*100);
		return average/100;
	}
	/**
	 * creates an array of people according to their maximum line capacity 
	 * @param numOfPeople number of customers of a specific type( regular,silver, or gold)
	 * @param maxLine the maximum amount of lines the customers can be on
	 * @return returns an array of numOfPeople customers and their maximum line set to maxLine
	 */
	public static Person[] createPeople(int numOfPeople,int maxLine)
	{
		Person[] array = new Person[numOfPeople];
		for(int i = 1;i<=numOfPeople;i++)
		{
			Person newPerson = new Person(i);
			newPerson.setStatus(Status.AVAILABLE);
			newPerson.setMaxLines(maxLine);
			array[i-1] = newPerson;
		}
		return array;
	}
	/**
	 * assigns all customers to random rides 
	 * @param list	a 2-d list of all customers 
	 * @param probability an array of doubles that contains the probabilities of each ride 
	 * <dt>Postcondition:
	 * 		<dd> all Person objects in list has been assigned to random rides according to the probability 
	 * 
	 */
	public static void addToLine(Person[][] list,double[]probability) 
	{
		for(int i = 0; i<3;i++) //iterate 3 times 
		{
			 for(int k = 0;k<list[i].length;k++)
			 {
				addToLine(list[i][k],probability);
			 }
		}
		for(int j = 0; j<2;j++)
		{
			for(int f = 0; f<list[j].length;f++)
			{
				addToLine(list[j][f],probability);
			}
		}
		for(int n = 0; n <1;n++)
		{
			for(int m = 0;m<list[n].length;m++)
			{
				addToLine(list[n][m],probability);
			}
		}
	}
	
	/**
	 * assigns a person Object to a random ride with default 25% probability for every ride 
	 * 
	 * @param person Person object to be added to a line 
	 * @param prob an array of probability in which the person selects a ride 
	 * <dt>Postcondition: 
	 * 		<dd> person has been added to a random ride 
	 * 
	 */
	public static void addToLine(Person person,double[]prob)
	{

		 Ride choose = RandomGenerator.selectRide(SevenFlags,prob);
		 person.addLine(choose);
		 if(person.getStatus().toString().equals("AVAILABLE"))
		 {
			 if(choose.holdingQueue.isFull())
			 {
				 choose.virtualLine.enqueue(person);

				  
			 }
			 else if((choose.peopleOnRide.isFull()))
			 {
				 
				 choose.holdingQueue.enqueue(person);
				 person.setStatus(Status.HOLDING);

			 }
			 else
			 {
				 choose.peopleOnRide.enqueue(person);
				 person.setStatus(Status.ONRIDE);

				 

			 }
		 }
		 else //if(person.getStatus().toString().equals("HOLDING"))
		 {
			 //if(choose.holdingQueue.isFull())
			// {
				 choose.virtualLine.enqueue(person);
			// }
			// else
				// choose.holdingQueue.enqueue(person);
		 }


		 
		 
	 }
	
	/**
	 * prints people on each ride and the status of everyone in the list 
	 * @param list a 2-d list of all people created initially 
	 */
	public static void print(Person[][]list)
	{
		for(Ride d:SevenFlags)
		{
			System.out.println(d);
			System.out.println("--------------------------");
		}

		System.out.println(printRegular(list[2]));
		System.out.println(printSilver(list[1]));
		System.out.println(printGold(list[0]));
		
		
	}
	/**
	 * returns a string of regular customers, the line they're on, and their status 
	 * @param regular an array of regular customers 
	 * @return returns a list string representation of regular customers 
	 * 
	 */
	public static String printRegular(Person[] regular)
	{
		String result = "Regular Customers:\nNum Line   Status\n--------------------------\n";
		
		for(int i = 0; i<regular.length;i++)
		{
			result+= String.format("%-4s%-7s%-10s", i+1,regular[i].getLines().get(0).getName(),
			  regular[i].getStatus())+"\n";
		}
		return result;
	}
	
	/**
	 * returns a string of silver customers, the 2 lines they're on, and their status 
	 * @param silver an array of silver customers 
	 * @return returns a list string representation of silver customers 
	 * 
	 */
	public static String printSilver(Person[] silver)
	{
		String result = "Silver Customers:\nNum Line 1 Line 2 Status\n--------------------------\n"; 
		
		for(int i = 0; i<silver.length;i++)
		{
			result+= String.format("%-4s%-7s%-7s%-10s", i+1,silver[i].getLines().get(0).getName(),
		      silver[i].getLines().get(1).getName(),silver[i].getStatus())+"\n";
		}
		return result;
	}
	
	/**
	 * returns a string of gold customers, the 3 lines they're on, and their status 
	 * @param gold an array of gold customers 
	 * @return returns a list string representation of silver customers 
	 * 
	 */
	public static String printGold(Person[] gold)
	{
		String result = "Gold Customers:\nNum Line 1 Line 2 Line 3 Status\n--------------------------\n"; 
		
		for(int i = 0; i<gold.length;i++)
		{
			result+= String.format("%-4s%-7s%-7s%-7s%-10s", i+1,gold[i].getLines().get(0).getName(),
		      gold[i].getLines().get(1).getName(),gold[i].getLines().get(2).getName(),
		      gold[i].getStatus())+"\n";
		}
		return result;
	}
	
	
		
	
	
}

