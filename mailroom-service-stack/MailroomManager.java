package iringMailroom;
/**
 * The main method runs a menu driven application which allows the user to create six instances 
 * of the PackageStack class and then prompts the user for a menu command selecting the operation.
 *  
 * @author Ying Zhang
 * 
 */
import java.util.Scanner;
public class MailroomManager
{
	private static PackageStack aToG;//stack storing packages with recipient initial a-g
	private static PackageStack hToJ;//stack storing packages with recipient initial h-j
	private static PackageStack kToM;//stack storing packages with recipient initial k-m
	private static PackageStack nToR;//stack storing packages with recipient initial n-r
	private static PackageStack sToZ;//stack storing packages with recipient initial s-z
	private static FloorStack floor;//floor stack
	
	public static void main(String[]args) throws FullStackException, EmptyStackException 
	{
		PackageStack[] stack = new PackageStack[6]; //make an array of stacks
		aToG = new PackageStack(); hToJ = new PackageStack();
		kToM = new PackageStack(); nToR = new PackageStack();
		sToZ = new PackageStack(); floor = new FloorStack();
		stack[0]=floor;stack[1]=aToG;stack[2]=hToJ;stack[3]=kToM;stack[4]=nToR;stack[5]=sToZ;
		int currentDay = 0;
		
		System.out.println("Welcome to the Irving Mailroom Manager. It is day 0.\n");
		System.out.println("Menu:\n\tD) Deliver a package\n\tG) Get someone's package"
		  + " \n\tT) Make it tomorrow\n\tP) Print the stacks\n"
		  + "\tM) Move a package from one stack to another\n"
		  + "\tF) Find packages in the wrong stack and move to floor\n"
		  + "\tL) List all packages awaiting a user\n\tS) Sort\n\tQ) Quit");
		String input;
		do
		{
			System.out.print("\nPlease select an option: ");
			Scanner user = new Scanner(System.in);
			input = user.next().toUpperCase();
			/**
			 * if user inputs "D", prompt user to enter recipient name and weight. 
			 * use entered parameters to create a package and push it into a stack
			 * according to the recipient's name initial
			 * 
			 * if the corresponding stack isFull, push it into the nearest by stack
			 * 
			 */
			if(input.equals("D"))
			{
				Scanner inner = new Scanner(System.in);
				System.out.print("Please enter the recipient name:");
				String name = inner.nextLine();
				System.out.print("Please enter the weight (lbs):");
				Double weight = inner.nextDouble();
				Package s;
				try
				{
					 s = new Package(name,currentDay,weight);
				}
				catch(IllegalArgumentException x)
				{
					System.out.println(x.getMessage());
					continue;
				}
				System.out.println("\nA "+weight+" lb package is awaiting pickup by "+name);
				try
				{
					addToStack(s);
				}
				catch(FullStackException x)
				{
					addToAdjacent(s);
					continue;
				}
				
				
			}
			/**
			 * if user inputs "P", print out a string representation of packages in each stack 
			 */
			if(input.equals("P"))
			{
				print();
			}
			
			/**
			 * if user inputs "G", prompt user to enter recipient name 
			 * checks corresponding stack and retrieves the topmost package
			 * with the recipient name
			 */
			if(input.equals("G"))
			{
				int move = 0;
				Scanner newInput = new Scanner(System.in);
				System.out.print("Please enter the recipient name:"); 
				String name = newInput.nextLine();
				PackageStack check = checkStack(name);
				if(check.isEmpty())
				{
					System.out.println("\nThere are no packages for "+name);
					continue;
				}

			
				try
				{
					while(!check.peek().getRecipient().equals(name)&&!check.isEmpty())
					{
						floor.push(check.pop());
						move++;
					}
				} 
				catch (EmptyStackException e) 
				{
					continue;
				}
				
				try
				{
					if(check.peek().getRecipient().equals(name))
					{
						
						Package remove = null;
						if(move!=0)
						{
							System.out.println("Move "+ move+" packages from "+stackNum(check)
							  +"to floor");
							print();
							remove = check.pop();
						}
						else
						{
							System.out.println("\n"+name+"'s package was the first in the stack");
						}
						System.out.println("\nGive "+name+" "+remove.getWeight()+" lb package"
						  +" delivered on day "+remove.getArrivalDate()+"\n");
						if(move!=0)
							System.out.println("Return "+move+" packages to "+stackNum(check)
							  +" from floor");
						
					}
					while(move!=0)
					{
						check.push(floor.pop());
						move--;
					}
					print();
					
					
					
				}
				catch(Exception e )
				{
					System.out.println("Did not find a package for "+name);
					continue;
					
				}
				
				
				
			}
			/**
			 * if user inputs "T", increment currentDay
			 * check if all packages in each stack's arrival date is at least 5 days prior
			 * if a package arrived at least five days ago, remove it and return to sender 
			 * 
			 */
			if(input.equals("T"))
			{
				currentDay++;
				System.out.print("\nIt is now day "+currentDay+"\n");
				
				
				try
				{
				/*	int found =  sendBack(aToG,currentDay) +sendBack(hToJ,currentDay)
					  +sendBack(kToM,currentDay)+sendBack(nToR,currentDay)
					  +sendBack(sToZ,currentDay); */
					int found = 0;
					for(int i =1;i<stack.length;i++)
						found+=sendBack(stack[i],currentDay);
					if(found>0)
						System.out.println(found+" packages have been returned to sender");

					
				}
				catch(Exception x)
				{
					System.out.print("bad");
					continue;
				}
				
				
			}
			
			/**
			 * if user inputs "E", remove all packages in the floor stack 
			 * 
			 */
			if(input.equals("E"))
			{
				if(floor.isEmpty())
				{
					System.out.println("\nThere are no packages on the floor");
					continue;
				}
				while(!floor.isEmpty())
					floor.pop();
				System.out.println("\nThe floor has been emptied.");
			}
			/**
			 * if user inputs "M", prompt user to enter source stack and destination stack 
			 * move the topmost package from the source stack to the destination stack 
			 * 
			 * throws EmptyStackException if source stack is empty
			 * throws FullStackException if destination stack is full
			 */
			if(input.equals("M"))
			{
				Scanner n = new Scanner(System.in);
				System.out.print("Please enter the source stack (enter 0 for floor):");
				int source = n.nextInt();
				System.out.print("Please enter the destination stack:");
				int destination = n.nextInt();
				if(stack[source].isEmpty())
				{
					System.out.println("\nThere are no packages in source stack to move");
					continue;
				}
				if(stack[destination].isFull())
				{
					System.out.println("\ndestination stack is full");
					continue;
				}
				stack[destination].push(stack[source].pop());
			}
			
			/**
			 * if user inputs "F", for all regular PackageStacks, if the recipient's name doesn't
			 * correspond to the stack range of acceptable initials, move it to floor
			 * 
			 */
			if(input.equals("F"))
			{
				int found = 0;
				for(int i=1;i<stack.length;i++)
				{
					boolean send = sendWrongToFloor(stack[i]);
					if(send)
						found++;
				}
				if(found>0)
				{
					System.out.println("Misplaced packages moved to floor");
				}
			}
			
			/**
			 * if user inputs "L", prompt user to enter recipient name
			 * 
			 * for each stack, not including the floor stack, loop to check and match 
			 * to see if there're packages with the same recipient name
			 * 
			 */
			if(input.equals("L"))
			{
				Scanner s = new Scanner(System.in);
				System.out.print("Please enter the recipient name: ");
				String name = s.nextLine();
				System.out.println("\n"+name+" has "+countPackages(stack,name)+" packages in total");
				find(stack,name);				
				
			}
			/** 
			 * if user inputs "S", sort all stacks according to recipient initials 
			 */
			if(input.equals("S"))
			{
				for(int i = 0;i<stack.length;i++)
					sort(stack[i]);
				System.out.println("\nSorting complete...");
				
			}
		}
		while(!input.equals("Q"));
		System.out.println("Good bye, Use Amazon Locker next time");
		
	}
	/**
	 * Add to the package stack that accepts the package recipient's name initial
	 * 
	 * @param n
	 * 		package ready to add to the stack corresponding to its initials
	 * <dt>Precondition:
	 * 		<dd>package stack is not full
	 * <dt>Postcondition:
	 * 		<dd><code>n</code> is pushed onto the top of the stack 
	 * @throws FullStackException
	 * 		indicates the corresponding stack is full
	 */
	public static void addToStack(Package n) throws FullStackException
	{
		
		PackageStack temp = checkStack(n.getRecipient());
		temp.push(n);
		
	}
	
