
public class Card {
	//De waarde van de kaart
	private int value;
	//De naam van de kaart
	private String name;
	
	public Card(int value, String name){
		this.value = value;
		this.name = name;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getName(){
		return this.name;
	}
}
