import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


@SuppressWarnings("serial")
public class Login extends JFrame implements ActionListener {
	public Login() {
		this.setTitle("Login");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = this.getContentPane();
		c.setLayout(null); // 레이아웃 사용 X
		int gap = 10;
		//ID입력 칸
		JLabel label_id = new JLabel("ID");
		label_id.setBounds(20,20,30,20); //x,y,가로크기,세로크기 설정
		c.add(label_id);
		JTextField tf_id = new JTextField();
		tf_id.setBounds(50+gap,20,150,20);
		c.add(tf_id);
		//PW입력 칸
		JLabel label_pwd = new JLabel("PWD");
		label_pwd.setBounds(20,40+gap,30,20); //x,y,가로크기,세로크기 설정
		c.add(label_pwd);
		JPasswordField tf_pwd = new JPasswordField();
		tf_pwd.setBounds(50+gap,40+gap,150,20);
		c.add(tf_pwd);
		//로그인 버튼
		JButton login = new JButton("로그인");
		login.setBounds(40,60+gap*2,80,30); //x,y,가로크기,세로크기 설정
		c.add(login);
		//취소 버튼
		JButton cancel = new JButton("회원가입");
		cancel.setBounds(130,60+gap*2,80,30); //x,y,가로크기,세로크기 설정
		c.add(cancel);
		@SuppressWarnings("unused")
		MyActionListener ma = new MyActionListener();
		//--------------------------------------------------------
		//일반적인 클래스로 MyActionListener를 생성해서 사용하는 방법
		//		login.addActionListener(ma);
		//		cancel.addActionListener(ma);
		//--------------------------------------------------------		
		//별도로 class를 생성하지 않고 ActionListener인터페이스를 상속받아 사용하는 방법
		//ActionPerformed()를 Overriding 하고 addActionListener(this)처럼 this 사용
		//login.addActionListener(this);
		//cancel.addActionListener(this);
		//--------------------------------------------------------
		//익명클래스로 이벤트리스너 작성 변수.addActionListener(new ActionListener(){} 로작성하면 오류메시지에서 unimplement 사용
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("로그인 처리");
			}});
		//-----------------------------------------------------------------//
		this.setSize(800, 600);
		this.setVisible(true);
	}
	//--------------------------------------------------------
	//클래스로 생성해서 ActionListener를 사용하는 방법
	class MyActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//		JButton btn=(JButton)e.getSource();
			//		if(btn.getText().equals("로그인")) {
			//			System.out.println("로그인 처리됨");
			//		}
			//		else if(btn.getText().equals("회원가입")) {
			//			System.out.println("회원가입 처리");
			//		}

		}

	}

	public static void main(String[] args) {
		new Login();
	}
	//--------------------------------------------------------
	//ActionListener를 상속받아 Override하여 사용하는 방법
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn=(JButton)e.getSource();
		if(btn.getText().equals("로그인")) {
			System.out.println("로그인 처리됨");
		}
		else if(btn.getText().equals("회원가입")) {
			System.out.println("회원가입 처리");
		}
	}

}



