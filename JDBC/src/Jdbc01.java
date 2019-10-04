import java.sql.*;

public class Jdbc01 {
	/*
	static Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String uid = "hr";
	String pass = "1234";
	String sql = "select * from members";
	*/

	public static void main(String[] args) {
		Connection conn=null;
		Statement stmt=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");// jdbc driver load
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:myoracle","ora_user","hong");// 연결
			System.out.println("연결완료");

			//excuteQuery안에 내용이 길어지니 변수에 원하는 sql문을 넣고 executeQuery값에 넣는게 이상적임
			stmt =conn.createStatement();
			//Insert문
			//stmt.executeQuery("insert into student(id,name,dept) values('1234567','김덕배','도박학과')");
			//Update문
			//stmt.executeQuery("update student set dept='철학과' where name='이기자'");
			//Delete문
			//stmt.executeQuery("Delete student where name='이기자'");
			//Select문
			ResultSet rs = stmt.executeQuery("select * from student");
			System.out.println("== 학번 ==== 이름 === 학과 ==");
			while(rs.next()) {
				//System.out.print(rs.getString("id")); //칼럼 이름대신 칼럼 인덱스도 사용 가능
				System.out.print(rs.getString(1));
				System.out.print("\t     ");
				System.out.print(rs.getString("name"));
				System.out.print("\t ");
				System.out.println(rs.getString("dept"));
			}
			rs.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("연결안됨");
			e.printStackTrace();
		}
	}
}
