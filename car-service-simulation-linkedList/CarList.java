/**
 * The <code>CarList</code> class implements an linkedList-like data structure
 * containing <code>Car</code> objects wrapped in nodes
 * 
 * @author Ying Zhang 
 *
 */
public class CarList {
	private CarListNode head; // The first node in the linked-list
	private CarListNode tail; // the last node in the linked-list
	private CarListNode cursor; // the node currently pointed at
	private int numCar; // the total number of cars in the linked-list

	/**
	 * Constructs an instance of <code>CarList</code> with an empty list of cars
	 * 
	 * <dt>Postcondition:
	 * <dd>This CarList has been initialized with <code>head</code>,
	 * <code>tail</code>, and <code>cursor</code> all set to null.
	 * 
	 */
	public CarList() {
		head = null;
		tail = null;
		cursor = null;
		numCar = 0;
	}

	/**
	 * returns the total number of cars in the list
	 * 
	 * @return returns <code>numCar</code>
	 */
	public int numCars() {
		return numCar;
	}

	/**
	 * returns Car wrapped in the cursor node
	 * 
	 * @return returns <code>Car</code> stored in the cursor node. If the cursor
	 *         is null, then this method should return null as well.
	 */
	public Car getCursorCar() {
		return cursor.getData();
	}

	/**
	 * returns the cursor to the start of the list
	 * 
	 * <dt>Postcondition:
	 * <dd>If head is not null, the cursor now references the first
	 * <code>CarListNode</code> in this list.
	 * <dd>If head is null, the cursor is set to null as well (there are no Cars
	 * in this list).
	 */
	public void resetCursorToHead() {
		cursor = head;
	}

	/**
	 * Moves the cursor to select the next CarListNode in the list
	 * <dt>Postcondition:
	 * <dd>the cursor now wraps around the next <code>CarListNode</code> in the
	 * list.
	 * 
	 * @throws EndOfListException
	 *             indicates the cursor is at tail of the list and can not go
	 *             any further
	 */
	public void cursorForward() throws EndOfListException {
		if (cursor == tail || cursor.getNext() == null)
			throw new EndOfListException();
		else
			cursor = cursor.getNext();
	}

	/**
	 * Moves the cursor to select the previous CarListNode in the list
	 * <dt>Postcondition:
	 * <dd>the cursor now wraps around the previous <code>CarListNode</code> in
	 * the list.
	 * 
	 * @throws EndOfListException
	 *             indicates the cursor is at head of the list and can not go
	 *             any further
	 */
	public void cursorBackward() throws EndOfListException {
		if (cursor == head || cursor.getPrev() == null)
			throw new EndOfListException();
		else
			cursor = cursor.getPrev();
	}

	/**
	 * Inserts the indicated car into the list
	 * 
	 * @param newCar
	 *            Object <code>Car</code> to be inserted before the cursor in
	 *            the list
	 * 
	 *            <dt>Precondition:
	 *            <dd>newCar is not null.
	 * 
	 *            <dt>Postcondition:
	 *            <dd>newCar has been wrapped in a new CarListNode object.
	 *            <dd>If cursor was previously not null, the newly created
	 *            CarListNode has been inserted into the list before the cursor.
	 *            <dd>If cursor was previously null, the newly created
	 *            CarListNode has been set as the new head of the list (as well
	 *            as the tail).
	 *            <dd>The cursor remains unchanged unless the list was
	 *            previously empty.
	 * @exception IllegalArgumentException
	 *                indicates <code>newCar</code> is not of type
	 *                <code>Car</code>.
	 */
	public void insertBeforeCursor(Car newCar) {
		if (newCar == null)
			throw new IllegalArgumentException("You did not enter a valid car info");
		CarListNode tempNode = new CarListNode(newCar);
		if (cursor == null) {
			head = tempNode;
			tail = tempNode;
			cursor = tempNode;
		} else {
			if (cursor.getPrev() == null) {
				tempNode.setNext(cursor);
				cursor.setPrev(tempNode);
				head = tempNode;
			} else {
				tempNode.setNext(cursor);
				tempNode.setPrev(cursor.getPrev());
				cursor.getPrev().setNext(tempNode);
				cursor.setPrev(tempNode);

			}
		}
		numCar++;

	}

