import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Test extends JFrame {
	public Test() {
		JScrollPane sp = new JScrollPane();
		JPanel jp = new JPanel();
		
		setContentPane(jp);
		setSize(500, 300);
		setVisible(true);
	}
	public static void main(String args[]) {
		new Test();
	}
}
