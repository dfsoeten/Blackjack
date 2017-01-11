import java.util.Map;
import java.util.Scanner;

public class Casino {
	
	Dealer dealer;
	Player player;
	Blackjack blackjack;
	
	private boolean game = true;
	
	//Start het spel
	public void start(){
		
		//Deler
		this.dealer = new Dealer();
		
		//Speler
		this.player = new Player();
		
		//Blackjack
		this.blackjack = new Blackjack(dealer, player);
		
		//De gameloop
		while(this.getGame()){
			//Haal de spelers naam op en vraag aan hem met hoeveel handen hij/zij wilt spelen
			this.initializeGame();
			//Maak de twee kaarten aan per hand voor de speler
			this.player.createHand();
			//Maak een kaart aan voor de deler
			this.dealer.createHand();
			
			//Laat de speler spelen en update het board tussendoor
			while(this.blackjack.getPlayerTurns()){
				this.blackjack.drawBoard();
				this.blackjack.playerTurn();
			}
			
			//Laat de deler "spelen"
			this.blackjack.dealerTurn();
			
			//Laat het resultaat zien aan de speler
			this.blackjack.result();
				
			
			this.terminizeGame();
			this.setGame(this.blackjack.playAgain());
		}
	}
	
	private void initializeGame(){
		//Instancier de Utils klasse, hierin staan validatiemethodes voor de invoer van de gebruiker
		Utilities utilities = new Utilities();
		
		//Maak een scanner zodat we gebruikersinvoer kunnen opvangen
		Scanner scanner = new Scanner(System.in);
		
		//Welkomst bericht
		System.out.println("Welkom in het iCasino! We gaan fijn Blackjack spelen!");
		System.out.println("");
		//Commando's die de speler kan geven
		System.out.println("Commando's");
		System.out.println("----------");
		System.out.println("p = passen");
		System.out.println("d = draaien");
		System.out.println("2 = inzet verdubbelen");
		System.out.println("");
		
		//Vraag naar de naam van de speler, deze mag niet leeg zijn
		if(this.player.getName().equals("")){
			while(true){
				System.out.println("Wat is je naam?");
				this.player.setName(scanner.nextLine());
				
				if(this.player.getName().equals("")){
					System.out.println("Dat begrijp ik niet");
				}
				else{
					break;
				}
			}
		}
		
		//Geef het kapitaal dat de speler heeft weer
		System.out.println("Welkom, " + this.player.getName() + ". Je startkapitaal is € " + (int)this.player.getCapital());
		//Vraag naar de hoeveelheid handen waar de speler mee wilt spelen
		this.player.setHands(utilities.validateHand());
		//Vraag naar de hoeveelheid geld die de speler per hand wilt inzetten
		this.player.setBet(utilities.valitadeBet(this.player.getCapital(), this.player.getHands()));
		System.out.println("");
	}
	
	private void terminizeGame(){
		this.blackjack.setPlayerTurns(true);
		this.dealer.getHand().setActive(true);
		this.dealer.getHand().setStatus(null);
		
		//Stop alle kaarten van de deler in de gebruikte kaarten stapel
		for(Map.Entry<Integer, Card> dealerCard : this.dealer.getHand().getCards().entrySet()){
			this.dealer.getCards().putDiscardedCard(dealerCard.getValue());
		}
		
		//Haal alle kaarten van de deler weg
		this.dealer.getHand().getCards().clear();
		
		
		//Stop alle kaarten van de speler in de gebruikte kaarten stapel
		for (Map.Entry<Integer, Hand> playerHand : this.player.getHand().entrySet()){
			for(Map.Entry<Integer, Card> playerCard : playerHand.getValue().getCards().entrySet()){
				this.player.getCards().putDiscardedCard(playerCard.getValue());
			}
			
			//Haal alle kaarten van de speler weg
			playerHand.getValue().getCards().clear();
		}
		
		//Haal alle handen van de speler weg
		this.player.getHand().clear();
		
		//Stop de gebruikte kaarten weer terug wanneer de originele map voor de helft leeg is
		if(this.player.getCards().getCards().size() < (310 / 2)){
			for(Card card : this.player.getCards().getDiscardedCards()){
				this.player.getCards().getCards().add(card);
			}
			this.player.getCards().getDiscardedCards().clear();
		}
	}

	private boolean getGame(){
		return this.game;
	}
	
	private void setGame(boolean game){
		this.game = game;
	}
}
