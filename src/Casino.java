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
			//Maak de twee kaarten aan per hand
			this.player.createHand();
			
			//Laat de speler spelen en update het board tussendoor
			while(this.blackjack.getPlayerTurns()){
				this.blackjack.drawBoard();
				this.blackjack.playerTurn();
			}
			
			//Laat de deler "spelen"
			this.blackjack.dealerTurn();
			
			//Laat het resultaat zien aan de speler
			this.blackjack.result();
				
			//Speel het spel opnieuw, dit kan alleen wanneer je nog kapitaal over hebt
			if(this.blackjack.playAgain() && this.player.getCapital() > 0){
				this.terminizeGame();
			}
			else if(this.player.getCapital() == 0){
				System.out.println("Je hebt geen kapitaal, dus je kunt niet opnieuw spelen!");
				//Stop het spel
				this.setGame(false);
			}
			else{
				System.out.println("Tot de volgende keer!");
				//Stop het spel
				this.setGame(false);
			}
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
		//Naam van de speler
		String name = this.player.getName();
		//Het aantal kapitaal dat over was na de ronde 
		double capital = this.player.getCapital();
		
		//Maak nieuwe instanties aan voor de klasses, zodat alles weer opnieuw aangemaakt wordt.
		this.dealer = new Dealer();
		this.player = new Player();
		this.blackjack = new Blackjack(dealer, player);
		
		//Zet de waardes terug
		this.player.setName(name);
		this.player.setCapital(capital);
	}

	private boolean getGame(){
		return this.game;
	}
	
	private void setGame(boolean game){
		this.game = game;
	}
}
