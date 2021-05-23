//Since there are many duplicates in the process of setting up a button, this class is created to merge it all into one.
//It is the super class of every buttons in the programs such as 'play', 'shuffle', 'quit'.

package Gui;

import Application.Main;
import Logic.SoundUtility;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class NavButton extends Button {
	
	
	public NavButton(String text, int width, int height, int cornerRadii, int borderWidth, Color highlightColor) {
		super(text);
		this.setPrefWidth(width);
		this.setPrefHeight(height);
		
		//set font accordingly
		this.setFont(new Font((height > 60 ? 20: 15)));
		
		setupBasicLook(cornerRadii, borderWidth, highlightColor);
	}
	
	
	public void setupBasicLook(int cornerRadii, int borderWidth, Color highlightColor) {
		this.setBorder(new Border(new BorderStroke(Color.GREY, 
	            BorderStrokeStyle.SOLID, new CornerRadii(cornerRadii), new BorderWidths(borderWidth))));
		this.setTextFill(Color.BLACK);
		this.setBackground(new Background(new BackgroundFill(Color.DARKGREY,new CornerRadii(cornerRadii), Insets.EMPTY)));
		this.setOnMouseEntered(event -> {
			this.setTextFill(Color.WHITE);
			this.setBackground(new Background(new BackgroundFill(highlightColor,new CornerRadii(cornerRadii), Insets.EMPTY)));
			this.setBorder(new Border(new BorderStroke(Color.WHITE, 
		            BorderStrokeStyle.SOLID, new CornerRadii(cornerRadii), new BorderWidths(borderWidth+1))));
		});
		this.setOnMouseExited(event -> {
			this.setTextFill(Color.BLACK);
			this.setBackground(new Background(new BackgroundFill(Color.DARKGREY,new CornerRadii(cornerRadii), Insets.EMPTY)));
			this.setBorder(new Border(new BorderStroke(Color.GREY, 
		            BorderStrokeStyle.SOLID, new CornerRadii(cornerRadii), new BorderWidths(borderWidth))));
		});
		
	}
	
	public void linkSetup(Scene nextScene) {
		this.setOnAction(e -> {
			Main.window.setScene(nextScene);
		});
	}
	
	

}
