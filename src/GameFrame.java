import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GameFrame extends JFrame {
	
	GamePanel gamePanel = new GamePanel();
	
	GameFrame(){
		gamePanel = new GamePanel();
		this.add(gamePanel);
		this.setTitle("Space Pong");
		this.setResizable(false);
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1024,768);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	public void run() {
		gamePanel.run();
	}

}
