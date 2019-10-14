import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
//학과별 대여목록 볼 수 있도록

public class BookManager extends JPanel {
	public static JTextField[] tf_nrb = new JTextField[7];
	DefaultTableModel model = null;
	DefaultTableModel model2 = null;
	static JTable table=null;
	String rentNo = null;
	String lastRentNo = null;
	String today=null;
	static Integer rnCount = null;
	static String query = null;
	//체크박스에 필요한 변수
	ArrayList<String> deptName = new ArrayList<>();
	int deptNum=0;
	//----------------------------------
	public BookManager() {
		query="select br.rentno rn,b.title title, b.no no, b.author, br.id id, br.rdate rdate, br.returndate redate" + 
				" from books b, (select * from bookRent2) br where b.no = br.bookno order by rentno";
		SimpleDateFormat format1 = new SimpleDateFormat ("YYYYMMdd");
		Date time = new Date();
		today = format1.format(time);
		setLayout(null);
		
		//최초 테이블 생성및 레이아웃 지정
		String colName[]={"대여번호","도서명","도서번호","출판사","대여한 회원","대여날짜","반납날짜"}; // 표에 출력할 칼럼명
		model=new DefaultTableModel(colName,0); // 표의 데이터
		table = new JTable(model); // 테이블에 모델(데이터) 바인딩
		JScrollPane jp = new JScrollPane(table);
		jp.setSize(new Dimension(575,230));
		jp.setLocation(10, 50);
		add(jp);
		table.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				table = (JTable)e.getComponent();
				model=(DefaultTableModel)table.getModel();
				//model.getValueAt의 값이 object라 String으로 변환해준다
				rentNo = (String)model.getValueAt(table.getSelectedRow(),0);
				String[] value=new String[7];
				for(int i=0;i<7;i++) {
					value[i] = (String)model.getValueAt(table.getSelectedRow(), i);
					tf_nrb[i].setText(value[i]);
				}
			}
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
		});

		JLabel rentInfo = new JLabel("대여정보");
		JLabel bookInfo = new JLabel("도서정보");
		rentInfo.setFont(new Font("Gothic",Font.BOLD,25));
		rentInfo.setSize(100, 50);
		bookInfo.setFont(new Font("Gothic",Font.BOLD,25));
		bookInfo.setSize(100, 50);
		bookInfo.setLocation(10, 280);
		rentInfo.setLocation(10,0);
		JLabel[] a = new JLabel[7];
		a[0] = new JLabel("대여번호");
		a[1] = new JLabel("도 서 명");
		a[2] = new JLabel("도서번호");
		a[3] = new JLabel("출 판 사");
		a[4] = new JLabel("대여회원");
		a[5] = new JLabel("대여날짜");
		a[6] = new JLabel("반납날짜");
		int k=0;
		for(int i=0;i<7;i++) {
			if(i==3 || i==6)
				k++;
			
			a[i].setSize(new Dimension(60,30));
			a[i].setLocation(10+(k*190), 330+(i*33)-(k*99));
			add(a[i]);
			
			tf_nrb[i] = new JTextField();
			tf_nrb[i].setLocation(75+(k*190),334+(i*33)-(k*99));
			tf_nrb[i].setSize(100, 22);
			add(tf_nrb[i]);
		}
		tf_nrb[5].setText(today);
		tf_nrb[0].setEnabled(false);
		tf_nrb[5].setEnabled(false);
		tf_nrb[6].setEnabled(false);
		String buttonName[] = {"도서등록","도서수정","도서관리","도서대여","도서반납"};
		JButton[] book = new JButton[5];
		MyActionListener ma = new MyActionListener();
		
		for(int i=0; i<5;i++)
		{	
			book[i] = new JButton(buttonName[i]);
			book[i].setSize(90,30);
			book[i].setLocation(55+(100*i),450);
			book[i].addActionListener(ma);
			add(book[i]);
		}
		//==========================================================================
		//학과별정렬 체크박스 만드는 부분
		ResultSet rs = null;    // select한 결과를 저장하는 객체
		try{
			rs = DBManager.stmt.executeQuery("select DISTINCT dept from student");
			int i=1;
			deptName.add(0,"전체");
			while(rs.next()) {
				deptName.add(i,rs.getString("dept"));
				i++;
			}
			deptNum = deptName.size();
		}catch(Exception e){
			e.printStackTrace();
		}
		JLabel l_dept=new JLabel("학과");
		l_dept.setBounds(150, 15, 30, 20);
		add(l_dept);
		
		String[] dept = new String[deptNum];
		dept[0]=deptName.get(0);
		for(int i=1;i<deptNum;i++) 
			{dept[i]=deptName.get(i);}
		
		JComboBox cb_dept=new JComboBox(dept);
		cb_dept.setBounds(185, 15, 100, 20);
		
		add(cb_dept);
		//콤보박스에 익명클래스로 리스너 등록하고 내용 넣어주는 코드
		cb_dept.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JComboBox cb=(JComboBox)e.getSource();     
				int deptIndex=cb.getSelectedIndex();
				//동적쿼리를 만들기 위한 기본적인 틀
				if(deptIndex==0){ // 전체
					query="select br.rentno rn,b.title title, b.no no, b.author, br.id id, br.rdate rdate, br.returndate redate" + 
							" from books b, (select * from bookRent2) br where b.no = br.bookno order by rn";
					show();
				}else { //Index 에 맞는 dept이름으로 필터링
					query = "select br.rentno rn,b.title title, br.bookno bn, s.id id, s.name name, s.dept dept, br.rdate rd" + 
					" from student s, books2 b, bookrent2 br where br.id = s.id and br.bookno = b.no" +
					" and dept='"+dept[deptIndex]+"' order by rn";
					showDept();
				}
			}});
		//===========================================================================
		//반납안한 사람만 보기위한 필터링을 추가
		JCheckBox ch = new JCheckBox("반납한사람 제외");
		ch.setBounds(460, 10, 150, 30);
		ch.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(ch.isSelected()) {
					query="select br.rentno rn,b.title title, b.no no, b.author, br.id id, br.rdate rdate, br.returndate redate" + 
							" from books b, (select * from bookRent2) br where b.no = br.bookno and br.returndate is null order by rn";
					show();
				}
				else {
					query="select br.rentno rn,b.title title, b.no no, b.author, br.id id, br.rdate rdate, br.returndate redate" + 
							" from books b, (select * from bookRent2) br where b.no = br.bookno order by rn";
					show();
				}
			}
		});
		add(ch);
		//===========================================================================
		show();
		add(bookInfo);
		add(rentInfo);
		setSize(700,500);
		setVisible(true);
	}
	
	class MyActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			ResultSet rs=null;
			String rentNo = tf_nrb[0].getText();
			String bookNo = tf_nrb[2].getText();
			String id = tf_nrb[4].getText();
			String rdate = tf_nrb[5].getText();
			String cmd = e.getActionCommand();
			switch(cmd) {
			case "도서관리":
				new BookAdd();
				break;
			case "도서대여":
				try {
					rs = DBManager.stmt.executeQuery("select max(rentno) as bn from bookRent2");
					rs.next();
					lastRentNo = rs.getString("bn");
					String lastDay = lastRentNo.substring(0,8);
					if(lastDay.equals(today)) {
						String a = lastRentNo.substring(8,11);
						rnCount = Integer.parseInt(a);
						String c = String.format("%03d", rnCount+1);
						try {
							DBManager.stmt.executeUpdate("insert into bookrent2 values('"+(today+c)+"','"+bookNo+"','"+id+"','"+rdate+"',null)");
							JOptionPane.showMessageDialog(null,"대여가 완료됬습니다..","알림",JOptionPane.INFORMATION_MESSAGE);
						}
						catch(Exception p) {
							p.printStackTrace();
							JOptionPane.showMessageDialog(null,"오류가 발생","알림",JOptionPane.INFORMATION_MESSAGE);
						}
					}
					//마지막대출이 오늘 이전일 경우
					else {
						String d = String.format("%03d", 1);
						try {
							DBManager.stmt.executeUpdate("insert into bookrent2 values('"+(today+d)+"','"+bookNo+"','"+id+"','"+rdate+"',null)");
							JOptionPane.showMessageDialog(null,"대여가 완료됬습니다..","알림",JOptionPane.INFORMATION_MESSAGE);
						}
						catch(Exception p) {
							p.printStackTrace();
							JOptionPane.showMessageDialog(null,"오류가 발생","알림",JOptionPane.INFORMATION_MESSAGE);
						}
					}
					for(int i=0;i<7;i++) {
						tf_nrb[i].setText("");
					}
					rs.close();
					show();
				}
				catch(Exception e2) {
					e2.printStackTrace();
				}
				break;
			case "도서반납":
				String redate = today;
				try {
					DBManager.stmt.executeUpdate("update bookrent2 set returndate='"+redate+"' where rentno='"+rentNo+"'");
					JOptionPane.showMessageDialog(null,"반납이 완료됬습니다..","알림",JOptionPane.INFORMATION_MESSAGE);
					show();
				}
				catch(Exception e2) {
					e2.printStackTrace();
				}
				break;
			}
		}
	}
	
	public void show(){
		try{
			ResultSet rs= DBManager.stmt.executeQuery(query);
			String colName[]={"대여번호","도서명","도서번호","출판사","대여한 회원","대여날짜","반납날짜"}; // 표에 출력할 칼럼명
			model=new DefaultTableModel(colName,0); // 표의 데이터
			table.setModel(model);
			model.setNumRows(0);
			while(rs.next()){
				String[] row=new String[7];
				row[0]=rs.getString("rn");
				row[1]=rs.getString("title");
				row[2]=rs.getString("no"); 
				row[3]=rs.getString("author");
				row[4]=rs.getString("id");
				row[5]=rs.getString("rdate");
				row[6]=rs.getString("redate");
				model.addRow(row);
			}
			rs.close();
		}
		catch(Exception e1){
			System.out.println(e1.getMessage());
			e1.printStackTrace();
		}
	}
	
	public void showDept(){
		try{
			String colName[]={"대여번호","도서명","도서번호","학번","이름","학과","대여날짜"}; // 표에 출력할 칼럼명
			model2=new DefaultTableModel(colName,0); // 표의 데이터
			ResultSet rs= DBManager.stmt.executeQuery(query);
			table.setModel(model2);
			model2.setNumRows(0);
			while(rs.next()){
				String[] row=new String[7];
				row[0]=rs.getString("rn");
				row[1]=rs.getString("title");
				row[2]=rs.getString("bn"); 
				row[3]=rs.getString("id");
				row[4]=rs.getString("name");
				row[5]=rs.getString("dept");
				row[6]=rs.getString("rd");
				model2.addRow(row);
			}
			rs.close();
		}
		catch(Exception e1){
			System.out.println(e1.getMessage());
			e1.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
	}
}
