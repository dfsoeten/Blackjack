import java.util.LinkedHashMap;

public class Hand {
	private int handNumber;
	
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
	
	
}
