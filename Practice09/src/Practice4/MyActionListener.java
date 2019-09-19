package Practice4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyActionListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<4;i++) {
			Practice04.bk_num[i] = Integer.parseInt(Practice04.tf_num[i].getText());
			Practice04.sum+=Practice04.bk_num[i];
		}
		for(int i=0;i<4;i++) {
			Practice04.bkp[i] = (360*Practice04.bk_num[i])/Practice04.sum;
			Practice04.bkp[i] = Math.round(Practice04.bkp[i]);
			Practice04.gap += Practice04.bkp[i];
			System.out.println(Practice04.bkp[i]);
		}
		Practice04.gap = 360-Practice04.gap;
		System.out.println("gap :" + Practice04.gap);
		Practice04.cp.repaint();
	}
}
