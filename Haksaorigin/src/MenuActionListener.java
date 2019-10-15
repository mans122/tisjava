

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
				MainProcess.haksa.dispose();
				Haksa.main.showFrameTest();
				break;
			case "Exit":
				System.exit(0);
				break;
			case "대출목록":
				
				break;
			}
		}
		
	public static void main(String[] args) {
	}

}

