import java.util.Scanner;

public class Casino {
	
	private boolean game = true;
	
	//Start het spel
	public void start(){
		
		while(this.getGame()){
			//Deler
			Dealer dealer = new Dealer();
			
			//Speler
			Player player = new Player();
			
			//Blackjack
			Blackjack blackjack = new Blackjack(dealer, player);
			
			this.initializeGame(player);
			player.createHand();
			
			while(blackjack.getPlayerTurns()){
				blackjack.drawBoard();
				blackjack.playerTurn();
			}
			
			Dealer: while(true){
				blackjack.dealerTurn();
				
				break Dealer;
			}
			
			
			this.setGame(false);
		}
	}
	
	private void initializeGame(Player player){
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
		//Vraag naar de naam van de speler
		System.out.println("Wat is je naam?");
		player.setName(scanner.nextLine());
		//Geef het kapitaal dat de speler heeft weer
		System.out.println("Welkom, " + player.getName() + ". Je startkapitaal is € " + player.getCapital());
		//Vraag naar de hoeveelheid handen waar de speler mee wilt spelen
		player.setHands(utilities.validateHand());
		//Vraag naar de hoeveelheid geld die de speler per hand wilt inzetten
		player.setBet(utilities.valitadeBet(player.getCapital(), player.getHands()));
		System.out.println("");
	}

	private boolean getGame(){
		return this.game;
	}
	
	private void setGame(boolean game){
		this.game = game;
	}
}
