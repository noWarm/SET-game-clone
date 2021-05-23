//This class represents the playing cards. It will be placed in to a CardCell.

package Gui;

public class Card {
	
	private String cardID;
	
//	cardID represents: shape, color, shading pattern, number
//	'9999' means an empty card
	
	public Card(String cardID) {
		this.cardID = cardID;
	}


	public String getCardID() {
		return cardID;
	}


	

}
