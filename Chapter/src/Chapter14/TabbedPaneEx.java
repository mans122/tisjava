package Chapter14;
import javax.swing.*;
import java.awt.*;


public class TabbedPaneEx extends JFrame {
	public TabbedPaneEx() {
		setTitle("ÅÇÆÒ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		JTabbedPane pane = createTabbedPane();
		c.add(pane,BorderLayout.CENTER);
		setSize(500, 500);
		setVisible(true);
	}
	private JTabbedPane createTabbedPane() {
		JTabbedPane pane = new JTabbedPane();
		pane.addTab("tab1", new JLabel(new ImageIcon("img/2.png")));
		pane.addTab("tab2", new JLabel(new ImageIcon("img/3.png")));
		pane.addTab("tab3", new MyPanel());
		return pane;
	}
	
	public static void main(String[] args) {
		new TabbedPaneEx();
	}

}
