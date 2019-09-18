package Ex11_3;
import javax.swing.*;
import java.awt.*;

public class ButtonEx extends JFrame {
	public ButtonEx() {
		setTitle("이미지버튼");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		ImageIcon normalIcon = new ImageIcon("img/normalIcon.gif");
		ImageIcon rolloverIcon = new ImageIcon("img/rolloverIcon.gif");
		ImageIcon pressedIcon = new ImageIcon("img/pressedIcon.gif");
		
		JButton btn = new JButton("Call~",normalIcon);
		btn.setPressedIcon(pressedIcon);
		btn.setRolloverIcon(rolloverIcon);
		c.add(btn);
		
		setSize(250,150);
		setVisible(true);
	}

	public static void main(String[] args) {
		new ButtonEx();
	}

}
