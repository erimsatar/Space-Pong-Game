import javax.swing.JPanel;
import objects.*;
import objects.stellar.Heart;
import objects.stellar.Meteorite;
import objects.stellar.Reverse;
import objects.stellar.Star;
import objects.stellar.StellarObject;
import objects.stellar.Ufo;

import javax.swing.Timer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.*;
import java.util.Arrays;

public class PlayPanel extends JPanel implements Runnable, KeyListener,ActionListener {
	public static int GAME_WITDH = 1024;
	public static int GAME_HEIGHT = 635;
	
	
	private Color color = Color.WHITE;
	

	
	private BufferedImage image;
	private Graphics g;
	
	private int FPS= 50;
	
	private Paddle paddle;
	private Ball ball;
	
	private boolean gameEnd = false;
	
	private int objectCount = 5;
	private StellarObject[] StellarObjects = new StellarObject[objectCount];

	
	private static int PADDLE_WITDH = 120;
	private static int PADDLE_HEIGHT = 20;
	private int paddlePosX = 512;
	private int paddlePosY = 600;
	
	private int ballRadius =10;
	
	
	InfoPanel infoPanel;
	StartPanel startPanel;
	
	public PlayPanel(InfoPanel infoPanel, StartPanel startPanel) {
		super();
		this.setPreferredSize(new Dimension(GAME_WITDH,GAME_HEIGHT));
		
		this.infoPanel = infoPanel;
		this.startPanel = startPanel;
		
		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(this);
		
	}
	

