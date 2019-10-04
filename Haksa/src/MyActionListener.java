import java.awt.event.*;
import java.sql.*;
import javax.swing.JOptionPane;

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
		//등록시 값을 받아오기위한 변수선언
		String id = new String();
		String name = new String();
		String dept = new String();
		String address = new String();

		switch(cmd) {
		case "등록":
			for(int i=0;i<4;i++) {//tf_num값 비어있는게 있는지 확인
			if(Haksa.tf_num[i].getText().length()==0 || Haksa.tf_num[i].getText().equals("")) {
					value = 1;//비었으면 value값을 1로 설정
				}
			}
			if (value != 1){
				try {
					//학번,이름,학과,주소를 순서대로 변수에 넣어줌
					id = Haksa.tf_num[0].getText();
					name = Haksa.tf_num[1].getText();
					dept = Haksa.tf_num[2].getText();
					address = Haksa.tf_num[3].getText();
					//JDBC연결
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn=DriverManager.getConnection(url,uid,pass);
					System.out.println("연결완료");//연결 잘 됬는지 확인코드
					stmt=conn.createStatement();
					
					//stmt.executeQuery("insert into student values("+id+","+name+","+dept+","+address+")");
					stmt.executeQuery("insert into student values('"+id+"','"+name+"','"+dept+"','"+address+"')");
					
					System.out.println(id);
					System.out.println(name);
					System.out.println(dept);
					System.out.println(address);
				}
				
				
				
				catch(Exception inputE) {
					inputE.printStackTrace();
				}
				for(int i=0;i<4;i++) {
					Haksa.tf_num[i].setText("");
				}
			}
			else {//비었으면 메시지 출력
				JOptionPane.showMessageDialog(null,"값이 입력되지 않았습니다.","경고",JOptionPane.WARNING_MESSAGE);
				break;
			}
			break;
//--------------------------------------------------------------			
		case "목록":
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn=DriverManager.getConnection(url,uid,pass);
				System.out.println("연결완료");
				stmt=conn.createStatement();
				rs = stmt.executeQuery("select * from student");
				
				while(rs.next()) {
					
					//Haksa.taList.append(rs.getString("id"));
					Haksa.taList.append(rs.getString("id")); //칼럼 이름대신 칼럼 인덱스도 사용 가능
					Haksa.taList.append("\t     ");
					Haksa.taList.append(rs.getString("name"));
					Haksa.taList.append("\t ");
					Haksa.taList.append(rs.getString("dept"));
					Haksa.taList.append("\t ");
					Haksa.taList.append(rs.getString("address"));
					Haksa.taList.append("\n");
				}
				rs.close();
				conn.close();
			} catch (Exception listE) {
				listE.printStackTrace();
			}
			System.out.println("목록");
			break;
//--------------------------------------------------------------
		case "수정":
			System.out.println("수정");
			break;
//--------------------------------------------------------------			
		case "삭제":
			if(JOptionPane.showConfirmDialog(null, "정말삭제하시겠습니까?", "삭제", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) 
				System.out.println("삭제처리");
			break;


		}

	}

}
