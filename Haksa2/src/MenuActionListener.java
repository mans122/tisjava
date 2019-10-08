

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

	public class MenuActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			switch(cmd) {
			case "학생정보":
				Haksa.panel.removeAll();
				Haksa.panel.revalidate();
				Haksa.panel.repaint();
				Haksa.panel.add(new Student());
				Haksa.panel.setLayout(null);
				break;
			case "Exit":
				System.exit(0);
				break;
			case "대출목록":
				Haksa.panel.removeAll();
				Haksa.panel.revalidate();
				Haksa.panel.repaint();
				Haksa.panel.add(new BookRent());
				Haksa.panel.setLayout(null);
				break;
			}
		}
		
	public static void main(String[] args) {
	}

}

