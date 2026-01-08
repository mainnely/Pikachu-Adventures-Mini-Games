package pikachu;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

public class Maze_Map extends JPanel {

	int tileWidth = 65, tileHeight = 64;
	int spriteWidth = 66, spriteHeight = 66;

	//	Starting location

	int pikachuY = 380, pikachuX = 5;
	String pikachuDirection = "still";

	//	Items

	GameItem item1;
	GameItem item2;
	GameItem item3;

	int item1X = 195, item1Y = 320;
	int item2X = 650, item2Y = 384;
	int item3X = 63, item3Y = 64;

	int speed;

	//	Timer

	JLabel timerLabel;
	Timer gameTimer;
	ActionListener gameTimerAction;
	int timeLimit = 60;

	Timer t;
	Maze_Character pikachu;


	public Maze_Map(JFrame f, JFrame frmMain) {

		this.setBackground(new Color(182, 190, 157));
		this.setLayout(null);

		timerLabel = new JLabel("<html><span style='font-family:\\\"Press Start 2P\\\", cursive; font-size:12px; font-weight:bold;'>TIME:  " + timeLimit + " seconds</span></html>\"\r\n"
				+ "");
		timerLabel.setBounds(25, 16, 200, 35);
		this.add(timerLabel);

		URL urlTitle = Home.class.getResource("/titleJ.png");
		ImageIcon icnTitle = new ImageIcon(urlTitle);
		JLabel lblTitle = new JLabel();
		this.add(lblTitle);
		lblTitle.setIcon(icnTitle);
		lblTitle.setBounds(320, 0, 156, 65);

		URL urlTime = Home.class.getResource("/timeJ.png");
		ImageIcon icnTime = new ImageIcon(urlTime);
		JLabel lblTime = new JLabel();
		this.add(lblTime);
		lblTime.setIcon(icnTime);
		lblTime.setBounds(8, 16, 171,35);

		URL urlGameOver = Home.class.getResource("/gameOverJ.png");
		ImageIcon icnGameOver = new ImageIcon(urlGameOver);
		JLabel lblGameOver = new JLabel();
		this.add(lblGameOver);
		lblGameOver.setIcon(icnGameOver);
		lblGameOver.setBounds(255, 290, 280,160);
		lblGameOver.setVisible(false);

		URL urlWin = Home.class.getResource("/winJ.png");
		ImageIcon icnWin = new ImageIcon(urlWin);
		JLabel lblWin = new JLabel();
		this.add(lblWin);
		lblWin.setIcon(icnWin);
		lblWin.setBounds(255,300,300,190);
		lblWin.setVisible(false);

		ImageIcon playBtnImage = new ImageIcon(getClass().getResource("/playbtnJ.png"));
		JButton btnPlay = new JButton(playBtnImage);
		btnPlay.setBounds(347, 690, 110, 50);
		btnPlay.setOpaque(false);
		btnPlay.setContentAreaFilled(false);
		btnPlay.setBorderPainted(false);
		this.add(btnPlay);

		ImageIcon homeBtnImage = new ImageIcon(getClass().getResource("/homeJ.png"));
		JLabel lblHome = new JLabel(homeBtnImage);
		lblHome.setBounds(680, 16, 85, 35);
		lblHome.setOpaque(false);
		lblHome.setIcon(homeBtnImage);
		this.add(lblHome);

		ImageIcon againBtnImage = new ImageIcon(getClass().getResource("/againJ.png"));
		JButton btnAgain = new JButton(againBtnImage);
		btnAgain.setBounds(340, 510, 120, 50);
		btnAgain.setOpaque(false);
		btnAgain.setContentAreaFilled(false);
		btnAgain.setBorderPainted(false);
		this.add(btnAgain);
		btnAgain.setVisible(false);

		item1 = new GameItem("Item1");
		item1.setBounds(item1X, item1Y, tileWidth, tileHeight);
		this.add(item1);

		item2 = new GameItem("Item2");
		item2.setBounds(item2X, item2Y, tileWidth, tileHeight);
		this.add(item2);

		item3 = new GameItem("Item3");
		item3.setBounds(item3X, item3Y, tileWidth, tileHeight);
		this.add(item3);

		pikachu = new Maze_Character("Pikachu");
		this.add(pikachu);

		int mapMatrix[][] = {
				{1,1,1,1,1,0,0,0,1,1,1,1},
				{1,0,1,0,0,0,1,0,0,0,0,1},
				{1,0,1,0,1,1,1,1,1,1,0,1},
				{1,0,0,0,0,0,0,1,0,0,0,1},
				{1,1,1,1,1,1,0,1,0,1,1,1},
				{1,0,0,0,1,0,0,1,0,0,1,1},
				{0,0,1,0,1,0,1,1,1,0,0,1},
				{1,1,1,0,1,0,1,0,0,0,1,1},
				{1,1,0,0,1,0,1,0,1,1,0,2},
				{1,0,0,1,1,0,1,0,0,0,0,1},
				{1,1,0,0,0,0,1,0,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1}
		};

		int x = 0;
		int y = 0;

		for(int[] row: mapMatrix) {
			for(int tile : row) {

				JLabel lblTile = new JLabel();
				this.add(lblTile);

				if(tile == 1) {

					URL urlTile = Home.class.getResource("/TreeJ.png");
					ImageIcon icnTile = new ImageIcon(urlTile);
					lblTile.setIcon(icnTile);
				} else if (tile == 0) {

					URL urlTile = Home.class.getResource("/pathJ.png");
					ImageIcon icnTile = new ImageIcon(urlTile);
					lblTile.setIcon(icnTile);
				} else {
					URL urlTile = Home.class.getResource("/c_ASHJ.png");
					ImageIcon icnTile = new ImageIcon(urlTile);
					lblTile.setIcon(icnTile);
				}
				lblTile.setBounds(x, y, 65, 64);
				x += 65;
			}
			x = 0;
			y += 64;
		}


		this.setPreferredSize(new Dimension(65 * mapMatrix[0].length, 64 * mapMatrix.length));


		t = new Timer(1000/24, e -> {
			int newX = pikachuX, newY = pikachuY;
			switch(pikachuDirection) {
			case "right":
				pikachu.direction = "right";
				newX += pikachuX<800 ? pikachu.speed : 0;
				break;
			case "left":
				pikachu.direction = "left";
				newX -= pikachuX>0 ? pikachu.speed : 0;
				break;
			case "up":
				pikachu.direction = "up";
				newY -= pikachuY>0 ? pikachu.speed : 0;
				break;
			case "down":
				pikachu.direction = "down";
				newY += pikachuY<800 ? pikachu.speed : 0;
				break;
			}

			if (!pikachu.hasCollidedWith(mapMatrix, newX, newY, spriteWidth, spriteHeight, tileWidth, tileHeight)) {
				pikachuX = newX;
				pikachuY = newY;
			}

			if (collidedWithAsh(mapMatrix, newX, newY, spriteWidth, spriteHeight, tileWidth, tileHeight)) {
				lblWin.setVisible(true);
				t.stop();
				gameTimer.stop();
			}

			checkItemCollisions(pikachuX, pikachuY);
			pikachu.setBounds(pikachuX, pikachuY, tileWidth, tileHeight);

			this.setFocusable(true);
			this.requestFocusInWindow();
			this.repaint();
		});

		gameTimerAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timeLimit--;
				if (timeLimit >= 0) {
					timerLabel.setText("<html><span style='font-family:\\\"Press Start 2P\\\", cursive; font-size:12px; font-weight:bold;'>TIME:  " + timeLimit + "</span></html>\"\r\n"
							+ "");
				}
				if (timeLimit <= 0) {
					gameTimer.stop(); 
					lblGameOver.setVisible(true);
					btnAgain.setVisible(true);
					t.stop();

				}
			}
		};

		btnPlay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				t.start();
				btnPlay.setVisible(false);
				lblTitle.setVisible(false);
				gameTimer = new Timer(1000, gameTimerAction);
				gameTimer.start();
			}
		});

		btnAgain.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lblGameOver.setVisible(false);
				btnAgain.setVisible(false);
				pikachuY = 380; pikachuX = 5;
				t.start();
				btnPlay.setVisible(false);
				lblTitle.setVisible(false);

				if (gameTimer != null) {
					gameTimer.stop();
				}
				pikachu.resetSpeed();
				timeLimit = 60;
				timerLabel.setText("<html><center>TIME: " + timeLimit + " seconds</center></html>");

				gameTimer = new Timer(1000, gameTimerAction);
				gameTimer.start();
				
				hideItems();


			}
		});

		lblHome.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				f.setVisible(true);
				frmMain.dispose();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
			}
		});


		this.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					pikachuDirection = "right";
				}
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					pikachuDirection = "left";
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					pikachuDirection = "down";
				}
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					pikachuDirection = "up";
				}
			}

			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					pikachuDirection = "still";
				}
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					pikachuDirection = "still";
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					pikachuDirection = "still";
				}
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					pikachuDirection = "still";
				}
			}
		});

		this.setFocusable(true);
		this.requestFocusInWindow();
	}

	private void hideItems() {
        item1.setBounds(item1X, item1Y, tileWidth, tileHeight);
        item2.setBounds(item2X, item2Y, tileWidth, tileHeight);
        item3.setBounds(item3X, item3Y, tileWidth, tileHeight);
    }

	private void checkItemCollisions(int x, int y) {
		if ( x < item1X + tileWidth && x + spriteWidth > item1X && y < item1Y + tileHeight && y + spriteHeight > item1Y) {
			item1.hideItems();
			pikachu.increaseSpeed(1);
		}
		if (x < item2X + tileWidth && x + spriteWidth > item2X && y < item2Y + tileHeight && y + spriteHeight > item2Y) {
			item2.hideItems();
			pikachu.increaseSpeed(2);
		}
		if (x < item3X + tileWidth && x + spriteWidth > item3X && y < item3Y + tileHeight && y + spriteHeight > item3Y) {
			item3.hideItems();
			pikachuY = 380; pikachuX = 5;
		}
	}

	public boolean collidedWithAsh(int[][] mapMatrix, int nX, int nY, int spriteWidth, int spriteHeight, int tileWidth, int tileHeight) {
		int tileX = ((nX + spriteWidth / 2) / tileWidth);
		int tileY = ((nY + spriteHeight / 2) / tileHeight);

		if (tileX < 0 || tileX >= mapMatrix[0].length || tileY < 0 || tileY >= mapMatrix.length) {
			return false;
		}

		return mapMatrix[tileY][tileX] == 2;
	}

	public class GameItem extends JPanel{

		JLabel lblItem1;
		JLabel lblItem2;
		JLabel lblItem3;

		public GameItem(String item) {

			lblItem1 = new JLabel();
			lblItem1.setOpaque(false);
			this.add(lblItem1);

			URL urlItem1 = Home.class.getResource("/mysteryJ.png");
			ImageIcon icnItem1 = new ImageIcon(urlItem1);

			lblItem2 = new JLabel();
			lblItem2.setOpaque(false);
			this.add(lblItem2);

			lblItem3 = new JLabel();
			lblItem3.setOpaque(false);
			this.add(lblItem3);
			
			lblItem1.setIcon(icnItem1);
			lblItem2.setIcon(icnItem1);
			lblItem3.setIcon(icnItem1);

			setOpaque(false);
		}

		void hideItems() {
			lblItem2.setBounds(-50, -50, 1,1);
			lblItem1.setBounds(-50, -50, 1,1);
			lblItem3.setBounds(-50, -50, 1,1);
			this.setBounds(-50, -50, 1, 1);
		}

	}
}