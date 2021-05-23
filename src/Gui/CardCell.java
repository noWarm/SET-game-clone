//This CarCell class is essentially a card holder for each cards to be placed onto the CardBoardPane. 
//It also handles the graphics and the event listeners of each cards.

package Gui;

import Logic.GameMonitor;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class CardCell extends Pane {
	private Card card;
	private int position; //position on the board from 0 -11

//	private Label idLabel;	
	private Canvas canvas;
	private GraphicsContext gc;
	
	private int width = 180;
	private int height = 120;
	private int cornerRadii = 8;
	
	private Color unhighlightColor = Color.WHITE;
	private Color highlightColor = Color.GREY;
	
	private String shape;
	private Color color;
	private String pattern;
	private int number;
	
	private EventHandler cardHandler = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent e) {
			onClickHandler();
		}
	};

	public CardCell(Card card,int position) {
		
		this.card = card;
		
		
		this.setPrefWidth(width);
		this.setPrefHeight(height);
		
		this.position = position;
		
//		idLabel = new Label();
//		idLabel.textProperty().setValue(card.getCardID());
//		this.getChildren().add(idLabel);
		
		canvas = new Canvas(width,height);
		gc = canvas.getGraphicsContext2D();
		this.getChildren().add(canvas);
		
		if(card.getCardID().equals("9999")) {
			this.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(cornerRadii), Insets.EMPTY)));
		}
		else {
			stripInfo(card);
			draw(gc);
			
			this.setBackground(new Background(new BackgroundFill(unhighlightColor, new CornerRadii(cornerRadii), Insets.EMPTY)));				
			this.addEventHandler(MouseEvent.MOUSE_CLICKED, cardHandler);
		}
	}
	
	public void stripInfo(Card card) {
		this.shape = card.getCardID().charAt(0) == '0' ? "roundRect" : (card.getCardID().charAt(0) == '1' ? "diamond" : "jelly");
		this.color = card.getCardID().charAt(1) == '0' ? Color.RED : (card.getCardID().charAt(1) == '1' ? Color.GREEN  : Color.BLUE );
		this.pattern = card.getCardID().charAt(2) == '0' ? "blank" : (card.getCardID().charAt(2) == '1' ? "stripe"  : "solid" );
		this.number = card.getCardID().charAt(3) == '0' ? 1 : (card.getCardID().charAt(3) == '1' ? 2  : 3 );
	}
	
//	This method changes the CardCell into another card from the deck pile.
	public void changeCard(Card card) {
		this.card = card;
		stripInfo(card);
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		this.setBackground(new Background(new BackgroundFill(card.getCardID() == "9999" ? Color.BLACK : unhighlightColor, new CornerRadii(cornerRadii), Insets.EMPTY)));
		draw(gc);
	}
	
	
