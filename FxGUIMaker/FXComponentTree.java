import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;

/**
 * The <code>FXComponentTree</code> class implements a manager for the tree object.
 * it holds references to the root and the cursor (fr traversal purposes), as well 
 * as be able to generate and read in from a text file
 * 
 * @author Ying Zhang 
 */
public class FXComponentTree
{
	private FXTreeNode root;//the root reference of a tree- first node 
	private FXTreeNode cursor; //cursor reference
	
	/**
	 * returns cursor node 
	 * @return returns cursor node 
	 */
	public FXTreeNode getCursor() 
	{
		return cursor;
	}
	/**
	 * returns root node 
	 * @return returns root node 
	 */
	public FXTreeNode getRoot()
	{
		return root;
	}

	/**
	 * creates an instance of FXComponentTree
	 * @param fileName name of the file that contains a tree format
	 * <dt>Postcondition:
	 * 		<dd>checks to see if passed in fileName exists, if not, create an empty tree 
	 * 		    with just the AnchorPane
	 * 		<dd> sets index of root to 0 
	 * 		<dd> sets cursor to root
	 */
	public FXComponentTree(String fileName)
	{
		File file = new File("fileName");
		if(!file.exists())
			
		{
			root = new FXTreeNode("AnchorPane", ComponentType.AnchorPane);
			root.setIndex("0");
			cursor = root;
		}
		//text is set to null since AnchorPane is a container
	}
	/**
	 * sets newRoot as the root of the tree 
	 * @param newRoot FXTreeNode reference
	 * <dt>Postcondition:
	 * 		<dd> newRoot is assigned to root
	 */
	public void setRoot(FXTreeNode newRoot)
	{
		this.root = newRoot;
		cursor = root;
		
	}
	/**
	 * creates an instance of FXComponentTree with an empty fileName
	 * automatically creates an empty tree with one anchorpane 
	 */
	public FXComponentTree()
	{
		this("");
	}
	/**
	 * move cursor to root reference 
	 * <dt>Postcondition:
	 * 		<dd> cursor now references the root node 
	 */
	public void cursorToRoot()
	{
		cursor = root;

	}
	/**
	 * delete node at a specified index of the cursor node 
	 * @param index index of child to be deleted 
	 * <dt>Precondition:
	 * 		<dd> index should be greater than 0 (index starts at 1)
	 * 		<dd> cursor must have at least 1 child that can be deleted 
	 * 		<dd> index cannot be greater than the number of children in cursor
	 * <dt>Postcondition:
	 * 		<dd> child at index is deleted from the tree, as well as all of its children
	 * @throws InvalidArgumentException-indicates the index creates a hole in the array
	 * @throws EmptyTreeException-indicates the node has no child to be deleted
	 * @throws IllegalArgumentException-indicates the index is negative 
	 */
	//index starts at 1 
	public void deleteChild(int index) throws Exception
	{
			
		if(index<=0)
			throw new IllegalArgumentException("negative index");
		else if(cursor.hasChildren()==false)
			throw new EmptyTreeException("current node has no child");
		else if(index>cursor.getNumChildren())
			throw new InvalidArgumentException();
		else
		{
			cursor.getChildren()[index-1] = null;
			for(int i = index-1;i<cursor.getNumChildren();i++)
				cursor.getChildren()[i] = cursor.getChildren()[i+1];
			cursor.deleteChild();
		}	
			
	}
	
	/**
	 * adds a Child to the cursor node 
	 * @param index index where the node should be inserted 
	 * @param node FXTree node to be inserted into the tree 
	 * <dt>Precondition:
	 * 		<dd> index must be greater than 0
	 * 		<dd> index cannot be greater than the number of children in cursor+1
	 * 		<dd> the children array in cursor cannot be full
	 * <dt>Postcondition:
	 * 		<dd> added a child in the cursor node children array
	 * @throws IllegalArgumentException-indicates the index entered is negative 
	 * @throws InvalidArgumentException-indicates the index creates a hold in the tree
	 * @throws FullTreeException-indicates the cursor already has 10 children 
	 * 
	 */
	public void addChild(int index, FXTreeNode node)throws Exception
	{
		
		if(root==null || root.getType().toString().equals(node.getType().toString()))
		{

			setRoot(node);

			return;
		}

		else if(index<=0) 
			throw new IllegalArgumentException("invalid index");
		else if(index>cursor.getNumChildren()+1)
			throw new InvalidArgumentException();
		else if(cursor.getNumChildren()==cursor.getMaxChildren())
				throw new FullTreeException();
		
		else
		{

				node.setParent(cursor);
				

				for(int i = cursor.getNumChildren();i>index-1;i--)
					cursor.getChildren()[i] = cursor.getChildren()[i-1];
				cursor.getChildren()[index-1] = node;
				cursor.addChild();
				cursorToChild(index);
				
			
		}
		
	}
	/**
	 * reassigns text to the cursor node 
	 * @param text String text to be assigned to cursor's text 
	 * <dt>postcondition:
	 * 		<dd> passed in parameter text is set as text at cursor 
	 */
	public void setTextAtCursor(String text)
	{
		if(cursor.getType().specialType().equals("container"))
			return;
		else 
		{
			cursor.setText(text);
		}
	}

	/**
	 * points cursor reference to one of its children 
	 * <dt>Precondition:
	 * 		<dd> index must be greater than 0
	 * 		<dd> index must within the range of cursor's numChildren
	 * @param index position of child for the cursor to move to 
	 * @throws IllegalArgumentException-indicates negative index 
	 * @throws InvalidArgumentException-indicates the index reference is invalid
	 */
	public void cursorToChild(int index)throws Exception
	{
		if(index<=0) 
			throw new IllegalArgumentException("negative index");
		else if(index>cursor.getNumChildren())
			throw new InvalidArgumentException();
		else
		{
			cursor = cursor.getChildren()[index-1];
		}
	}
	
