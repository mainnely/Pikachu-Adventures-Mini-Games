package pikachu;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class Home {
	
	public static void main(String[] args) {
		JFrame f=new JFrame("Pikachu Adventure");
		Pikachu c = new Pikachu(f);

	
		f.setSize(815,839);
		f.setVisible(true);
		f.setLayout(null);
		
		c.setBounds(0, 0, 800, 800);

		f.add(c);
		
		Thread bgmThread = new Thread(() -> {
			try {
				String bgm = "/PikachuBGM.wav";
				InputStream inputBGM = Home.class.getResourceAsStream(bgm);
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputBGM);
				Clip bgmclip = AudioSystem.getClip();
				bgmclip.open(audioStream);
				bgmclip.loop(Clip.LOOP_CONTINUOUSLY);
				
				while(!Thread.interrupted()) {
					try{
						Thread.sleep(1000);
					}
					catch(InterruptedException exx) {
						break;
					}
				}
				bgmclip.close();
			}
			catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
						ex.printStackTrace();
			};
		});
		bgmThread.start();
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);	
		}

}