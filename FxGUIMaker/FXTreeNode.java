/**
 * The <code>FXTreeNode</code> class implements a node in the tree. The
 * node contains type of component being represented,
 * an array of children (null if this will be a Control), 
 * and string for the text (null if this is a Container)
 * 
 * @author Ying Zhang 
 *
 */
	
public class FXTreeNode 
{
	private String text; //text contained in the component
	private ComponentType type;//type of component being represented 
	private FXTreeNode parent;//current node's parent node 
	private FXTreeNode[] children;//an array of nodes/children
	final int maxChildren = 10;//maximum amount of children in the array 
	private int numChildren = 0;//current number of children
	private String index;//node index in the tree

	
	/**
	 * returns the node's index in the tree 
	 * @return returns String index
	 */
	public String getIndex() {
		return index;
	}
	/**
	 * assigns parameter index to the FXTreeNode attribute index
	 * @param index node position in the tree 
	 * <dt>Postcondition:
	 * 		<dd> parameter index is assigned to the attribute index
	 * 
	 */
	public void setIndex(String index) {
		this.index = index;
	}
	/**
	 * Constructs an instance of <code>FXTreeNode</code>.
	 * 
	 * @param text text contained in the component
	 * @param type the type of component being represented 
	 * <dt>Postcondition:
	 * 		<dd> attribute type is initialized to passed in parameter type
	 * 		<dd> the children array is initialized, null if type is of category "control"
	 * 		<dd> attribute text is initialized to passed in parameter text,
	 * 			 if the node is of type container, the text is set to null
	 * 		<dd> index is initialized as an empty string
	 */
	public FXTreeNode(String text,ComponentType type)
	{
		
		setType(type);
		setChildren();
		setText(text);
		setIndex("");
	
	}
	/**
	 * checks to see if the node has any children in the array, returns true if there are
	 * 
	 * @return returns false if numChildren is 0, true otherwise 
	 * 
	 */
	public boolean hasChildren()
	{
		return (!(numChildren==0));
	}
	/**
	 * returns the number of node objects in the children array 
	 * 
	 * @return returns node attribute numChildren
	 */
	public int getNumChildren()
	{
		return numChildren;
	}
	/**
	 * increments numChildren
	 * <dt>Postcondition:
	 * 		<dd> numChildren is incremented by 1
	 */
	public void addChild()
	{
		numChildren ++;
	}
	/**
	 * decrements numChildren
	 * <dt>Postcondition:
	 * 		<dd> numChildren is decremented by 1
	 */
	public void deleteChild()
	{
		numChildren--;
	}
	/**
	 * returns text contained in the node 
	 * @return returns text
	 * 
	 */
	public String getText() 
	{
			return text;
	}
	/**
	 * initializes node attribute text 
	 * @param text text contained in the node 
	 * <dt>Postcondition:
	 * 		<dd> attribute text is initialized as passed in parameter text
	 */
	public void setText(String text) 
	{
		if(type.specialType().equals(("container")))
			text = null;
		else 
			this.text = text;
	}

	/**
	 * returns tree node's component type
	 * @return returns node's component type 
	 * 
	 */
	public ComponentType getType() 
	{
		return type;
	}
	/**
	 * initializes node attribute type
	 * 
	 * @param type the type of component being represented 
	 * <dt>Postcondition:
	 * 		<dd> passed in parameter type is assigned to node attribute type 
	 * 
	 */
	public void setType(ComponentType type)
	{
		this.type = type;
	}
	/**
	 * returns the parent node of the current node 
	 * @return returns parent node
	 * 
	 */
	public FXTreeNode getParent()
	{
		return parent;
	}
	/**
	 * sets parent node
	 * @param parent current node's parent node 
	 * <dt>Postcondition:
	 * 		<dd>passed in parameter parent is set as the node parent
	 */
	public void setParent(FXTreeNode parent) 
	{
		this.parent = parent;
	}
	/**
	 * returns all children of current node
	 * @return returns an array of FXTreeNode
	 */
	public FXTreeNode[] getChildren() 
	{
		return children;
	}
	/**
	 * initializes children array
	 * <dt>Postcondition: 
	 * 		<dd>children array is initialized with a size of maxChildren
	 * 		<dd>children array is null if node is of type "control"
	 */
	public void setChildren() 
	{
		if(type.specialType().equals("control"))
			return;
		else 
			children = new FXTreeNode[maxChildren];
	}
	/**
	 * returns maxChildren
	 * @return returns the maximum amount of children the array can hold 
	 */
	public int getMaxChildren()
	{
		return maxChildren;
	} 
	/**
	 * returns a string representation of FXTreeNode's attribute componentType and text
	 * if componentType is of "container", then there is no text to be displayed 
	 */
	public String toString()
	{
		String result = "";
		if(type.specialType().equals("control"))
			result = getType().toString()+": "+getText();
		else 
			result = getType().toString();
		return result;
	}


	
	
}
