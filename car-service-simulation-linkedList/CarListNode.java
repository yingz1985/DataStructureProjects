
/**
 * The <code>CarListNode</code> class implements a node in a linked list. The
 * node contains an object of type <code>car</code> and are linked to nodes
 * before and after the node.
 * 
 * @author Ying Zhang 
 *
 */
public class CarListNode {
	private Car data; // Car object in the node
	private CarListNode next; // the next node in the linked list
	private CarListNode prev; // the previous node in the linked list

	/**
	 * Constructs an instance of <code>CarListNode</code>.
	 * 
	 * @param initData
	 *            Object <code>Car</code> at the given node
	 *            <dt>Precondition:
	 *            <dd>passed in parameter can not be null.
	 * 
	 *            <dt>Postcondition:
	 *            <dd>This CarListNode has been initialized to wrap the
	 *            indicated Car, and prev and next have been set to null.
	 * 
	 * @throws IllegalArgumentException
	 *             indicates the passed in object is null.
	 */
	public CarListNode(Car initData) throws IllegalArgumentException {
		if (initData == null)
			throw new IllegalArgumentException();

		data = initData;
		next = null;
		prev = null;
	}

	/**
	 * returns a car wrapped in the current node
	 * 
	 * @return returns the object <code>Car</code> at current node
	 */
	public Car getData() {
		return data;
	}

	/**
	 * returns the next node in the linked list
	 * 
	 * @return returns the object <code>CarListNode</code> linked next to the
	 *         current node
	 */
	public CarListNode getNext() {
		return next;
	}

	/**
	 * returns the previous node in the linked list
	 * 
	 * @return returns the object <code>CarListNode</code> linked a node before
	 *         the current node
	 */
	public CarListNode getPrev() {
		return prev;
	}

	/**
	 * sets the object passed in as the car stored in the node
	 * 
	 * @param data
	 *            object <code>Car</code>
	 * 
	 *            <dt>Postcondition: <code>data</code> initialized as the
	 *            <code>Car</code> for the current node
	 * 
	 */
	public void setData(Car data) {
		this.data = data;
	}

	/**
	 * sets the object passed in as the car stored in the next node
	 * 
	 * @param next
	 *            object <code>Car</code>
	 * 
	 *            <dt>Postcondition:
	 *            <dd><code>next</code> initialized as the <code>Car</code> for
	 *            the next node
	 * 
	 */
	public void setNext(CarListNode next) {
		this.next = next;
	}

	/**
	 * sets the object passed in as the car stored in the previous node
	 * 
	 * @param prev
	 *            object <code>Car</code>
	 * 
	 *            <dt>Postcondition:
	 *            <dd><code>prev</code> initialized as the <code>Car</code> for
	 *            the previous node
	 */
	public void setPrev(CarListNode prev) {
		this.prev = prev;
	}
	
}
