
public class Player extends Participant{
	private String name;
	private int capital = 1000;
	private int hands;
	private int bet;
	
	
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
}
