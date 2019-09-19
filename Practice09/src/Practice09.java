import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
public class Practice09 extends JFrame {
	//Á¡ Âï±â°¡ ¾È‰Î
	//private Vector<Point> vStart = new Vector<Point>();
	private ArrayList<Point> vStart = new ArrayList<Point>();
	//private Vector<Point> vEnd = new Vector<Point>();
	public Practice09() {
		setTitle("asd");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MyPanel panel = new MyPanel();
		panel.setBackground(Color.WHITE);
		panel.setVisible(true);
		setContentPane(panel);
		setSize(500,500);
		setVisible(true);
	}

		public static void main(String[] args) {
		new Practice09();
	}
	class MyPanel extends JPanel {
		public MyPanel() {
			addMouseMotionListener(new MouseMotionListener() {
				public void mouseMoved(MouseEvent e) {}
				public void mouseDragged(MouseEvent e) {
					vStart.add(e.getPoint());
					repaint();
				}
			});
			addMouseListener(new MouseListener() {
				public void mouseReleased(MouseEvent e) {	}
				public void mousePressed(MouseEvent e) {
					vStart.add(null);
					vStart.add(e.getPoint());
				}
				public void mouseExited(MouseEvent e) {	}
				public void mouseEntered(MouseEvent e) {}
				public void mouseClicked(MouseEvent e) {}
			});
		}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.BLUE);
			for(int i=1;i<vStart.size();i++) {
				if(vStart.get(i-1)==null)
					continue;
				else if(vStart.get(i)== null)
					continue;
				else
				//Point s = vStart.elementAt(i);
				//Point e = vEnd.elementAt(i);
				//g.drawLine((int)s.getX(), (int)s.getY(), (int)e.getX(), (int)e.getY());
				g.drawLine((int) vStart.get(i - 1).getX(), (int) vStart.get(i - 1).getY(),
					       (int) vStart.get(i).getX(), (int) vStart.get(i).getY());

			}
		}
	}
	
	

}
