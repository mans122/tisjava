import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.*;

public class Login extends JFrame{
	public static JPanel back;
	public static Login frame;
	public static JTextField loginField;
	public static JTextField pwdField;
	public static JScrollPane scrollPane;
	ImageIcon icon;
	public Login() {
		icon = new ImageIcon("img/back1.jpg");

		back = new JPanel(null) {
			public void paintComponent(Graphics g) {
				//g.drawImage(icon.getImage(), 0, 0, null);
				Dimension d = getSize();
				g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
				setOpaque(false); //그림을 표시하게 설정,투명하게 조절
				super.paintComponent(g);
			}
		};
		
		//로그인 칸
		JLabel loginLabel = new JLabel("ID");
		loginLabel.setFont(new Font("Serif",Font.BOLD,22));
		loginLabel.setLocation(350, 145);
		loginLabel.setSize(loginLabel.getPreferredSize().getSize());
		
		loginField = new JTextField();
		loginField.setLocation(350,180);
		loginField.setSize(200,30);
		
		JButton loginButton = new JButton("로그인");
		loginButton.setFont(new Font("Serif",Font.BOLD,15));
		//loginButton.setSize(loginButton.getPreferredSize().getSize());
		loginButton.setSize(100, 32);
		loginButton.setLocation(580, 180);
		
		//비밀번호 칸
		JLabel pwdLabel = new JLabel("PASSWORD");
		pwdLabel.setFont(new Font("Serif",Font.BOLD,22));
		pwdLabel.setLocation(350, 230);
		pwdLabel.setSize(pwdLabel.getPreferredSize().getSize());
		
		pwdField = new JTextField();
		pwdField.setLocation(350,265);
		pwdField.setSize(200,30);
		
		//회원가입 버튼
		JButton signUp = new JButton("회원가입");
		signUp.setFont(new Font("Serif",Font.BOLD,15));
		//signUp.setSize(pwdButton.getPreferredSize().getSize());
		signUp.setSize(100, 32);
		//System.out.print(signUp.getPreferredSize().getSize());
		signUp.setLocation(580, 265);
		
		LoginActionListener la = new LoginActionListener();
		loginButton.addActionListener(la);
		signUp.addActionListener(la);
		
		back.add(loginField);
		back.add(loginLabel);
		back.add(loginButton);
		
		back.add(pwdLabel);
		back.add(pwdField);
		back.add(signUp);

		scrollPane = new JScrollPane(back);
		setContentPane(scrollPane);

	}
	public static void main(String[] args) {
		frame = new Login();
		frame.setTitle("학사관리 로그인");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 600);
		Dimension frameSize = frame.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2);
		frame.setResizable(false);
		frame.setVisible(true);
	}

}
