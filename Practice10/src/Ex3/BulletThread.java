package Ex3;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

class BulletThread extends Thread implements Runnable{
	private JLabel bulletLabel;
	private int nowX = Back.shipLabel.getX();
	private int nowY = Back.shipLabel.getY();
	private ImageIcon bomb = new ImageIcon("img/bomb.png");
	public BulletThread(JLabel bulletLabel) {
		this.bulletLabel = bulletLabel;
	}
	public void run() {
		bulletLabel.setVisible(true);
		while(true) {
			try {
				bulletLabel.setLocation(nowX+25,nowY);
				nowY-=5;
				if(nowY<=0) {
					nowY=Back.shipLabel.getY();
					bulletLabel.setVisible(false);
					this.interrupt();
					break;
				}
				if((bulletLabel.getY()<=EnermyThread.enermyLabel.getY() && bulletLabel.getY()>=0) && (bulletLabel.getX()+6 >= EnermyThread.enermyLabel.getX() && bulletLabel.getX()+6 <= EnermyThread.enermyLabel.getX()+64)) {
					Back.enermyLabel.setIcon(bomb);
					EnermyThread.running=false;
					bulletLabel.setVisible(false);
					System.out.println("¸ÂÀ½");
					break;
				}
				Thread.sleep(20);
			}
			catch(Exception e) {
				return;	
			}
			BulletThread.interrupted();
		}
	}
}

