package Ex9_4;
import javax.swing.*;
import java.awt.*;
public class BorderLayoutEx extends JFrame {
	public BorderLayoutEx() {
		setTitle("BorderLayout Sample");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		JPanel pNorth = new JPanel();
		pNorth.setBackground(Color.RED);
		pNorth.add(new JButton("ss"));
		
		JPanel pCenter = new JPanel();
		pCenter.setBackground(Color.BLUE);
		
		JPanel pSouth = new JPanel();
		pSouth.setBackground(Color.GREEN);
		
		JPanel pEast = new JPanel();
		pEast.setBackground(Color.BLACK);
		
		JPanel pWest = new JPanel();
		pWest.setBackground(Color.WHITE);
		
		//c.setLayout(new BorderLayout(50,20));
		c.add(pCenter,BorderLayout.CENTER);
		c.add(pNorth,BorderLayout.NORTH);
		c.add(pSouth,BorderLayout.SOUTH);
		c.add(pEast,BorderLayout.EAST);
		c.add(pWest,BorderLayout.WEST);
		
		setSize(300, 200);
		setVisible(true);
	}
	public static void main(String[] args) {
		new BorderLayoutEx();
	}

}
