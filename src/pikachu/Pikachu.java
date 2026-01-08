package pikachu;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import javax.swing.*;


public class Pikachu extends JPanel{
	Timer t;
	
	String strLogoDirection = "up";
	int logoY = 95;
	
	Image newdropimg;
	Image newFindingimg;
	Image newIlaganimg;
	Image newPikasaurimg;
	
	public Pikachu(JFrame f) {
		this.setLayout(null);
		
		
		URL urlHomePikachu = Pikachu.class.getResource("/HomePikachuG.gif");
		ImageIcon icnHomePikachu = new ImageIcon(urlHomePikachu);
		JLabel lblHomePikachu = new JLabel(icnHomePikachu);
		lblHomePikachu.setBounds(-69,532 ,263,187);
		this.add(lblHomePikachu);
		
		URL urlPikasaur = Pikachu.class.getResource("/PIKASAUR_Allen.png");
		ImageIcon icnPikasaur = new ImageIcon(urlPikasaur);
		newPikasaurimg = icnPikasaur.getImage().getScaledInstance(485,119, java.awt.Image.SCALE_SMOOTH); 
		JLabel lblPikasaur = new JLabel(new ImageIcon(newPikasaurimg));
		this.add(lblPikasaur);		
		
		URL urlIlagan = Pikachu.class.getResource("/ILAGAN_Allen.png");
		ImageIcon icnIlagan = new ImageIcon(urlIlagan);
		newIlaganimg = icnIlagan.getImage().getScaledInstance(475,113, java.awt.Image.SCALE_SMOOTH); 
		JLabel lblIlagan = new JLabel(new ImageIcon(newIlaganimg));
		this.add(lblIlagan);
		
		URL urlFinding = Pikachu.class.getResource("/FINDING_Allen.png");
		ImageIcon icnFinding = new ImageIcon(urlFinding);
		newFindingimg = icnFinding.getImage().getScaledInstance(426,77, java.awt.Image.SCALE_SMOOTH); 
		JLabel lblFinding = new JLabel(new ImageIcon(newFindingimg));
		this.add(lblFinding);
		
		URL urlDrop = Pikachu.class.getResource("/DROP_Allen.png");
		ImageIcon icnDrop = new ImageIcon(urlDrop);
		newdropimg = icnDrop.getImage().getScaledInstance(477,113, java.awt.Image.SCALE_SMOOTH); 
		JLabel lblDrop = new JLabel(new ImageIcon(newdropimg));
		this.add(lblDrop);
			
		URL urlLogo = Pikachu.class.getResource("/MLogo_Allen.png");
		ImageIcon icnLogo = new ImageIcon(urlLogo);
		JLabel lblLogo = new JLabel(icnLogo);
		this.add(lblLogo);
		
		URL urlMain = Pikachu.class.getResource("/Main_Allen.png");
		ImageIcon icnMain = new ImageIcon(urlMain);
		JLabel lblMain = new JLabel(icnMain);
		lblMain.setBounds(0,0,800,800);
		this.add(lblMain);
		
		lblPikasaur.setBounds(172,485 ,485,119);
		lblIlagan.setBounds(150,400,475,113);
		lblFinding.setBounds(157,590,426,77);
		lblDrop.setBounds(147,320 ,477,113);
		lblLogo.setBounds(118,logoY ,549,224);
		
		t = new Timer(1000/30, e -> {
			if(strLogoDirection == "up") {
				logoY -= 2;
				if(logoY <= 90)
					strLogoDirection = "down";
			} else {
				logoY += 2;
				if(logoY >= 110)
					strLogoDirection = "up";
				
				repaint();
			}
			lblLogo.setBounds(118,logoY ,549,224);
		});
		
//		drop game_ALTHEA
		
		lblDrop.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				f.setVisible(false);
			    JFrame frmMain = new JFrame("Drop That Pokeball");
		        frmMain.setSize(800, 800);
		        frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        GameViewT gameViewT = new GameViewT(f, frmMain);
		        frmMain.add(gameViewT);
		        frmMain.revalidate();
		        frmMain.setVisible(true);
		        frmMain.setLocationRelativeTo(null);	
		        frmMain.setResizable(false);
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
				newdropimg = icnDrop.getImage().getScaledInstance(487,123, java.awt.Image.SCALE_SMOOTH);
				lblDrop.setIcon(new ImageIcon(newdropimg));
				lblDrop.setBounds(142,315 ,477,113);
				
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				newdropimg = icnDrop.getImage().getScaledInstance(477,113, java.awt.Image.SCALE_SMOOTH);
				lblDrop.setIcon(new ImageIcon(newdropimg));
				lblDrop.setBounds(147,320 ,477,113);
			}});
		
