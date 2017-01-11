
public class Dealer extends Participant{
	
	private Hand dealerHand = new Hand(1);
	
	
	public Dealer(){
		this.dealerHand.addCard(1, this.getCards().getRandomCard());
		
		this.updateStatus();
		
	}
	
	public Hand getHand(){
		return this.dealerHand;
	}
	
	//Deze methode update de hand van de dealer
	public void updateStatus(){
		Utilities utilities = new Utilities();
		
		if(utilities.sumValues(this.dealerHand.getCards()) == 21){
			this.dealerHand.setActive(false);
			this.dealerHand.setStatus(Hand.statuses.BLACKJACK);
		}
		else if(utilities.sumValues(this.dealerHand.getCards()) > 17 && utilities.sumValues(this.dealerHand.getCards()) < 21){
			this.dealerHand.setActive(false);
			this.dealerHand.setStatus(null);
		}
		else if(utilities.sumValues(this.dealerHand.getCards()) > 21){
			this.dealerHand.setActive(false);
			this.dealerHand.setStatus(Hand.statuses.Dood);
		}
	}
}
