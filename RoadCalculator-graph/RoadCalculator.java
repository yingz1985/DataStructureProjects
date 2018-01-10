
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import big.data.DataSource;
import java.util.Scanner;

/**
 * <code>RoadCalculator</code> builds a graph of Nodes and Edges 
 * by calling the BigData class from within your buildGraph method. 
 * Next, Using the information contained within the graph to build 
 * the Minimum Spanning Tree (MST). Should also be able to take in 
 * input in the form of two strings from the user 
 * (source city and destination city) and use Djikstra’s Algorithm 
 * to find the shortest path between their two corresponding Nodes.

 * 
 * @author Ying Zhang 
 * 
 */
public class RoadCalculator
{
	private static HashMap<String,Node> graph;//a hashmap containing all nodes
	private static LinkedList<Edge> mst;//a linked-List of the minimal spanning tree
	
	public static void main(String[]args)
	{
		System.out.println("Please enter graph URL: ");
		Scanner user = new Scanner(System.in);
		String location = user.nextLine();
		try
		{
			graph = buildGraph(location); //first try to build the graph
			//assign the finished graph to the data field graph 
			//We will manipulate the finished graph

		}
		catch(Exception x)
		{
			System.out.println("Oops, an error has occured");
		}
		
		mst = buildMST(graph);
		printMST(mst);
		
		String option = "";
		do
		{
			//while user does not enter Q, keep prompting the user to enter
			//source and final location and calculate the shortest path
			//to get from source to final using the Djikstra algorithm
			
			System.out.println("Enter a starting point for shortest path or Q to quit:");
			option = user.nextLine();
			
			if(!option.toUpperCase().equals("Q"))
			{
				String start = option;
				System.out.println("Enter a destination:");
				Scanner input = new Scanner(System.in);
				String end = input.nextLine();
				System.out.println("Distance: "+Djikstra(graph, start, end));
				//String result = graph.get(end).returnPath().replaceAll("[", "");
				System.out.println("Path:\n"+graph.get(end).returnPath()+"\n");
				
			}
		}
		while(!(option.toUpperCase().equals("Q")));
		
		System.out.println("Goodbye,system exiting...");
	}
	/**
	 * prints the minimal spanning tree
	 * 
	 * @param mst A LinkedList representation of the minimal spanning tree
	 */
	public static void printMST(LinkedList<Edge> mst)
	{
		System.out.println("Minimum Spanning Tree: \n"
				+ "----------------------------");
		for(int i = 0;i<mst.size();i++)
		{
			System.out.println(mst.get(i));
		}
		System.out.println("----------------------------");
	}
	/**
	 * Constructs a graph from a passed in location
	 * <dt>precondition:
	 * 		<dd>The location has to be an accepted URL format for the graph
	 * @param location an URL the user passes in at runtime
	 * @return returns a hashmap with the nodes of the graph stored
	 * <dt>postcondition:
	 * 		<dd>A connected graph of Nodes and Edges has been constructed.
	 * 
	 */
	public static HashMap<String, Node> buildGraph(String location)
	{
		 HashMap<String,Node> cities = new HashMap<String,Node>();
		 DataSource ds = DataSource.connectXML(location);
		 ds.load();
		 System.out.println("Loading Map...\n");
		 
		 String cityNamesStr=ds.fetchString("cities");
		 String[] cityNames=cityNamesStr.substring
		   (1,cityNamesStr.length()-1).replace("\"","").split(",");
		 System.out.println("Cities\n----------------------------");
		 for(int i = 0;i<cityNames.length;i++)
			 
		{///Users/Bagel/Documents/workspace/Graph/src/hw7.xml
			 Node cityNode = new Node(cityNames[i]);
			 cities.put(cityNames[i], cityNode);
			 
			 System.out.println(cityNames[i]);
		}
		 
		 String roadNamesStr=ds.fetchString("roads");
		 roadNamesStr = roadNamesStr.replaceAll(",,", ",");
		 String[] roadNames=roadNamesStr.substring(2,roadNamesStr.length()-2)
		   .split("\",\"");
		 System.out.println("----------------------------\nRoads:\n"
		   + "----------------------------");


		 for(int i = 0;i<roadNames.length;i++)
			{
			 	//System.out.println(roadNames[i]);
			 	String[] edge = roadNames[i].split(",");
			 	Node node1 = cities.get(edge[0]);
			 	Node node2 = cities.get(edge[1]);
			 	int cost = Integer.valueOf(edge[2]);
			 	Edge A = new Edge(node1,node2,cost);
			 	Edge B = new Edge(node2,node1,cost);
			 	node1.getEdges().add(A);
			 	node2.getEdges().add(B);
			 	System.out.println(B);

			}
		 System.out.println("----------------------------");
		 return cities;
		
	}
	/**
	 * Constructs and returns a minimal spanning tree of the graph 
	 * <dt>Precondition:
	 * 		<dd> passed in hashmap must be a connected graph of nodes and edges
	 * @param graph A connected graph of Nodes and Edges
	 * @return returns a minimal spanning tree stored in a linked list
	 * <dt>Postcondition:
	 * 		<dd>A connected Minimum Spanning Tree in the form of a Linked List has been constructed
	 */
	public static LinkedList<Edge> buildMST(HashMap<String, Node> graph)
	{
		LinkedList<Edge> sample = new LinkedList<Edge>();
		
		ArrayList<Edge> edges = new ArrayList<Edge>() ;
		int numNodes = graph.size();
		
		//fill up the arraylist of all edges
		for(Node s: graph.values())
		{

				Object[] array= s.getEdges().toArray();
				for(Object m : array)
					edges.add((Edge) m);
			
		}
	
			Collections.sort(edges,new EdgeComparator());//sorts the arraylist of edges by weight
			sample.add(edges.get(0));
			edges.get(0).getA().setVisited(true);
			edges.get(0).getB().setVisited(true);

			
			numNodes = numNodes-2;
			//first element
			while(numNodes>0)
			{
				for(int i = 0;i<edges.size();i++)
				{
					if((edges.get(i).getA().isVisited())
					&&!(edges.get(i).getB().isVisited()))
					//exactly one is visited but not both (node) add to mst
					//set second node to visited
					{
						sample.add(edges.get(i));
						numNodes--;
						edges.get(i).getB().setVisited(true);
						break;
					}
						
				}
			}

	
	
		return sample;
		
	}
	