   /**
    * moves cursor to the parent node 
    * <dt>Postcondition:
    * 		<dd> cursor now references its parent node 
    */
	public void cursorToParent()
	{
		cursor = cursor.getParent();
	}
	/**
	 * generates a tree from a file
	 * @param fileName name of the file passed in 
	 * @return returns a FXComponentTree as indicated in the file
	 * @throws FileNotFoundException-indicates the file does not exist
	 * 
	 */
    public FXComponentTree readFromFile(String fileName) throws FileNotFoundException 
    {
        String[] result ;
        FXComponentTree fileTree= new FXComponentTree();
        try 
        {
        	File file =  new File(fileName);
        	Scanner read = new Scanner(System.in);
        	read = new Scanner(file);
            
            while (read.hasNextLine()) 
            {
            	//(format: position in tree, space, component type, 
            	//a space character, text of component (if applicable), newline)
            	
            	//separate each line in the file by spacing
            	//array[0] is a string of indices separated by dashes 
            	//array[1] is componentType
            	//rest of the array can be combined into one string 
            	String store = read.nextLine();
            	result = store.split(" ");
            	//
            	String path = result[0].replace("-", "");
            	ComponentType name = ComponentType.valueOf(result[1]);
            	String text = "";
                this.cursorToRoot();
                
                
                for(int i = 1;i<path.length()-1;i++)
                {
                	cursorToChild(path.charAt(i)-'0'+1);
                }
                
                for(int i = 2;i<result.length;i++)
                {
                	text +=result[i]+" ";
                }

                FXTreeNode temp = new FXTreeNode(text,name);
                addChild(path.charAt(path.length()-1)-'0'+1,temp);
                     
            }
            read.close();
            System.out.println(fileName+ " loaded");
        } 
        
        catch (Exception x)
        {
        	
            System.out.println(fileName+" not found.");
        }
        cursorToRoot();
      	return fileTree;
        
    }
    /**
     * converts a tree into string format and creates a document in the system
     * @param tree a tree ready to be parsed into file format
     * @throws FileNotFoundException-indicates the filename cannot be created/written on
     * <dt>Postcondition:
     * 		<dd>default file with name "tree.txt" is created with tree data 
     */
    public void writeToFile(FXComponentTree tree) throws FileNotFoundException
    {
    	writeToFile(tree,"tree.txt");
    }
    /**
     * Generates a text file that reflects the structure of the FXComponentTree
     * @param tree a tree ready to be parsed into file format
     * @param fileName name of the file created
     * <dt>Postcondition:
     * 		<dd>file with name fileName is created with tree data
     * @throws FileNotFoundException -indicates the filename cannot be created/written on
     */
    public void writeToFile(FXComponentTree tree,String fileName) throws FileNotFoundException
    {
    	String readyToAdd = tree.fileString();
    	File file = new File(fileName);
    	PrintWriter write = new PrintWriter(file);
    	write.print(readyToAdd);
    	write.close();
    }
    /**
     * returns a string of tree data ready to be written onto file
     * @return returns text file format of tree
     */
    public String fileString() 
    {
    	return "0 AnchorPane\n"+fileString(root).replaceAll(":", "");
    }
    /**
     * returns a string of tree data ready to be written onto file
     * @param node root node reference- parsing from root
     * @return returns a String representation of the text file format of the tree 
     * <dt>Postcondition:
     * 		<dd> all nodes have been assigned indices in the text file format
     */
	public String fileString(FXTreeNode node) 
	{
		 
		setIndex();
		String result = "";
		
		if(node.hasChildren())
		{
			for(int i = 0;i<node.getNumChildren();i++)
			{
		
					result+=node.getChildren()[i].getIndex()+" "+node.getChildren()[i]+"\n"+fileString(node.getChildren()[i]);
				
			}
		}
		
		return result;

	}
	/**
	 * sets indices for all nodes in the tree
	 * <dt>Postcondition:
	 * 		<dd> index at root is initialized as "0"
	 */
	public void setIndex()
	{
		if(root!=null)
			root.setIndex("0");
		setIndex(root);
	}
	/**
	 * recursively sets each node's index by adding all its parent node's indices
	 * @param node node to start setting index
	 * <dt>Postcondition:
	 * 		<dd> all indices starting from node has indices assigned
	 */
	public void setIndex(FXTreeNode node)
	{

		if(node.hasChildren())
		{
			for(int i = 0;i<node.getNumChildren();i++)
			{
					FXTreeNode child = node.getChildren()[i];
					if(child.getParent().getIndex()!=null)
						child.setIndex(child.getParent().getIndex()+"-"+i);
					setIndex(node.getChildren()[i]);
				
			}
		}
	}
	/**
	 * calls another recusive toString method
	 * @return returns a string representation of the tree
	 */
	public String toString()
	{
		return toString(root,0);
	}
	/**
	 * returns a String representation of the tree recursively 
	 * 
	 * @param node node to start returning the string 
	 * @param space how many indentations (deeper the level, more spaces)
	 * @return returns a string representation of the tree 
	 */
	public String toString(FXTreeNode node,int space)
	{
		
		String result = "";
		for(int i = 0;i<space;i++)
		{
			result+="  ";
		}
		if(node==cursor)
			result+="==>";
		else
			result+="+--";
		result+=node+"\n";
		if(node.hasChildren())
		{
			for(int i = 0;i<node.getNumChildren();i++)
			{
				
				result += "  "+toString(node.getChildren()[i],space+1);
			}
		}
		return result;
	}


	
	

}
