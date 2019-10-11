import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Haksa extends JFrame{
	static JFrame f = new JFrame();
	public static MainProcess main;
	DBManager db = null;
	ResultSet rs = null;
	static JPanel panel = null;
	public Haksa() {
		db = new DBManager();
		f.setTitle("학사관리");
		f.setPreferredSize(new Dimension(500,500));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = f.getContentPane();
		panel = new JPanel();
		JMenuBar mb = new JMenuBar();
		
		JMenu Menu1 = new JMenu("학생관리");
		JMenu Menu2 = new JMenu("도서관리");
		JMenuItem itemLoad = new JMenuItem("학생정보");
		JMenuItem itemExit = new JMenuItem("Exit");
		JMenuItem item2 = new JMenuItem("대출 및 반납");
		JMenuItem item3 = new JMenuItem("대출현황");
		JMenuItem item4 = new JMenuItem("대출목록");
		JMenuItem item5 = new JMenuItem("도서 추가");
		Menu1.add(itemLoad);
		Menu1.addSeparator();
		Menu1.add(itemExit);
		Menu2.add(item2);
		Menu2.addSeparator();
		Menu2.add(item3);
		Menu2.add(item4);
		Menu2.add(item5);
		mb.add(Menu1);
		mb.add(Menu2);
		HaksaMenuActionListener listener = new HaksaMenuActionListener();
		
		itemLoad.addActionListener(listener);
		itemExit.addActionListener(listener);
		item2.addActionListener(listener);
		item3.addActionListener(listener);
		item4.addActionListener(listener);
		item5.addActionListener(listener);
		
		f.setJMenuBar(mb);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					db.Close();
				}
				catch(Exception we) {
					we.printStackTrace();
				}
			}
		});
		c.add(panel);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		f.setLocation((screenSize.width-f.getPreferredSize().width)/2,(screenSize.height-f.getPreferredSize().height)/2);
		f.pack();
		f.setResizable(false);
		f.setVisible(true);
	}
	public void setMain(MainProcess main) {
		this.main = main;
	}
	public static void main(String[] args) {
		//new Haksa();
	}
}

