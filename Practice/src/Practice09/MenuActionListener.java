package Practice09;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MenuActionListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		switch(cmd) {
		case "apple":
		Self.imageLabel.setIcon(new ImageIcon("img2/apple.jpg"));
			break;
		case "banana":
			System.out.println("¹Ù³ª³ª");
			Self.imageLabel.setIcon(new ImageIcon("img2/banana.jpg"));
			break;
		case "kiwi":
			Self.imageLabel.setIcon(new ImageIcon("img2/kiwi.jpg"));
			break;
		case "mango":
			Self.imageLabel.setIcon(new ImageIcon("img2/mango.jpg"));
			break;
		}
	}
	
}