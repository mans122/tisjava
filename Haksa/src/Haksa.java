import javax.swing.*;
import java.awt.*;

public class Haksa extends JFrame {
	public Haksa() {
		setTitle("학사관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.LEFT));

		//tf_id = 학번
		c.add(new JLabel("학번"));
		JTextField tf_id=new JTextField(20);
		c.add(tf_id);

		//tf_name = 이름
		c.add(new JLabel("이름"));
		JTextField tf_name=new JTextField(20);
		c.add(tf_name);

		//tf_dep = 학번
		c.add(new JLabel("학번"));
		JTextField tf_dep=new JTextField(20);
		c.add(tf_dep);

		//tf_address = 학번
		c.add(new JLabel("주소"));
		JTextField tf_address=new JTextField(20);
		c.add(tf_address);
		
		JTextArea taList=new JTextArea(15,25);
		JScrollPane sp = new JScrollPane(taList);
		c.add(sp);
		
		JButton btnInsert = new JButton("등록");
		JButton btnList = new JButton("목록");
		JButton btnUpdate = new JButton("수정");
		JButton btnDelete = new JButton("삭제");
		c.add(btnInsert);
		c.add(btnList);
		c.add(btnUpdate);
		c.add(btnDelete);
		
		taList.append("===========================================\n");
		taList.append("학번	이름	학과	주소\n");
		taList.append("===========================================\n");



		setSize(300,500);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Haksa();
	}

}
