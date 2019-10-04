package Practice10;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyCharacterListener extends Thread implements KeyListener{
	//public static int bulletCount = 0;
	//private BulletThread[] bullet = new BulletThread[100];
	//public static int s;
	public MyCharacterListener(JLabel shipLabel) {}
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch(keyCode) {
		//case KeyEvent.VK_UP:
		//	Game.shipLabel.setLocation(Game.shipLabel.getX(),Game.shipLabel.getY()-Game.FLYING_UNIT); break;
		//case KeyEvent.VK_DOWN:
		//	Game.shipLabel.setLocation(Game.shipLabel.getX(),Game.shipLabel.getY()+Game.FLYING_UNIT); break;
//		case KeyEvent.VK_R:
//			s=1;
//			break;
		case KeyEvent.VK_RIGHT:
			Back.shipLabel.setLocation(Back.shipLabel.getX()+Back.FLYING_UNIT,Back.shipLabel.getY());
			if(Back.shipLabel.getX()>=730)
				Back.shipLabel.setLocation(-50,Back.shipLabel.getY());
			break;
		case KeyEvent.VK_LEFT:
			Back.shipLabel.setLocation(Back.shipLabel.getX()-Back.FLYING_UNIT,Back.shipLabel.getY());
			if(Back.shipLabel.getX()<=-50)
				Back.shipLabel.setLocation(730,Back.shipLabel.getY());
			break;
		/*case KeyEvent.VK_SPACE:
			if(bulletCount==99){
				Back.noBullet.setVisible(true);
				Back.noBullet.setText("총알이 없습니다");
			}else {
			Back.bulletLabel[bulletCount].setSize(16, 16);
			bullet[bulletCount] = new BulletThread(Back.bulletLabel[bulletCount]);
			bullet[bulletCount].start();
			bulletCount++;
			//System.out.println(bulletCount);
			}
			break;*/
		case KeyEvent.VK_F1:
			EnermyThread.setStop(true);
			EnermyThread.nowX= -50;
			Back.count = 0;
			Back.enermyLabel.setIcon(new ImageIcon("img/enermy2.png"));
			System.out.println("f1");
		}
	}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}