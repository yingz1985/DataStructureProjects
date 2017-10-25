//Ying Zhang

package finalProject;

public class Card {
	private String name = "";
	private String color = "";
	private int value = 0;
	private String typeCard = "";
	public Card(String name, String color, int value){
		//setUp for regular card
		this.name = name;
		this.color = color;
		this.value = value;
		this.typeCard = "normal";}
	
	public Card(String color){
		this.color = color;	}
	
	public void setColor(String color){
		this.color = color;}
	

	public Card(String name, String color, int value, String typeCard){
		//setup for special cards
		this.name = name;
		this.color = color;
		this.value = value;
		this.typeCard = typeCard;}
	
	public Card(int value){
		//default is wild Card
		this.value = value+8;
		name = "Monster \"Wild\"";
		typeCard = "wild";
		color = "TBD";}
	
	public String getName() {
		return name;}
	
	public String getColor() {
		return color;}
	
	public int getValue() {
		return value;}

	public String getTypeCard() {
		return typeCard;}
	
	public String toString(){
		if(this.getTypeCard().equals("normal"))
		return name+" #"+value+" "+color;
		else if(this.getTypeCard().equals("special"))
		return name+" "+color;
		else {
			if(this.getColor().equals("TBD")) return name;
			else return name+" "+ color;}
		}
	
	public boolean equals(Object o){
		if(o instanceof Card){
			if(name==((Card)o).getName() && color ==((Card)o).getColor()
					&& value==((Card)o).getValue())
				return true;
			else return false;
		}
		return false;
	} 
	public boolean match(Card o){
		if(o.getTypeCard().equals("special")){
			if(this.getColor().equals(o.getColor())||this.getTypeCard().equals("wild")||this.getName().equals(o.getName()))
				return true; 
		}
		else{
			if(this.getName().equals(o.getName())||
				this.getColor().equals(o.getColor())||
				this.getValue()==o.getValue()||
				this.getTypeCard().equals("wild")){
			return true;}
			}
			return false;
		}
	


}

