//The Main Application Class. All scenes are handled here.


package Application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import Animation.AnimationUtility;
import Gui.Card;
import Gui.CardBoardPane;
import Gui.MenuPane;
import Gui.NavButton;
import Logic.GameMonitor;
import Logic.SoundUtility;

public class Main extends Application{
	public static Stage window;
	public static Scene menuScene, tutorialScene,  boardScene, creditsScene, continueScene, congratsScene;
	
	
	private static NavButton button_Tutorial, button_Play, button_Credits;
	public static VBox root2;
	
	@Override
	public void start(Stage stage) {
		
		
		
		
		
		window = stage;
//		Scene1 (Menu Selection Scene) =====================================================================
		
		VBox logoBox = new VBox();
		logoBox.setPrefHeight(200);
		logoBox.setAlignment(Pos.BOTTOM_CENTER);
		
		Text logo = new Text();
		logo.setFont(new Font(125));
		logo.setText("SET");
		logo.setFill(Color.BLACK);
		logo.setStroke(Color.WHITE);
		logo.setStrokeWidth(6);
		
		logoBox.getChildren().add(logo);
		


		
		VBox buttonBox = new VBox();
		buttonBox.setPrefHeight(600);
		buttonBox.setSpacing(20);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setFillWidth(true);
		
		button_Tutorial = new NavButton("HOW TO PLAY", 200, 100, 50, 6, Color.DARKSALMON);
		button_Play = new NavButton("PLAY", 200, 100, 50, 6, Color.DARKSALMON);
		button_Credits = new NavButton("CREDITS", 200, 100, 50, 6, Color.DARKSALMON);
	
		
		
		
		buttonBox.getChildren().addAll(button_Tutorial, button_Play, button_Credits);
		
		
		VBox layout1 = new VBox();
		layout1.setPrefWidth(1010);
		layout1.setAlignment(Pos.CENTER);
		layout1.getChildren().addAll(logoBox, buttonBox);
		
		
		VBox background1 = new VBox();
		background1.setPrefWidth(1010);
		background1.setPrefHeight(800);
		
		
		BackgroundImage bg = new BackgroundImage(new Image("file:res/bgImages/setbg03.png", 1010, 800, false, false),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		
		
		background1.setBackground(new Background(bg));
		
		
//		Load Animation In The Background
		AnimationUtility.loadImage();
		
		
	
		final Group root1 = new Group(background1, AnimationUtility.handAnim, layout1);
		menuScene = new Scene(root1);
		stage.setScene(menuScene);
		SoundUtility.playMusic(SoundUtility.menuPageSound);
		
		
//		(Game Board Scene) =====================================================================
		
		
		
		GameMonitor.startGame(1); 
		MenuPane myMenuPane = GameMonitor.getMenuPane();
		CardBoardPane myCardBoardPane = GameMonitor.getCardBoardPane();
		
		root2 = new VBox();
		root2.setSpacing(10);
		root2.setPrefWidth(1010);
		
		root2.getChildren().addAll(myMenuPane, myCardBoardPane);
		
		boardScene = new Scene(root2);
		
		
		
//		(Tutorial and credits Scenes Setup) =====================================================================

		tutorialScene = tutorialSceneSetUp();
		creditsScene = creditsSceneSetUp();
		
//		Link the main menu button with the initialized scenes
		
		button_Tutorial.linkSetup(tutorialScene);
		button_Play.linkSetup(boardScene);
		button_Credits.linkSetup(creditsScene);
		
		
		
	

		stage.setTitle("SET - Marsha J. Falco");
		stage.setResizable(false);
		stage.show();
		
	}
	
	
//	This method set up the continue scene between each levels
	public static Scene continueSceneSetUp() {
		
		HBox layout = new HBox();
		layout.setAlignment(Pos.CENTER);
		layout.setSpacing(10);
		layout.setPadding(new Insets(20,40,20,40));
		
		layout.setPrefWidth(1010);
		layout.setPrefHeight(800);
		layout.setSpacing(20);
		
		
		NavButton mainMenuButton = new NavButton("QUIT", 200, 100, 50, 6, Color.ORANGERED);
		mainMenuButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				
				GameMonitor.startGame(GameMonitor.getLevel());
				Main.window.setScene(menuScene);
			}
		});
		
		
		
		NavButton continueButton = new NavButton("CONTINUE", 200, 100, 50, 6, Color.FORESTGREEN);
		continueButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				
				GameMonitor.startGame(GameMonitor.getLevel());
				
			}
		});		
		
		
		layout.getChildren().addAll(mainMenuButton, continueButton);
		layout.setBackground(new Background(new BackgroundFill(Color.web("#c4c4c4"),CornerRadii.EMPTY, Insets.EMPTY)));
		
		Scene scene = new Scene(layout);
		
		return scene;
	}
	
