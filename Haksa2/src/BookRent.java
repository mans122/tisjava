import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.Character.Subset;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class BookRent extends JPanel{
	ArrayList<String> deptName = new ArrayList<>();
	DefaultTableModel model;
	JTable table;
	String query;
	JRadioButton all = new JRadioButton("전체");
	JRadioButton noReturn = new JRadioButton("미반납");
	
	int deptNum=0;
	public BookRent(){
		query="select s.id, s.name, b.title, br.rDate"
				+" from student s, books b, bookRent br"
				+" where br.id=s.id"
				+" and br.bookNo=b.no"; 
		// DB연결
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
		setLayout(null);//레이아웃설정. 레이아웃 사용 안함.

		// 학과별 필터링 콤보박스
		JLabel l_dept=new JLabel("학과");
		l_dept.setBounds(10, 10, 30, 20);
		add(l_dept);
		
		ButtonGroup bg= new ButtonGroup();
		bg.add(all);
		bg.add(noReturn);
		all.setBounds(200,10,100,20);
		noReturn.setBounds(320, 10,100,20);
		MyActionListener ma = new MyActionListener();
		all.addItemListener(ma);
		noReturn.addItemListener(ma);
		
		add(all);
		add(noReturn);
		
		
		String[] dept = new String[deptNum];
		dept[0]=deptName.get(0);
		for(int i=1;i<deptNum;i++) 
			{dept[i]=deptName.get(i);}
		
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
					bookList();
				}else { //Index 에 맞는 dept이름으로 필터링
					query += " and s.dept='"+dept[deptIndex]+"' order by br.no";
					bookList();
				}
			}});
		
		String colName[]={"학번","이름","도서명","대출일"};
		model=new DefaultTableModel(colName,0);
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(480,200));
		add(table);
		JScrollPane sp=new JScrollPane(table);
		sp.setBounds(10, 40, 470, 390);
		add(sp); 

		setSize(500,500);
		setVisible(true);
		bookList();
	}
	
	public void bookList(){
		try{
			// Select문 실행     
			ResultSet rs= DBManager.stmt.executeQuery(query);
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
			System.out.println(e1.getMessage());
		}
	}
	
	class MyActionListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			if(all.isSelected()) {
				System.out.print("전체 목록");
				Haksa.panel.removeAll();
				Haksa.panel.revalidate();
				Haksa.panel.repaint();
				Haksa.panel.add(new BookRent());
				Haksa.panel.setLayout(null);
				Haksa.f.setPreferredSize(new Dimension(500,500));
				Haksa.f.setResizable(false);
				Haksa.f.pack();
			}
			if(noReturn.isSelected()) {
				NoReturn();
			}
		}
	}
	public void NoReturn () {
		setLayout(null);
		String colName[]={"대여날짜","학번","책번호","책이름"}; // 표에 출력할 칼럼명
		model=new DefaultTableModel(colName,0); // 표의 데이터
		table = new JTable(model); // 테이블에 모델(데이터) 바인딩
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		JScrollPane jp = new JScrollPane(table);
//		jp.setSize(new Dimension(475,430));
//		jp.setLocation(10, 50);
		jp.setBounds(10, 40, 470, 390);
		add(jp);
		try{
			// Select문 실행
			ResultSet rs=DBManager.stmt.executeQuery("select substr(aa.no,1,4) year,substr(aa.no,5,2) month,substr(aa.no,7,2) day,aa.id ,aa.bookno bkn,bs.title" + 
					" from  books bs, " + 
					" (select DISTINCT b.no no,b.id id, b.bookno bookno, a.bn bn from bookRent b," + 
					" (select br.id id, br.bookno bn  from bookRent br,bookReturn rb where br.id=rb.id and br.bookno = rb.bookno) a" + 
					" where b.id = a.id(+) and b.bookno = a.bn(+)) aa" + 
					" where bn is null and bs.no = aa.bookno order by aa.no");

			//JTable 초기화
			model.setNumRows(0);
			while(rs.next()){
				String[] row=new String[4];
				//row[0]=rs.getString("year"+"년"+"month"+"월"+"day"+"일");
				row[0]=rs.getString("year")+rs.getString("month")+rs.getString("day");
				row[1]=rs.getString("id");
				row[2]=rs.getString("bkn");
				row[3]=rs.getString("title");
				model.addRow(row);
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