	/**
	 * if the stack corresponding to the package is full, add to adjacent stack
	 * <dt>Precondition:
	 * 		<dd>the corresponding stack is full.
	 * @param n
	 * 		package ready to be pushed into the top of the stack 
	 * <dt>Postcondition:
	 * 		package is added to the the nearest non-full stack available
	 * @throws FullStackException
	 * 		indicates the adjacent stack is also full
	 */
	public static void addToAdjacent(Package n)throws FullStackException

	{
		char first = n.getRecipient().toUpperCase().charAt(0);
		if(first>='A'&&first<='G')
		{
			System.out.print("As stack 1 was full,");
			if(!hToJ.isFull())
			{
				hToJ.push(n);
				System.out.println("it was placed in stack 2.");
				return;
			}
			if(!kToM.isFull())
			{
				kToM.push(n);
				System.out.println("it was placed in stack 3.");
				return;
			}
			if(!nToR.isFull())
			{
				nToR.push(n);
				System.out.println("it was placed in stack 5.");
				return;
			}
			if(!sToZ.isFull())
			{
				sToZ.push(n);
				System.out.println("it was placed in stack 6.");
				return;
			}
		}
		if(first>='H'&&first<='J')
		{
			System.out.print("As stack 2 was full, ");
			if(!aToG.isFull())
			{
				aToG.push(n);
				System.out.println("it was placed in stack 1.");
				return;
			}
			if(!kToM.isFull())
			{
				kToM.push(n);
				System.out.println("it was placed in stack 3.");
				return;
			}
			if(!nToR.isFull())
			{
				nToR.push(n);
				System.out.println("it was placed in stack 4.");
				return;
			}
			if(!sToZ.isFull())
			{
				sToZ.push(n);
				System.out.println("it was placed in stack 5.");
				return;
			}
		}
		if(first>='K'&&first<='M')
		{
			System.out.print("As stack 3 was full, ");
			if(!hToJ.isFull())
			{
				hToJ.push(n);
				System.out.println("it was placed in stack 2.");
				return;
			}
			if(!nToR.isFull())
			{
				nToR.push(n);
				System.out.println("it was placed in stack 4.");
				return;
			}
			if(!aToG.isFull())
			{
				aToG.push(n);
				System.out.println("it was placed in stack 1.");
				return;
			}
			if(!sToZ.isFull())
			{
				sToZ.push(n);
				System.out.println("it was placed in stack 5.");
				return;
			}
		}
		if(first>='N'&&first<='R')
		{
			System.out.print("As stack 4 was full, ");
			if(!kToM.isFull())
			{
				kToM.push(n);
				System.out.println("it was placed in stack 3.");
				return;
			}
			if(!sToZ.isFull())
			{
				sToZ.push(n);
				System.out.println("it was placed in stack 5.");
				return;
			}
			if(!hToJ.isFull())
			{
				hToJ.push(n);
				System.out.println("it was placed in stack 2.");
				return;
			}
			if(!aToG.isFull())
			{
				aToG.push(n);
				System.out.println("it was placed in stack 1.");
				return;
			}
			
		}
		if(first>='S'&&first<='Z')
		{
			System.out.print("As stack 5 was full, ");
			
			if(!nToR.isFull())
			{
				System.out.println("it was placed in stack 4.");
				nToR.push(n);
				return;
			}
			if(!kToM.isFull())
			{
				kToM.push(n);
				System.out.println("it was placed in stack 3.");
				return;
			}
			if(!hToJ.isFull())
			{
				hToJ.push(n);
				System.out.println("it was placed in stack 2.");
				return;
			}
			if(!aToG.isFull())
			{
				aToG.push(n);
				System.out.println("it was placed in stack 1.");
				return;
			}
		}
		else
		{
			floor.push(n);
			System.out.println("All stacks are filled, the package is on the floor");
		}
		
 		
		
	}
	
