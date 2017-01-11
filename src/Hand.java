import java.util.LinkedHashMap;
import java.util.Map;

public class Hand {
	public enum statuses{
		Gepast,
		Dubbel,
		Dood,
		BLACKJACK
	}
	
	public enum outcomes{
		WON,
		LOST,
		DRAW
	}
	
	private int handNumber;
	private int bet;
	private boolean active = true;
	
	private statuses status = null;

	private outcomes result;
	
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
	
	public statuses getStatus(){
		return this.status;
	}
	
	public void setStatus(statuses status){
		this.status = status;
	}
	
	public outcomes getResult(){
		return this.result;
	}
	
	public void setResult(outcomes result){
		this.result = result;
	}
}
