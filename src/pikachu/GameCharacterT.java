package pikachu;

import java.awt.*;
import java.net.URL;
import javax.swing.*;

public class GameCharacterT extends JPanel{

	double speed = 15;
	int counter = 1;
	String direction = "still";

	public GameCharacterT(String type) {
		this.setOpaque(false);
		this.setLayout(null);

		JLabel lblSprite = new JLabel();
		lblSprite.setBounds(0,0,120,100);
		
		URL StillURL = Home.class.getResource("/charT.png");
		ImageIcon icnStill = new ImageIcon(StillURL  );

		URL Left1URL = Home.class.getResource("/r_left_1T.png");
		ImageIcon icnLeft1 = new ImageIcon(Left1URL  );
		URL LeftmURL = Home.class.getResource("/r_left_mT.png");
		ImageIcon icnLeftm = new ImageIcon(LeftmURL );
		URL Left2Url = Home.class.getResource("/r_left_2T.png");
		ImageIcon icnLeft2 = new ImageIcon(Left2Url);

		URL Right1URL = Home.class.getResource("/r_right_1T.png");
		ImageIcon icnRight1 = new ImageIcon(Right1URL);
		URL RightmURL = Home.class.getResource("/r_right_mT.png");
		ImageIcon icnRightm = new ImageIcon(RightmURL);
		URL Right2Url = Home.class.getResource("/r_right_2T.png");
		ImageIcon icnRight2 = new ImageIcon(Right2Url);
		
		this.add(lblSprite);
		
		Timer t = new Timer(1000/5, e -> {
			switch(direction) {
			case "right":
				if(counter == 1)
					lblSprite.setIcon(icnRight1);
				if(counter == 2)
					lblSprite.setIcon(icnRightm);
				if(counter == 3)
					lblSprite.setIcon(icnRight2);
				if(counter == 4)
					lblSprite.setIcon(icnRight1);
				counter += 1;
				if(counter == 5)
					counter = 1;
				break;
			case "left":
				if(counter == 1)
					lblSprite.setIcon(icnLeft1);
				if(counter == 2)
					lblSprite.setIcon(icnLeftm);
				if(counter == 3)
					lblSprite.setIcon(icnLeft2);
				if(counter == 4)
					lblSprite.setIcon(icnLeft1);
				counter += 1;
				if(counter == 5)
					counter = 1;
				break;
			case "still":
				if(counter == 1)
					lblSprite.setIcon(icnStill);
}

			repaint();
		});

		t.start();
	}
	}
