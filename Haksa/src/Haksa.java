import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class Haksa extends JFrame {
	//학번~주소를 입력받을 TextField 4개 선언
	public static JTextField[] tf_num = new JTextField[4];
	//목록을 출력할 TextArea를 taList라는 이름으로 생성 후 크기 지정,
	public static JTextArea taList=new JTextArea(16,29);
	public static JButton[] btnSearch = new JButton[4];
	public Haksa() {
		setTitle("학사관리");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.LEFT,10,3));
		
		btnSearch[0] = new JButton("검색");
		btnSearch[1] = new JButton("검색");
		btnSearch[2] = new JButton("검색");
		btnSearch[3] = new JButton("검색");
		
		btnSearch[0].setPreferredSize(new Dimension(60,22));
		btnSearch[1].setPreferredSize(new Dimension(60,22));
		btnSearch[2].setPreferredSize(new Dimension(60,22));
		btnSearch[3].setPreferredSize(new Dimension(60,22));
		//텍스트필드 생성 후 라벨,텍스트필드 패널에 등록
		for(int i=0;i<4;i++) {
			tf_num[i] = new JTextField(19);
			}
		c.add(new JLabel("학번"));
		c.add(tf_num[0]);
		c.add(btnSearch[0]);
		c.add(new JLabel("이름"));
		c.add(tf_num[1]);
		c.add(btnSearch[1]);
		c.add(new JLabel("학과"));
		c.add(tf_num[2]);
		c.add(btnSearch[2]);
		c.add(new JLabel("주소"));
		c.add(tf_num[3]);
		c.add(btnSearch[3]);
		
		
		//택스트필드를 스크롤패널에 넣어 sp를 만든 후 컨테이너에 등록
		JScrollPane sp = new JScrollPane(taList);
		c.add(sp);
		
		//버튼생성
		JButton btnInsert = new JButton("등록");
		JButton btnList = new JButton("목록");
		JButton btnUpdate = new JButton("수정");
		JButton btnDelete = new JButton("삭제");
		MyActionListener ma = new MyActionListener();//리스너 생성
		SearchActionListener sa = new SearchActionListener();
		//버튼을 리스너에 등록
		btnSearch[0].addActionListener(sa);
		btnSearch[1].addActionListener(sa);
		btnSearch[2].addActionListener(sa);
		btnSearch[3].addActionListener(sa);
		btnInsert.addActionListener(ma);
		btnList.addActionListener(ma);
		btnUpdate.addActionListener(ma);
		btnDelete.addActionListener(ma);
		
		
		//버튼들 크기조절
		btnInsert.setPreferredSize(new Dimension(73,30));
		btnList.setPreferredSize(new Dimension(73,30));
		btnUpdate.setPreferredSize(new Dimension(73,30));
		btnDelete.setPreferredSize(new Dimension(73,30));
		
		//버튼을 패널에 등록
		c.add(btnInsert);
		c.add(btnList);
		c.add(btnUpdate);
		c.add(btnDelete);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
				setVisible(false);
				dispose();
				Login.frame.setEnabled(true);
				Login.frame.setFocusable(true);}
				catch(Exception we) {}
			}
		});
		//텍스트필드 수정못하게 설정
		taList.setEditable(false);
		setSize(350,480);
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2);
		setVisible(true);
		setResizable(false);
	}
	public static void main(String[] args) {
		new Haksa();
	}
}