	/**
	 * Count the total number of packages that are in the stack for more than 5 days and remove them 
	 * @param mixed
	 * 		Package stack ready to be check for old packages 
	 * @param currentDay
	 * 		current day all packages are in 
	 * @return
	 * 		returns number of packages in the <code>mixed</code>
	 * 		that have been there for more than 5 days
	 * <dt>Precondition:
	 * 		<dd><code>mixed</code> is not empty
	 * @throws EmptyStackException
	 * 		indicates <code>mixed</code> stack is empty
	 * <dt>Postcondition:
	 * 		<dd>old packages are removed from the <code>mixed</code> stack
	 * @throws FullStackException
	 *  	indicates stack is full
	 */

	public static int sendBack(PackageStack mixed,int currentDay) throws EmptyStackException, FullStackException
	{
		int found = 0;
		int count = 0;
		while(!mixed.isEmpty())
		{
			Package pop = mixed.pop();
			if(currentDay-pop.getArrivalDate()>=5)
			{
				found++;
			}
			else		
			{
				floor.push(pop);
				count++;
			}
		}
		while(count>0)
		{
			mixed.push(floor.pop());
			count--;
		}
		return found;
		
	}
	
	/**
	 * Assigns the corresponding stack according to the recipient name
	 * @param n
	 * 		recipient name
	 * @return
	 * 		returns the Package stack that the Package with 
	 * 		recipient name <code>n</code> should be placed in
	 */
	public static PackageStack checkStack(String n)
	{
		PackageStack unknown;
		char first = n.toUpperCase().charAt(0);
		if(first>='A'&&first<='G')
			unknown = aToG;
		else if(first>='H'&&first<='J')
			unknown = hToJ;
		else if(first>='K'&&first<='M')
			unknown = kToM;
		else if(first>='N'&&first<='R')
			unknown = nToR;
		else if(first>='S'&&first<='Z')
			unknown = sToZ;	
		else 
			unknown = floor;
		return unknown;
	}
	
