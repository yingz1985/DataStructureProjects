import java.util.HashSet;
import java.util.LinkedList;

/**
 * 
 * <code>Node</code> represents a City that will be a part of our graph of connected cities.
 * Each city has a name, a HashSet containing which cities it is adjacent to (represented by an edge),
 * and a boolean value to represent if the node has been visited by the graph traversal.
 * 
 * @author Ying Zhang 
 * 
 */
public class Node 
{
	private String name;//name of the node
	private HashSet<Edge>edges; //connections or edges to another node
	private boolean visited;//whether or not the node has been visited or not
	private LinkedList<String> path;//current shortest path from the starting node to this node
	private int distance;
	
	/**
	 * Constructs an instance of <code>Node</code>
	 * @param name name of the node
	 * 
	 * <dt>Postcondition:
	 * 		<dd> parameter name has been assigned to the data field name
	 * 		<dd> path and edges have been initizalized to empty collections
	 * 		<dd> distance is set to the maximum value java can hold up to
	 * 		<dd> visited to set to false
	 */
	public Node(String name)
	{
		this.name = name;
		visited = false;
		path = new LinkedList<String>();
		distance = Integer.MAX_VALUE;
		edges = new HashSet<Edge>();
		
	}
	/**
	 * returns name of the node
	 * @return returns attribute name
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * sets attribute name
	 * @param name name of the node
	 * <dt>Postcondition:
	 * 		<dd> paramater name has been assigned to the field name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * returns all the edges the node is connected with in a hashset
	 * @return returns the HashSet edges
	 */
	public HashSet<Edge> getEdges()
	{
		return edges;
	}

	/**
	 * returns true is the node has been visited, false otherwise
	 * @return returns a boolean value whether or not 
	 * 		the node has been visited 
	 */
	public boolean isVisited()
	{
		return visited;
	}
	/**
	 * Set attribute visited
	 * @param visited a boolean value indicating whether or not visited
	 * <dt>Postcondition:
	 * 		<dd> parameter visiter has been assigned to the field visited
	 */
	public void setVisited(boolean visited)
	{
		this.visited = visited;
	}
	
	/**
	 * returns a linkedList of the shortest path from root to current node
	 * @return returns attribute path
	 * 
	 */
	public LinkedList<String> getPath()
	{
		return path;
	}
	/**
	 * resets the LinkedList path by assigning it to an empty LinkedList
	 */
	public void resetPath()
	{
		this.path = new LinkedList<String>();
	}
	/**
	 * returns a string representation of the shortest path 
	 * @return	returns a string representation of the LinkedList path
	 */
	public String returnPath()
	{
		String result = "";
		for(int i = 0;i<path.size();i++)
		{
			result+=path.get(i)+",";
		}
		return result;
	}
	/**
	 * returns the length of the current known shortest distance 
	 * from the starting given to the current node
	 * @return	returns attribute distance
	 */
	public int getDistance()
	{
		return distance;
	}
	/**
	 * Set distance 
	 * @param distance	length of the current known shortest path 
	 * from the given node to the starting node in Dijkstra's
	 * <dt>Postcondition:
	 * 		<dd> parameter distance has been assigned to the attribute distance
	 */
	public void setDistance(int distance)
	{
		this.distance = distance;
	}
	
	
	/**
	 * returns the data field attribute String name
	 * @return returns a string representation of the node
	 */
	public String toString()
	{
		return name;
	}
	
	
	
}
