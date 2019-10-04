import javax.swing.*;
import java.awt.*;

public class Haksa extends JFrame {
	//학번~주소를 입력받을 TextField 4개 선언
	public static JTextField[] tf_num = new JTextField[4];
	//목록을 출력할 TextArea 선언
	public static JTextArea taList=new JTextArea(15,27);
	
	public Haksa() {
		setTitle("학사관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//24크기 텍스트필드 4개 생성
		for(int i=0;i<4;i++) {tf_num[i] = new JTextField(24);}
		c.add(new JLabel("학번"));
		c.add(tf_num[0]);
		c.add(new JLabel("이름"));
		c.add(tf_num[1]);
		c.add(new JLabel("학과"));
		c.add(tf_num[2]);
		c.add(new JLabel("주소"));
		c.add(tf_num[3]);
		//TextArea를 taList라는 이름으로 생성 후 크기 지정,
		//패널에 등록
		JScrollPane sp = new JScrollPane(taList);
		c.add(sp);
		
		//버튼생성
		JButton btnInsert = new JButton("등록");
		JButton btnList = new JButton("목록");
		JButton btnUpdate = new JButton("수정");
		JButton btnDelete = new JButton("삭제");
		MyActionListener ma = new MyActionListener();//리스너 생성
		//버튼을 리스너에 등록
		btnInsert.addActionListener(ma);
		btnList.addActionListener(ma);
		btnUpdate.addActionListener(ma);
		btnDelete.addActionListener(ma);
		//버튼들 크기조절
		btnInsert.setPreferredSize(new Dimension(74,30));
		btnList.setPreferredSize(new Dimension(74,30));
		btnUpdate.setPreferredSize(new Dimension(74,30));
		btnDelete.setPreferredSize(new Dimension(74,30));
		//버튼을 패널에 등록
		c.add(btnInsert);
		c.add(btnList);
		c.add(btnUpdate);
		c.add(btnDelete);

		taList.setEditable(false);
		taList.append("==========================================\n");
		taList.append("학번\n");
		taList.append("==========================================\n");

		setSize(335,480);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Haksa();
	}
}
