//This class keeps track of the states of the game such as remaining cards in the deck, the deck on the board, and the score.
//It also updates the programs every time a user pick a card, found a correct set, and control the shuffling action.


package Logic;

import java.util.ArrayList;

import Application.Main;
import Gui.Card;
import Gui.CardBoardPane;
import Gui.CardCell;
import Gui.MenuPane;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

public class GameMonitor {
	
	public static ArrayList<Card> deckPile;
	public static ArrayList<Card> discardPile;
	
	public static ArrayList<CardCell> playingCardList;
	public static ArrayList<CardCell> selectedCardList;
	
	public static CardBoardPane cardBoardPane;
	public static MenuPane menuPane;
	
	public static int level = 1;
	public static int score = 0;
	


	public static void startGame(int currentlevel) {
		
//		System.out.println("BeginLevel " +  String.valueOf(currentlevel));
		
		
		if(deckPile == null) deckPile = new ArrayList<Card>();
		
		if(discardPile == null) {
			discardPile = new ArrayList<Card>();
		}else {
			discardPile.clear();
		}
		
		if(playingCardList == null) {
			playingCardList = new ArrayList<CardCell>();
			
		}else {
			playingCardList.clear();
		}
		
		if(selectedCardList == null) {
			selectedCardList = new ArrayList<CardCell>();
		}else {
			selectedCardList.clear();
		}
		
		

		deckPile = CardUtility.getStartingDeck();
		score = 0;
		

		for(int i=0;i<12;i++) {
			
			playingCardList.add(new CardCell(CardUtility.pickRandomCard(), i));
			
		}
		
		if(menuPane == null) {

			menuPane = new MenuPane();
			
		}
		
		
		if(cardBoardPane == null) {

			cardBoardPane = new CardBoardPane();
		}
		else {
			cardBoardPane = new CardBoardPane();
			Main.root2.getChildren().clear();
			Main.root2.getChildren().addAll(menuPane, cardBoardPane);
			Main.window.setScene(Main.boardScene);
		}

		
		menuPane.setScoreLabelText();
		menuPane.setLevelLabelText();

		
	}
	
	public static void removeCardFromDeck(int index) {
		deckPile.remove(index);
	}
	
	public static void removeCardFromSelectedList(CardCell cardCell) {
		for(CardCell x: selectedCardList) {
			if(x.getCard().getCardID() == cardCell.getCard().getCardID()) {
				x.unhighlight();
				selectedCardList.remove(x);
				break;
				
			}
		}
	}
	
	public static void clearSelectedList() {
		for(int i=0;i<selectedCardList.size();i++) {
			selectedCardList.get(i).unhighlight();
		}
		selectedCardList.clear();
	}
	
	public static void updateCardStatus(CardCell cardCell) {
		
		if (cardExist(cardCell)) {
			removeCardFromSelectedList(cardCell);
			SoundUtility.playMusic(SoundUtility.unselectCardSound);
		}
		
		else {
			SoundUtility.playMusic(SoundUtility.selectCardSound);
			selectedCardList.add(cardCell);
			cardCell.highlight();
			if(selectedCardList.size() == 3) {
				if(isProperSet()) {
					correctSetHandler();
					
				}
				else {
					wrongSetHandler();
					
				}
				
				
			}
		}
	}
	
