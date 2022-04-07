package objects;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Paddle extends Rectangle implements KeyListener {
	public int x;
	public int y;
	public int WITDH ;
	public int HEIGHT ;
	public int xVelocity;
	public int speed = 10;
	public Color color = Color.black;
	
	public Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT){
		super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
		WITDH = PADDLE_WIDTH;
		HEIGHT =PADDLE_HEIGHT;
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g) {
		g.setColor(color);	
		g.fillRect(x, y, WITDH, HEIGHT);
		this.setBounds(x, y, WITDH, HEIGHT);
	}
	
	public void update() {
		if(x<=0) {
		x= 0;		
		}
		if(x>1024-WITDH) {
			x = 1024-WITDH;
		}
		x+=xVelocity;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			setXDirection(-speed);
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			setXDirection(speed);
		}
	}
		
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			setXDirection(0);
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			setXDirection(0);
		}
		
	}
	
	public void setXDirection(int xDirection) {
		xVelocity = xDirection;
	}
	
	


}