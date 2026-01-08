package pikachu;

import java.awt.Color;
import java.net.URL;
import javax.swing.*;

import pikachu.Maze_Map.GameItem;

public class Maze_Character extends JPanel{

	int speed;
	int defaultSpeed = 3;

	int counter = 1;

	String direction = "right";

	public Maze_Character(String type) {

		this.setOpaque(false);
		this.setLayout(null);

		JLabel lblSprite = new JLabel();
		lblSprite.setBounds(0, 0, 65, 64);

		URL urlUp1 = Home.class.getResource("/up_1J.png");
		ImageIcon icnUp1 = new ImageIcon(urlUp1);
		URL urlUp2 = Home.class.getResource("/up_2J.png");
		ImageIcon icnUp2 = new ImageIcon(urlUp2);
		URL urlUp3 = Home.class.getResource("/up_3J.png");
		ImageIcon icnUp3 = new ImageIcon(urlUp3);

		URL urlDown1 = Home.class.getResource("/down_1J.png");
		ImageIcon icnDown1 = new ImageIcon(urlDown1);
		URL urlDown2 = Home.class.getResource("/down_2J.png");
		ImageIcon icnDown2 = new ImageIcon(urlDown2);
		URL urlDown3 = Home.class.getResource("/down_3J.png");
		ImageIcon icnDown3 = new ImageIcon(urlDown3);

		URL urlLeft1 = Home.class.getResource("/left_1J.png");
		ImageIcon icnLeft1 = new ImageIcon(urlLeft1);
		URL urlLeft2 = Home.class.getResource("/left_2J.png");
		ImageIcon icnLeft2 = new ImageIcon(urlLeft2);
		URL urlLeft3 = Home.class.getResource("/left_3J.png");
		ImageIcon icnLeft3 = new ImageIcon(urlLeft3);

		URL urlRight1 = Home.class.getResource("/right_1J.png");
		ImageIcon icnRight1 = new ImageIcon(urlRight1);
		URL urlRight2 = Home.class.getResource("/right_2J.png");
		ImageIcon icnRight2 = new ImageIcon(urlRight2);
		URL urlRight3 = Home.class.getResource("/right_3J.png");
		ImageIcon icnRight3 = new ImageIcon(urlRight3);

		this.add(lblSprite);
		this.speed = 3;


		Timer t = new Timer(1000/6, e -> {
			switch(direction) {
			case "right":
				if(counter == 1)
					lblSprite.setIcon(icnRight1);
				if(counter == 2)
					lblSprite.setIcon(icnRight3);
				if(counter == 3)
					lblSprite.setIcon(icnRight1);
				if(counter == 4)
					lblSprite.setIcon(icnRight2);
				counter += 1;
				if(counter == 5)
					counter = 1;
				break;
			case "left":
				if(counter == 1)
					lblSprite.setIcon(icnLeft1);
				if(counter == 2)
					lblSprite.setIcon(icnLeft3);
				if(counter == 3)
					lblSprite.setIcon(icnLeft1);
				if(counter == 4)
					lblSprite.setIcon(icnLeft2);
				counter += 1;
				if(counter == 5)
					counter = 1;
				break;
			case "up":
				if(counter == 1)
					lblSprite.setIcon(icnUp1);
				if(counter == 2)
					lblSprite.setIcon(icnUp3);
				if(counter == 3)
					lblSprite.setIcon(icnUp1);
				if(counter == 4)
					lblSprite.setIcon(icnUp2);
				counter += 1;
				if(counter == 5)
					counter = 1;
				break;
			case "down":
				if(counter == 1)
					lblSprite.setIcon(icnDown1);
				if(counter == 2)
					lblSprite.setIcon(icnDown3);
				if(counter == 3)
					lblSprite.setIcon(icnDown1);
				if(counter == 4)
					lblSprite.setIcon(icnDown2);
				counter += 1;
				if(counter == 5)
					counter = 1;
				break;
			}

			repaint();
		});

		t.start();
	}


	public boolean hasCollidedWith(int[][] mapMatrix, int nX, int nY, int spriteWidth, int spriteHeight, int tileWidth, int tileHeight) {
		int tileX = ((nX + spriteWidth / 2) / tileWidth);
		int tileY = ((nY + spriteHeight / 2) / tileHeight);
		if (tileX < 0 || tileX >= mapMatrix[0].length || tileY < 0 || tileY >= mapMatrix.length) {
			return false;
		}


		return mapMatrix[tileY][tileX] == 1;
	}


	public void increaseSpeed(int increment) {
			speed = defaultSpeed + increment;
	}
	public void resetSpeed() {
		this.speed = defaultSpeed;
	}
}


