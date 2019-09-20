package Ex14_9;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class MenuAndFileDialogEx extends JFrame {
	private JLabel imgLabel = new JLabel();
	private JLabel label = new JLabel("Hello");
	private JPanel panel = new JPanel();
	public MenuAndFileDialogEx() {
		this.setTitle("Menu");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		panel.setLayout(new FlowLayout());
		c.setLayout(new BorderLayout()); 
		c.add(panel,BorderLayout.CENTER);//c에 CENTER에 패널 panel을 올려준다.
		label.setFont(new Font("Gothic",Font.ITALIC,50));
		panel.add(label);
		panel.add(imgLabel);
		createMenu();
		//getContentPane()으로하면 메인프레임에 바로 넣어주게됨.
		//getContentPane().add(label);
		//getContentPane().add(imgLabel,BorderLayout.CENTER);
		setSize(600	, 600);
		setVisible(true);
	}
	//메뉴 만들어 프레임에 삽입
	private void createMenu() {
		JMenuBar mb = new JMenuBar();
		JMenuItem[] menuItem = new JMenuItem[4];
		String[] itemTitle = {"열기","Color","ReShow","Exit"};
		JMenu screenMenu = new JMenu("File");
		
		//4개의 메뉴아이템을 Screen 메뉴에 삽입한다.
		MenuActionListener listener = new MenuActionListener();
		for(int i=0;i<menuItem.length;i++) {
			menuItem[i] = new JMenuItem(itemTitle[i]);//메뉴아이템 이름에 itemTitle[i]를 넣어준다
			menuItem[i].addActionListener(listener);//메뉴아이템에 리스너를 등록한다.
			screenMenu.add(menuItem[i]);//scrrenMenu에 만들어진 menuItem을 넣어준다.
		}
		mb.add(screenMenu); //MenuBar mb에 값을 넣어준 screenMenu를 넣어준다.
		setJMenuBar(mb); //MenuBar mb를 프레임에 세팅한다.
	}
	class MenuActionListener implements ActionListener{
		private JFileChooser chooser;
		public MenuActionListener() {
			chooser = new JFileChooser();
		}
		public void actionPerformed(ActionEvent e) {
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg","gif","png");
			chooser.setFileFilter(filter);
			
			String cmd = e.getActionCommand();
			switch(cmd) {
			case "열기":
				int ret = chooser.showOpenDialog(null);
				if(ret !=JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null,"파일을 선택하지 않았습니다.","경고",JOptionPane.WARNING_MESSAGE);
					return;
				}
				//파일선택하고 열기 버튼 눌렀을 경우
				String filePath = chooser.getSelectedFile().getPath();//파일경로명 리턴
				imgLabel.setIcon(new ImageIcon(filePath));
				pack();
				break;
			case "Color":
				Color selectedColor = JColorChooser.showDialog(null, "Color", Color.YELLOW);
				if(selectedColor != null)
					label.setForeground(selectedColor);
				break;
			case "ReShow":
				imgLabel.setVisible(true);
				break;
			case "Exit":
				System.exit(0);
				break;
			}
		}
		
	}
	public static void main(String[] args) {
		new MenuAndFileDialogEx();
	}

}

