import java.util.Map;

public class Blackjack {
	private Dealer dealer;
	private Player player;
	
	public Blackjack(Dealer dealer, Player player){
		this.dealer = dealer;
		this.player = player;
	}
	
	public void drawBoard(){
		System.out.println("***************************************************************");
		System.out.print("Deler: ");
		for (Map.Entry<Integer, Card> dealerCards : this.dealer.getHand().getCards().entrySet()){
			System.out.print(dealerCards.getValue().getName() + " ");
		}
		System.out.println("");
		System.out.println("---------------------------------------------------------------");
		for (Map.Entry<Integer, Hand> playerHands : this.player.getHand().entrySet()){
			System.out.print(this.player.getName() + ", hand " + playerHands.getValue().getHandNumber() + ": ");
			for (Map.Entry<Integer, Card> playerCards : playerHands.getValue().getCards().entrySet()){
				System.out.print(playerCards.getValue().getName() + " ");
				
			}
			System.out.print("                       Inzet = " + player.getBet());
			System.out.println("");
		}
		System.out.println("***************************************************************");
		
		
		
	}
}
