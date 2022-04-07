package objects.stellar;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class StellarObject extends Rectangle {
	BufferedImage img = null;
	int img_height;
	int img_width;
	int x;
	int y;
	public static int GAME_WITDH = 1024;
	int speed;
	public Color color = Color.WHITE;
	
	public StellarObject(String s) {
		try {
		    img = ImageIO.read(new File(s));
		    img_height = img.getHeight();
		    img_width = img.getWidth();
		} catch (IOException e) {
			System.out.println(e);
		}
		
	}
	
	public void setPosition() {
		Random r = new Random();
		x = r.nextInt(800)+ 50;
		y = r.nextInt(10) + y;
		this.setBounds(x, y, img_width, img_height);
		speed = r.nextInt(10)+1;
		
	}
	
	public void draw(Graphics g) {
		g.setColor(color);	
		g.fillRect(x, y, img_width, img_height);
		g.drawImage(img, x, y, null);
		this.setBounds(x, y, img_width, img_height);
	}
	
	public void move() {
		if(x+speed<= 0 || x+speed+img_width>=GAME_WITDH) {
			speed = -speed;
		}
		x+=speed;
	}
}
