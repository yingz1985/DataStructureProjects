/**
 * 
 * <code>Edge</code>implements the Comparable interface. 
 * An Edge represents an adjacency between two cities in our country. 
 * In other words, two cities (Nodes) are adjacent if and only if 
 * they have an Edge between them. 
 * The Edge class will contain two Node references and an integer 
 * representing the cost of laying down Internet cable between the two cities. 
 * 
 * @author Ying Zhang 
 * 
 */
public class Edge implements Comparable
{
	private Node A;//The starting node
	private Node B;//The connected node
	private int cost;//cost between the two nodes

	/**
	 * Constructs an instance of <code>Edge</code>
	 * @param A the starting node
	 * @param B the destination node
	 * @param cost the cost to go from A to B
	 * <dt>Postcondition:
	 * 		<dd> parameters have been assigned to according datafields
	 */
	public Edge(Node A,Node B,int cost)
	{
		this.A = A;
		this.B = B;
		this.cost = cost;
	}
	/**
	 * Compares the current edge’s cost to otherEdge’s cost.
	 * @param o Edge object to be compared to
	 *  
	 * @return -1 if the current edge’s cost is less than otherEdge’s cost,
	 * 0 if equal, and 1 if greater than.
	 * 
	 */
	public int compareTo(Object o)
	{
		if(o instanceof Edge)
		{
			if(cost<((Edge)o).getCost())
				return -1;
			if(cost>((Edge)o).getCost())
				return 1;
			else return 0;
						
		}
		else
			return -2;
	}
	/**
	 * returns a string representation of the edge object
	 * @return A string representation of the nodes and the associated cost
	 */
	public String toString()
	{
		return A + " to "+ B +" "+cost;
	}
	/**
	 * returns the starting node
	 * @return	returns node A
	 */
	public Node getA()
	{
		return A;
	}
	
	/**
	 * Set starting node
	 * @param a	starting node
	 * <dt>Postcondition:
	 * 		<dd>parameter a has been assigned to the starting node A
	 */
	public void setA(Node a)
	{
		A = a;
	}
	/**
	 * returns the destination node
	 * @return	returns node B
	 */
	public Node getB()
	{
		return B;
	}
	
	/**
	 * Set destination node 
	 * @param b	a destination node 
	 * <dt>Postcondition:
	 * 		<dd>parameter b has been assigned to the destination node B
	 */
	public void setB(Node b)
	{
		B = b;
	}
	/**
	 * returns the cost to go from node A to node B
	 * @return returns cost
	 */
	public int getCost()
	{
		return cost;
	}

	/**
	 * Set the cost to go from node A to node A
	 * @param cost the cost of laying down Internet cable between the two cities
	 * <dt>Postcondition:
	 * 		<dd> parameter cost has been assigned to the attribute cost
	 */
	public void setCost(int cost)
	{
		this.cost = cost;
	}
	


}
