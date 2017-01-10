import java.util.LinkedHashMap;

public class Hand {
	private int handNumber;
	private int bet;
	private boolean active = true;
	private String status = "";
	
	private LinkedHashMap<Integer, Card> cards = new LinkedHashMap<Integer, Card>();
	
	public Hand(int handNumber) {
		this.handNumber = handNumber;
	}

	public int getHandNumber(){
		return this.handNumber;
	}
	
	public void addCard(int key, Card card){
		this.cards.put(key, card);
	}
	
	public LinkedHashMap<Integer, Card> getCards(){
		return this.cards;
	}
	
	public Card getCards(int key){
		return this.cards.get(key);
	}
	
	public int getBet(){
		return this.bet;
	}
	
	public void setBet(int bet){
		this.bet = bet;
	}
	
	public void substractBet(int substraction){
		this.bet -= substraction;
	}
	
	public void addBet(int add){
		this.bet += add;
	}
	
	public boolean getActive(){
		return this.active;
	}
	
	public void setActive(boolean active){
		this.active = active;
	}
	
	public String getStatus(){
		return this.status;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
}
