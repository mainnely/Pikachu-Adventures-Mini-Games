package pikachu;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class GameViewC extends JPanel {

    int road1W = 7;
    int road1H = 50;
    int road1Y = 0;
    int road1Speed = 8;

    int road2W = 7;
    int road2H = 50;
    int road2Y = 0;
    int road2Speed = 8;

    Image backgroundImage;
    AnimatedTree treeBg;

    ImageIcon pikachuIcon;
    ImageIcon geodudeIcon;
    ImageIcon gameOver;
    ImageIcon playAgain;
    ImageIcon play;
    ImageIcon home;

    boolean isGameOver = false;

    ArrayList<Geodude> geodudes;

    GameCharacterC pikachu;
    int pikachuSpeed = 140;

    int score = 0;
    JLabel lblScore;

    JButton btnPlayAgain;
    JButton btnPlay;
    JButton btnHome;


    public GameViewC(JFrame f, JFrame frmHome) {
        this.setLayout(null);

        backgroundImage = new ImageIcon(getClass().getResource("/bg1C.png")).getImage();
        pikachuIcon = new ImageIcon(getClass().getResource("/PikachuC.gif"));
        geodudeIcon = new ImageIcon(getClass().getResource("/GeodudeC.gif"));
        gameOver = new ImageIcon(getClass().getResource("/gameOverC.png"));
        playAgain = new ImageIcon(getClass().getResource("/playAgainC.png"));
        play = new ImageIcon(getClass().getResource("/PlayC.png"));
        home = new ImageIcon(getClass().getResource("/HomeC.png")); 
        treeBg = new AnimatedTree();
        geodudes = new ArrayList<>();
        pikachu = new GameCharacterC(265, 650, pikachuIcon);

        btnPlay = new JButton(play);
        btnPlay.setBounds(339, 657, 106, 50);
        btnPlay.setOpaque(false);
        btnPlay.setContentAreaFilled(false);
        btnPlay.setBorderPainted(false);
        this.add(btnPlay);

        btnPlayAgain = new JButton(playAgain);
        btnPlayAgain.setBounds(339, 500, 106, 50);
        btnPlayAgain.setOpaque(false);
        btnPlayAgain.setContentAreaFilled(false);
        btnPlayAgain.setBorderPainted(false);
        btnPlayAgain.setVisible(false);
        this.add(btnPlayAgain);

        JLabel lblHome = new JLabel(home);
        lblHome.setBounds(680, 13, 85, 36);
        lblHome.setOpaque(false);
        lblHome.setIcon(home);
        this.add(lblHome);

        URL urlTitle = Home.class.getResource("/TitleC.png");
        ImageIcon icnTitle = new ImageIcon(urlTitle);
        JLabel lblTitle = new JLabel();
        this.add(lblTitle);
        lblTitle.setIcon(icnTitle);
        lblTitle.setBounds(315, 10, 154, 100);

        lblScore = new JLabel("<html><span style='font-family: Courier; font-weight: bold; font-size: 14px; color: black;'>Score: 0</span></html>");
        this.add(lblScore);
        lblScore.setBounds(50, 25, 120, 17);

        URL urlScore = Home.class.getResource("/boxScoreC.png");
        ImageIcon icnScore = new ImageIcon(urlScore);
        JLabel lblScoreBackground = new JLabel();
        this.add(lblScoreBackground);
        lblScoreBackground.setIcon(icnScore);
        lblScoreBackground.setBounds(9, 16, 171, 35);

        Timer t = new Timer(1000 / 60, e -> {
            if (!isGameOver) {
                updateRoad();
                treeBg.update();
                updateGeodudes();
                checkCollisions();
                repaint();
            }
        });

        Timer geodudeTimer = new Timer(1500, e -> addGeodude());

        btnPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.start();
                geodudeTimer.start();
                btnPlay.setVisible(false);
                requestFocusInWindow();
            }
        });

        btnPlayAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
                t.start();
                geodudeTimer.start();
                btnPlayAgain.setVisible(false);
                requestFocusInWindow();
            }
        });

        lblHome.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                f.setVisible(true);
                frmHome.dispose();
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

        addKeyControls();
    }

    void resetGame() {
        isGameOver = false;
        score = 0;
        lblScore.setText("<html><span style='font-family: Courier; font-weight: bold; font-size: 14px; color: black;'>Score: 0</span></html>");
        geodudes.clear();
        pikachu.x = 265;
        pikachu.y = 650;
        repaint();
    }

    void updateRoad() {
        road1Y += road1Speed;
        if (road1Y >= road1H + 50) {
            road1Y = 0;
        }
        road2Y += road2Speed;
        if (road2Y >= road2H + 50) {
            road2Y = 0;
        }
    }

    void checkCollisions() {
        for (Geodude geodude : geodudes) {
            if (geodude.getBounds().intersects(pikachu.getBounds())) {
                isGameOver = true;
                btnPlayAgain.setVisible(true);
                break;
            }
        }
    }

    public void addKeyControls() {

        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!isGameOver) {
                    int key = e.getKeyCode();
                    if (key == KeyEvent.VK_LEFT) {
                        movePikachu(-pikachuSpeed);
                    }
                    if (key == KeyEvent.VK_RIGHT) {
                        movePikachu(pikachuSpeed);
                    }
                }
            }
        });
    }

    void movePikachu(int moveX) {
        pikachu.x += moveX;
        int leftBound = 190 - pikachuIcon.getIconWidth() / 2;
        int rightBound = 580 - pikachuIcon.getIconWidth() / 2;
        if (pikachu.x < leftBound) {
            pikachu.x = leftBound;
        } else if (pikachu.x > rightBound) {
            pikachu.x = rightBound;
        }
        repaint();
    }

    void updateGeodudes() {
        ArrayList<Geodude> toRemove = new ArrayList<>();
        for (Geodude geodude : geodudes) {
            geodude.update();
            if (geodude.Gy >= getHeight()) {
                toRemove.add(geodude);
                score++;
                lblScore.setText("<html><span style='font-family: Courier; font-weight: bold; font-size: 14px; color: black;'>Score: " + score + "</span></html>");
                adjustGeodudeSpeed();
            }
        }
        geodudes.removeAll(toRemove);
    }

    void addGeodude() {
        geodudes.add(new Geodude());
    }

    void adjustGeodudeSpeed() {
        int newSpeed = 8 + (score / 5) * 2;
        for (Geodude geodude : geodudes) {
            geodude.setSpeed(newSpeed);
        }
    }

    public class AnimatedTree {
        int treeX = 2;
        int treeY = -17;
        int speed = 8;
        int treeHeight = 100;

        int tree2X = 650;
        int tree2Y = -28;
        int speed2 = 8;
        int treeHeight2 = 300;

        Image treeImage;
        Image treeImage2;

        public AnimatedTree() {
            treeImage = new ImageIcon(getClass().getResource("/tree1C.png")).getImage();
            treeImage2 = new ImageIcon(getClass().getResource("/tree2C.png")).getImage();
        }

        void update() {
            treeY += speed;
            tree2Y += speed2;

            if (treeY >= getHeight()) {
                treeY = -treeHeight - getHeight();
            }

            if (tree2Y >= getHeight()) {
                tree2Y = -treeHeight2 - getHeight();
            }
        }

        void draw(Graphics g) {
            if (treeImage != null) {
                g.drawImage(treeImage, treeX, treeY, null);
            }

            if (treeImage2 != null) {
                g.drawImage(treeImage2, tree2X, tree2Y, null);
            }
        }
    }

    public class Geodude {
        int Gx;
        int Gy;
        int speed = 7;
        int width = geodudeIcon.getIconWidth();
        int height = geodudeIcon.getIconHeight();
        Random rand = new Random();

        public Geodude() {
            resetPosition();
        }

        void update() {
            Gy += speed;
        }

        void resetPosition() {
            Gy = -height;
            Gx = rand.nextInt(400) + 190;
        }

        void draw(Graphics g) {
            if (geodudeIcon != null) {
                g.drawImage(geodudeIcon.getImage(), Gx, Gy, null);
            }
        }

        public Rectangle getBounds() {
            return new Rectangle(Gx + 20, Gy + 20, width - 20, 60);
        }

        public void setSpeed(int newSpeed) {
            this.speed = newSpeed;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        g.setColor(Color.WHITE);
        for (int y1 = road1Y; y1 < getHeight(); y1 += road1H + 50) {
            g.fillRect(259, y1, road1W, road1H);
        }

        for (int y2 = road2Y; y2 < getHeight(); y2 += road2H + 50) {
            g.fillRect(512, y2, road2W, road2H);
        }

        treeBg.draw(g);

        for (Geodude geodude : geodudes) {
            geodude.draw(g);
        }

        if (pikachu != null) {
            pikachu.draw(g);
        }

        if (isGameOver) {
            g.drawImage(gameOver.getImage(), (getWidth() - gameOver.getIconWidth()) / 2, (getHeight() - gameOver.getIconHeight()) / 2, this);
           
            Font font = new Font("Arial", Font.BOLD, 45);
            g.setFont(font);
            g.setColor(new Color (252, 213, 23));
            String scoreText = "Score: " + score;
            int x = (getWidth() - g.getFontMetrics().stringWidth(scoreText)) / 2;
            int y = getHeight() / 10 + gameOver.getIconHeight(); 
            g.drawString(scoreText, x, y);
        }
    }
}
