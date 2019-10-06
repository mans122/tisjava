package Haksa2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Haksa2.JPanel02.MyActionListener;

public class JPanel02 extends JFrame{
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

