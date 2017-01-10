import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Blackjack {
	private Dealer dealer;
	private Player player;

	private boolean playerTurns = true;
	private boolean dealerTurns = true;


	public Blackjack(Dealer dealer, Player player) {
		this.dealer = dealer;
		this.player = player;
	}

	public void playerTurn() {
		for (Map.Entry<Integer, Hand> playerHands : this.player.getHand().entrySet()) {
			if (playerHands.getValue().getActive()) {
				this.choose(playerHands.getValue().getHandNumber(), playerHands.getValue());
				
				this.setPlayerTurns(true);
			}
			else{
				this.setPlayerTurns(false);
			}
		}
		
		if(this.getPlayerTurns())
			System.out.println("");
	}
	
	public void dealerTurn(){
		Utilities utilities = new Utilities();
		
		while(this.dealer.getHand().getActive()){
			Scanner scanner = new Scanner(System.in);
				
			//Laat de delerhand zien
			System.out.println("Deler: " + this.getDealerHand());
			//Druk op een toets om de deler te laten spelen
			System.out.println("Druk op return om de dealer te laten spelen...");
			//Voeg een willekeurige kaart aan de hand toe
			this.dealer.getHand().addCard((this.dealer.getHand().getCards().size() + 1), this.dealer.getCards().getRandomCard());
			//Update de status van de hand
			this.dealer.getHand().updateDealerStatus();
				
			scanner.nextLine();
		}
		//Laat dit zien als de delerhand dood is
		if(this.dealer.getHand().getStatus() != ""){
			System.out.println("Deler: " + this.getDealerHand() + "\t\t\t" + this.dealer.getHand().getStatus());
		}
		//Laat dit zien wanneer de hand tussen 17 en 21 ligt
		else{
			System.out.println("Deler: " + this.getDealerHand());
			System.out.println("De deler heeft gepast en is geëindigd met " + utilities.sumValues(this.dealer.getHand().getCards()) + " punten.");
		}
	}

	public void drawBoard() {
		System.out.println("***************************************************************");

		System.out.print("Deler: ");
		System.out.print(this.getDealerHand());
		System.out.println("");

		System.out.println("---------------------------------------------------------------");
		for (Map.Entry<Integer, Hand> playerHands : this.player.getHand().entrySet()) {
			System.out.format("%-40s %-11s %-10s%n",
					this.player.getName() + ", hand " + playerHands.getValue().getHandNumber() + ": " + this.getPlayerHand(playerHands.getKey()),
					"Inzet " + playerHands.getValue().getBet(), 
					playerHands.getValue().getStatus()
			);
		}
		System.out.println("***************************************************************");
		System.out.println("");
	}

	public String getPlayerHand(int handNumber) {
		String handLine = "";

		for (Map.Entry<Integer, Card> playerCards : this.player.getHand(handNumber).getCards().entrySet()) {
			handLine += playerCards.getValue().getName() + " ";
		}

		return handLine;
	}

	public String getDealerHand() {
		String handLine = "";

		for (Map.Entry<Integer, Card> playerCards : this.dealer.getHand().getCards().entrySet()) {
			handLine += playerCards.getValue().getName() + " ";
		}

		return handLine;
	}

	private void choose(int handNumber, Hand hand) {
		Scanner scanner = new Scanner(System.in);

		loop: while (true) {
			System.out.println(this.player.getName() + ", wat wil je doen met je hand " + handNumber + "?");

			String input = scanner.nextLine().toLowerCase();

			switch (input) {
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

	private void pass(Hand hand) {
		//Deactiveer de hand
		hand.setActive(false);
		//Geef de hand een status
		hand.setStatus("Gepast");
	}

	private void flip(Hand hand) {
		//Voeg een willekeurige kaart aan de hand toe
		hand.addCard((hand.getCards().size() + 1), this.player.getCards().getRandomCard());

		//Update de status van de hand
		hand.updatePlayerStatus();
		
		//Laat de nieuwe situatie zien
		System.out.format("%-40s %-11s %-10s%n",
				"Niewe situatie: " + this.getPlayerHand(hand.getHandNumber() - 1),
				"Inzet " + hand.getBet(), 
				hand.getStatus()
		);
	}

	private void doubleBet(Hand hand) {

		if (hand.getBet() > player.getCapital()) {
			System.out.println("Je hebt niet genoeg kapitaal!");
			this.choose(hand.getHandNumber(), hand);

		} else {
			//Verdubbel de inleg
			player.substractCapital(hand.getBet());
			hand.addBet(hand.getBet());

			//Voeg een willekeurige kaart aan de hand toe
			hand.addCard((hand.getCards().size() + 1), this.player.getCards().getRandomCard());

			//Update de status van de hand
			hand.updatePlayerStatus();

			//Deactiveer de hand
			hand.setActive(false);

			//Geef de hand een status
			hand.setStatus("Dubbel");
			
			//Laat de nieuwe situatie zien
			System.out.format("%-40s %-11s %-10s%n",
					"Niewe situatie: " + this.getPlayerHand(hand.getHandNumber() - 1),
					"Inzet " + hand.getBet(), 
					hand.getStatus()
			);
		}
	}
	
	public boolean getPlayerTurns(){
		return this.playerTurns;
	}
	
	public void setPlayerTurns(boolean playerTurns){
		this.playerTurns = playerTurns;
	}
	
	public boolean getDealerTurns(){
		return this.dealerTurns;
	}
	
	public void setDealerTurns(boolean dealerTurns){
		this.dealerTurns = dealerTurns;
	}
}
