/**
* The <code>LunchLineSimulator</code> creates two instances of the 
* <code>StudentLine</code> object and provides an interface for a user 
* to manipulate the list
* 
* @author Ying Zhang
* 
* 
*/

import java.util.Scanner;

public class LunchLineSimulator 
{
	private static StudentLine realityA; //default StudentLine
	private static StudentLine realityB; //alternate StudentLine

	/**
	* The main method runs a menu driven application which first creates two empty StudentLines 
	* and then prompts the user for a menu command selecting the operation. 
	* The required information is then requested from the user based on the selected operation.
	* You can find the list of menu options in the UI required functions section.
	*/
	
	public static void main(String[]args)throws Exception
	{
		realityA = new StudentLine(); //initialize realityA with 0 students
		realityB = new StudentLine(); //initialize realityB with 0 students
		
		boolean inRealityA = true; //user is in default inRealityA
		Scanner input = new Scanner(System.in);
		String option = " ";
		StudentLine switchReality = realityA; //default reality
											  //when user requests to change reality
											  //assign realityB to switchReality
		
		System.out.println("Welcome to the Middle School where you are the master of the lunch line. \n"
		  + "You may subject students to whatever form of culinary torture you desire.\n\n"
		  + "You are currently in Reality A.\n");
		
		System.out.println("Menu:\n"
		  + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
		  + "A - Add a student to the end of the line\n"
		  + "C - Have a new student cut a friend\n"
		  + "B - Have the bully remove a student\n"
		  + "U - Update a student's money amount\n"
		  + "P - Print lunch line in current reality \n"
		  + "S - Serve a student \n"
		  + "O - Switch to the other reality \n"
		  + "T - Trade places with friend \n"
		  + "E - Check if the two realities are equal\n"
		  + "D - Duplicate this reality into the other reality\n"
		  + "Q - Quit middle school and move on to real life\n"
		  + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		do
		{
			System.out.print("Please enter an option: "); //prompt user to enter an option from menu
			option = input.next().toUpperCase(); //allows upper and lower case entries
			Scanner newInput = new Scanner(System.in); //allows names to be entered with spaces
			
			/*
			* default StudentLine: realityA
			* 
			* If user inputs option A, scanner will prompt user to enter name and money amount
			* as parameters to create a new student object added to the current StudentLine
			* 
			* IllegalArgumentException is caught if money amount is negative
			* exception prints out message and proceeds to menu again
			*/
			if(option.equals("A"))
			{
				try
				{
					System.out.print("Please enter Student name: ");
					String name = newInput.nextLine();
					System.out.print("Please enter money amount: ");
					double money = newInput.nextDouble();
					
					
					Student newStudent = switchReality.addStudent(new Student(name,money));;
					int currentStudents = switchReality.numStudents();
					System.out.println("\n"+name+" has been added to position "+currentStudents
					  +". "+newStudent.getName() +" has $ "+newStudent.getMoney()+"\n");
					
				}
				
				catch(Exception x)
				{
					System.out.println(x.getMessage()+"\n");
					continue;
				}
				
			}
			
			 /**
			 * If user inputs "C", scanner will prompt use to enter a new student attribute 
			 * to create a student object, and an index to place the student object in
			 * 
			 * NOTE: INDEX STARTS FROM 1
			 * 
			 * If the line is empty, user will be prompted to choose option A from the menu
			 * 
			 * InvalidArgumentException is caught if index is invalid
			 * IllegalArgumentException is caught if entered money amount is negative
			 * console prints out error message and proceeds to menu again 
			 */
			 if(option.equals("C"))
			 {
				 if(switchReality.numStudents()==0)
				 {
						System.out.println("There is no one in front. please try add option (A)");
						continue;	
				 }
				 
				try
				{
					System.out.print("Please enter Student name: ");
					String name1 = newInput.nextLine();
					System.out.print("Please enter money amount: ");
					double money1 = newInput.nextDouble();
					System.out.print("Please enter position to cut: ");
					int pos = newInput.nextInt();
					
					switchReality.addStudent(pos-1, new Student(name1,money1));
					
					String nameTemp = switchReality.getStudent(pos).getName();
					System.out.println(name1+" has cut "+ nameTemp
					  +" and is now in position "+ pos +". "+name1 
					  +" has: $"+money1+"\n");
					
				}
				catch(Exception x)
				{
					System.out.println(x.getMessage()+"\n");
					continue;
				}
			}
			 
			 /**
			 * if user inputs "B", scanner will prompt the user to enter an index of student to "bully"
			 * targeted student is removed from the StudentLine array 
			 * 
			 * NOTE: INDEX STARTS AT 1
			 * 
			 * InvalidArgumentException is thrown if entered index is invalid 
			 * Exception is also caught if the line is empty, calling EmptyLineException
			 * console prints error message and proceeds to menu
			 */
			 if(option.equals("B"))
			 {
				 try
				 {
					 System.out.print("Please enter student index to bully: ");
					 int target = newInput.nextInt();
					 
					 Student removed = switchReality.getStudent(target-1);
					 switchReality.removeStudent(target-1);
					 
					 System.out.println("The bully took "+ removed.getName()+"'s lunch money. " 
					   +removed.getName()+" left the line feeling hungry and angry.\n");
				 }
				 catch(Exception x)
				 {
					 System.out.println("Entered index does not match any students."
					   + " attempt to bully failed.\n");
					 continue;	 
				 }
			 }
			 
			 /**
			 * if user inputs "T", the Scanner will prompt the user to enter two indices 
			 * and swap the Student objects at the two indices
			 * 
			 * NOTE: INDEX STARTS AT 1 
			 *
			 * InvalidArgumentException is thrown if either index is invalid
			 * console prints error message and proceeds to menu again
			 */
			 if(option.equals("T"))
			 {
				 try
				 {
					 System.out.print("Enter student 1 index to be swapped: ");
					 int index1 = newInput.nextInt();
					 System.out.print("Enter student 2 index to be swapped: ");
					 int index2 = newInput.nextInt();
					 switchReality.swapStudents(index1-1, index2-1);
					 String student1  = switchReality.getStudent(index2-1).getName();
					 String student2  = switchReality.getStudent(index1-1).getName();
					 System.out.println(student1 +" has switched places with " +student2+"\n");
				 }
				 
				 catch(Exception x)
				 {
					System.out.println(x.getMessage()+"\n");
					continue;
				 }
				 
			 }
				 
				 
			/**
			* if user inputs "U", Scanner will prompt user to enter an index and a new money amount
			* The entered money amount will be the new money amount of Student at input index
			* 
			* NOTE: INDEX STARTS AT 1
			* 
			* IllegalArgumentException is thrown if entered money amount is negative
			* InvalidArgumentException is thrown if index entered is invalid
			* console prints error message and proceeds to menu
			*/
			if(option.equals("U"))
			{
				try
				{
					System.out.print("Please enter student index to update money: ");
					int index = newInput.nextInt();
					System.out.print("Please enter new money amount: $");
					double newMoney = newInput.nextDouble();
					switchReality.getStudent(index-1).setMoney(newMoney);
					
					Student updated = switchReality.getStudent(index-1);
					System.out.println("Due to a shady transaction with the floor. "
					  +updated.getName()+ " now has $" +updated.getMoney()+"\n");
					
				}
				
				catch(Exception x)
				{
					System.out.println(x.getMessage()+"\n");
					continue;
				}
			}
			
			/**
			* if user inputs "S", the first student in the array is removed
			* students after the first will move up one index
			* 
			* EmptyLineException is called if the the array is empty
			* console prints error message and proceeds to menu
			*/
			if(option.equals("S"))
			{
				try
				{
					String served = switchReality.getStudent(0).getName();
					switchReality.removeStudent(0);
					System.out.println(served+" has been served today's special. "
					  +served+" leaves the line happily"+"\n");
			    }
				catch(Exception x)
				{
					System.out.println("There are no students to serve lunch to. "
					  + "Mystery Meat Martha is sad"+"\n");
					continue;
				}
			}
			
			/**
			* if user inputs "P", current StudentLine calls the toString method 
			* prints a list of students and their money amount
			* 
			* if the line is empty, prints out a message stating so
			*/
			if(option.equals("P"))
			{
				
				if(switchReality.numStudents()==0)
				{
					System.out.println("There are no students on this line");
					continue;
				}
				
				if(switchReality.equals(realityA))
					System.out.println("Lunch line in Reality A: ");
				else 
					System.out.println("Lunch line in Reality B: ");
				
				System.out.print(switchReality.toString());
				System.out.println("");
			}
			
			/**
			* if user inputs "O", current student line is switched. 
			* switchReality checks for current student line, and switch to the other
			* prints out a message confirming successful switch of reality
			*/
			if(option.equals("O"))
			{
				if(inRealityA)
				{
					switchReality = realityB;
					inRealityA = false;
					System.out.println("You are now in reality B \n");
				}
				else
				{
					switchReality = realityA;
					inRealityA = true;
					System.out.println("You are now in reality A \n");
				}
			}
			
			/**
			* if user inputs "E", the equals method is called to compare StudentLine realityA and realityB
			* returns true if they have the same order of students with the same attributes
			* false otherwise
			*/
			if(option.equals("E"))
			{
				boolean equal = realityA.equals(realityB);
				
				if(equal)
					System.out.println("The two realities are equal.\n");
				else
					System.out.println("The two realities are not equal.\n");
			 }
			
			/**
			* if user inputs "D", current lunch line is duplicated into the other
			* 
			* if currently in realityA, then realityA calls its clone method 
			* and assigns its clone StudentLine to realityB
			* 
			* if currently in realityB, then realityB calls its clone method 
			* and assigns its clone StudentLine to realityA
			* 
			* Exception is caught if line is empty, or object type doesn't match
			*/
			if(option.equals("D"))
			{
				try
				{
					if(switchReality.equals(realityA))
					{
						realityB = (StudentLine) realityA.clone();
						System.out.println("reality A has been duplicated into reality B \n");	
					}
					else
					{
						realityA = (StudentLine) realityB.clone();
						System.out.println("reality B has been duplicated into reality A \n");
					}	
				}
				
				catch(Exception x)
				{
					System.out.println("clone failed.\n");
					continue;	
				}
			}
			
			
		
		}
		
		//if user enters "P", exits the while loop and close the menu options
		
		while(!option.equals("Q"));
		
		System.out.println("You are now leaving the Middle School Lunch Line Simulator.");
				
		
	}
	
}	
