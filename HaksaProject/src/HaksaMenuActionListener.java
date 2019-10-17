

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.jfree.ui.RefineryUtilities;

	public class HaksaMenuActionListener implements ActionListener{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
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
				Haksa.f.setPreferredSize(new Dimension(500,500));
				Haksa.f.setResizable(false);
				Haksa.f.pack();
				break;
			case "대출 및 반납":
				Haksa.panel.removeAll();
				Haksa.panel.revalidate();
				Haksa.panel.repaint();
				Haksa.panel.add(new BookManager());
				Haksa.f.setPreferredSize(new Dimension(600,550));
				Haksa.panel.setLayout(null);
				Haksa.f.pack();
				break;

			case "Exit":
				System.exit(0);
				break;
			case "대출현황":
				Haksa.panel.removeAll();
				Haksa.panel.revalidate();
				Haksa.panel.repaint();
				Haksa.panel.add(new BookGraph());
				Haksa.f.setPreferredSize(new Dimension(600,500));
				Haksa.panel.setLayout(null);
				Haksa.f.pack();
				break;
			case "3D":
				System.out.println("dd");
				PieChart3D pi = new PieChart3D();
				Haksa.panel.removeAll();
				Haksa.panel.revalidate();
				Haksa.panel.repaint();
				Haksa.panel.setLayout(null);
				Haksa.panel.add(pi);
				Haksa.f.setPreferredSize(new Dimension(600,500));
				Haksa.f.pack();
				break;	
			}
		}
	public static void main(String[] args) {
	}

}

