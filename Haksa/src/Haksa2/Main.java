package Haksa2;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame{
	public Login2 login2 = null;
	public Haksa2 haksa2 = null;

	public void change(String panelName) {
		if(panelName.equals("Haksa2")) {
			getContentPane().removeAll();
			getContentPane().add(haksa2);
			revalidate();
			repaint();
		}
	}
	
	public static void main(String[] args) {
		Main win= new Main();
		
		System.out.print("1");
		win.login2 = new Login2(win);
		//win.haksa2 = new Haksa2(win);
		System.out.print("22");
		win.add(win.haksa2);
		System.out.print("3");
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.print("4");
		win.setSize(1000, 600);
		win.setResizable(false);
		win.setVisible(true);

	}
}
