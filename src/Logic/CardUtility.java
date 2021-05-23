//This class serves 2 purpose. 1) Create a new deck for each level. 2) Pick a random card.
package Logic;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import Gui.Card;
import Gui.CardCell;
import javafx.application.Platform;

public class CardUtility {
	
	public static ArrayList<Card> getStartingDeck() {
		
		ArrayList<Card> deck = new ArrayList<Card>();
		switch(GameMonitor.getLevel()) {
		case 1:
			//1 * 2 * 2 * 3
			
			for(int i=1;i<2;i++) {
				for(int j=1;j<3;j++) {
					for(int k=1;k<3;k++) {
						for(int l=0;l<3;l++) {
							deck.add(new Card(String.valueOf(i)+ String.valueOf(j)+ String.valueOf(k)+ String.valueOf(l))); 
							
						}
					}
				}
			}break;

		case 2:
			
			//3 * 3 * 2 * 2
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					for(int k=1;k<3;k++) {
						for(int l=0;l<2;l++) {
							deck.add(new Card(String.valueOf(i)+ String.valueOf(j)+ String.valueOf(k)+ String.valueOf(l))); 
							
						}
					}
				}
			}break;
			
		case 3:
			
			//3 * 2 * 3 * 2
			for(int i=0;i<3;i++) {
				for(int j=1;j<3;j++) {
					for(int k=0;k<3;k++) {
						for(int l=1;l<3;l++) {
							deck.add(new Card(String.valueOf(i)+ String.valueOf(j)+ String.valueOf(k)+ String.valueOf(l))); 
							
						}
					}
				}
			}break;
			
		case 4:
			//3 * 3 * 3 * 3
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					for(int k=0;k<3;k++) {
						for(int l=0;l<3;l++) {
							deck.add(new Card(String.valueOf(i)+ String.valueOf(j)+ String.valueOf(k)+ String.valueOf(l))); 
							
						}
					}
				}
			}break;
		}
		
		return deck;
	}
		
	public static Card pickRandomCard() {
		
		int random = ThreadLocalRandom.current().
				nextInt(0, GameMonitor.getDeckPile().size());	

		
		
		Card randomCard = GameMonitor.getDeckPile().get(random);
		GameMonitor.removeCardFromDeck(random);


		
		return randomCard;
	}
	
	
	
	
	

	
	
}
