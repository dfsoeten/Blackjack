
public class Card {
	//De waarde van de kaart
	private int value;
	//De naam van de kaart
	private String name;
	
	public Card(int value, String name){
		this.setValue(value);
		this.setName(name);
	}
	
	public int getValue(){
		return this.value;
	}
	
	public void setValue(int value){
		this.value = value;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
}
