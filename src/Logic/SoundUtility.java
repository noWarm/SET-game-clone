//This class handles all the sound clips.
package Logic;

import javafx.scene.media.AudioClip;

public class SoundUtility {
	public static AudioClip menuPageSound = new AudioClip("file:res/sound/menuSound.wav");
	public static AudioClip illegalSetSound = new AudioClip("file:res/sound/illegalSet.wav");
	public static AudioClip correctSetSound = new AudioClip("file:res/sound/correctSet.wav");
	public static AudioClip unselectCardSound = new AudioClip("file:res/sound/unselectCard.wav");
	public static AudioClip selectCardSound = new AudioClip("file:res/sound/selectCard.wav");
	public static AudioClip shuffleSound = new AudioClip("file:res/sound/shuffleSound.wav");
	public static AudioClip winSound = new AudioClip("file:res/sound/russia.wav");
	
	public static void playMusic (AudioClip audioclip) {
		if(audioclip == menuPageSound) {
			audioclip.setCycleCount(AudioClip.INDEFINITE);
		}

		
		audioclip.play();
	}
	
	
	
	
}
