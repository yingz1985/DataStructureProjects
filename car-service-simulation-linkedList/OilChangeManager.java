
/**
 * 
 * @author Ying Zhang

 *
 */
import java.util.Scanner;

public class OilChangeManager {
	private static CarList joeCarList; // Joe's car list
	private static CarList donnyCarList; // donny's car list
	private static CarList finishedList; // finished car list
	private static Car cutCar; // temporary car cut from a list ready to be
								// pasted

	/**
	 * The main method runs a menu driven application which first creates an
	 * empty CarList and then prompts the user for a menu command selecting the
	 * operation. The required information is then requested from the user based
	 * on the selected operation.
	 * 
	 */
	public static void main(String[] args)

	{
		joeCarList = new CarList(); // initializes joe's car list with all nodes
									// set to null
		donnyCarList = new CarList(); // initializes donny's car list with all
										// nodes set to null
		finishedList = new CarList(); // initializes finished list ready to add
										// cars from either list
		cutCar = null; // temporary field for Car type copied from a list

		System.out.println("Welcome to Tony's Discount Oil Change Computer Management System!\n"
				+ "\nMenu:\nL) Edit Job Lists for Joe and Donny \n" + "M) Merge Job Lists \nP) Print Job Lists\n"
				+ "F) Paste car to end of finished car list\n" + "S) Sort Job Lists \n" + "Q) Quit.\n");
		String option = "";
		do {
			Scanner input = new Scanner(System.in);
			System.out.print("Please select an option from menu: "); // prompt user to enter an option from menu
			option = input.next().toUpperCase(); // allows upper and lower case
													// entries

			/**
			 * if user inputs "L" to edit the list, prompt the user to choose
			 * between which list to manipulate
			 */
			if (option.equals("L")) {
				System.out.print("Please select a list - Joe (J) or Donny (D):");
				String listOption = input.next().toUpperCase();
				CarList switchCar;
				String innerOption = "";

				if (listOption.equals("J") || listOption.equals("D")) {
					if (listOption.equals("J"))
						switchCar = joeCarList;
					else
						switchCar = donnyCarList;

					System.out.println("\nOptions:\n" + "A) Add a car to the end of the list\n" + "F) Cursor Forward\n"
							+ "H) Cursor to Head\n" + "T) Cursor to Tail\n" + "B) Cursor Backward\n"
							+ "I) Insert car before cursor\n" + "X) Cut car at cursor\n" + "V) Paste before cursor\n"
							+ "R) Remove cursor\n");

					System.out.print("Please select an option to edit list: ");
					innerOption = input.next().toUpperCase();

					/**
					 * if user inputs "A", the scanner will prompt the user to
					 * enter car type and the owner name to create a new car
					 * object ready to be appended to the end of the selected
					 * car list
					 * 
					 * Exception is caught is entered car type does not match
					 * the serviced car list from Enum Make -> prints a message
					 * indicating an invalid input for car make and proceeds to
					 * the main menu
					 */
					if (innerOption.equals("A")) {
						Scanner in = new Scanner(System.in);
						System.out.print("Please enter vehicle make "
								+ "(Ford, GMC, Chevy, Jeep, Dodge, Chrysler, and Lincoln):");
						String carType = in.nextLine();
						System.out.print("Please enter owner's name:");
						String name = in.nextLine();
						try {
							if (listOption.equals("J")) {
								joeCarList.appendToTail(new Car(Make.valueOf(carType.toUpperCase()), name));
								System.out
										.println("\n" + name + "'s " + carType + " has been scheduled for an oil change"
												+ " with Joe and has been added to his list.\n ");
							} else {
								donnyCarList.appendToTail(new Car(Make.valueOf(carType.toUpperCase()), name));
								System.out
										.println("\n" + name + "'s " + carType + " has been scheduled for an oil change"
												+ " with Donny and has been added to his list. \n");
							}
						} catch (Exception x) {
							System.out.println("Sorry we don't service " + carType + "\n");
							continue;
						}

					}
					/**
					 * if user selects "F", then the cursor in the selected list
					 * is forwarded by one
					 * 
					 * EndOfLineException is caught if the cursor is at the end
					 * of the list exception prints out message and proceeds to
					 * menu again
					 */
					if (innerOption.equals("F")) {
						try {
							switchCar.cursorForward();
							if (switchCar == joeCarList)
								System.out.println("Cursor Moved Forward in Joe's List\n");
							else
								System.out.println("Cursor Moved Forward in Donny's List\n");

						} catch (Exception x) {
							System.out.println("\n" + x.getMessage() + "\n");
							continue;
						}
					}

					/**
					 * if user selects "H" then the cursor is reset to head in
					 * the selected list
					 * 
					 * NOTE: even if the list is empty, cursor could be set to
					 * null;
					 */
					if (innerOption.equals("H")) {
						switchCar.resetCursorToHead();
						if (switchCar == joeCarList)
							System.out.println("Cursor has been reset to head in Joe's List\n");
						else
							System.out.println("Cursor has been reset to head in Donny's List\n");

					}

					/**
					 * if user selects "T", then the cursor is set to tail since
					 * I don't have a method to set cursor to tail keep
					 * forwarding cursor until it reaches the end of the list
					 * 
					 * Prints out a message if the list is empty.
					 */
					if (innerOption.equals("T")) {
						try {
							if (switchCar.numCars() < 1) {
								System.out.println("\nThere aren't enough cars in the list.\n");
								continue;
							}
							switchCar.resetCursorToHead();
							int i = 1;
							while (i < switchCar.numCars()) {
								switchCar.cursorForward();
								i++;
							}
							if (switchCar == joeCarList)
								System.out.println("Cursor has been set to tail in Joe's List\n");
							else
								System.out.println("Cursor has been set to tail in Donny's List\n");

						} catch (Exception x) {
							System.out.println("\n" + x.getMessage() + "\n");
						}
					}
					/**
					 * if user selects "B", the cursor reference will move back
					 * one node.
					 * 
					 * EmptyLineException is caught is cursor.getPrev() is null
					 * or the list is empty then the option proceeds to main
					 * menu
					 */
					if (innerOption.equals("B")) {
						try {
							switchCar.cursorBackward();
							if (switchCar == joeCarList)
								System.out.println("Cursor Moved Backward in Joe's List\n");
							else
								System.out.println("Cursor Moved Backward in Donny's List\n");

						} catch (Exception x) {
							System.out.println("\n" + x.getMessage() + "\n");
							continue;
						}
					}
					/**
					 * if user inputs "I", prompt user to enter Car attributes
					 * from scanner and create a temporary Car object to be
					 * inserted before the cursor in the selected CarList
					 * 
					 * Exception is caught if the entered car make is invalid
					 */
					if (innerOption.equals("I")) {
						Scanner in = new Scanner(System.in);
						System.out.print("Please enter vehicle make "
								+ "(Ford, GMC, Chevy, Jeep, Dodge, Chrysler, and Lincoln):");
						String carType = in.nextLine();
						System.out.print("Please enter owner's name:");
						String name = in.nextLine();
						Car temp;
						try {
							temp = new Car(Make.valueOf(carType.toUpperCase()), name);
						} catch (Exception x) {
							System.out.println("Sorry we don't service " + carType + "\n");
							continue;
						}

						try {
							switchCar.insertBeforeCursor(temp);
							if (switchCar == joeCarList)
								System.out
										.println("\n" + name + "'s " + carType + " has been scheduled for an oil change"
												+ " with Joe and has been added to his list before the cursor.\n ");
							else
								System.out
										.println("\n" + name + "'s " + carType + " has been scheduled for an oil change"
												+ " with Donny and has been added to his list before the cursor.\n ");
						} catch (Exception x) {
							System.out.println("\n" + x.getMessage() + "\n");
							continue;
						}

					}
					/**
					 * if user inputs "X", set data field cutCar to the current
					 * cursor in the selected list Call CarList.removeCursor()
					 * to cut cursor from the selected list
					 * 
					 * Exception is caught is the list is empty or the cursor is
					 * empty message is print and proceeds to main menu
					 */
					if (innerOption.equals("X")) {
						try {
							cutCar = switchCar.removeCursor();
							if (switchCar == joeCarList)
								System.out.println("Cursor cut in Joe's List\n");
							else
								System.out.println("Cursor cut in Donny's List\n");

						} catch (Exception x) {
							System.out.println("\nNothing to copy" + "\n");
							continue;
						}

					}
					/**
					 * if user inputs "V", insert before cursor the Car
					 * temporarily stored at cutCar ( if cutCar is not null)
					 * 
					 * Exception is caught if cutCar is not initialized or
					 * copied any Car reference from any list prints an error
					 * message and proceeds to main menu
					 */
					if (innerOption.equals("V")) {
						try {
							switchCar.insertBeforeCursor(cutCar);
							if (switchCar == joeCarList)
								System.out.println("Cursor pasted in Joe's list\n");
							else
								System.out.println("Cursor pasted in Donny's List\n");
							cutCar = null;

						} catch (Exception x) {
							System.out.println("\nNothing to paste\n");
							continue;
						}

					}

					/**
					 * if user inputs "R", the cursor node is removed from the
					 * selected list, the new cursor is currently the previous
					 * node or the head if the cursor previously referenced the
					 * head of the list
					 * 
					 * if the list if empty, EmptyListException is caught error
					 * message is print out and proceeds to the main menu
					 */
					if (innerOption.equals("R")) {
						try {
							switchCar.removeCursor();
							if (switchCar == joeCarList)
								System.out.println("Cursor removed in Joe's list\n");
							else
								System.out.println("Cursor removed in Donny's List\n");
						} catch (Exception x) {
							System.out.println("\nYou have an empty list of cars\n");
							continue;
						}
					}

				}

			}
			/**
			 * if user inputs "P", then print a string representation of all
			 * three car lists if there are no cars in the list, print "[empty]"
			 */
			if (option.equals("P")) {
				System.out.println("\nJoe's List:\n  Make         Owner\n" + "----------------------");
				if (joeCarList.numCars() == 0)
					System.out.println("[empty]");
				else
					System.out.println(joeCarList.toString());

				System.out.println("\nDonny's List:\n  Make         Owner\n" + "----------------------");
				if (donnyCarList.numCars() == 0)
					System.out.println("[empty]");
				else
					System.out.println(donnyCarList.toString());

				System.out.println("\nFinished List:\n  Make         Owner\n" + "----------------------");
				if (finishedList.numCars() == 0)
					System.out.println("[empty]\n");
				else
					System.out.println(finishedList.toString());
			}
			/**
			 * if user inputs "S", prompt user to select a car list to be sorted
			 * 
			 * program passes all nodes sequentially into a temporary arrayList
			 * of Cars call the bubbleSort method to sort the selected list and
			 * add sorted elements back to the linked list
			 * 
			 */
			if (option.equals("S")) {
				Scanner select = new Scanner(System.in);
				System.out.print("Please select a list - Joe (J) or Donny (D):");
				String newOpt = input.next().toUpperCase();
				try {
					CarList switchCar;

					if (newOpt.equals("J") || newOpt.equals("D")) {
						if (newOpt.equals("J")) {
							if (joeCarList.numCars() == 0) {
								System.out.println("\nNo cars to be sorted in Joe's list\n");
								continue;
							}
							switchCar = joeCarList;

						} else {
							if (donnyCarList.numCars() == 0) {
								System.out.println("\nNo cars to be sorted in Donny's list\n");
								continue;
							}
							switchCar = donnyCarList;
						}
						switchCar.sort();
						System.out.println("\nsort complete\n");

					}
				} catch (Exception x) {
					System.out.println("Unable to sort list");
					continue;
				}
			}
			/**
			 * if user inputs "F", cutCar from the data field is inserted into
			 * the finished list
			 * 
			 * Exception is caught is cutCar is not initialized meaning it
			 * didn't cut any cars from either list proceeds to main menu after
			 * error message is print out
			 */
			if (option.equals("F")) {
				try {
					finishedList.appendToTail(cutCar);
					System.out.println("\nCar pasted in finished list.");
					cutCar = null;
				} catch (Exception x) {
					System.out.println("\nThere is nothing to paste\n");
					continue;
				}
			}
			/**
			 * if user enters "M", prompt the user to choose a destination list
			 * in which the other list emerges into.
			 * 
			 * While both car lists are not empty, the nodes in the other list
			 * are constantly inserted before the cursor at the destination list
			 * Note: the destination list sets the cursor to the second node and
			 * forward one node each time a node is pasted from the other list
			 * if the destination list is shorter in length, then the remaining
			 * nodes will be appended to tail
			 * 
			 * Set aside a temporary object Car to help reset the cursor to its
			 * original position after the two lists have been merged together
			 */
			if (option.equals("M")) {
				Scanner o = new Scanner(System.in);
				System.out.print("Please select a destination list - Joe (J) or Donny (D):");

				CarList tempList;
				CarList altList;
				String destination = o.nextLine().toUpperCase();

				if (destination.equals("J") || destination.equals("D")) {
					if (joeCarList.numCars() == 0 && donnyCarList.numCars() == 0) {
						System.out.println("\nBoth car lists are empty.\n");
						continue;
					}
					if (destination.equals("J")) {
						if (donnyCarList.numCars() == 0) {
							System.out.println("\nThere are no cars to be pasted into Joe's list\n");
							continue;
						}
						tempList = joeCarList;
						altList = donnyCarList;
					} else {
						if (joeCarList.numCars() == 0) {
							System.out.println("\nThere are no cars to be pasted into Joe's list\n");
							continue;
						}
						tempList = donnyCarList;
						altList = joeCarList;
					}

					int pos = tempList.numCars();
					int total = tempList.numCars() + altList.numCars();
					Car temp = null;
					if (tempList.numCars() > 0)
						temp = tempList.getCursorCar();
					altList.resetCursorToHead();
					tempList.resetCursorToHead();
					int count = 1;
					int increment = tempList.numCars();
					try {

						while (increment < total) {
							if (count >= pos) {
								tempList.appendToTail(altList.removeCursor());
								increment++;
								count++;

							} else {
								if (count < pos) {
									tempList.cursorForward();

								}
								tempList.insertBeforeCursor(altList.removeCursor());
								increment++;
								count++;
							}
						}
						if (tempList == joeCarList)
							System.out.println("\nDonny's list merged into Joe's\n");

						else
							System.out.println("\nJoe's list merged into Donny's\n");
						tempList.resetCursorToHead();
						if (temp == null)
							continue;
						while (tempList.getCursorCar() != temp) {
							tempList.cursorForward();
						}

					} catch (Exception x) {
						System.out.println("\n");
						continue;
					}
				}
			}
		}
		// user input Q suggests ending the menu program
		while (!option.equals("Q"));
		System.out.println("Enjoy your entirement!");
	}

	/**
	 * returns an array of sorted Cars ready to be added back into the linked
	 * list sequentially
	 * 
	 * @param list
	 *            a list of Cars ready to be sorted
	 * @return returns a list of Cars sorted in ascending order based on their
	 *         the string representation of the car type and the respective
	 *         order if they are of same type
	 *         <dt>Postcondition:
	 *         <dd>The array is sorted in ascending order
	 */
	public static Car[] bubbleSort(Car[] list) {
		// in ascending order
		for (int i = 0; i < list.length; i++) {
			for (int j = i + 1; j < list.length; j++) {
				if (list[i].compareTo(list[j]) > 0) {
					Car temp = list[i];
					list[i] = list[j];
					list[j] = temp;
				}
			}
		}
		return list;
	}
	

}
