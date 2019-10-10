import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class BookRent extends JPanel{
	ArrayList<String> deptName = new ArrayList<>();
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

	public static void main(String[] args) {
	}
}