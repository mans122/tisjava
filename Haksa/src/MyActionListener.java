import java.awt.event.*;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class MyActionListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		//입력받는 액션의 이름을 String형태의 변수 cmd에 저장
		String cmd = e.getActionCommand();

		//JDBC 연결을 위한 변수 선언
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs = null;
		String url = "jdbc:oracle:thin:@localhost:1521:myoracle";
		String uid = "ora_user";
		String pass = "hong";
		
		//값비었는지 확인하기위한 value값 변수 선언
		int value = 0;
		
		//값을 받아오기위한 변수선언 및 값 입력
		String id = Haksa.tf_num[0].getText();
		String name = Haksa.tf_num[1].getText();
		String dept = Haksa.tf_num[2].getText();
		String address = Haksa.tf_num[3].getText();

		switch(cmd) {
		case "등록":
			for(int i=0;i<4;i++) {//tf_num값 비어있는게 있는지 확인
			if(Haksa.tf_num[i].getText().length()==0 || Haksa.tf_num[i].getText().equals("")) {
					value = 1;//비었으면 value값을 1로 설정
				}
			}
			if (value != 1){
				try {
					//JDBC연결
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn=DriverManager.getConnection(url,uid,pass);
					stmt=conn.createStatement();
					//입력받은 값 삽입하는 코드
					stmt.executeQuery("insert into student values('"+id+"','"+name+"','"+dept+"','"+address+"')");
					
					conn.close();
				}
				catch(Exception inputE) {
					inputE.printStackTrace();
				}
			}
			else {//비었으면 메시지 출력
				JOptionPane.showMessageDialog(null,"값이 입력되지 않았습니다.","경고",JOptionPane.WARNING_MESSAGE);
				break;
			}
			//입력이 완료된 텍스트필드를 비워주기위한 코드
			for(int i=0;i<4;i++) {
				Haksa.tf_num[i].setText("");
			}
			break;
//--------------------------------------------------------------			
		case "목록":
			try {
				//목록을 누를때마다 새로 입력하기위해 setText에 아무값도 입력하지않고 초기화함
				Haksa.taList.setText("");
				Haksa.taList.append("============================================\n");
				Haksa.taList.append("  학번	이름	학과	주소\n");
				Haksa.taList.append("============================================\n");

				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn=DriverManager.getConnection(url,uid,pass);
				stmt=conn.createStatement();
				rs = stmt.executeQuery("select * from student order by id");
				
				while(rs.next()) {
					//DB에서 값을 받아와서 taList에 추가해줌, 구분을위해 공백도 따로 입력해줌
					Haksa.taList.append(rs.getString("id")); //칼럼 이름대신 칼럼 인덱스도 사용 가능
					Haksa.taList.append("	");
					Haksa.taList.append(rs.getString("name"));
					Haksa.taList.append("	");
					Haksa.taList.append(rs.getString("dept"));
					Haksa.taList.append("	");
					Haksa.taList.append(rs.getString("address"));
					Haksa.taList.append("\n");
				}
				rs.close();
				conn.close();
			} catch (Exception listE) {
				listE.printStackTrace();
			}
			break;
//--------------------------------------------------------------
		case "수정":
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn=DriverManager.getConnection(url,uid,pass);
				stmt=conn.createStatement();
				//id의 길이가 0이 아닐때 길이가 0이 아닌것들을 update해줌
				if(id.length()!=0) {
					if(name.length()!=0) {
						stmt.executeQuery("update student set name='"+name+"' where id = '"+id+"'");	}
					if(dept.length()!=0) {
						stmt.executeQuery("update student set dept='"+dept+"' where id = '"+id+"'");	}
					if(address.length()!=0) {
						stmt.executeQuery("update student set address='"+address+"' where id = '"+id+"'");	}
					JOptionPane.showMessageDialog(null,"수정이 완료되었습니다.","알림",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"수정할 학번을 입력해주세요","경고",JOptionPane.WARNING_MESSAGE);
					break;
				}
				conn.close();
			}
			catch(Exception inputE) {
				inputE.printStackTrace();
			}
			for(int i=0;i<4;i++) {
				Haksa.tf_num[i].setText("");
			}
			break;
//--------------------------------------------------------------			
		case "삭제":
			if(JOptionPane.showConfirmDialog(null, "정말삭제하시겠습니까?", "삭제", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
				if(id.length()!=0) {
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						conn=DriverManager.getConnection(url,uid,pass);
						stmt=conn.createStatement();
						stmt.executeQuery("delete from student where id = '"+id+"'");

						conn.close();
					}
					catch(Exception deleteE) {
						deleteE.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null,"삭제할 학번을 입력해주세요","경고",JOptionPane.WARNING_MESSAGE);
				}
			}
			break;
		}
		//검색버튼 별로 그 조건에 맞는 결과만 출력
		if(e.getSource() == Haksa.btnSearch[0]){
			try {
				Haksa.taList.setText("");
				Haksa.taList.append("============================================\n");
				Haksa.taList.append("  학번	이름	학과	주소\n");
				Haksa.taList.append("============================================\n");

				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn=DriverManager.getConnection(url,uid,pass);
				stmt=conn.createStatement();
				rs = stmt.executeQuery("select * from student where id="+id+" order by id");

				while(rs.next()) {
					//DB에서 값을 받아와서 taList에 추가해줌, 구분을위해 공백도 따로 입력해줌
					Haksa.taList.append(rs.getString("id")); //칼럼 이름대신 칼럼 인덱스도 사용 가능
					Haksa.taList.append("	");
					Haksa.taList.append(rs.getString("name"));
					Haksa.taList.append("	");
					Haksa.taList.append(rs.getString("dept"));
					Haksa.taList.append("	");
					Haksa.taList.append(rs.getString("address"));
					Haksa.taList.append("\n");
				}
				rs.close();
				conn.close();
			} catch (Exception listE) {
				listE.printStackTrace();
			}
		}

	}
}

