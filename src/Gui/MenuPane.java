//This class is the Upper menu bar displayed in the boardScene.

package Gui;

import Application.Main;
import Logic.GameMonitor;
import Logic.SoundUtility;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MenuPane extends HBox {
	
	private Label scoreLabel;
//	private Label leftCardLabel;
	private Label levelLabel;
//	private Label ObjectiveLabel;
	public static NavButton shuffleButton;
	public static NavButton mainMenuButton;
	
	public MenuPane() {
		this.setPrefHeight(100);
		this.setPrefWidth(1010);
		this.setAlignment(Pos.CENTER);
		this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setSpacing(20);
		
//		ObjectiveLabel = new Label();
//		ObjectiveLabel.textProperty().setValue("Find 4 SETs");
//		ObjectiveLabel.setTextFill(Color.LIME);
//		ObjectiveLabel.setFont(new Font(20));
		
		scoreLabel = new Label();
		setScoreLabelText();
		scoreLabel.setTextFill(Color.WHITE);
		scoreLabel.setFont(new Font(30));
		
//		leftCardLabel = new Label();
//		setleftCardLabelText();
//		leftCardLabel.setTextFill(Color.WHITE);
		
		levelLabel = new Label();
		setLevelLabelText();
		levelLabel.setTextFill(Color.LIME);
		levelLabel.setFont(new Font(30));
		
		
		shuffleButton = new NavButton("SHUFFLE", 120, 60, 20, 2, Color.BLUEVIOLET);
		shuffleButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				GameMonitor.shuffle();

			}
		});

		
		mainMenuButton = new NavButton("QUIT", 100, 60, 20, 2, Color.ORANGERED);
		mainMenuButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Main.window.setScene(Main.menuScene);
				GameMonitor.clearSelectedList();

			}
		});
		
	
		this.getChildren().addAll(scoreLabel,levelLabel,shuffleButton,mainMenuButton);
		
	}
	
	public void setScoreLabelText() {
		scoreLabel.textProperty().setValue("SET(s) Found: " + GameMonitor.getScore() + "/4          ");
	}
	
//	public void setleftCardLabelText() {
//		leftCardLabel.textProperty().setValue("Cards Left: " + GameMonitor.getDeckPile().size());
//	}
	
	public void setLevelLabelText() {
		levelLabel.textProperty().setValue("Level: " + String.valueOf(GameMonitor.getLevel()) );
	}
	

}
