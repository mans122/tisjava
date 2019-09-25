package Ex3;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
class EnermyThread extends Thread{
	public static boolean running = true;
	public static JLabel enermyLabel;
	public static int nowX = -100;
	private int randomX = ((int)(Math.random()*7))+3;
	public EnermyThread(JLabel enermyLabel) {
		this.enermyLabel = enermyLabel;
	}
	public static void setStop(boolean stop) {
		running = stop;
	}
	public void run() {
		while(true) {
			try {
				while(running) {
					try {
						
						if(nowX==-50) {
							Back.enermyLabel.setVisible(true);
							randomX =((int)(Math.random()*7))+3;
						}
						enermyLabel.setLocation(nowX,10);
						nowX+=randomX;
						if(nowX>=730){
							nowX= -50;
							randomX =((int)(Math.random()*7))+3;
							break;
						}
						Thread.sleep(7);
					}
					catch(Exception e) {	return;	}
				}Thread.sleep(1);
			}
			catch(Exception g) {return;}
		}
	}
}

