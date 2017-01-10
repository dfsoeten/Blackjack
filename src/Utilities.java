import java.util.Scanner;

public class Utilities {

	//Valideer de invoer voor de hoeveelheid handen waarmee gespeeld word
	public int validateHand(){
		Scanner scanner = new Scanner(System.in);
		
		while(true){
			System.out.println("Met hoeveel handen wil je spelen (1-5)?");
		
			if(!scanner.hasNextInt()){
				System.out.println("Dat begrijp ik niet.");
				scanner.next();
			}
			else{
				int hand = scanner.nextInt();
				
				if(hand <= 0 || hand > 5){
					System.out.println("Dat aantal ligt niet tussen de 1 en 5.");
				}else{
					//scanner.close();
					return hand;
				}
			}
		}
	}
	
	//Valideer de invoer voor de inzet
	public int valitadeBet(int capital, int hands){
		Scanner scanner = new Scanner(System.in);
				
		while(true){
			System.out.println("Met welke inzet wil je spelen (1-" + (capital / hands) + ")?");
		
			if(!scanner.hasNextInt()){
				System.out.println("Dat begrijp ik niet.");
				scanner.next();
			}
			else{
				int bet = scanner.nextInt();
				
				if(bet < 1 || bet > (capital / hands)){
					System.out.println("Dat aantal ligt niet tussen de 1 en " + (capital / hands) + ".");
				}else{
					return bet;
				}
			}
		}
	}
	
}
