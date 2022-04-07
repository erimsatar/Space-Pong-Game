import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPanel extends JPanel {
	int lives;
	int score;
	int level;
	JLabel liveLabel;
	JLabel scoreLabel;
	JLabel timeLabel;
	JLabel levelLabel;
	int i = 0; // for time to decrease
	int totalTime = 60;
	Color bgColor = Color.WHITE;
	Color fgColor = Color.BLACK;
	
	InfoPanel(){
		this.setPreferredSize(new Dimension(1024,50));
		this.setFocusable(false);
		this.setLayout(new FlowLayout());
		this.setBackground(bgColor);
		lives = 3;
		score =0;
		level =1;
		levelLabel = new JLabel("Level: "+ level +"              ");
		liveLabel = new JLabel("Lives: "+ lives+ "              ");
		scoreLabel = new JLabel("Score: " + score+ "              ");
		timeLabel = new JLabel("Time Left = 1:00");
		levelLabel.setForeground(fgColor);
		liveLabel.setForeground(fgColor);
		scoreLabel.setForeground(fgColor);
		timeLabel.setForeground(fgColor);
		
		levelLabel.setFont(new Font("Verdana",Font.PLAIN,20));
		liveLabel.setFont(new Font("Verdana",Font.PLAIN,20));
		scoreLabel.setFont(new Font("Verdana",Font.PLAIN,20));
		timeLabel.setFont(new Font("Verdana",Font.PLAIN,20));
		this.add(levelLabel);
		this.add(liveLabel);
		this.add(scoreLabel);
		this.add(timeLabel);
	}
	
	public void update() {
		levelLabel.setText("Level: "+ level +"              ");
		liveLabel.setText("Live: "+ lives+"              ");
		scoreLabel.setText("Score: " + score+"              ");
		i+=1;
		
		if(i%30==0) {
			totalTime-=1;
			}
		if(totalTime==60) {
			timeLabel.setText("Time Left = 1:00");
		}
		else {
			timeLabel.setText(String.format("Time Left = 00:%d", totalTime));
		}
	}
	
	public void endUpdate() {
		levelLabel.setText("");
		liveLabel.setText("");
		scoreLabel.setText("Score: " + score);
		timeLabel.setText("");
	}
}

