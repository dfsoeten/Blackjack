import java.util.LinkedHashMap;

public class Player extends Participant{
	
	private String name;
	private int capital = 1000;
	private int hands;
	private int bet;
	
	private LinkedHashMap<Integer, Hand> hand = new LinkedHashMap<Integer, Hand>();
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public int getCapital(){
		return this.capital;
	}
	
	public void setCapital(int startingCapital){
		this.capital = startingCapital;
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
	
	public void addCaptial(int add){
		this.capital += add;
	}
	
	public void createHand(){
		for(int i = 0; i < this.getHands(); i++){
			//Maakt de aangegeven hoeveelheid handen aan
			this.hand.put(i, new Hand(i + 1));
			//Voeg twee willekeurige kaarten toe aan de hand
			this.hand.get(i).addCard(1, this.getCards().getRandomCard());
			this.hand.get(i).addCard(2, this.getCards().getRandomCard());
			
			//Haal geld weg uit je startkapitaal en voeg het toe aan de hand
			this.substractCapital(this.getBet());
			this.hand.get(i).setBet(this.getBet());
		}
	}
}
