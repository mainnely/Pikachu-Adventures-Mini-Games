package pikachu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameViewT extends JPanel {
    String pikachuDirection = "still";
    int pikachuX = 320, pikachuY = 630;
    int score = 0;
    Image icnbg, icnscoreboard;
    List<FallingObject> fallingObjects;
    Random random = new Random();
    boolean gameOver = false;
    Timer gameTimer;
    GameCharacterT pikachu;
    ImageIcon imggameover;
    JLabel lblgameover = new JLabel();

    JButton playButton;
    JButton playAgainButton;

    public GameViewT(JFrame f, JFrame frmMain) {
    	this.setLayout(null);
        ImageIcon homeBtnImage = new ImageIcon(getClass().getResource("/homeT.png"));
        JLabel lblHome = new JLabel(homeBtnImage);
        lblHome.setBounds(680, 16, 85, 35);
        lblHome.setOpaque(false);
        lblHome.setIcon(homeBtnImage);
        this.add(lblHome);
             
        URL urlbg = Home.class.getResource("/backgT.png");
        icnbg = new ImageIcon(urlbg).getImage();

        pikachu = new GameCharacterT("Pikachu");
        pikachu.setBounds(pikachuX, pikachuY, 120, 100);
        this.add(pikachu);

        URL urllogo = Home.class.getResource("/logoT.png");
        ImageIcon icnlogo = new ImageIcon(urllogo);
        JLabel lbllogo = new JLabel();
        lbllogo.setBounds(335, 9, 154, 59);
        this.add(lbllogo);
        lbllogo.setIcon(icnlogo);

        URL urlplay = Home.class.getResource("/playT.png");
        ImageIcon icnplay = new ImageIcon(urlplay);
        playButton = new JButton(icnplay);
        playButton.setBounds(347, 556, 106, 50);
        playButton.setOpaque(false);
        playButton.setContentAreaFilled(false);
        playButton.setBorderPainted(false);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        URL urlplayAgain = Home.class.getResource("/playAgainT.png");
        ImageIcon icnplayAgain = new ImageIcon(urlplayAgain);
        playAgainButton = new JButton(icnplayAgain);
        playAgainButton.setBounds(300, 550, 200, 50);
        playAgainButton.setOpaque(false);
        playAgainButton.setContentAreaFilled(false);
        playAgainButton.setBorderPainted(false);
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
                playAgainButton.setVisible(false);
                lblgameover.setVisible(false);
            }
        });
        playAgainButton.setVisible(false);

        this.add(playButton);
        this.add(playAgainButton);

        URL urlscoreboard = Home.class.getResource("/scoreboardT.png");
        icnscoreboard = new ImageIcon(urlscoreboard).getImage();

        fallingObjects = new ArrayList<>();

        this.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    pikachuDirection = "right";
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    pikachuDirection = "left";
                }
                GameViewT.this.requestFocusInWindow();
            }

            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    pikachuDirection = "still";
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    pikachuDirection = "still";
                }
            }
        });

        gameTimer = new Timer(1000 / 60, e -> {
            if (!gameOver) {
                updateGame();
            }
        });

        this.setFocusable(true);
        this.requestFocusInWindow();

 
        Timer fallingObjectTimer = new Timer(1000, e -> {
            if (!gameOver) {
                createFallingObject();
            }
        });
        fallingObjectTimer.start();


        URL urlgameover = Home.class.getResource("/gameoverT.png");
        imggameover = new ImageIcon(urlgameover);
        lblgameover.setBounds(248, 290, 283, 166);
        lblgameover.setIcon(imggameover);
        lblgameover.setVisible(false);
        this.add(lblgameover);
        
        lblHome.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				f.setVisible(true);
				frmMain.dispose();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

        });
    }

    private void startGame() {
        score = 0;
        gameOver = false;
        fallingObjects.clear();
        pikachuX = 320;
        pikachuY = 630;
        playButton.setVisible(false);
        playAgainButton.setVisible(false);
        gameTimer.start();
        this.requestFocusInWindow();
    }

    private void createFallingObject() {
        int x = random.nextInt(800);
        boolean isThunderbolt = random.nextBoolean();
        FallingObject object = isThunderbolt ? new Thunderbolt(x, 0) : new Pokeball(x, 0);
        fallingObjects.add(object);
    }

    private void updateGame() {
        switch (pikachuDirection) {
            case "right":
                pikachu.direction = "right";
                pikachuX += pikachuX < getWidth() - 120 ? pikachu.speed : 0;
                break;
            case "left":
                pikachu.direction = "left";
                pikachuX -= pikachuX > 0 ? pikachu.speed : 0;
                break;
        }
        pikachu.setBounds(pikachuX, pikachuY, 120, 100);


        updateFallingObjects();
        this.repaint();
    }
    private void updateFallingObjects() {
        List<FallingObject> toRemove = new ArrayList<>();
        for (FallingObject obj : fallingObjects) {
            obj.y += obj.speed;
            if (obj.y > this.getHeight()) {
                toRemove.add(obj);
            }

            if (obj.getBounds().intersects(pikachuX, pikachuY, 120, 100)) {
                if (obj instanceof Thunderbolt) {

                    score += 10;
                } else if (obj instanceof Pokeball) {

                    gameOver();
                    return;
                }
                toRemove.add(obj);
            }
        }
        fallingObjects.removeAll(toRemove);
    }

    private void gameOver() {
        gameOver = true;
        gameTimer.stop();
        playAgainButton.setVisible(true);
        lblgameover.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(icnbg, 0, 0, this.getWidth(), this.getHeight(), this);


        for (FallingObject obj : fallingObjects) {
            obj.draw(g);
        }


        g.drawImage(icnscoreboard, 10, 10, 182, 38, this);


        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 20, 36);


        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 36));
            g.drawString("Game Over!", this.getWidth() / 2 - 100, this.getHeight() / 2);
        }
    }


    abstract class FallingObject {
        int x, y, speed;

        FallingObject(int x, int y) {
            this.x = x;
            this.y = y;
            this.speed = 5; 
        }

        abstract void draw(Graphics g);

        Rectangle getBounds() {
            return new Rectangle(x, y, 50, 50); 
        }
    }


    class Thunderbolt extends FallingObject {
        Image image;

        Thunderbolt(int x, int y) {
            super(x, y);
            URL url = Home.class.getResource("/thunderT.png");
            image = new ImageIcon(url).getImage();
        }

        @Override
        void draw(Graphics g) {
            g.drawImage(image, x, y, 50, 50, null);
        }
    }


    class Pokeball extends FallingObject {
        Image image;

        Pokeball(int x, int y) {
            super(x, y);
            URL url = Home.class.getResource("/ballT.png");
            image = new ImageIcon(url).getImage();
        }

        @Override
        void draw(Graphics g) {
            g.drawImage(image, x, y, 50, 50, null);
        }
    }
}
