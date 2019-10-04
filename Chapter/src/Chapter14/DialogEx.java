package Chapter14;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MyDialog extends JDialog{
	private JTextField tf = new JTextField(10);
	private JButton okButton = new JButton("OK");
	
	public MyDialog(JFrame frame, String title) {
		//3번째 파라미터값에 true를 주면 modal값 true로 생성
		super(frame,title,true);
		setLayout(new FlowLayout());
		add(tf);
		add(okButton);
		setSize(200,100);
		//다이얼로그 OK버튼에 Action리스너 달기
		//익명클래스로 작성
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}
}

public class DialogEx extends JFrame{
	private MyDialog dialog;
	
	public DialogEx() {
		super("다열롴 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton btn = new JButton("Show Dialog");
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		//다이얼로그 생성
		dialog = new MyDialog(this, "Test Dialog");
		
		//Show Dialog버튼 액션리스너 연결
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(true);
			}
		});
		c.add(btn);
		getContentPane().add(btn);
		setSize(300, 300);
		setVisible(true);
	}

	public static void main(String[] args) {
		new DialogEx();
	}

}
