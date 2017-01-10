import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;

public class Cards {	
	//Hierin worden alle kaartobjecten in opgeslagen
	//private LinkedHashMap<Integer, Card> cards = new LinkedHashMap<Integer, Card>();
	
	ArrayList<Card> cards = new ArrayList<Card>(); //310
	
	public Cards(){
		
		String suits = "♠♥♦♣";
	    String cardLetter[] = new String[] {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
	    int[] values = new int[] {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
	    
	    
	    for(int i = 0; i < 6; i++){
			for(int s = 0; s < suits.length(); s++){
				for(int cl = 0; cl < cardLetter.length; cl++){

					this.cards.add(new Card(values[cl], suits.charAt(s) + cardLetter[cl]));
					
				}
			}
	    }
	}
	
	//Haal alle kaarten op
	public ArrayList<Card> getCards(){
		return this.cards;
	}
	
	//Haal een kaart op
	public Card getCards(int index){
		return this.cards.get(index);
	}
	
	//Haal er willekeurig een kaart uit
	public Card getRandomCard(){
		//Haal een willekeurige kaart op
		Random rand = new Random();
		int RandomCard = rand.nextInt(this.cards.size());
		
		//Sla de kaart op in zijn eigen instantie
		Card card = this.getCards(RandomCard);
		this.cards.remove(RandomCard);
		
		return card;
	}
}
