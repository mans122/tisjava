package Ex10_1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MyActionListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton)e.getSource();
		if(b.getText().equals("Action")) {
			b.setText("¾×¼Ç");
			System.out.println(b.getText());
		}
		else {
			b.setText("Action");
			System.out.println(b.getText());
		}
	}

}
