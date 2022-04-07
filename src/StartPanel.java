import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class StartPanel extends JPanel {
	boolean start;
	boolean move;
	boolean dark;
	Color color = Color.WHITE;
	
	StartPanel(){
		this.setBackground(color);
		this.setFocusable(false);
		this.setPreferredSize(new Dimension(1024,50));
		JButton startButton = new JButton("start");
		start = false;
		this.add(startButton);
		startButton.addActionListener(
		    		  new ActionListener() {
		    			  public void actionPerformed(ActionEvent e) {
		    				  if (start) { 
		    					  startButton.setText("Start");
		    				      start = false;
		    				  }
		    				  else {
		    					  startButton.setText("Stop");
		    					  start = true;
		    				  }
		    			
		    			  }

		    		  }
		      );
		
		JButton moveButton = new JButton("Move Stellars");
		this.add(moveButton);
		move = false;
		moveButton.addActionListener(
	    		  new ActionListener() {
	    			  public void actionPerformed(ActionEvent e) {
	    				  if (move) { 
	    					  moveButton.setText("Move Stellars");
	    				      move = false;
	    				  }
	    				  else {
	    					  moveButton.setText("Stop Stellars");
	    					  move = true;
	    				  }
	    			
	    			  }

	    		  }
	      );
		
		
		JButton themeButton = new JButton("Dark Theme");
		this.add(themeButton);
		move = false;
		themeButton.addActionListener(
	    		  new ActionListener() {
	    			  public void actionPerformed(ActionEvent e) {
	    				  if (dark) { 
	    					  themeButton.setText("Dark Theme");
	    				      dark = false;
	    				  }
	    				  else {
	    					  themeButton.setText("Light Theme");
	    					  dark = true;
	    					  
	    				  }
	    			
	    			  }

	    		  }
	      );		
	}

}
