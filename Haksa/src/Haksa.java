import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Haksa extends JFrame {
	public static Connection conn=null;
	public static Statement stmt=null;
	public static ResultSet rs = null;
	String url = "jdbc:oracle:thin:@localhost:1521:myoracle";
	String uid = "ora_user";
	String pass = "hong";
	
	static DefaultTableModel model = null;
	static JTable table=null;
	static MainProcess main;
	
	//학번~주소를 입력받을 TextField 4개 선언
	public static JTextField[] tf_num = new JTextField[4];
	public static JButton[] btnSearch = new JButton[4];
	public Haksa() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(url,uid,pass);
			stmt=conn.createStatement();
		}catch(Exception aa){
			aa.printStackTrace();
		}
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
		
		//text area대신 table
		String colName[]={"학번","이름","학과","주소"}; // 표에 출력할 칼럼명
		model=new DefaultTableModel(colName,0); // 표의 데이터
		table = new JTable(model); // 테이블에 모델(데이터) 바인딩
		table.setPreferredScrollableViewportSize(new Dimension(320,280));//테이블 사이즈
		c.add(new JScrollPane(table));

		table.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				table = (JTable)e.getComponent();
				model=(DefaultTableModel)table.getModel();
				//model.getValueAt의 값이 object라 String으로 변환해준다
				String id = (String)model.getValueAt(table.getSelectedRow(), 0); //선택한 값의 id를 구한것
				String name = (String)model.getValueAt(table.getSelectedRow(), 1);
				String dept = (String)model.getValueAt(table.getSelectedRow(), 2);
				String address = (String)model.getValueAt(table.getSelectedRow(), 3);
				
				tf_num[0].setText(id);
				tf_num[1].setText(name);
				tf_num[2].setText(dept);
				tf_num[3].setText(address);
			}
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
		});
		//택스트필드를 스크롤패널에 넣어 sp를 만든 후 컨테이너에 등록
		//JScrollPane sp = new JScrollPane(taList);
		//c.add(sp);
		
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
					conn.close();
					JOptionPane.showMessageDialog(null,"로그인 화면으로 돌아갑니다.","알림",JOptionPane.INFORMATION_MESSAGE);
					main.showFrameLogin(); // 메인창 메소드를 이용해 띄우기
				}
				catch(Exception we) {
					we.printStackTrace();
				}
			}
		});
		setSize(350,480);
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2);
		setVisible(true);
		setResizable(false);
	}
	public void setMain(MainProcess main) {
		this.main = main;
	}
	public static void main(String[] args) {
	}
}
