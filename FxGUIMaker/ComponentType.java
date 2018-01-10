/**
 * The <code>ComponentType</code> documents components in frameworks like Javafx
 * 
 * @author Ying Zhang 
 *
 */
public enum ComponentType 
{
	Button, Label, TextArea, HBox, VBox, AnchorPane;

	/**
	 * returns a String representation of the component's category 
	 * 
	 * @return returns either "control" or "container" based on the component type
	 */
	public String specialType()
	{
		switch(this)
		{
			case Button:
			case Label:
			case TextArea:
				return "control";
		default:
				return "container";
		}
	}
	/**
	 * 
	 * @param s A capital letter that matches one of the component types
	 * 
	 * @return returns the component type name starts with the letter s
	 * 
	 */
	public static ComponentType match(String s)
	{
		for(ComponentType type: ComponentType.values())
		{
			if(type.toString().charAt(0)==(s.charAt(0)))
				return type;
		}
		return null;
	}

}