	/**
	 * Constructs an arraylist of unvisited nodes 
	 * @param graph A connected graph of Nodes and Edges
	 * @return returns an ArrayList of all the nodes contained in the graph
	 * <dt>Postcondition:
	 * 		<dd> all paths have been cleared for all nodes
	 * 		<dd> all nodes have been set to unvisited
	 * 		<dd> all node distance have been set to infinity(largest val)
	 */
	public static ArrayList<Node> resetNodes(HashMap<String,Node> graph)
	{
		ArrayList<Node> node = new ArrayList<Node>();
		for(Node nodes:graph.values())
		{
			nodes.resetPath();
			nodes.setVisited(false);
			nodes.setDistance(Integer.MAX_VALUE);
			node.add(nodes);
			
		}
		
		return node;
		
	}
	/**
	 * Finds and returns the cheapest cost from source to destination
	 * <dt>Precondition:
	 * 		<dd>Both source and dest string must exist in the graph hashmap(valid city names)
	 * @param graph A connected graph of Nodes and Edges
	 * @param source string name of the source node
	 * @param dest	string name of the destination node
	 * @return The cost of the cheapest path from source to dest.
	 */
	public static int Djikstra(HashMap<String,Node> graph, String source, String dest)
	{
 
		if(source.equals(dest))
			return 0;
		
		if(graph.containsKey(source)&&graph.containsKey(dest))
		{
			
			ArrayList<Node> unvisited = resetNodes(graph);
			graph.get(source).setDistance(0);
			Collections.sort(unvisited,new NodeComparator());

			Node current = unvisited.get(0);
			
			while((!unvisited.isEmpty()))
			{
				
	
			
				Object[] AllEdges = current.getEdges().toArray();
				for(int i= 0;i<AllEdges.length;i++)
				{
					Edge currentEdge = (Edge)(AllEdges[i]);
					int totalCost = current.getDistance()+currentEdge.getCost();
					
					if(totalCost < currentEdge.getB().getDistance())
					{
						currentEdge.getB().setDistance(totalCost);
						currentEdge.getB().resetPath();


						currentEdge.getB().getPath().add(current.returnPath()+current.getName()+"");
						
					}
					
				
				}
				current.setVisited(true);
				unvisited.remove(current);
				for(Node s:unvisited)
				{
					if(s.getDistance()<Integer.MAX_VALUE)
					{
						current = s;
					}
				}
			
			
			}
			
	
			
			
			
			
		}
		
		else
		{
			System.out.println("Invalid location given");
			return Integer.MAX_VALUE;
		}
		
		
		return graph.get(dest).getDistance();
		
	
	}

	
}
