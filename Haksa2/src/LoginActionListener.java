import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
public class LoginActionListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		ResultSet rs = null;
		String cmd = e.getActionCommand();
		String id = Login.loginField.getText();
		String pwd = Login.pwdField.getToolTipText();
		DBManager db = new DBManager();
		switch(cmd) {
		case "로그인":
			if(id.length()==0) {
				JOptionPane.showMessageDialog(null,"ID를 입력하세요","경고",JOptionPane.WARNING_MESSAGE);
			}
			else {
				try {
					rs = DBManager.stmt.executeQuery("select count(*) as count from student where id='"+id+"'");
					rs.next();
					rs.getInt("count");
					System.out.print(id);
					if(id.equals("test") || rs.getInt("count") == 1) {
						JOptionPane.showMessageDialog(null,"로그인 성공","알림",JOptionPane.INFORMATION_MESSAGE);
						Login.main.showFrameTest(); // 메인창 메소드를 이용해 띄우기
					}
					else {
						JOptionPane.showMessageDialog(null,"로그인 실패","알림",JOptionPane.INFORMATION_MESSAGE);
					}
					rs.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			Login.loginField.setText("");
			break;

		case "회원가입":
			System.out.println(pwd);
			break;
		}
	}

}
