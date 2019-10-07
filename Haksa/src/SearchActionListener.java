import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JOptionPane;

public class SearchActionListener implements ActionListener{
	//검색한 결과를 rs에 넣어준 후, 그 결과값을 ws로 받아서 결과값을 talist에 출력해주는 메서드
	static void tableShow(ResultSet ws) {
		try {
			Haksa.taList.setText("");
			Haksa.taList.append("============================================\n");
			Haksa.taList.append("  학번	이름	학과	주소\n");
			Haksa.taList.append("============================================\n");

			while(ws.next()) {
				Haksa.taList.append(ws.getString("id")); 
				Haksa.taList.append("	");
				Haksa.taList.append(ws.getString("name"));
				Haksa.taList.append("	");
				Haksa.taList.append(ws.getString("dept"));
				Haksa.taList.append("	");
				Haksa.taList.append(ws.getString("address"));
				Haksa.taList.append("\n");
			}
			ws.close();
		} catch (Exception listE) {
			listE.printStackTrace();
		}
	}
	public void actionPerformed(ActionEvent e) {
		String id = Haksa.tf_num[0].getText();
		String name = Haksa.tf_num[1].getText();
		String dept = Haksa.tf_num[2].getText();
		String address = Haksa.tf_num[3].getText();

		String url = "jdbc:oracle:thin:@localhost:1521:myoracle";
		String uid = "ora_user";
		String pass = "hong";

		Connection conn=null;
		ResultSet rs = null;
		Statement stmt=null;
		
		boolean isNumber = false;
		if(id.length()!=0)
		{
			if (MyActionListener.isStringInt(id))
				isNumber=true;
			else
				isNumber=false;
		}
		//학번으로 검색
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(url,uid,pass);
			stmt=conn.createStatement();

			if(e.getSource() == Haksa.btnSearch[0]){
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
						Haksa.tf_num[0].setText(rs.getString("id")); 
						Haksa.tf_num[1].setText(rs.getString("name"));
						Haksa.tf_num[2].setText(rs.getString("dept"));
						Haksa.tf_num[3].setText(rs.getString("address"));
					}
				}
			}
			//이름으로 검색
			if(e.getSource() == Haksa.btnSearch[1]){
				rs = stmt.executeQuery("select * from student where name ='"+name+"'");
				tableShow(rs);
				Haksa.tf_num[1].setText("");
			}
			//학과로 검색
			if(e.getSource() == Haksa.btnSearch[2]){
				rs = stmt.executeQuery("select * from student where dept like '%"+dept+"%'");
				tableShow(rs);
				Haksa.tf_num[2].setText("");
			}
			//주소로 검색
			if(e.getSource() == Haksa.btnSearch[3]){
				rs = stmt.executeQuery("select * from student where address like'%"+address+"%'");
				tableShow(rs);
				Haksa.tf_num[1].setText("");
			}
			conn.close();
		}
		catch(Exception e1 ) {
			e1.printStackTrace();
		}
	}
}



