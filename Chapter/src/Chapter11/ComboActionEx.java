package Chapter11;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class ComboActionEx extends JFrame {
	private String[] fruits = {"apple","banana","kiwi","mango"};
	private ImageIcon[] images= {
			new ImageIcon("img/apple.jpg"),
			new ImageIcon("img/banana.jpg"),
			new ImageIcon("img/kiwi.jpg"),
			new ImageIcon("img/mango.jpg")};
	private JLabel imgLabel = new JLabel(images[0]);
	private JComboBox<String> strCombo = new JComboBox<>(fruits);
	
	public ComboActionEx() {
		setTitle("ÄÞº¸");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		c.add(strCombo);
		c.add(imgLabel);
		
		strCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>)e.getSource();
				int index = cb.getSelectedIndex();
				imgLabel.setIcon(images[index]);
			}
		});
		
		setSize(300,250);
		setVisible(true);
	}
	public static void main(String[] args) {
		new ComboActionEx();
	}

}
