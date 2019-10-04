package Chapter14;
import javax.swing.*;
import java.awt.*;
public class ToolBarEx extends JFrame{
	private Container c;
	public ToolBarEx() {
		setTitle("툴바");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c= getContentPane();
		createToolBar();
		setSize(400, 200);
		setVisible(true);
		
	}
	
	//툴바생성하고 컨텐츠팬에 부착
	private void createToolBar() {
		//툴바생성
		JToolBar toolBar = new JToolBar("Kitae Menu");
		toolBar.setBackground(Color.LIGHT_GRAY);
		JButton btnNew=new JButton("New");
		toolBar.add(btnNew);
		//툴바에 메뉴로 사용할 컴포넌트 삽입
		toolBar.add(new JButton(new ImageIcon("images/open.jpg")));
		toolBar.addSeparator();
		toolBar.add(new JButton(new ImageIcon("images/save.jpg")));
		toolBar.add(new JLabel("search"));
		toolBar.add(new JTextField("text field"));
		JComboBox<String> combo = new JComboBox<String>();
		combo.addItem("java");
		combo.addItem("c#");
		combo.addItem("c");
		combo.addItem("c++");
		toolBar.add(combo);
		
		c.add(toolBar,BorderLayout.NORTH);
	}
	public static void main(String[] args) {
		new ToolBarEx();
	}

}
