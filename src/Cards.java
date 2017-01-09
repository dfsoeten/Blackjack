import java.util.LinkedHashMap;

public class Cards {
	//kaartnamen en hun waardes
	private String[][] cardnames = {
			{"11", "KA"}, {"2", "K2"}, {"3", "K3"}, {"4", "K4"}, {"5", "K5"}, {"6", "K6"}, {"7", "K7"}, {"8", "K8"}, {"9", "K9"}, {"10", "K10"}, {"10", "KJ"}, {"10", "KQ"}, {"10", "KK"},
			{"11", "SA"}, {"2", "S2"}, {"3", "S3"}, {"4", "S4"}, {"5", "S5"}, {"6", "S6"}, {"7", "S7"}, {"8", "S8"}, {"9", "S9"}, {"10", "S10"}, {"10", "SJ"}, {"10", "SQ"}, {"10", "SK"},
			{"11", "HA"}, {"2", "H2"}, {"3", "H3"}, {"4", "H4"}, {"5", "H5"}, {"6", "H6"}, {"7", "H7"}, {"8", "H8"}, {"9", "H9"}, {"10", "H10"}, {"10", "HJ"}, {"10", "HQ"}, {"10", "HK"},
			{"11", "RA"}, {"2", "R2"}, {"3", "R3"}, {"4", "R4"}, {"5", "R5"}, {"6", "R6"}, {"7", "R7"}, {"8", "R8"}, {"9", "R9"}, {"10", "R10"}, {"10", "RJ"}, {"10", "RQ"}, {"10", "RK"}
			
	};
	
	//Hierin worden alle kaartobjecten in opgeslagen
	private LinkedHashMap<Integer, Card> cards = new LinkedHashMap<Integer, Card>();
	
	public Cards(){
		int counter = 0;
		
		for(int i = 0; i < 6; i++){
			for(String[] card : this.cardnames){
				this.cards.put(counter, new Card(Integer.parseInt(card[0]), card[1]));
				counter++;
			}
		}
	}
}
