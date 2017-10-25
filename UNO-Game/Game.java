//Ying Zhang

package finalProject;
import java.util.Scanner;
import java.util.ArrayList;

public class Game {
	private static Deck full = new Deck();

//	private static ArrayList<Card> discarded = new ArrayList();
	//not sure if i need a discarded pile
	//Hao said i could simply move down the number 
	private static ArrayList hand1 = new ArrayList();
	private static ArrayList hand2 = new ArrayList();
	private static ArrayList<Card> discarded = new ArrayList();
	
	

	public static void main(String[]args){
	boolean playerFirst = true;
	int index = 0; boolean uno = false;
	

	Scanner s = new Scanner(System.in);
	
	System.out.println("Please enter player name: ");
	Player me = new Player(s.nextLine());
	Player computer = new Player("Computer");
	
	Card playCard;
	Card compCard;
	do{	
	playCard = full.drawCard(index);
	index ++;
	compCard = full.drawCard(index);
	index++;}
	while(full.drawCard(index-1).getTypeCard()!="normal" || full.drawCard(index-2).getTypeCard()!="normal"||
			(full.drawCard(index-1).getValue()==full.drawCard(index-2).getValue()));
	
	System.out.println("Your card is: "+full.drawCard(index-2));
	System.out.println("The computer's card is: "+full.drawCard(index-1)) ;
	
	if(full.drawCard(index-2).getValue()>full.drawCard(index-1).getValue()){
		System.out.println("Player goes first");
		playerFirst = true;}
	else {System.out.println("computer goes first");
			playerFirst = false;
	}
	
	
	full = new Deck();
	for(int i = 0;i<5;i++){
	hand1.add(full.getDeck().get(i));
	hand2.add(full.getDeck().get(i+5));}
	me.setHand(hand1); computer.setHand(hand2);
	// successfully sets hand1 and hand2 into the player class
	System.out.println("The computer's cards are: "+computer.getHand());

	index = 11;
	int oriIndex = 0;
	do{
		full.drawCard(index);
		index++;
		oriIndex = index-1;
		
	}
	while(full.drawCard(oriIndex).getTypeCard().equals("special")||full.drawCard(oriIndex).getTypeCard().equals("wild"));
	Card t = full.drawCard(index-1);
	System.out.println("The card on the table is " + t);
	discarded.add(t);
 
	Card match = discarded.get(discarded.size()-1); 
	
boolean didNot = true;
boolean skipUser = false;
boolean skipComp = false;
boolean computerWins = false;
boolean playerWins = false;

if(!playerFirst){
	while(didNot){
		if(computer.canMatch(match)){
			Card k = new Card("");
			for(int i = 0; i<computer.getHand().size();i++){
				 k = computer.getHand().get(i);
				if(computer.getHand().get(i).match(match)){
					discarded.add(computer.putDownCard(i)); 
					System.out.println("\nNow the computer plays..."+"\nComputer puts down "+ k);
					 
					if(k.getTypeCard().equals("wild")){
						match = discarded.get(discarded.size()-1); 
						int choose = (int)Math.random()*5+1;
						if(choose==1)match.setColor("red");
						else if(choose==2)match.setColor("blue");
						else if(choose==3)match.setColor("green");
						else match.setColor("yellow");
						
						System.out.println("The computer chooses: " + match.getColor());
						break;}

					
					if(k.getTypeCard().equals("special")){
						for(int m = 0; m<k.getValue();m++)
						me.getHand().add(full.drawCard(index++));
						System.out.println("Player draws card");
						skipUser = true; 
						break;	}
					
					else{
						
						break;}
					
					
					}	
			 
			}
				 
				}	
		
		else{
				computer.getHand().add(full.drawCard(index++));
				System.out.println("Computer drew a card.");
				break;
		
			}
		didNot = false;
		}
	System.out.println("The computer's cards are: "+computer.getHand());
	
}


boolean notPut = true;
	while(uno!=true){
		
		if(full.getDeck().size()<1||index>full.getDeck().size()-1){
			full.setDeck(discarded);
			full.shuffle();	
			index = 0;}
		
		match = discarded.get(discarded.size()-1); 
		System.out.println("\nYou currently have: " + me.getHand());
		
			
		
		
			if(skipUser==false){
				Scanner ne = new Scanner(System.in);
		System.out.println("Would you like to draw card or put down card: (enter 'd' for drawing or 'p' for put down)");
		String next = s.next();

		if(next.equals("d")){
			me.getHand().add(full.drawCard(index++)); 
			skipUser = true;
		}
 
		else if(next.equals("p")&& me.canMatch(match)){
			while(notPut){
			skipUser = true;
				      System.out.println("Choose a card position you would like to discard");
					  int n = s.nextInt();
				if(n<me.getHand().size()){
				  Card temp = me.getHand().get(n);
				  if(temp.match(match)){
					  
					  discarded.add(me.putDownCard(n)); 
					  notPut = false;
				
					  
					  if(temp.getTypeCard().equals("wild")){
						  Scanner m = new Scanner(System.in);
						  System.out.println("Please indicate a color of choice: ");
							String k = m.nextLine();
							discarded.get(discarded.size()-1).setColor(k);
							System.out.println("Computer must place a card of color: "+k);
							}
					  
					  else if(temp.getTypeCard().equals("special")){
							for(int i = 0; i<temp.getValue();i++){
							computer.getHand().add(full.drawCard(index++));
							}	
							System.out.println("Computer draws card");
							skipComp = true;
							
					  }
					  
					  else{
						  System.out.println("You currently have: "+ me.getHand());
						
					  }
				  }
				  
				  else System.out.print("Input mismatch: ");
				}
				else {System.out.print("Index Out of Bounds: ");{
					 
				}
				
				}
				  
		}
		}
		else{ 
			  System.out.println("Your input is not valid. default: draw card"); 
		  
			     me.getHand().add(full.drawCard(index++)); 
			 }
		
		notPut = true;
		System.out.println("The card on the table is currently: "+discarded.get(discarded.size()-1) );
		if(me.getHand().size()<3){
			System.out.println("You have two cards or less, would you like to call UNO!");
			String n = ne.nextLine();
			if(me.getHand().size()==0&&n.equalsIgnoreCase("UNO")){ 
					playerWins = true;
					uno = true;
					break;}	
			if(!n.equalsIgnoreCase("UNO")){
				System.out.println("You did not call UNO successfully, draw card.");
				me.getHand().add(full.drawCard(index++));
				me.getHand().add(full.drawCard(index++));} 
				}
			
			}

		skipUser = false;
		match = discarded.get(discarded.size()-1); 
		
		if(full.getDeck().size()<1||index>full.getDeck().size()-1){
			full.setDeck(discarded);
			full.shuffle();	
			index = 0;}
		
		//computer's turn 
		if(skipComp==false){
			skipComp = true;	
		if(computer.canMatch(match)){
			Card k = new Card("");
			for(int i = 0; i<computer.getHand().size();i++){
				 k = computer.getHand().get(i);
				if(computer.getHand().get(i).match(match)){
					discarded.add(computer.putDownCard(i)); 
					System.out.println("\nNow the computer plays..."+"\nComputer puts down "+ k);
					System.out.println("The computer's cards are: "+computer.getHand());
					 
					if(k.getTypeCard().equals("wild")){
						match = discarded.get(discarded.size()-1); 
						int choose = (int)Math.random()*5+1;
						if(choose==1)match.setColor("red");
						else if(choose==2)match.setColor("blue");
						else if(choose==3)match.setColor("green");
						else match.setColor("yellow");
						
						System.out.println("The computer chooses: " + match.getColor());
						break;}
				

					
					if(k.getTypeCard().equals("special")){
						for(int m = 0; m<k.getValue();m++)
						me.getHand().add(full.drawCard(index++));
						System.out.println("Player draws card");
						skipUser = true; 
						break;
							}
					
					
						break;

					}
				
			
			}
				 
				}	
		
		else{
				computer.getHand().add(full.drawCard(index++));
				System.out.println("----------------------------------------");
				System.out.println("                                        ");
				System.out.println("Computer drew a card.");
				System.out.println("                                        ");
				System.out.println("----------------------------------------"); 
				
		
			}

		if(computer.getHand().size()<3){
			System.out.println("----------------------------------------");
			System.out.println("                                        ");
			System.out.println("Watch out: computer calls UNO");
			System.out.println("                                        "); 
			System.out.println("----------------------------------------"); 
			if(computer.getHand().size()==0){ 
					computerWins = true;
					uno = true;
					break;}}
			}
		
		skipComp = false;
		

		
		
		
		
		
		
			
	
		
		
		
		
		
	//	System.out.println("computer has: "+computer.getHand().size());
		System.out.println("The card on the table is currently: "+discarded.get(discarded.size()-1) );
			
		}
	
	if(playerWins){
		System.out.println("----------------------------------------");
		System.out.println("                                        ");
		System.out.println("                                        ");
		System.out.println("                                        ");
		System.out.println("                                        ");
		System.out.println("congratulations!"+" "+me.getName()+" you won!");
		System.out.println("                                        ");
		System.out.println("                                        ");
		System.out.println("                                        ");
		System.out.println("                                        ");
		System.out.println("----------------------------------------");
		}
	else if(computerWins){
		System.out.println("----------------------------------------");
		System.out.println("                                        ");
		System.out.println("                                        ");
		System.out.println("                                        ");
		System.out.println("                                        ");
		System.out.println("I am sorry, you lost terribly :-(");
		System.out.println("                                        ");
		System.out.println("                                        ");
		System.out.println("                                        ");
		System.out.println("                                        ");
		System.out.println("----------------------------------------");
	}
	
		
		
		
	
	}
	}
	
	


