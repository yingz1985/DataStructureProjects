//Ying Zhang

package finalProject;
import java.util.ArrayList;
public class Player {
	String name = "Computer";
	private ArrayList<Card> hand;
	
	public Player(String name){
		this.name = name;}
	

	public String getName() {
		return name;}
	
	
	public boolean canMatch(Card o){
		
		if(o.getName().equals("normal")){
		
		for(int i = 0; i<this.getHand().size();i++){
			Card temp = this.getHand().get(i);
			if(temp.getName().equals(o.getName())||temp.getColor().equals(o.getColor())||
					temp.getValue()==o.getValue()||temp.getTypeCard().equals("wild")) return true;
		}
		return false;}
		
		else 
			for(int i = 0; i<this.getHand().size();i++){
				Card temp = this.getHand().get(i);
				if(temp.getName().equals(o.getName())||temp.getColor().equals(o.getColor())
						|| temp.getTypeCard().equals("wild")) return true;
			}
		return false;
			
		
	}
	
	public void setHand(ArrayList<Card> hand1) {
	//	System.arraycopy(hand1, 0, this.hand, 0, hand.size()-1);
		this.hand = hand1;}

	public ArrayList<Card> getHand() {
		
		return hand;}

	
	public Card putDownCard(int n){
		Card temp = hand.get(n);
		hand.remove(n);
		return temp;} 
	
		
	
	public String toString(){
		String temp = "";
		for (int i=0;i<hand.size();i++){
		temp+=hand.get(i)+"\n";}
		return temp;}

	}
	
	
	

		
	

	
	


