package Haksa2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
public class LoginActionListener2 implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs = null;
		String cmd = e.getActionCommand();
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String uid = "ora_user";
		String pass = "hong";
		String id = Login2.loginField.getText();
		String pwd = Login2.pwdField.getText();
		
		int value = 0;

		switch(cmd) {
		case "로그인":
			if(id.length()==0) {
				JOptionPane.showMessageDialog(null,"ID를 입력하세요","경고",JOptionPane.WARNING_MESSAGE);
			}
			else {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn=DriverManager.getConnection(url,uid,pass);
					stmt=conn.createStatement();
					rs = stmt.executeQuery("select count(*) as count from student where id='"+id+"'");
					rs.next();
					rs.getInt("count");
					if(rs.getInt("count") == 1) {
						JOptionPane.showMessageDialog(null,"로그인 성공","알림",JOptionPane.INFORMATION_MESSAGE);
						Login2.frame.setEnabled(false);
						new Haksa2();
					}
					else {
						JOptionPane.showMessageDialog(null,"로그인 실패","알림",JOptionPane.INFORMATION_MESSAGE);
					}
					rs.close();
					conn.close();
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			Login2.loginField.setText("");
			break;

		case "회원가입":
			System.out.println(pwd);
			break;
		}
	}

}
