import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import objects.Ball;
import objects.Paddle;

public class GamePanel extends JPanel { //implements Runnable {
	
	static final int GAME_WIDTH = 1024;
	static final int GAME_HEIGHT = 768;
	static final int BALL_RADIUS = 10;
	static final int PADDLE_WIDTH = 120;
	static final int PADDLE_HEIGHT = 120;
	
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Paddle paddle;
	Ball ball;
	Score score;
	
	InfoPanel infoPanel ;
	PlayPanel playPanel ;
	StartPanel startPanel ;
	
	
	GamePanel(){
		infoPanel = new InfoPanel();
		startPanel = new StartPanel();
		playPanel = new PlayPanel(infoPanel,startPanel);
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		this.setBackground(Color.black);
		infoPanel.setBackground(Color.white);
		startPanel.setBackground(Color.white);
		playPanel.setBackground(Color.red);
		this.add(infoPanel);
		this.add(playPanel);
		this.add(startPanel);
		this.setPreferredSize(new Dimension(GAME_WIDTH,GAME_HEIGHT));
		
		
	}
	
	
	
	public void run() {
			playPanel.run();
		
		
	}
	
}
