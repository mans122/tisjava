package Haksa2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/*
class JPanel01 extends JFrame{
	private JButton jButton1;
	private JScrollPane jScrollPane1;
	private JTextArea jTextArea1;
	private PanelChange win;

	public JPanel01(PanelChange win) {
		this.win = win;
		setLayout(null);

		jButton1 = new JButton("버튼");
		jButton1.setSize(70, 20);
		jButton1.setLocation(10, 10);
		add(jButton1);

		jButton1.addActionListener(new MyActionListener());
	}

	class MyActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			win.change("panel02");
		}

	}
}
 */
/*
class JPanel02 extends JFrame{
	private JTextField textField;
	private PanelChange win;
	public JPanel02(PanelChange win) {
		setLayout(null);
		this.win = win;
		JLabel lb = new JLabel("id");
		lb.setBounds(31,40,67,15);
		add(lb);

		JButton btn = new JButton("btn");
		btn.setSize(70,20);
		btn.setLocation(10, 10);
		add(btn);
		btn.addActionListener(new MyActionListener());
	}

	class MyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			win.change("panel01");
		}
	}
}
*/
public class PanelChange extends JFrame{
	public JPanel01 jpanel01 = null;
	public JPanel02 jpanel02 = null;

	public void change(String panelName) {
		if(panelName.equals("panel01")) {
			getContentPane().removeAll();
			getContentPane().add(jpanel01);
			revalidate();
			repaint();
		}
		else {
			getContentPane().removeAll();
			getContentPane().add(jpanel02);
			revalidate();
			repaint();
		}
	}
	public static void main(String[] args) {
		PanelChange win = new PanelChange();
		win.getContentPane();
		win.setTitle("테스트");
		win.jpanel01 = new JPanel01(win);
		win.jpanel02 = new JPanel02(win);

		win.add(win.jpanel01);
		win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		win.setSize(500, 700);
		win.setVisible(true);
	}

}