	/**
	 * returns a string representation of the stack number corresponding to the index in the array
	 * @param n
	 * 		the package stack 
	 * @return
	 * 		returns the stack number in string form
	 */
	public static String stackNum(PackageStack n)
	{
		if(n==aToG)
			return "Stack 1";
		if(n==hToJ)
			return "Stack 2";
		if(n==kToM)
			return "Stack 3";
		if(n==nToR)
			return "Stack 4";
		if(n==sToZ)
			return "Stack 5";
		else 
			return "Floor";
		
	}
	/**
	 * 
	 * prints the array of stacks alphabetically/sequentially 
	 * 
	 */

	public static void print()
	{
		System.out.println("Current Packages: ");
		System.out.println("--------------------------------"
				 +"\nStack 1 (A-G):|"+aToG.toString()+"\n"
				 +"Stack 2 (H-J):|"+hToJ.toString()+"\n"
				 +"Stack 3 (K-M):|"+kToM.toString()+"\n"
				 +"Stack 4 (N-R):|"+nToR.toString()+"\n"
				 +"Stack 5 (S-Z):|"+sToZ.toString()+"\n"
				 +"Floor:|"+floor.toString());
	}
	/**
	 * Returns true if a package in a stack does not fall into the range of accepted name initials
	 * 
	 * @param stack
	 * 		Package stack to be checked 
	 * @return
	 * 		returns true if found packages in the wrong stack and move them to Floor
	 * <dt>Postcondition:
	 * 		<dd>packages in the wrong stack is moved to floor
	 * @throws EmptyStackException
	 * 		indicates the stack is empty
	 * @throws FullStackException
	 * 		indicates the stack is full
	 */
	public static boolean sendWrongToFloor(PackageStack stack) throws EmptyStackException, FullStackException
	{
		FloorStack hand = new FloorStack();
		int count = 0;
		boolean found = false;
		while(!stack.isEmpty())
		{
			Package free = stack.pop();
			if(!(checkStack(free.getRecipient())==stack))
			{
				floor.push(free);
				found = true;
			}
			else
			{
				hand.push(free);
				count++;
			}
		}
		while(count>0)
		{
			stack.push(hand.pop());
			count--;
		}
		return found;
	}
	/**
	 * sorts all package stacks 
	 * @param stack
	 * 		non-sorted stack 
	 * @throws EmptyStackException
	 * 		indicates the stack is empty
	 * @throws FullStackException
	 * 		indicates the stack is full
	 * <dt>Postcondition:
	 * 		<dd>packages are sorted alphabetically according to recipient name
	 */
	public static void sort(PackageStack stack) throws EmptyStackException, FullStackException
	{
		if(!stack.isEmpty())
		{
			Package temp = stack.pop();
			sort(stack);
			insert(stack,temp);
		}
	}
	/**
	 * rearranges packages alphabetically recursively 
	 * @param stack
	 * 		non-sorted stack
	 * @param n
	 * 		the topmost package in the stack 
	 * @throws FullStackException
	 * 		indicates the stack is empty
	 * @throws EmptyStackException
	 * 		indicates the stack is full
	 */
	public static void insert(PackageStack stack,Package n) throws FullStackException, EmptyStackException
	{
		if(stack.isEmpty())
		{
			stack.push(n);
		}
		else if(n.getRecipient().compareTo(stack.peek().getRecipient())==1)
		{
			stack.push(n);
		}
		else
		{
			Package temp = stack.pop();
			insert(stack,n);
			stack.push(temp);
		}
	}
	
