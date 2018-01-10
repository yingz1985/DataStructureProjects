import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The <code>FXGuiMaker</code> that takes in a text file, generates a
 * FXComponentTree and provides an interface for a user to manipulate the tree.
 * 
 * @author Ying Zhang
 *
 */
public class FXGuiMaker
{
	private static FXComponentTree tree = new FXComponentTree();
	// empty tree with just a anchorpane

	public static void main(String[] args) throws FileNotFoundException
	{

		tree = new FXComponentTree();
		System.out.println("Welcome to counterfeit SceneBuilder.\n");
		System.out.println("Menu:\n\tL) Load from file\n\tP) Print tree"
				+ "\n\tC) Move cursor to a child node\n\tR) Move cursor to root"
				+ "\n\tA) Add a child\n\tE) Edit text of cursor\n\tU) Move Cursor up to parent"
				+ "\n\tD) Delete child\n\tS) Save to file\n\tQ) Quit");

		String option = "";
		do
		{
			Scanner input = new Scanner(System.in);
			System.out.print("\nPlease select an option from menu: ");
			// prompt user to enter an option from menu
			option = input.next().toUpperCase();

			/**
			 * if user inputs "L", prompt user to enter a valid fileName and
			 * create a tree from the file
			 */
			if (option.equals("L"))
			{
				Scanner read = new Scanner(System.in);
				System.out.print("Please enter filename:");
				String fileName = read.nextLine();
				try
				{
					tree.readFromFile(fileName);
				}
				catch (Exception x)
				{
					System.out.println(x.getMessage());
				}
			}
			/**
			 * print the tree when the user inputs "P"
			 */
			if (option.equals("P"))
			{
				try
				{
					
					System.out.print("\n" + tree);
				}
				catch(Exception x)
				{
					continue;
				}

			}

			/**
			 * user inputs "C" - prompt user to enter the index of the child
			 * move cursor to the index of the child
			 */
			if (option.equals("C"))
			{
				Scanner read = new Scanner(System.in);
				System.out.print(
						"Please enter number of child (starting with 1): ");
				int index = read.nextInt();
				try
				{
					tree.cursorToChild(index);
					System.out.println("Cursor Moved to " + tree.getCursor());
				}
				catch (Exception x)
				{
					System.out.println(x.getMessage());
				}

			}
			/**
			 * move cursor reference to root
			 */
			if (option.equals("R"))
			{
				tree.cursorToRoot();
				System.out.println("Cursor is at root");

			}
			/**
			 * user inputs "A" - prompt user to select a component type (initial
			 * is sufficient) prompt user to enter text of the new node prompt
			 * user to enter index of where to place the node add node to
			 * indicated position
			 */
			if (option.equals("A"))
			{
				Scanner read = new Scanner(System.in);
				Scanner num = new Scanner(System.in);
				System.out.print(
						"Select component type (H - HBox, V - VBox, T - TextArea, B - Button, L - Label):");
				String s = read.nextLine();
				System.out.print("Please enter text:");
				String text = read.nextLine();

				System.out.print("Please enter an index:(starting from 1):");
				int index = num.nextInt();
				FXTreeNode temp = new FXTreeNode(text,
						ComponentType.match(s.toUpperCase()));
				try
				{
					tree.addChild(index, temp);
				}
				catch (Exception x)
				{
					System.out.println(x.getMessage());
					continue;
				}

			}
			/**
			 * user inputs "E", prompt user to enter text to be edited at cursor
			 * if cursor is at a container then display a message, else change
			 * text at cursor
			 */
			if (option.equals("E"))
			{
				Scanner scan = new Scanner(System.in);
				System.out.print("Please enter new text:");
				String text = scan.nextLine();
				if (tree.getCursor().getType().specialType()
						.equals("container"))
					System.out.println("cannot edit text for a container.");
				else
				{
					tree.setTextAtCursor(text);
					System.out.println("Text Ediited.");
				}
			}
			/**
			 * user inputs "D", prompt user to enter the index of the child to
			 * be removed throw exception if precondition is violated remove
			 * child at index of the cursor node
			 */
			if (option.equals("D"))
			{
				Scanner scan = new Scanner(System.in);
				System.out.print(
						"Please enter number of child (starting with 1):");
				int index = scan.nextInt();
				System.out.println(tree.getCursor().getChildren()[index - 1]
						+ " removed.");
				try
				{
					tree.deleteChild(index);
				}
				catch (Exception e)
				{
					System.out.println("bad remove");
					continue;
				}
			}
			/**
			 * move cursor up to the parent node
			 */
			if (option.equals("U"))
			{
				if (tree.getCursor().getParent() != null)
				{
					tree.cursorToParent();
					System.out.println("Cursor moved to " + tree.getCursor());
				}
				else
				{
					System.out.println("Cursor does not have a valid parent");
				}
			}
			/**
			 * user inputs "S", prompt user for a fileName for the tree to be
			 * saved saves file of tree (tree should be able to load again into
			 * the menu and make further revisions)
			 */
			if (option.equals("S"))
			{
				Scanner scan = new Scanner(System.in);
				System.out.print("Please enter a filename:");
				String fileName = scan.nextLine();
				try
				{
					tree.writeToFile(tree, fileName);
					System.out.println("File saved.");
				}
				catch (Exception x)
				{
					System.out.println("error occurred");
				}

			}
		}
		while (!option.equals("Q"));

		System.out.println("Leaving FXGuiMaker...");

	}
}
