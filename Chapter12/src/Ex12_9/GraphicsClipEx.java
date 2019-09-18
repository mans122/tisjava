package Ex12_9;
import javax.swing.*;
import java.awt.*;
public class GraphicsClipEx extends JFrame {
	private MyPanel panel = new MyPanel();
	
	public GraphicsClipEx() {
		setTitle("Å¬¸®ÇÎ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);
		
		setSize(300,400);
		setVisible(true);
	}
	
	class MyPanel extends JPanel{
		private ImageIcon icon = new ImageIcon("img/img0.jpg");
		private Image img = icon.getImage();
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			//g.setClip(100,20,150,150);
			//g.setClip(50, 50, 100, 100);
			g.clipRect(150, 150, 50	, 50);
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
			g.setColor(Color.YELLOW);
			g.setFont(new Font("Arial", Font.ITALIC,40));
			
		
		}
	}
	public static void main(String[] args) {
		new GraphicsClipEx();
	}

}
