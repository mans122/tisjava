package Ex3;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

class BulletThread extends Thread implements Runnable{
	private JLabel bulletLabel;
	public static int stop = 0;
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
//				if(MyCharacterListener.bulletCount==99) {
//					Back.noBullet.setVisible(true);
//					Back.noBullet.setLocation(Back.shipLabel.getX()+70, Back.shipLabel.getY()-10);
//					Thread.sleep(1);
//					}
				bulletLabel.setLocation(nowX+25,nowY);
				nowY-=3;
				
				if(nowY<=0) {
					nowY=Back.shipLabel.getY();
					bulletLabel.setVisible(false);
					this.interrupt();
					break;
				}
				if((bulletLabel.getY()-3<=EnermyThread.enermyLabel.getY() && bulletLabel.getY()-3>=EnermyThread.enermyLabel.getY()-64) && (bulletLabel.getX() >= EnermyThread.enermyLabel.getX() && bulletLabel.getX()+6 <= EnermyThread.enermyLabel.getX()+64)) {
					Back.enermyLabel.setIcon(bomb);
					Thread.sleep(20);
					Back.enermyLabel.setIcon(new ImageIcon("img/enermy2.png"));
					//EnermyThread.setStop(false);
					EnermyThread.nowX=-50;
					Back.enermyLabel.setVisible(false);
					bulletLabel.setVisible(false);
					Back.count++;
					Back.killCount.setText("Kill : "+Integer.toString(Back.count));
					this.interrupt();
					break;
				}
				Thread.sleep(1);
			}
			catch(Exception e) {
				return;	
			}
//			BulletThread.interrupted();
		}
	}
}

