package objects;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Ball extends Rectangle {
	public int x;
	public int y;
	public int r;
	public double velX;
	public double velY;
	public long time;
	public double gravity;
	public double previousVelY;
	public Color color = Color.RED;
	public static double increase =1;
	
	public Ball(long time){
		x =10;
		y=10;
		r=10;
		velX =4*increase;
		velY =1*increase;
		this.time = time;
		gravity = 9.8;
		
		
		
	}
	
	public void update() {
//		previousVelY = velY;
		velY += gravity*((double) time/1000);
		x+=velX;
		y+=velY;
		
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x, y, 2*r, 2*r);
		this.setBounds(x, y, 2*r, 2*r);
	}
	
	public void speedUp() {
//		Random r = new Random();
//		int i = r.nextInt(20) +10;
		increase*= 1.5;
	}

}
