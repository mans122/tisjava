import javax.swing.*;
import java.awt.*;
//라벨,버튼,패널 등에 패딩값
//label.setBorder(BorderFactory.createEmptyBorder(0 , 0, 0 , 0));

public class Haksa extends JFrame {
	public Haksa() {
		setTitle("학사관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.LEFT));

		//tf_id = 학번
		c.add(new JLabel("  학번  "));
		JTextField tf_id=new JTextField(24);
		c.add(tf_id);

		//tf_name = 이름
		c.add(new JLabel("  이름  "));
		JTextField tf_name=new JTextField(24);
		c.add(tf_name);

		//tf_dep = 학번
		c.add(new JLabel("  학과  "));
		JTextField tf_dep=new JTextField(24);
		c.add(tf_dep);

		//tf_address = 학번
		c.add(new JLabel("  주소  "));
		JTextField tf_address=new JTextField(24);
		c.add(tf_address);
		
		JTextArea taList=new JTextArea(15,28);
		JScrollPane sp = new JScrollPane(taList);
		c.add(sp);
		
		JButton btnInsert = new JButton("등록");
		JButton btnList = new JButton("목록");
		JButton btnUpdate = new JButton("수정");
		JButton btnDelete = new JButton("삭제");
		btnInsert.setPreferredSize(new Dimension(74,30));
		btnList.setPreferredSize(new Dimension(74,30));
		btnUpdate.setPreferredSize(new Dimension(74,30));
		btnDelete.setPreferredSize(new Dimension(74,30));
		c.add(btnInsert);
		c.add(btnList);
		c.add(btnUpdate);
		c.add(btnDelete);
		
		taList.setEditable(false);
		taList.append("============================================\n");
		taList.append("  학번	이름	학과	주소\n");
		taList.append("============================================\n");

		
		createMenu();
		setSize(335,480);
		setResizable(false);
		setVisible(true);
	}
	private void createMenu() {
		JMenuBar mb = new JMenuBar();
		JMenuItem[] menuItem = new JMenuItem[4];
		String[] itemTitle= {"Open","Save","aaa","dd"};
		JMenu screenMenu = new JMenu("File");
		MenuActionListener listener = new MenuActionListener();
		for(int i=0;i<menuItem.length;i++) {
			menuItem[i] = new JMenuItem(itemTitle[i]);
			menuItem[i].addActionListener(listener);
			screenMenu.add(menuItem[i]);
		}
		mb.add(screenMenu);
		setJMenuBar(mb);
		
	}
	
	public static void main(String[] args) {
		new Haksa();
	}

}