//	This method changes the CardCell into an empty card when a correct set is found and there's no card left in the deck
	public void changeCard() {
		this.card = new Card("9999");
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		this.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(cornerRadii), Insets.EMPTY)));
		this.removeEventHandler(MouseEvent.MOUSE_CLICKED, cardHandler);
	}
	
	public void draw(GraphicsContext gc) {
		
		switch(shape) {
			case "roundRect": drawRoundRect(gc); break; //use javafx to draw with strokeRoundRect
			default: drawDiamondOrJelly(gc); break; //use javafx to display images premade from external image software.
		}
		
	}
	

	public void drawRoundRect(GraphicsContext gc) {
		
		int sizex = 30;
		int sizey = 70;
		int arcx = 40;
		int arcy = 50;
		int gap = 20;
		int linesize = 3;
		
		gc.setFill(color);
		
		
		switch(pattern) {
//		BlankCase ============================================================================	
			case "blank":
				
				gc.setLineWidth(linesize);
				gc.setStroke(color);
				
				
				
				switch(number) {
					case 1: 
						gc.strokeRoundRect(width/2 - sizex/2, height/2 - sizey/2, sizex, sizey,arcx,arcy);
						break;
					case 2:
						gc.strokeRoundRect(width/2 - sizex/2 - gap, height/2 - sizey/2, sizex, sizey,arcx,arcy);		
						gc.strokeRoundRect(width/2 - sizex/2 + gap, height/2 - sizey/2, sizex, sizey,arcx,arcy);
						break;
					case 3:
						gc.strokeRoundRect(width/2 - sizex/2, height/2 - sizey/2, sizex, sizey,arcx,arcy);
						gc.strokeRoundRect(width/2 - sizex/2 - gap*2, height/2 - sizey/2, sizex, sizey,arcx,arcy);		
						gc.strokeRoundRect(width/2 - sizex/2 + gap*2, height/2 - sizey/2, sizex, sizey,arcx,arcy);
						break;
					
				}
				break;
				
				
//			StripeCase ============================================================================	
			case "stripe":
				
				String imagePath = "file:res/stripeImages/" + card.getCardID().charAt(0) + card.getCardID().charAt(1) +".png";
				gc.setLineWidth(linesize);
				gc.setStroke(color);
				
				
				switch(number) {
					case 1: 
						
						
						gc.strokeRoundRect(width/2 - sizex/2, height/2 - sizey/2, sizex, sizey,arcx,arcy);
						gc.drawImage(new Image(imagePath), width/2 - 24, height/2 - 40);
						break;
					case 2:
						gc.strokeRoundRect(width/2 - sizex/2 - gap, height/2 - sizey/2, sizex, sizey,arcx,arcy);		
						gc.strokeRoundRect(width/2 - sizex/2 + gap, height/2 - sizey/2, sizex, sizey,arcx,arcy);
						gc.drawImage(new Image(imagePath), width/2 - 24 - gap, height/2 - 40);
						gc.drawImage(new Image(imagePath), width/2 - 24 + gap, height/2 - 40);
						break;
					case 3:
						gc.strokeRoundRect(width/2 - sizex/2, height/2 - sizey/2, sizex, sizey,arcx,arcy);
						gc.strokeRoundRect(width/2 - sizex/2 - gap*2, height/2 - sizey/2, sizex, sizey,arcx,arcy);		
						gc.strokeRoundRect(width/2 - sizex/2 + gap*2, height/2 - sizey/2, sizex, sizey,arcx,arcy);
						gc.drawImage(new Image(imagePath), width/2 - 24, height/2 - 40);
						gc.drawImage(new Image(imagePath), width/2 - 24 - gap*2, height/2 - 40);
						gc.drawImage(new Image(imagePath), width/2 - 24 + gap*2, height/2 - 40);
						break;
				
			}
				
				
				
				
				
				
				break;
				
				
				
				
//				SolidCase ============================================================================
			case "solid": 
				switch(number) {
					case 1: 
						gc.fillRoundRect(width/2 - sizex/2, height/2 - sizey/2, sizex, sizey,arcx,arcy);
						break;
					case 2:
						gc.fillRoundRect(width/2 - sizex/2 - gap, height/2 - sizey/2, sizex, sizey,arcx,arcy);		
						gc.fillRoundRect(width/2 - sizex/2 + gap, height/2 - sizey/2, sizex, sizey,arcx,arcy);
						break;
					case 3:
						gc.fillRoundRect(width/2 - sizex/2, height/2 - sizey/2, sizex, sizey,arcx,arcy);
						gc.fillRoundRect(width/2 - sizex/2 - gap*2, height/2 - sizey/2, sizex, sizey,arcx,arcy);		
						gc.fillRoundRect(width/2 - sizex/2 + gap*2, height/2 - sizey/2, sizex, sizey,arcx,arcy);
						break;
						
				}
				break;
		}
	}
	
	public void drawDiamondOrJelly(GraphicsContext gc) {
	
		int sizex = 30;
		int sizey = 70;
		int arcx = 40;
		int arcy = 50;
		int gap = 20;
		int linesize = 4;
		
		gc.setFill(color);
		
		String imagePath = "file:res/diamondAndJelly/" + card.getCardID().charAt(0) + card.getCardID().charAt(1) + card.getCardID().charAt(2) +".png";	
				
				
		switch(number) {		
			case 1: 
				gc.drawImage(new Image(imagePath), width/2 - sizex/2, height/2 - sizey/2);
				break;
			case 2:
				gc.drawImage(new Image(imagePath), width/2 - sizex/2 - gap, height/2 - sizey/2);
				gc.drawImage(new Image(imagePath), width/2 - sizex/2 + gap, height/2 - sizey/2);
				break;
			case 3:
				gc.drawImage(new Image(imagePath), width/2 - sizex/2, height/2 - sizey/2);
				gc.drawImage(new Image(imagePath), width/2 - sizex/2 - gap*2, height/2 - sizey/2);
				gc.drawImage(new Image(imagePath), width/2 - sizex/2 + gap*2, height/2 - sizey/2);
				break;
		
		}
				

		
		
		
	}
	
	
	private void onClickHandler() {
		GameMonitor.updateCardStatus(this);
	}
	
	public void highlight() {
		this.setBackground(new Background(new BackgroundFill(highlightColor, new CornerRadii(cornerRadii), Insets.EMPTY)));;
	}
	
	public void unhighlight() {
		this.setBackground(new Background(new BackgroundFill(unhighlightColor, new CornerRadii(cornerRadii), Insets.EMPTY)));;
	}
	
	public int getPosition() {
		return position;
	}

	public Card getCard() {
		return card;
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public GraphicsContext getGc() {
		return gc;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
	
	public EventHandler getCardHandler() {
		return cardHandler;
	}

}
      