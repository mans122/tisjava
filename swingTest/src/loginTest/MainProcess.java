package loginTest;

import javax.swing.JFrame;

public class MainProcess{
	LoginView loginView;
	TestFrm testFrm;
	
	public static void main(String[] args) {
		
		// 메인클래스 실행
		MainProcess main = new MainProcess();
		main.loginView = new LoginView(); //로그인창 보이기
		main.loginView.setMain(main); // 로그인창에게 메인클래스 보내기
	}
	
	// �뀒�뒪�듃�봽�젅�엫李�
	public void showFrameTest(){
		loginView.dispose(); // 로그인창 닫기
		this.testFrm = new TestFrm(); // 테스트프레임 오픈
	}

}
