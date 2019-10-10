import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
select substr(aa.no,1,4) year,substr(aa.no,5,2) month,substr(aa.no,7,2) day,aa.id id,aa.bookno bn,bs.title
from  books bs,   
(select DISTINCT b.no no,b.id id,b.bookno bookno,a.bn bn from bookRent b,
(select br.id id, br.bookno bn  from bookRent br,bookReturn rb where br.id=rb.id and br.bookno = rb.bookno) a
where b.id = a.id(+) and b.bookno = a.bn(+)) aa
where bn is null and bs.no = aa.bookno order by aa.no;

 */
public class NoReturnBook extends JPanel {
	DefaultTableModel model = null;
	JTable table=null;
	public NoReturnBook() {
		System.out.println("미반납 책");
		setLayout(null);
		String colName[]={"대여날짜","학번","책번호","책이름"}; // 표에 출력할 칼럼명
		model=new DefaultTableModel(colName,0); // 표의 데이터
		table = new JTable(model); // 테이블에 모델(데이터) 바인딩
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		JScrollPane jp = new JScrollPane(table);
		jp.setSize(new Dimension(460,250));
		jp.setLocation(10, 140);
		add(jp);
		table.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				table = (JTable)e.getComponent();
				model=(DefaultTableModel)table.getModel();
				//model.getValueAt의 값이 object라 String으로 변환해준다
//				String id = (String)model.getValueAt(table.getSelectedRow(), 0); //선택한 값의 id를 구한것
//				String name = (String)model.getValueAt(table.getSelectedRow(), 1);
//				String dept = (String)model.getValueAt(table.getSelectedRow(), 2);
//				String address = (String)model.getValueAt(table.getSelectedRow(), 3);
//				
//				tf_num[0].setText(id);
//				tf_num[1].setText(name);
//				tf_num[2].setText(dept);
//				tf_num[3].setText(address);
			}
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			
		});
		try{
			// Select문 실행
			ResultSet rs=DBManager.stmt.executeQuery("select * from student order by id");

			//JTable 초기화
			Student.model.setNumRows(0);
			while(rs.next()){
				String[] row=new String[4];
				row[0]=rs.getString("year"+"년"+"month"+"월"+"day"+"일");
				row[1]=rs.getString("id");
				row[2]=rs.getString("bookno");
				row[3]=rs.getString("title");
				Student.model.addRow(row);
			}
			rs.close();
		}
		catch(Exception e1){
			System.out.println(e1.getMessage());
		}
	
		setSize(500,500);
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