	/**
	 * returns the number of packages with the same recipient name
	 * @param stack
	 * 		array of stacks to search for the package with recipient name
	 * @param find
	 * 		recipient name 
	 * @return
	 * 		returns the number of packages with the same recipient name
	 * <dt>Postcondition:
	 * 		<dd>prints the package found with the corresponding name <code>find</code>
	 * @throws EmptyStackException
	 * 		indicates the stack is empty
	 * @throws FullStackException
	 * 		indicates the stack is full
	 */
	public static int find(PackageStack[] stack,String find) throws EmptyStackException, FullStackException
	{
		int count = 0;
		int found = 0;
		for(int i = 0;i<stack.length;i++)
		{

			while(!stack[i].isEmpty())
			{
				if(stack[i].peek().getRecipient().equals(find))
				{
					found++;
					System.out.println("Package "+found+" is in "+stackNum(stack[i])
					  +", it was delivered on day "+stack[i].peek().getArrivalDate()
					  +" and weighs "+stack[i].peek().getWeight()+" lbs");
				}
				floor.push(stack[i].pop());
				count++;
			}
			while(count>0)
			{
				stack[i].push(floor.pop());
				count--;
			}
			
		}
		return found;
	}
	/**
	 * returns the number of packages with the same recipient name
	 * @param stack
	 * 		array of stacks to search for the package with recipient name
	 * @param find
	 * 		recipient name 
	 * @return
	 * 		returns the number of packages with the same recipient name
	 * @throws EmptyStackException
	 * 		indicates the stack is empty
	 * @throws FullStackException
	 * 		indicates the stack is full
	 * 
	 * ** note: same method as find() except find prints a string of the package found
	 */
	public static int countPackages(PackageStack[] stack,String find) throws EmptyStackException, FullStackException
	{
		int count = 0;
		int found = 0;
		for(int i = 0;i<stack.length;i++)
		{

			while(!stack[i].isEmpty())
			{
				if(stack[i].peek().getRecipient().equals(find))
				{
					found++;
				}
				floor.push(stack[i].pop());
				count++;
			}
			while(count>0)
			{
				stack[i].push(floor.pop());
				count--;
			}
			
		}
		return found;
	}


}