//	This method set up the congratulations scene after the final level.
	public static Scene congratsSceneSetUp() {
		
		SoundUtility.menuPageSound.stop();
		SoundUtility.winSound.play();
		
		VBox layout = new VBox();
		layout.setAlignment(Pos.CENTER);
		layout.setSpacing(50);
		layout.setPadding(new Insets(20,40,20,40));
		
		layout.setPrefWidth(1010);
		layout.setPrefHeight(800);
		
		Text congratText = new Text();
		congratText.setFont(new Font(80));
		congratText.setText("You Cleared the Game!\n Congratulations~");
		congratText.setFill(Color.FORESTGREEN);
		congratText.setStroke(Color.LIME);
		congratText.setTextAlignment(TextAlignment.CENTER);
		
		
		
		NavButton mainMenuButton = new NavButton("BACK TO MENU", 200, 100, 50, 6, Color.ORANGERED);
		
		
		mainMenuButton.setOnAction(new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event) {
				
				SoundUtility.playMusic(SoundUtility.menuPageSound);
				SoundUtility.winSound.stop();
				
				GameMonitor.setLevel(1);
				GameMonitor.startGame(GameMonitor.getLevel());
				window.setScene(menuScene);
				
				
			}
		});
		
		mainMenuButton.setLayoutX(405);
		mainMenuButton.setLayoutY(630);
		
		
		layout.getChildren().addAll(congratText, mainMenuButton);
		
		BackgroundImage bg = new BackgroundImage(new Image("file:res/bgImages/congrats.jpeg", 1010, 800, false, false),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		
		
		layout.setBackground(new Background(bg));
		
	
		
		Scene scene = new Scene(layout);
		
		return scene;
	}
	
	
//	This method set up the credits Scene that is directly linked to the main menu.
	public static Scene creditsSceneSetUp() {

		
		Pane layout = new Pane();
		layout.setPrefWidth(1010);
		layout.setPrefHeight(800);
		
		
		NavButton mainMenuButton = new NavButton("BACK TO MENU", 200, 80, 50, 2, Color.ORANGERED);
		mainMenuButton.linkSetup(menuScene);
		
		mainMenuButton.setLayoutX(405);
		mainMenuButton.setLayoutY(630);
		
		
		
		BackgroundImage bg = new BackgroundImage(new Image("file:res/bgImages/creditPage.png", 1010, 800, false, false),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		
		
		layout.setBackground(new Background(bg));
		layout.getChildren().addAll(mainMenuButton);
		
		
		
		
		Scene scene = new Scene(layout);
		
		return scene;
		
	}
	
//	This method set up the tutorial (Rules) Scene that is directly linked to the main menu.
	public static Scene tutorialSceneSetUp() {
		Pane layout = new Pane();
		layout.setPrefWidth(1010);
		layout.setPrefHeight(800);
		
		
		NavButton mainMenuButton = new NavButton("BACK TO MENU", 200, 80, 50, 2, Color.ORANGERED);
		mainMenuButton.linkSetup(menuScene);

		mainMenuButton.setLayoutX(405);
		mainMenuButton.setLayoutY(630);
		
		
		BackgroundImage bg = new BackgroundImage(new Image("file:res/bgImages/rules.png", 1010, 800, false, false),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		
		
		layout.setBackground(new Background(bg));
		layout.getChildren().addAll(mainMenuButton);
		
		
		Scene scene = new Scene(layout);
		
		return scene;
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	
	}
	


}
