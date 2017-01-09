public class Dealer extends Participant{
	
	private Hand dealerHand = new Hand(1);
	
	
	public Dealer(){
		this.dealerHand.addCard(1, this.getCards().getRandomCard());
	}
	
	public Hand getHand(){
		return this.dealerHand;
	}
}
