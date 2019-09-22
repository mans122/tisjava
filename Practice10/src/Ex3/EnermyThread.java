package Ex3;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
class EnermyThread extends Thread{
	public static boolean running = true;
	public static JLabel enermyLabel;
	private int nowX = -100;
	private int randomX = ((int)(Math.random()*7))+3;
	public EnermyThread(JLabel enermyLabel) {
		this.enermyLabel = enermyLabel;
	}
	public void run() {
//		if(running==false) {
//			enermyLabel.setLocation(nowX,10);
//			enermyLabel.setIcon(new ImageIcon("img/enermy2.png"));
//			this.running = true;
//		}
//		else {
			while(running) {
				try {
					enermyLabel.setLocation(nowX,10);
					nowX+=randomX;
					if(nowX>=730){
						nowX=0;
						randomX =((int)(Math.random()*7))+3;
						System.out.println(randomX);
					}
					//if()
					Thread.sleep(7);
				}
				catch(Exception e) {	return;	}
			}
//		}

	}
}