//		finding game_JERMAINE
		
		lblFinding.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				f.setVisible(false);
				JFrame frmMain = new JFrame("Finding Ash");
				frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frmMain.setSize(800, 800);
				frmMain.setVisible(true);
			
				Maze_Map mazeMap = new Maze_Map(f, frmMain);
				frmMain.add(mazeMap);
				
				frmMain.revalidate();
				frmMain.setLocationRelativeTo(null);	
				frmMain.setResizable(false);
				
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
				newFindingimg = icnFinding.getImage().getScaledInstance(436,87, java.awt.Image.SCALE_SMOOTH);
				lblFinding.setIcon(new ImageIcon(newFindingimg));
				lblFinding.setBounds(152,585,436,87);
				
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				newFindingimg = icnFinding.getImage().getScaledInstance(426,77, java.awt.Image.SCALE_SMOOTH);
				lblFinding.setIcon(new ImageIcon(newFindingimg));
				lblFinding.setBounds(157,590,426,77);
			}});
		
//ILAGAN _ CHAN
		lblIlagan.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				f.setVisible(false);
				JFrame frmHome = new JFrame("Ilagan mo ang bato ko beybe");
			    frmHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    frmHome.setSize(800, 800);

			    GameViewC gvc = new GameViewC(f, frmHome);
			       
			    frmHome.add(gvc);

			    frmHome.setVisible(true);
			    frmHome.setLocationRelativeTo(null);	
			    frmHome.setResizable(false);
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
				newIlaganimg = icnIlagan.getImage().getScaledInstance(485,118, java.awt.Image.SCALE_SMOOTH);
				lblIlagan.setIcon(new ImageIcon(newIlaganimg));
				lblIlagan.setBounds(140,395,485,118);
				
				
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				newIlaganimg = icnIlagan.getImage().getScaledInstance(475,113, java.awt.Image.SCALE_SMOOTH);
				lblIlagan.setIcon(new ImageIcon(newIlaganimg));
				lblIlagan.setBounds(145,400,475,113);
			}});
		
//		PIKASAUR_BRYAN
		
		lblPikasaur.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				f.setVisible(false);
				JFrame frame = new JFrame("Pikachu Jumping Game");
		        GameView gameView = new GameView(f, frame);
		        frame.add(gameView);
		        frame.setSize(800, 800);
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        frame.setResizable(false);
		        frame.setVisible(true);
		        frame.setLocationRelativeTo(null);	
		        frame.setResizable(false);
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
				newPikasaurimg = icnPikasaur.getImage().getScaledInstance(495,128, java.awt.Image.SCALE_SMOOTH);
				lblPikasaur.setIcon(new ImageIcon(newPikasaurimg));
				lblPikasaur.setBounds(167,480,485,119);
				
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				newPikasaurimg = icnPikasaur.getImage().getScaledInstance(485,119, java.awt.Image.SCALE_SMOOTH);
				lblPikasaur.setIcon(new ImageIcon(newPikasaurimg));
				lblPikasaur.setBounds(172,485 ,485,119);
			}});
		t.start();
	}
}
