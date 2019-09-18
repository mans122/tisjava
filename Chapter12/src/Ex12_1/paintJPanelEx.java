package Ex12_1;
import javax.swing.*;
import java.awt.*;
public class paintJPanelEx extends JFrame {
	private MyPanel panel = new MyPanel();
	
	public paintJPanelEx() {
		setTitle("paintComponent()예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);
		
		setSize(500,800);
		setVisible(true);
	}
	
	class MyPanel extends JPanel{
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			/*
			g.setColor(Color.BLUE);
			g.drawRect(10, 10, 50, 50);
			g.drawRect(50, 50, 50, 50);
			g.setColor(Color.MAGENTA);
			g.drawRect(90, 90, 50, 50);
			g.drawLine(10,10,60,60);
			g.drawLine(60,10,10,60);
			g.setColor(new Color(255,10,30));
			g.fillArc(150, 150, 200, 200, 90, 270);
			int[] x= {30,10,30,60};
			int[] y= {250,275,300,200};
			g.drawPolygon(x,y,4);
			*/
			g.setFont(new Font("Jokerman",Font.ITALIC,15));
			g.drawString("1/4분기", 10, 65);
			g.setColor(Color.BLUE);
			g.fillRect(70, 50, 200, 20);
			g.setColor(Color.RED);
			g.fillRect(70, 80, 250, 20);
			g.setColor(Color.BLACK);
			g.fillRect(70, 110, 100, 20);
			g.setColor(Color.MAGENTA);
			g.fillRect(70, 140, 150, 20);
			
			int gap2=50;
			g.setColor(Color.BLACK);
			g.drawLine(50, 500, 50+gap2, 500-200);
			g.fillArc(95, 295, 10, 10, 0, 360);
			g.drawLine(50+gap2, 300, 50+gap2*2, 500-300);
			g.drawLine(50+gap2*2, 200, 50+gap2*3, 500-100);
			g.drawLine(50+gap2*3, 400, 50+gap2*4, 500-150);
			
			g.setColor(Color.RED);
			g.fillArc(150, 150, 200, 200, 0, 104);
			g.setColor(Color.BLUE);
			g.fillArc(150, 150, 200, 200, 104, 52*25/10);
			g.setColor(Color.GREEN);
			g.fillArc(150, 150, 200, 200,104+(52*25/10), 52);
			g.setColor(Color.PINK);
			g.fillArc(150, 150, 200, 200,156+(52*25/10),360-(156+(52*25/10)));
		}
	}
	public static void main(String[] args) {
		new paintJPanelEx(); 
	}

}
