import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class BookRent extends JFrame{
	ArrayList<String> deptName = new ArrayList<>();
	Connection conn;//연결객체
	Statement stmt;
	DefaultTableModel model;
	JTable table;
	String query;
	int deptNum=0;
	public BookRent(){
		query="select s.id, s.name, b.title, br.rDate"
				+" from student s, books b, bookRent br"
				+" where br.id=s.id"
				+" and br.bookNo=b.no"; 
		// DB연결
		ResultSet rs = null;    // select한 결과를 저장하는 객체
		String url = "jdbc:oracle:thin:@localhost:1521:myoracle";      // 서버 url
		String uid = "ora_user";       // ID
		String pw = "hong";     // PW         
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");// jdbc driver load
			conn = DriverManager.getConnection(url,uid,pw);// 연결  
			stmt=conn.createStatement();
			
			rs = stmt.executeQuery("select DISTINCT dept from student");
			int i=1;
			deptName.add(0,"전체");
			while(rs.next()) {
				deptName.add(i,rs.getString("dept"));
				System.out.println(deptName.get(i));
				i++;
			}
			deptNum = deptName.size();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		setTitle("학생관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//x버튼 누르면 프로그램 종료

		setLayout(null);//레이아웃설정. 레이아웃 사용 안함.

		JLabel l_dept=new JLabel("학과");
		l_dept.setBounds(10, 10, 30, 20);
		add(l_dept);
		
		//String[] dept = new String[deptNum];
		String[] dept = new String[deptNum];
		dept[0]=deptName.get(0);
		for(int i=1;i<deptNum;i++) 
			dept[i]=deptName.get(i);
		
		//String[] dept={"전체","컴퓨터시스템","멀티미디어","컴퓨터공학"};
		JComboBox cb_dept=new JComboBox(dept);
		cb_dept.setBounds(45, 10, 100, 20);
		
		add(cb_dept);
		cb_dept.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JComboBox cb=(JComboBox)e.getSource();     
				int deptIndex=cb.getSelectedIndex();
				//동적쿼리를 만들기 위한 기본적인 틀
				query="select s.id, s.name, b.title, br.rDate"
						+" from student s, books b, bookRent br"
						+" where br.id=s.id"
						+" and br.bookNo=b.no";
				if(deptIndex==0){ // 전체
					query += " order by br.no";
					System.out.println(query);
					bookList();
				}else { //Index 에 맞는 dept이름으로 필터링
					query += " and s.dept='"+dept[deptIndex]+"' order by br.no";
					System.out.println(query);
					bookList();
				}
				/*else if(deptIndex==1){ // 컴퓨터시스템     
					query += " and s.dept='컴퓨터시스템' ";
					query += " order by br.no";
					bookList();
				}else if(deptIndex==2){ // 멀티미디어
					query += " and s.dept='멀티미디어' ";
					query += " order by br.no";
					bookList();
				}else if(deptIndex==3){ // 컴퓨터공학
					query += " and s.dept='컴퓨터공학' ";
					query += " order by br.no";
					bookList();
				}*/
			}});
		
		String colName[]={"학번","이름","도서명","대출일"};
		model=new DefaultTableModel(colName,0);
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(480,200));
		add(table);
		JScrollPane sp=new JScrollPane(table);
		sp.setBounds(10, 40, 470, 300);
		setResizable(false);
		add(sp); 

		//종료이벤트 처리.윈도우가 종료될 때 DB연결을 close함.
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				try {
					if(conn!=null){
						conn.close(); // 연결종료.
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		//setResizable(false);//화면크기고정
		setSize(500, 400);//화면크기
		setVisible(true);
		bookList();
	}

	public void bookList(){
		try{
			// Select문 실행     
			ResultSet rs=stmt.executeQuery(query);
			model.setNumRows(0);
			
			while(rs.next()){
				String[] row=new String[4];//컬럼의 갯수가 4
				row[0]=rs.getString("id");
				row[1]=rs.getString("name");
				row[2]=rs.getString("title");
				row[3]=rs.getString("rdate");
				model.addRow(row);
			}
			rs.close();

		}
		catch(Exception e1){
			//e.getStackTrace();
			System.out.println(e1.getMessage());
		}
	}

	public static void main(String[] args) {
		new BookRent();
	}
}