	public static void correctSetHandler() {
		
		SoundUtility.playMusic(SoundUtility.correctSetSound);
		
		Thread thread = new Thread(() -> {
			try {
				for(int i=0;i<12;i++) {
					if(playingCardList.get(i).getCard().getCardID()!="9999")
						playingCardList.get(i).removeEventHandler(MouseEvent.MOUSE_CLICKED, playingCardList.get(i).getCardHandler());
				}
				Thread.sleep(500);
				score += 1;
				
				for(int i=0;i<12;i++) {
					if(playingCardList.get(i).getCard().getCardID()!="9999")
						playingCardList.get(i).addEventHandler(MouseEvent.MOUSE_CLICKED, playingCardList.get(i).getCardHandler());
				}
				
				
				
				
				
//				Remove card from the playingList Put those cards into the discardPile
				for(CardCell x: selectedCardList) {
					
					if(GameMonitor.getDeckPile().size() > 0) {
						playingCardList.get(x.getPosition()).changeCard(CardUtility.pickRandomCard());			
					}
					
					else {
						playingCardList.get(x.getPosition()).changeCard();			
					}
					
					discardPile.add(x.getCard());		
				}
			
				

				
			
				
				Platform.runLater(new Runnable() {
					@Override
					public void run() {		
						menuPane.setScoreLabelText();	
						if(score == 4) {
							level++;
							
							menuPane.setLevelLabelText();
//							Show Congrats Message, going to the next level scene
							
							for(int i=0;i<12;i++) {
								CardCell cell = playingCardList.get(i);
								cell.getGc().clearRect(0, 0, cell.getCanvas().getWidth(), cell.getCanvas().getHeight());
							}
							
							if(level < 5) {

								Main.window.setScene(Main.continueSceneSetUp());
							}
							else {
								Main.window.setScene(Main.congratsSceneSetUp());
								
							}
						}
					}
				});
				
				selectedCardList.clear();
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		thread.start();
	}
	
	public static void wrongSetHandler() {
		
		SoundUtility.playMusic(SoundUtility.illegalSetSound);
		Thread thread = new Thread(() -> {
			try {
				
				
				for(int i=0;i<12;i++) {
					if(playingCardList.get(i).getCard().getCardID()!="9999")
					playingCardList.get(i).removeEventHandler(MouseEvent.MOUSE_CLICKED, playingCardList.get(i).getCardHandler());
				}
				Thread.sleep(300);
				
				for(int i=0;i<12;i++) {
					if(playingCardList.get(i).getCard().getCardID()!="9999")
					playingCardList.get(i).addEventHandler(MouseEvent.MOUSE_CLICKED, playingCardList.get(i).getCardHandler());
				}
				
				for(CardCell x: selectedCardList) {
					x.unhighlight();
				}
				selectedCardList.clear();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		thread.start();
		
		
	}
	
	public static boolean cardExist(CardCell cardCell) {
		boolean exist = false;
		for(CardCell x: selectedCardList) {
			if (x.getCard().getCardID() == cardCell.getCard().getCardID())
				return true;
		}
		return false;
	}
	
	public static boolean isProperSet() {
		
		//Initialize the list of 3 cards to be checked
		String[] idList = new String[3];
		for(int i=0;i<3;i++) {
			idList[i] = selectedCardList.get(i).getCard().getCardID();
		}
		
		//Initialize the bucket for checking 
		int[][] bucket = new int[4][3];
		for(int i=0;i<4;i++) {
			for(int j=0;j<3;j++) {
				bucket[i][j] = 0;
			}
		}
		
		//Populate the bucket according to the cards
		for(int i=0;i<4;i++) {
			for(int j=0;j<3;j++) {
				bucket[i][Character.getNumericValue(idList[j].charAt(i))]++;
			}
		}
		
		//Check if there are (2,1,0)
		for(int i=0;i<4;i++) {
			for(int j=0;j<3;j++) {
				if(bucket[i][j]==2) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public static void shuffle() {
		
		SoundUtility.playMusic(SoundUtility.shuffleSound);
		
		Thread thread = new Thread(() -> {
			try {
				menuPane.shuffleButton.setDisable(true);
				for(int i=0;i<12;i++) {
					if(playingCardList.get(i).getCard().getCardID()!="9999")
					playingCardList.get(i).removeEventHandler(MouseEvent.MOUSE_CLICKED, playingCardList.get(i).getCardHandler());
				}
				Thread.sleep(500);
				menuPane.shuffleButton.setDisable(false);
				
				
				Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
						
						clearSelectedList();
						for(CardCell x: playingCardList) deckPile.add(x.getCard());
						for(int i=0;i<12;i++) playingCardList.get(i).changeCard(CardUtility.pickRandomCard());
						
						for(int i=0;i<12;i++) {
//							System.out.println(playingCardList.get(i).getCard().getCardID());
							if(playingCardList.get(i).getCard().getCardID()!="9999")
								playingCardList.get(i).addEventHandler(MouseEvent.MOUSE_CLICKED, playingCardList.get(i).getCardHandler());
						}
						
						
					}
				});
				
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		});
		thread.start();
		
	}
	
	public static ArrayList<Card> getDeckPile() {
		return deckPile;
	}


	public static void setDeckPile(ArrayList<Card> deckPile) {
		GameMonitor.deckPile = deckPile;
	}


	public static ArrayList<Card> getDiscardPile() {
		return discardPile;
	}


	public static void setDiscardPile(ArrayList<Card> discardPile) {
		GameMonitor.discardPile = discardPile;
	}


	public static ArrayList<CardCell> getPlayingCardList() {
		return playingCardList;
	}


	public static void setPlayingCardList(ArrayList<CardCell> playingCardList) {
		GameMonitor.playingCardList = playingCardList;
	}

	public static ArrayList<CardCell> getSelectedCardList() {
		return selectedCardList;
	}


	public static void setSelectedCardList(ArrayList<CardCell> selectedCardList) {
		GameMonitor.selectedCardList = selectedCardList;
	}


	public static void setScore(int score) {
		GameMonitor.score = score;
	}

	public static int getScore() {
		return score;
	}

	public static CardBoardPane getCardBoardPane() {
		return cardBoardPane;
	}


	public static MenuPane getMenuPane() {
		return menuPane;
	}
	
	public static int getLevel() {
		return level;
	}
	
	public static void setLevel(int level) {
		GameMonitor.level = level;
	}
	
	
}
