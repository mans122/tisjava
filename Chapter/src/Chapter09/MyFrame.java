package Chapter09;
import javax.swing.*;
@SuppressWarnings("serial")

public class MyFrame extends JFrame {
	public MyFrame() {
		this.setTitle("300x300 스윙 프레임 만들기");
		this.setSize(1000,800);
		this.setVisible(true);
	}
public static class MyApp{	
	public static void main(String[] args) {
		new MyFrame();
	}
}
}
