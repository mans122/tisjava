package Ex2;
import javax.swing.*;
import java.awt.*;

public class Haksa extends JFrame {
	public static JTextField[] tf_num = new JTextField[4];
	public Haksa() {
		setTitle("학사관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//20크기 텍스트필드 4개 생성
		for(int i=0;i<4;i++) {tf_num[i] = new JTextField(20);}
		c.add(new JLabel("학번"));
		c.add(tf_num[0]);
		c.add(new JLabel("이름"));
		c.add(tf_num[1]);
		c.add(new JLabel("학번"));
		c.add(tf_num[2]);
		c.add(new JLabel("주소"));
		c.add(tf_num[3]);
		
		JTextArea taList=new JTextArea(15,25);
		JScrollPane sp = new JScrollPane(taList);
		c.add(sp);
		
		//버튼생성 및 리스너등록 ,패널 등록
		JButton btnInsert = new JButton("등록");
		JButton btnList = new JButton("목록");
		JButton btnUpdate = new JButton("수정");
		JButton btnDelete = new JButton("삭제");
		MyActionListener ma = new MyActionListener();//리스너 생성
		btnInsert.addActionListener(ma);
		btnList.addActionListener(ma);
		btnUpdate.addActionListener(ma);
		btnDelete.addActionListener(ma);
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
