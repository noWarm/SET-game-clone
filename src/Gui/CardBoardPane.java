//This class is the playing board. It uses GridPane to display all the 12 CardCells.

package Gui;

import Logic.GameMonitor;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class CardBoardPane extends GridPane {
	
	private String img_path;
	
	public CardBoardPane() {
		this.setPrefWidth(1010);
		this.setPrefHeight(700);
		this.setAlignment(Pos.CENTER);
		this.setVgap(30);
		this.setHgap(30);

		
		switch(GameMonitor.getLevel()) {
			case 1: img_path = "file:res/bgImages/forestbg.png"; break;
			case 2: img_path = "file:res/bgImages/bg2.jpg"; break;
			case 3: img_path = "file:res/bgImages/bg4.jpg"; break;
			case 4: img_path = "file:res/bgImages/bg3.jpg"; break;
		}
		
			
		BackgroundImage bg = new BackgroundImage(new Image(img_path, 1010, 700, false, true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		
		this.setBackground(new Background(bg));
		
		for(int i=0,row=0;row<3;row++) {
			for(int col=0;col<4;col++) {
				
				this.add(GameMonitor.getPlayingCardList().get(i++), col, row,1,1);
			}
		}
	}
	
	
	
	
	
	
	
	
	
}