	public void run() {
		paddle = new Paddle(paddlePosX,paddlePosY,PADDLE_WITDH,PADDLE_HEIGHT);
		ball = new Ball(1000/FPS);
		createStellarObjects();
		boolean[][] intersectionArray = new boolean[objectCount][2];
		
		
		

		image = new BufferedImage(GAME_WITDH,GAME_HEIGHT,BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		

		
			
		  int delay = 20; //milliseconds
		  
		  ActionListener taskPerformer = new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		    	  if(!gameEnd) {
		    		  if(startPanel.start) {
		    		  	gameUpdate();
		    		  	checkStellarBallCol(intersectionArray);
		    		  	StellarObjectMove();
		    	  	}
		  		  	requestFocus();
		  		  	gameRender();
		    	  	gameDraw();
		    	  	checkBall();
		    	  	checkDarkTheme();
		    	  }
		    	  checkGameEnd();
				
		      }
		  };
		  new Timer(delay, taskPerformer).start();
		
	}
	private void checkGameEnd() {
		if(infoPanel.lives <= 0) {
			gameEnd = true;
			infoPanel.endUpdate();
		}
		if(infoPanel.totalTime<= 0) {
			infoPanel.level+=1;
			infoPanel.totalTime = 60;
			ball.speedUp();
			ball = new Ball(1000/FPS);
		}
	}
	
	private void StellarObjectMove() {
		if(startPanel.move) {
			for(StellarObject o : StellarObjects) {
				o.move();
			}
		}
	}
	
	private void createStellarObjects() {
		int len = StellarObjects.length;
		for(int i = 0; i< len ; i++) {
			if(i%len == 0) {
				StellarObjects[i] = new Star();
			}
			else if(i%len ==1) {
				StellarObjects[i] = new Ufo();
			}
			else if (i%len == 2) {
				StellarObjects[i] = new Meteorite();
			}
			else if(i%len == 3) {
				StellarObjects[i] = new Heart();
			}
			else if(i%len ==4) {
				StellarObjects[i] = new Reverse();
			}
			StellarObjects[i].setPosition();
		}
	}
	
	
	private void checkBall() {
		if(ball.y <= 0) {
			ball.y = 0;
			ball.velY = -ball.velY;
		}
		if(ball.y >= GAME_HEIGHT - ballRadius) {
			infoPanel.lives -=1;
			if(infoPanel.lives > 0) {
				ball = new Ball(1000/FPS);
			}
		}
		if(ball.x <= 0) {
			ball.x = 0;
			ball.velX = -ball.velX;
		}
		if(ball.x >= GAME_WITDH - ballRadius) {
			ball.x = GAME_WITDH - ballRadius;
			ball.velX = -ball.velX;
		}
		
		if(ball.intersects(paddle)) {
			infoPanel.score+=50;
			ball.velY = - ball.velY;
		}				
	}
	
	private void checkDarkTheme() {
		if(startPanel.dark) {
			startPanel.setBackground(Color.BLACK);
			infoPanel.setBackground(Color.BLACK);
			this.color = Color.BLACK;
			ball.color = Color.WHITE;
			paddle.color = Color.WHITE;
			for(StellarObject o : StellarObjects) {
				o.color = Color.BLACK;
			}
			infoPanel.levelLabel.setForeground(Color.WHITE);
			infoPanel.liveLabel.setForeground(Color.WHITE);
			infoPanel.scoreLabel.setForeground(Color.WHITE);
			infoPanel.timeLabel.setForeground(Color.WHITE);
			
		}
		else {
			startPanel.setBackground(Color.WHITE);
			infoPanel.setBackground(Color.WHITE);
			this.color = Color.WHITE;
			ball.color = Color.RED;
			paddle.color = Color.BLACK;
			for(StellarObject o : StellarObjects) {
				o.color = Color.WHITE;
			}
			infoPanel.levelLabel.setForeground(Color.BLACK);
			infoPanel.liveLabel.setForeground(Color.BLACK);
			infoPanel.scoreLabel.setForeground(Color.BLACK);
			infoPanel.timeLabel.setForeground(Color.BLACK);
		}
	}
	
	private void checkStellarBallCol(boolean[][] colArray) {
		for(StellarObject o : StellarObjects) {
			if(o instanceof Star) { //0
				if(StellarCollisionCheck(colArray,0, o)){
					System.out.println("hit with Star");
					infoPanel.score+= 100;
				}
			}
			else if(o instanceof Ufo) { //1
				if(StellarCollisionCheck(colArray,1, o)){
					System.out.println("hit with Ufo");
					infoPanel.lives-=1;
				}				
			}
			else if(o instanceof Meteorite) { //2
				if(StellarCollisionCheck(colArray,2, o)){
					System.out.println("hit with Meteorite");
					ball.velX = ball.velX*1.2;
					ball.velY = ball.velY*1.2;
				}				
			}
			else if(o instanceof Heart) {
				if(StellarCollisionCheck(colArray,3,o)) {
					System.out.println("hit with Heart");
					infoPanel.lives+=1;
				}
			}
			else if (o instanceof Reverse) {
				if(StellarCollisionCheck(colArray,4,o)) {
					System.out.println("hit with Reverse");
					paddle.speed = -paddle.speed;
				}
			}
			
			
			
		}
	}
	
	private boolean StellarCollisionCheck(boolean[][] colArray, int i, StellarObject o) {
		if(o.intersects(ball)) {
			colArray[i][1] = colArray[i][0];
			colArray[i][0] = true;
		}
		else {
			colArray[i][0] = false;
		}
		if(colArray[i][0] && !colArray[i][1]) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private void gameUpdate() {
		if(infoPanel.lives>0) {
			paddle.update();
			ball.update();
			infoPanel.update();
			}
		
	}
	private void drawStellar() {
		for(StellarObject o : StellarObjects) {
			o.draw(g);
		}
	}
	
	private void gameRender() {
		g.setColor(color);
		g.fillRect(0, 0, GAME_WITDH, GAME_HEIGHT);
		g.setColor(Color.black);
		
		paddle.draw(g);
		drawStellar();
		ball.draw(g);
	}
	
	private void gameDraw() {
		Graphics g2 = this.getGraphics();
		g2.drawImage(image,0,0,null);
		g2.dispose();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		paddle.keyPressed(e);
	
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		paddle.keyReleased(e);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
