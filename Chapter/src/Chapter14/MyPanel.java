package Chapter14;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class MyPanel extends JPanel {
	private NorthPanel pNorth = new NorthPanel();
	private CenterPanel pCenter = new CenterPanel();
	public MyPanel() {
		this.setLayout(new BorderLayout());
		this.add(pNorth,BorderLayout.NORTH);
		this.add(pCenter,BorderLayout.CENTER);
		pNorth.setBackground(Color.GREEN);
		pCenter.setBackground(Color.YELLOW);
	}
	class CenterPanel extends JPanel{
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.RED);
			g.fillRect(10, 10, 50, 50);
			g.setColor(Color.BLUE);
			g.fillRect(100, 100, 50, 50);
			g.setColor(Color.RED);
			g.drawString("CenterPanel에 들어가는 패널입니다.", 100, 50);
		}
	}
	class NorthPanel extends JPanel{
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.RED);
			g.fillRect(10, 10, 50, 50);
			g.setColor(Color.BLUE);
			g.fillRect(100, 100, 50, 50);
			g.setColor(Color.RED);
			g.drawString("CenterPanel에 들어가는 패널입니다.", 100, 50);
		}
	}
}
