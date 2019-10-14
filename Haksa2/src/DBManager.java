import java.sql.*;

public class DBManager {
	public static Connection conn= null;
	public static Statement stmt = null;
//	String url = "jdbc:mysql://localhost:3306/sampledb?useSSL=false";
//	String uid = "hkd";
//	String pass = "1234";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String uid = "ora_user";
	String pass = "hong";
	public DBManager() {}
	public void Connection() {
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			DBManager.conn=DriverManager.getConnection(url,uid,pass);
			stmt=conn.createStatement();
			System.out.print("연결완료~");
		}catch(Exception e1){
			e1.printStackTrace();
		}
	}
	
	public void Close() {
		try {
			DBManager.conn.close();
			stmt.close();
			System.out.println("연결종료~");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
	}
}
