import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MyActionListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn=(JButton)e.getSource();
		if(btn.getText().equals("¹öÆ°1")) {
			Practice07.tf_time.setText(Practice07.time1);
		}
		
	}
	
}