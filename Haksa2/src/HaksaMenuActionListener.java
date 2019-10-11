

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

	public class HaksaMenuActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			switch(cmd) {
			case "대출 및 반납":
				Haksa.panel.removeAll();
				Haksa.panel.revalidate();
				Haksa.panel.repaint();
				Haksa.panel.add(new NoReturnBook());
				Haksa.f.setPreferredSize(new Dimension(500,500));
				Haksa.panel.setLayout(null);
				Haksa.f.pack();
				break;
			
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
			case "Exit":
				System.exit(0);
				break;
			case "대출목록":
				Haksa.panel.removeAll();
				Haksa.panel.revalidate();
				Haksa.panel.repaint();
				Haksa.panel.add(new BookRent());
				Haksa.panel.setLayout(null);
				Haksa.f.setPreferredSize(new Dimension(500,500));
				Haksa.f.setResizable(false);
				Haksa.f.pack();
				break;
			case "대출현황":
				Haksa.panel.removeAll();
				Haksa.panel.revalidate();
				Haksa.panel.repaint();
				Haksa.panel.add(new BookGraph());
				Haksa.f.setPreferredSize(new Dimension(620,500));
				Haksa.panel.setLayout(null);
				Haksa.f.pack();
				break;

			case "도서 추가":
				System.out.println("도서 추가");
				new BookAdd();
				break;
			}
		}
	public static void main(String[] args) {
	}

}

