package pikachu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class GameView extends JPanel {

	int pikachuX = 100, pikachuY = 530;
	int pikachuYVelocity = 0;
	boolean pikachuJumping = false;

	int obstacleX = 700, obstacleY = 560;
	int charizardX = 1500, charizardY = 180;

	Image pikachuImage;
	Image snorlaxImage;
	Image backgroundImage;
	Image charizardImage;
	ImageIcon playButtonImage;
	ImageIcon playAgainButtonImage;
	ImageIcon scoreImage;
	ImageIcon gameOverImage;
	ImageIcon homeImage;

	boolean gameStart = false;
	boolean gameOver = false;

	JButton playButton;
	JButton playAgainButton;
	JButton homeButton;

	JLabel scoreLabel;
	JLabel gameOverLabel;
	int score = 0;

	Random random = new Random();

	public GameView(JFrame f, JFrame frame) {
		this.setBackground(Color.WHITE);
		this.setLayout(null);

		pikachuImage = new ImageIcon(getClass().getResource("/pikachuB.gif")).getImage();
		snorlaxImage = new ImageIcon(getClass().getResource("/snorlaxB.png")).getImage();
		backgroundImage = new ImageIcon(getClass().getResource("/bgB.png")).getImage();
		charizardImage = new ImageIcon(getClass().getResource("/charizardB.gif")).getImage();
		homeImage = new ImageIcon(getClass().getResource("/homeB.png"));
		playButtonImage = new ImageIcon(getClass().getResource("/playB.png"));
		playAgainButtonImage = new ImageIcon(getClass().getResource("/againB.png"));
		scoreImage = new ImageIcon(getClass().getResource("/scoreB.png"));
		gameOverImage = new ImageIcon(getClass().getResource("/overB.png"));

		playButton = new JButton(playButtonImage);
		playButton.setBounds(300, 550, 200, 50);
		playButton.setOpaque(false);
		playButton.setContentAreaFilled(false);
		playButton.setBorderPainted(false);
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startGame();
			}
		});

		playAgainButton = new JButton(playAgainButtonImage);
		playAgainButton.setBounds(295, 550, 200, 50);
		playAgainButton.setOpaque(false);
		playAgainButton.setContentAreaFilled(false);
		playAgainButton.setBorderPainted(false);
		playAgainButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				restartGame();
			}
		});

		playAgainButton.setVisible(false);

		scoreLabel = new JLabel("Score: 0") {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(scoreImage.getImage(), 0, 0, getWidth(), getHeight(), this);
				g.setColor(Color.BLACK);
				g.setFont(getFont());
				FontMetrics fm = g.getFontMetrics();
				int textWidth = fm.stringWidth(getText());
				int textHeight = fm.getAscent();
				g.drawString(getText(), (getWidth() - textWidth) / 2, (getHeight() + textHeight) / 2 - fm.getDescent());
			}
		};
		scoreLabel.setFont(new Font("Arial", Font.BOLD, 26));
		scoreLabel.setBounds(10, 15, 150, 40);
		scoreLabel.setHorizontalTextPosition(JLabel.CENTER);
		scoreLabel.setVerticalTextPosition(JLabel.CENTER);

		gameOverLabel = new JLabel(gameOverImage);
		gameOverLabel.setBounds(250, 200, 300, 300);
		gameOverLabel.setVisible(false);

		JLabel lblHome = new JLabel(homeImage);
		lblHome.setBounds(695, 13, 85, 36);
		lblHome.setOpaque(false);
		lblHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Home button clicked");
			}
		});

		this.add(lblHome);
		this.add(scoreLabel);
		this.add(playAgainButton);
		this.add(playButton);
		this.add(gameOverLabel);

		Timer t = new Timer(1000 / 60, e -> {
			if (!gameOver && gameStart) {
				updateGame();
				repaint();
			}
		});

		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE && !pikachuJumping && !gameOver && gameStart) {
					pikachuJumping = true;
					pikachuYVelocity = -22;
				}
			}
		});

		lblHome.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				f.setVisible(true);
				frame.dispose();
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


		this.setFocusable(true);
		this.requestFocusInWindow();
		t.start();
	}

	void drawBackground(Graphics g) {
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
	}

	void drawPikachu(Graphics g) {
		g.drawImage(pikachuImage, pikachuX, pikachuY, this);
	}

	void drawObstacle(Graphics g) {
		g.drawImage(snorlaxImage, obstacleX, obstacleY, this);
	}

	void drawCharizard(Graphics g) {
		g.drawImage(charizardImage, charizardX, charizardY, this);
	}

	void updateGame() {
		if (pikachuJumping) {
			pikachuY += pikachuYVelocity;
			pikachuYVelocity += 1;
			if (pikachuY >= 530) {
				pikachuY = 530;
				pikachuJumping = false;
				pikachuYVelocity = 0;
			}
		}

		obstacleX -= 8;
		if (obstacleX + snorlaxImage.getWidth(null) < 0) {
			obstacleX = getWidth() + random.nextInt(500);
			score++;
			scoreLabel.setText("Score: " + score);
		}

		charizardX -= 4;
		if (charizardX + charizardImage.getWidth(null) < 0) {
			charizardX = getWidth() + random.nextInt(500);
		}

		Rectangle pikachuRect = new Rectangle(pikachuX, pikachuY, pikachuImage.getWidth(null), pikachuImage.getHeight(null));
		int snorlaxHitboxWidth = snorlaxImage.getWidth(null) - 80;
		int snorlaxHitboxHeight = snorlaxImage.getHeight(null) - 80;
		int snorlaxHitboxX = obstacleX + 40;
		int snorlaxHitboxY = obstacleY + 40;

		Rectangle snorlaxRect = new Rectangle(snorlaxHitboxX, snorlaxHitboxY, snorlaxHitboxWidth, snorlaxHitboxHeight);


		if (pikachuRect.intersects(snorlaxRect)) {
			gameOver = true;
			gameOverLabel.setVisible(true);
		}
	}

	void startGame() {
		gameStart = true;
		playButton.setVisible(false);
		requestFocusInWindow();
	}

	void restartGame() {
		pikachuX = 100;
		pikachuY = 530;
		pikachuYVelocity = 0;
		pikachuJumping = false;
		obstacleX = 700;
		obstacleY = 560;
		charizardX = 1000;
		gameOver = false;
		gameStart = false;
		score = 0;
		scoreLabel.setText("Score: 0");

		playButton.setVisible(true);
		playAgainButton.setVisible(false);
		gameOverLabel.setVisible(false);

		this.requestFocusInWindow();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawBackground(g);

		if (gameStart) {
			drawPikachu(g);
			drawObstacle(g);
			drawCharizard(g);

			if (gameOver) {
				playAgainButton.setVisible(true);
			} else {
				playAgainButton.setVisible(false);
			}
		} else {
			playButton.setVisible(true);
		}
	}
}