	/**
	 * Inserts the indicated Car after the tail of the list.
	 * 
	 * @param newCar
	 *            Object <code>Car</code> to be added to the end of the linked
	 *            list
	 * 
	 *            <dt>Precondition:
	 *            <dd>newCar is not null.
	 * 
	 *            <dt>Postcondition:
	 *            <dd>newCar has been wrapped in a new CarListNode object.
	 *            <dd>If tail was previously not null, the newly created
	 *            CarListNode has been inserted into the list at the end.
	 *            <dd>If tail was previously null, the newly created CarListNode
	 *            has been set as the new head of the list (as well as the tail
	 *            and the cursor).
	 *            <dd>The tail now references the newly created CarListNode
	 * 
	 * @exception IllegalArgumentException
	 *                indicates <code>newCar</code> is not of type
	 *                <code>Car</code>.
	 * 
	 */
	public void appendToTail(Car newCar) {
		if (newCar == null)
			throw new IllegalArgumentException();
		CarListNode tempNode = new CarListNode(newCar);
		if (tail == null) {
			head = tempNode;
			tail = tempNode;
			cursor = tempNode;
		} else {
			tempNode.setPrev(tail);
			tail.setNext(tempNode);
			tail = tempNode;
		}

		numCar++;

	}

	/**
	 * Removes the CarListNode referenced by cursor and returns the Car inside.
	 * <dt>Precondition:
	 * <dd>cursor is not null.
	 * 
	 * @return Returns <code>Car</code> removed at the cursor node
	 * 
	 *         <dt>Postcondition:
	 *         <dd>The CarListNode referenced by cursor has been removed from
	 *         the list.
	 *         <dd>All other CarListNodes in the list exist in the same Car as
	 *         before.
	 *         <dd>The cursor now references the previous CarListNode, or the
	 *         head if previous is null
	 * @throws EndOfListException
	 *             indicates the cursor is null; the list is empty
	 */
	public Car removeCursor() throws EndOfListException {
		if (cursor == null)
			throw new EndOfListException();

		Car cursorCar = getCursorCar();
		CarListNode newCursor;

		if (numCar == 1) {
			cursor = null;
			head = null;
			tail = null;
			numCar--;
			return cursorCar;

		}
		if (cursor == tail) {
			newCursor = cursor.getPrev();
			cursor.getPrev().setNext(null);
			cursor.setPrev(null);
		} else if (cursor == head) {
			newCursor = cursor.getNext();
			cursor.getNext().setPrev(null);
			cursor.setNext(null);
			head = newCursor;
		} else {
			newCursor = cursor.getPrev();
			cursor.getPrev().setNext(cursor.getNext());
			cursor.getNext().setPrev(cursor.getPrev());
		}

		cursor = newCursor;
		numCar--;
		return cursorCar;

	}

	/**
	 * Generates a string representation of the Car list
	 * 
	 * @return returns a list of string representations of Cars wrapped in nodes
	 *         sequentially
	 * 
	 */
	public String toString() {
		if (head == null)
			return null;

		CarListNode temp = head;
		String result = "";
		while (temp != null) {
			if (temp == cursor) {
				result += String.format("%s%-13s%-13s", "->", temp.getData().getMake(), temp.getData().getOwner())
						+ "\n";
				temp = temp.getNext();
			} else {
				result += temp.getData() + "\n";
				temp = temp.getNext();
			}
		}
		return result;

	}

	/**
	 * sorts the linked list
	 * 
	 * @throws EndOfListException
	 *             indicates the cursor has reached the end of the list
	 */
	public CarListNode sort() 
	{
		
		CarListNode newHead = this.head;
		CarListNode cursor = null;
		while(this.head!=null)
		{
			cursor = newHead; // always set it to head and compare 
			
			while(cursor.getNext()!=null && (head.getNext().getData().compareTo(cursor.getNext().getData())<0))
			{
				cursor = cursor.getNext();
			}

			CarListNode tem = null;//
			tem = new CarListNode(head.getData());
			tem.setNext(cursor.getNext());
			cursor.setNext(tem);
			head = head.getNext();
			
		}
		return newHead;

	}
	public static void main(String[]args)
	{
		Car c1 = new Car(Make.CHEVY,"c1");
		Car c2 = new Car(Make.CHRYSLER,"c2");
		Car c3 = new Car(Make.FORD,"c3");
		Car c4 = new Car(Make.LINCOLN,"c4");
		CarList temp = new CarList();
		temp.appendToTail(c1);
		temp.appendToTail(c4);
		temp.appendToTail(c2);
		temp.appendToTail(c3);
		CarListNode newList = temp.sort();
		while(newList.getNext()!=null)
		{
			System.out.println(newList.toString());
			newList = newList.getNext();
		}
		

	
	 
	}
	
	

}
