//Ying Zhang


package finalProject;
import java.util.ArrayList;
import java.util.Collections;
public class Deck {
	//a full deck of uno cards- initialized and shuffled 
	
	private ArrayList<Card> deck= new ArrayList<Card>();
	
	public ArrayList<Card> getDeck() {
		return deck;}
	public void setDeck(ArrayList<Card> n){
		this.deck = n;
	}
	


	//Card(name,color,value,special)
	public Deck(){
		String temp[] = {"red","yellow","blue","green"};
		String[] names = {"Big Bird","Zoe","Cookie Monster","Elmo","Baby Bear","Rosita","Grover"};
		for(int i = 0;i<names.length;i++){
			addCard(names[i],i+1,temp);}
		deck.add(new Card("Ernie and Bert \"Draw 1\"", "red",1,"special"));
		deck.add(new Card("Ernie and Bert \"Draw 1\"", "blue",1,"special"));
		deck.add(new Card("Oscar the Grouch \"Choose who to draw 2\"","yellow",2,"special"));
		deck.add(new Card("Oscar the Grouch \"Choose who to draw 2\"","green",2,"special"));
		for(int i = 1; i<5;i++){
			deck.add(new Card(i));
			}	
		Collections.shuffle(deck);
	}
	
	public void shuffle(){
		Collections.shuffle(deck);}
	
	public Card drawCard(int n){
		return deck.get(n);}
	
	
	public void addCard(String name,int value, String colors[]){
		for (int i=0;i<colors.length;i++){
			deck.add(new Card(name,colors[i],value));}}
	

	public String toString(){
		String temp = "";
		for (int i=0;i<deck.size();i++){
		temp+=deck.get(i)+"\n";}
		
		return temp;}
	
}
