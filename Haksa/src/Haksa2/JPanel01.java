package Haksa2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Haksa2.JPanel01.MyActionListener;

public class JPanel01 extends JFrame{
	private JButton jButton1;
	private JScrollPane jScrollPane1;
	private JTextArea jTextArea1;
	private PanelChange win;
	
	public JPanel01(PanelChange win) {
		this.win = win;
		setLayout(null);
		
		jButton1 = new JButton("¹öÆ°");
		jButton1.setSize(70, 20);
		jButton1.setLocation(10, 10);
		add(jButton1);
		jTextArea1 = new JTextArea();
		jScrollPane1 = new JScrollPane(jTextArea1);
		jScrollPane1.setSize(200, 150);
		jScrollPane1.setLocation(10, 40);
		add(jScrollPane1);
		
		jButton1.addActionListener(new MyActionListener());
	}
	
	class MyActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			win.change("panel02");
		}
	}
	public static void main(String[] args) {
	}
}