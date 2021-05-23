//This class handles the shuffling background animation shown in the main menu scene. It uses the class Timeline to set keyframe images.

package Animation;

import Application.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AnimationUtility {
	public static Group handAnim;
	public static Image[] keyImage = new Image[41];
	public static ImageView[] keyFrame = new ImageView[41];
	public static Timeline timeline = new Timeline();
	public static int count = 0;
	
	
	
	public static void loadImage() {
		
		String img_path = "/handKeyImages/frame";
		
		for(int i=0;i<9;i++) {
			
			keyImage[i] = (new Image(AnimationUtility.class.getResource(
					img_path + ((i<4) ? "0" : "") + String.valueOf(i+6) + ".png"
					).toString(), 1010,800, false,false));
			
			keyFrame[i] = new ImageView(keyImage[i]);
			keyFrame[i].fitWidthProperty().bind(Main.window.widthProperty());
			keyFrame[i].setOpacity((i/9 + 0.2));
			
		}
		
		handAnim = new Group(keyFrame[0]);
		
//		handAnim.setTranslateX(0);
//		handAnim.setTranslateY(0);
		
		
		timeline.setCycleCount(Timeline.INDEFINITE);
		

		
		for(int i=0;i<9;i++) {
			timeline.getKeyFrames().add(new KeyFrame(
				Duration.millis(80 + 80*i),
				e -> setKeyFrame()		
			));		
		}

		
		timeline.play();	
	}
	

	

	public static void setKeyFrame() {
		handAnim.getChildren().setAll(keyFrame[++count]);
		if(count == 8) {
			count = 0;
		}
	}

}
