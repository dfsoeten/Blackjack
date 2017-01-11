import java.util.Map;
import java.util.Scanner;

public class Blackjack {
	private Dealer dealer;
	private Player player;

	private boolean playerTurns = true;
	
	public Blackjack(Dealer dealer, Player player) {
		this.dealer = dealer;
		this.player = player;
	}

	//Deze methode handeld de beurt van de speler af
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
	
	//Deze methode handeld de beurt van de deler af
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
			this.dealer.updateStatus();
				
			scanner.nextLine();
		}
		//Laat dit zien als de delerhand dood is
		if(this.dealer.getHand().getStatus() == Hand.statuses.Dood || this.dealer.getHand().getStatus() == Hand.statuses.BLACKJACK){
			System.out.println("Deler: " + this.getDealerHand() + "\t\t\t" + this.dealer.getHand().getStatus());
		}
		//Laat dit zien wanneer de hand tussen 17 en 21 ligt
		else{
			System.out.println("Deler: " + this.getDealerHand());
			System.out.println("De deler heeft gepast en is geëindigd met " + utilities.sumValues(this.dealer.getHand().getCards()) + " punten.");
		}
		System.out.println("");
	}
	
	//Deze methode geeft de resultaten weer
	public void result(){
		Utilities utilities = new Utilities();
		
		for (Map.Entry<Integer, Hand> playerHands : this.player.getHand().entrySet()){
			this.result(
					playerHands.getValue(),
					utilities.sumValues(this.dealer.getHand().getCards()), 
					utilities.sumValues(playerHands.getValue().getCards())
			);
			
			if(playerHands.getValue().getResult() == Hand.outcomes.WON)
				System.out.println(this.player.getName() + ", je wint hand " + (playerHands.getKey() + 1) + " met een inzet van " + playerHands.getValue().getBet() + ".");
			
			if(playerHands.getValue().getResult() == Hand.outcomes.DRAW)
				System.out.println(this.player.getName() + ", je hebt hand " + (playerHands.getKey() + 1) + " gelijk gespeeld met een inzet van " + playerHands.getValue().getBet() + ".");
			
			if(playerHands.getValue().getResult() == Hand.outcomes.LOST)
				System.out.println(this.player.getName() + ", je verliest hand " + (playerHands.getKey() + 1) + " met een inzet van " + playerHands.getValue().getBet() + ".");
			
			playerHands.getValue().setBet(0);
		}
		System.out.println("Je kapitaal is nu € " + (int)this.player.getCapital());
		System.out.println("");
	}
	
	//Deze methode zorgt ervoor dat je opnieuw kan spelen
	public boolean playAgain(){
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("Wil je nog een keer spelen? (j/n)?");

			String input = scanner.nextLine().toLowerCase();

			switch (input) {
			case "j":
				if(this.player.getCapital() > 0){
					return true;
				}
				else{
					System.out.println("Je hebt geen kapitaal, dus je kunt niet opnieuw spelen!");
					return false;
				}
			case "n":
				System.out.println("Tot de volgende keer!");
				return false;
			default:
				System.out.println("Dat begrijp ik niet.");
				break;
			}
		}
	}

	//Deze methode laat het speelveld zien
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
					(playerHands.getValue().getStatus() == null) ? "" : playerHands.getValue().getStatus()
			);
		}
		System.out.println("***************************************************************");
		System.out.println("");
	}

	//Deze methode maakt een string met alle kaarten in een bepaalde hand van de speler
	public String getPlayerHand(int handNumber) {
		String handLine = "";

		for (Map.Entry<Integer, Card> playerCards : this.player.getHand(handNumber).getCards().entrySet()) {
			handLine += playerCards.getValue().getName() + " ";
		}

		return handLine;
	}

	//Deze methode maakt een string met alle kaarten in de hand van de deler
	public String getDealerHand() {
		String handLine = "";

		for (Map.Entry<Integer, Card> playerCards : this.dealer.getHand().getCards().entrySet()) {
			handLine += playerCards.getValue().getName() + " ";
		}

		return handLine;
	}
	
	//Deze handeld de keuze van de speler af
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

	//Deze methode past de hand
	private void pass(Hand hand) {
		//Deactiveer de hand
		hand.setActive(false);
		//Geef de hand een status
		hand.setStatus(Hand.statuses.Gepast);
	}

	//Deze methode draait een nieuwe kaart
	private void flip(Hand hand) {
		//Voeg een willekeurige kaart aan de hand toe
		hand.addCard((hand.getCards().size() + 1), this.player.getCards().getRandomCard());

		//Update de status van de hand
		this.player.updateStatus(hand);
		
		//Laat de nieuwe situatie zien
		System.out.format("%-40s %-11s %-10s%n",
				"Niewe situatie: " + this.getPlayerHand(hand.getHandNumber() - 1),
				"Inzet " + hand.getBet(), 
				(hand.getStatus() == null) ? "" : hand.getStatus()
		);
	}

	//Deze methode verdubbeld je inzet
	private void doubleBet(Hand hand) {
		Utilities utilities = new Utilities();
		
		if (hand.getBet() > player.getCapital()) {
			System.out.println("Je hebt niet genoeg kapitaal!");
			this.choose(hand.getHandNumber(), hand);

		} else {
			//Verdubbel de inleg
			player.substractCapital(hand.getBet());
			hand.addBet(hand.getBet());

			//Voeg een willekeurige kaart aan de hand toe
			hand.addCard((hand.getCards().size() + 1), this.player.getCards().getRandomCard());

			//Deactiveer de hand
			hand.setActive(false);

			//Geef de hand een status
			hand.setStatus(Hand.statuses.Dubbel);
			
			//Update de status van de hand
			this.player.updateStatus(hand);
			
			//Laat de nieuwe situatie zien
			System.out.format("%-40s %-11s %-10s%n",
					"Niewe situatie: " + this.getPlayerHand(hand.getHandNumber() - 1),
					"Inzet " + hand.getBet(), 
					(hand.getStatus() == null) ? "" : hand.getStatus()
			);
		}
	}
	
	//Deze methode berekend het resultaat van de speler en deler
	private void result(Hand hand, int dealerTotal, int playerTotal){
		
		if(dealer.getHand().getStatus() == Hand.statuses.BLACKJACK && hand.getStatus() == Hand.statuses.BLACKJACK){
			hand.setResult(Hand.outcomes.DRAW);
			this.player.addCaptial(hand.getBet());
		}
		
		if(dealer.getHand().getStatus() == Hand.statuses.BLACKJACK && playerTotal != 21){
			hand.setResult(Hand.outcomes.LOST);
		}
		
		if(dealerTotal != 21 && hand.getStatus() == Hand.statuses.BLACKJACK){
			hand.setResult(Hand.outcomes.WON);
			this.player.addCaptial((hand.getBet() * 2.5));
		}
		
		if(playerTotal > dealerTotal && dealer.getHand().getStatus() != Hand.statuses.Dood && hand.getStatus() != Hand.statuses.Dood){
			hand.setResult(Hand.outcomes.WON);
			this.player.addCaptial((hand.getBet() * 2));
		}
		
		if(playerTotal == dealerTotal && dealer.getHand().getStatus() != Hand.statuses.Dood && hand.getStatus() != Hand.statuses.Dood){
			hand.setResult(Hand.outcomes.DRAW);
			this.player.addCaptial(hand.getBet());
		}
		
		if(playerTotal < dealerTotal && dealer.getHand().getStatus() != Hand.statuses.Dood && hand.getStatus() != Hand.statuses.Dood){
			hand.setResult(Hand.outcomes.LOST);
		}
		
		if(hand.getStatus() == Hand.statuses.Dood){
			hand.setResult(Hand.outcomes.LOST);
		}
		
		if(dealer.getHand().getStatus() == Hand.statuses.Dood && playerTotal < 21){
			hand.setResult(Hand.outcomes.WON);
			this.player.addCaptial((hand.getBet() * 2));
		}	
	}
	
	public boolean getPlayerTurns(){
		return this.playerTurns;
	}
	
	public void setPlayerTurns(boolean playerTurns){
		this.playerTurns = playerTurns;
	}
}
