import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

class MenuActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			switch(cmd) {
			case "Load":
				break;
			case "Hide":
				break;
			case "ReShow":
				break;
			case "Exit":
				System.exit(0);
				break;
			}
		}
		
	}