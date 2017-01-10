import java.util.Map;
import java.util.Scanner;

public class Blackjack {
	private Dealer dealer;
	private Player player;
	
	public Blackjack(Dealer dealer, Player player){
		this.dealer = dealer;
		this.player = player;
	}
	
	public void playerTurn(){
		for (Map.Entry<Integer, Hand> playerHands : this.player.getHand().entrySet()){	
			if(playerHands.getValue().getActive()){
				this.choose(playerHands.getValue().getHandNumber(), playerHands.getValue());
				System.out.println("");
			}
		}
	}
	
	public void drawBoard(){
		System.out.println("***************************************************************");
		
		System.out.print("Deler: ");
		System.out.print(this.getDealerHand());
		System.out.println("");
		
		System.out.println("---------------------------------------------------------------");
		for (Map.Entry<Integer, Hand> playerHands : this.player.getHand().entrySet()){
			System.out.format(
					"%-40s %-11s %-10s%n", 
					this.player.getName() + ", hand " + playerHands.getValue().getHandNumber() + ": " + this.getPlayerHand(playerHands.getKey()),
					"Inzet " + playerHands.getValue().getBet(),
					playerHands.getValue().getStatus()
			);
		}
		System.out.println("***************************************************************");
		System.out.println("");
	}
	
	public String getPlayerHand(int handNumber){
		String handLine = "";
		
		for (Map.Entry<Integer, Card> playerCards : this.player.getHand(handNumber).getCards().entrySet()){
			handLine += playerCards.getValue().getName() + " ";
		}  
		
		return handLine;
	}
	
	public String getDealerHand(){
		String handLine = "";
		
		for (Map.Entry<Integer, Card> playerCards : this.dealer.getHand().getCards().entrySet()){
			handLine += playerCards.getValue().getName() + " ";
		}

		return handLine;
	}
	
	private void choose(int handNumber, Hand hand){
		Scanner scanner = new Scanner(System.in);
		
		loop: while(true){
			System.out.println(this.player.getName() + ", wat wil je doen met je hand " + handNumber + "?");
		
			String input = scanner.nextLine().toLowerCase();
			
			switch(input){
				case "p":
					this.pass(hand);
					break loop;
				case "d":
					this.flip(hand);
					break loop;
				case "2":
					this.doubleBet(hand);
					break loop;
				default: 
					System.out.println("Dat begrijp ik niet.");
					break;
			}
		}
	}
	
	private void pass(Hand hand){
		//Deactiveer de hand
		hand.setActive(false);
		//Geef de hand een status
		hand.setStatus("Gepast");
	}
	
	private void flip(Hand hand){
		//Voeg een willekeurige kaart aan de hand toe
		hand.addCard((hand.getCards().size() + 1), this.player.getCards().getRandomCard());
	}
	
	private void doubleBet(Hand hand){
	
		if(hand.getBet() > player.getCapital()){
			System.out.println("Je hebt niet genoeg kapitaal!");
			this.choose(hand.getHandNumber(), hand);
			
		}
		else{
			//Verdubbel de inleg
			player.substractCapital(hand.getBet());
			hand.addBet(hand.getBet());
			
			//Voeg een willekeurige kaart aan de hand toe
			hand.addCard((hand.getCards().size() + 1), this.player.getCards().getRandomCard());
			
			//Deactiveer de hand
			hand.setActive(false);
			
			//Geef de hand een status
			hand.setStatus("Dubbel");
		}
	}
}
