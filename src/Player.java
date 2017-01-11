import java.util.LinkedHashMap;

public class Player extends Participant{
	
	private String name = "";
	private double capital = 1000;
	private int hands;
	private int bet;
	
	private LinkedHashMap<Integer, Hand> hand = new LinkedHashMap<Integer, Hand>();
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public double getCapital(){
		return this.capital;
	}
	
	public void setCapital(double capital){
		this.capital = capital;
	}
	
	public int getHands(){
		return this.hands;
	}
	
	public void setHands(int hands){
		this.hands = hands;
	}
	
	public int getBet(){
		return this.bet;
	}
	
	public void setBet(int bet){
		this.bet = bet;
	}
	
	public LinkedHashMap<Integer, Hand> getHand(){
		return this.hand;
	}
	
	public Hand getHand(int handNumber){
		return this.hand.get(handNumber);
	}
	
	public void substractCapital(int substraction){
		this.capital -= substraction;
	}
	
	public void addCaptial(double add){
		this.capital += add;
	}
	
	//Deze methode stopt twee willekeurige kaarten in elke hand van de speler
	public void createHand(){
		for(int i = 0; i < this.getHands(); i++){
			//Maakt de aangegeven hoeveelheid handen aan
			this.hand.put(i, new Hand(i + 1));
			//Voeg twee willekeurige kaarten toe aan de hand
			this.hand.get(i).addCard(1, this.getCards().getRandomCard());
			this.hand.get(i).addCard(2, this.getCards().getRandomCard());
			
			//Update de status van de hand
			this.updateStatus(i);
			
			//Haal geld weg uit je startkapitaal en voeg het toe aan de hand
			this.substractCapital(this.getBet());
			this.hand.get(i).setBet(this.getBet());
		}
	}
	
	//Deze methode update de hand van de player
	public void updateStatus(int handNumber){
		Utilities utilities = new Utilities();
		
		if(utilities.sumValues(this.hand.get(handNumber).getCards()) == 21){
			this.hand.get(handNumber).setActive(false);
			this.hand.get(handNumber).setStatus(Hand.statuses.BLACKJACK);
		}
		else if(utilities.sumValues(this.hand.get(handNumber).getCards()) > 21){
			this.hand.get(handNumber).setActive(false);
			this.hand.get(handNumber).setStatus(Hand.statuses.Dood);
		}
	}
	
	//Deze methode update de hand van de player
	public void updateStatus(Hand hand){
		Utilities utilities = new Utilities();
		
		if(utilities.sumValues(hand.getCards()) == 21){
			hand.setActive(false);
			hand.setStatus(Hand.statuses.BLACKJACK);
		}
		else if(utilities.sumValues(hand.getCards()) > 21){
			hand.setActive(false);
			hand.setStatus(Hand.statuses.Dood);
		}
	}
}
