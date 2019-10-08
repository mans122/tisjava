import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MainProcess{
	public static Login login;
	public static Haksa haksa;
	
	static MainProcess main; 
	
	public static void main(String[] args) {
		
		// 硫붿씤�겢�옒�뒪 �떎�뻾
		main = new MainProcess();
		main.login = new Login(); //濡쒓렇�씤李� 蹂댁씠湲�
		main.login.setMain(main); // 濡쒓렇�씤李쎌뿉寃� 硫붿씤�겢�옒�뒪 蹂대궡湲�
		
		main.login.setTitle("학사관리 로그인");
		main.login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.login.setSize(500	, 535);
		Dimension frameSize = main.login.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		main.login.setLocation((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2);
		main.login.setResizable(false);
		main.login.setVisible(true);
	}
	
	public void showFrameTest(){
		login.dispose(); // 濡쒓렇�씤李� �떕湲�
		this.haksa = new Haksa(); // �븰�궗�봽�젅�엫 �삤�뵂
		main.haksa.setMain(main); // �븰�궗李쎌뿉寃� 硫붿씤�겢�옒�뒪 蹂대궡湲�
	}
}
