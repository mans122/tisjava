package Ex14_1;
import javax.swing.*;
public class MenuEx extends JFrame{
	public MenuEx() {
		setTitle("Menu");
		createMenu();
		setSize(250, 200);
		setVisible(true);
	}
	//메뉴 만들어 프레임에 삽입
	private void createMenu() {
		JMenuBar mb = new JMenuBar();
		JMenu screenMenu = new JMenu("Screen");
		
		JMenuItem itemLoad = new JMenuItem("Load");
		JMenuItem itemHide = new JMenuItem("Hide");
		JMenuItem itemReShow = new JMenuItem("ReShow");
		JMenuItem itemExit = new JMenuItem("Exit");
		screenMenu.add(itemLoad);
		screenMenu.add(itemHide);
		screenMenu.add(itemReShow);
		screenMenu.addSeparator();
		screenMenu.add(itemExit);
		mb.add(screenMenu);
		//Screen 메뉴에 메뉴아이템 생성 삽입
		/*screenMenu.add(new JMenuItem("Load"));
		screenMenu.add(new JMenuItem("Hide"));
		screenMenu.add(new JMenuItem("ReShow"));
		screenMenu.addSeparator();
		screenMenu.add(new JMenuItem("Exit"));
		
		//메뉴바에 메뉴 삽입
		mb.add(screenMenu);
		mb.add(new JMenu("Edit"));
		mb.add(new JMenu("Source"));
		mb.add(new JMenu("Project"));
		mb.add(new JMenu("Run"));
		*/
		//메뉴바를 프레임에 부착
		setJMenuBar(mb);
	}
	public static void main(String[] args) {
		new MenuEx();
	}

}
