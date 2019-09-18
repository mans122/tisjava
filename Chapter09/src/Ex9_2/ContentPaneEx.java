package Ex9_2;
import javax.swing.*;
import java.awt.*;
@SuppressWarnings("serial")
public class ContentPaneEx extends JFrame{
	public ContentPaneEx() {
		setTitle("ContentPane°ú JFrame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setLayout(new FlowLayout(FlowLayout.LEFT,30,40));
		
		contentPane.add(new JButton("OK"));
		contentPane.add(new JButton("Cancel"));
		contentPane.add(new JButton("Ignore"));
		
		setSize(300,200);
		setVisible(true);
	}
	public static void main(String[] args) {
		new ContentPaneEx();
	}

}
