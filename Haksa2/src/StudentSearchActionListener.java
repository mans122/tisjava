import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JOptionPane;

public class StudentSearchActionListener implements ActionListener{
	static Connection conn=null;
	static Statement stmt=null;
	static String url = "jdbc:oracle:thin:@localhost:1521:myoracle";
	static String uid = "ora_user";
	static String pass = "hong";
	
	//검색한 결과를 rs에 넣어준 후, 그 결과값을 ws로 받아서 결과값을 talist에 출력해주는 메서드
	static void tableShow(ResultSet ws) {
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(url,uid,pass);
			stmt=conn.createStatement();

			//JTable 초기화
			Student.model.setNumRows(0);
			while(ws.next()){
				String[] row=new String[4];//컬럼의 갯수가 3
				row[0]=ws.getString("id");
				row[1]=ws.getString("name");
				row[2]=ws.getString("dept");
				row[3]=ws.getString("address");
				Student.model.addRow(row);
			}
			ws.close();
		}
		catch(Exception e1){
			System.out.println(e1.getMessage());
		}
	}
	public void actionPerformed(ActionEvent e) {
		String id = Student.tf_num[0].getText();
		String name = Student.tf_num[1].getText();
		String dept = Student.tf_num[2].getText();
		String address = Student.tf_num[3].getText();
		ResultSet rs = null;
		boolean isNumber = false;
		if(id.length()!=0)
		{
			if (StudentActionListener.isStringInt(id))
				isNumber=true;
			else
				isNumber=false;
		}
		//학번으로 검색
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(url,uid,pass);
			stmt=conn.createStatement();

			if(e.getSource() == Student.btnSearch[0]){
				if(id.length()==0) {
					JOptionPane.showMessageDialog(null,"값이 입력되지 않았습니다.","경고",JOptionPane.WARNING_MESSAGE);
				}
				else if(isNumber == false) {
					JOptionPane.showMessageDialog(null,"ID는 숫자만 입력해주세요","경고",JOptionPane.WARNING_MESSAGE);
				}
				else if(id.length()!=7) {
					JOptionPane.showMessageDialog(null,"ID는 7자리로 입력해주세요","경고",JOptionPane.WARNING_MESSAGE);
				}
				else {
					rs = stmt.executeQuery("select * from student where id ='"+id+"'");
					tableShow(rs);
					//textarea에 보여주던가 텍스트필드 채워놓던가
					rs = stmt.executeQuery("select * from student where id ='"+id+"'");
					while(rs.next()) {
						Student.tf_num[0].setText(rs.getString("id")); 
						Student.tf_num[1].setText(rs.getString("name"));
						Student.tf_num[2].setText(rs.getString("dept"));
						Student.tf_num[3].setText(rs.getString("address"));
					}
				}
			}
			//이름으로 검색
			if(e.getSource() == Student.btnSearch[1]){
				rs = stmt.executeQuery("select * from student where name ='"+name+"'");
				tableShow(rs);
				Student.tf_num[1].setText("");
			}
			//학과로 검색
			if(e.getSource() == Student.btnSearch[2]){
				rs = stmt.executeQuery("select * from student where dept like '%"+dept+"%'");
				tableShow(rs);
				Student.tf_num[2].setText("");
			}
			//주소로 검색
			if(e.getSource() == Student.btnSearch[3]){
				rs = stmt.executeQuery("select * from student where address like'%"+address+"%'");
				tableShow(rs);
				Student.tf_num[1].setText("");
			}
			conn.close();
		}
		catch(Exception e1 ) {
			e1.printStackTrace();
		}
	}
}



