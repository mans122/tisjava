import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MainProcess{
	public static Login login;
	public static Haksa haksa;
	static MainProcess main;
	public static void main(String[] args) {
		DBManager db = new DBManager();
		db.Connection();
		main = new MainProcess();
		MainProcess.login = new Login(); 
		MainProcess.login.setMain(main); 
		MainProcess.login.setTitle("학사관리 로그인");
		MainProcess.login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainProcess.login.setSize(500	, 300);
		Dimension frameSize = MainProcess.login.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		MainProcess.login.setLocation((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2);
		MainProcess.login.setResizable(false);
		MainProcess.login.setVisible(true);
	}
	
	public void showFrameTest(){
		login.dispose(); 
		MainProcess.haksa = new Haksa(); 
		MainProcess.haksa.setMain(main); 
	}
	public void showFrameLogin(){
		MainProcess.haksa.dispose();
		MainProcess.login = new Login(); 
		MainProcess.login.setMain(main); 
		MainProcess.login.setTitle("학사관리 로그인");
		MainProcess.login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainProcess.login.setSize(500	, 300);
		Dimension frameSize = MainProcess.login.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		MainProcess.login.setLocation((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2);
		MainProcess.login.setResizable(false);
		MainProcess.login.setVisible(true);
	}

}
