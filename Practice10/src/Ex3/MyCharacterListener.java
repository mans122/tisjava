package Ex3;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;

public class MyCharacterListener extends Thread implements KeyListener{
	public MyCharacterListener(JLabel shipLabel) {}
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch(keyCode) {
		//case KeyEvent.VK_UP:
		//	Game.shipLabel.setLocation(Game.shipLabel.getX(),Game.shipLabel.getY()-Game.FLYING_UNIT); break;
		//case KeyEvent.VK_DOWN:
		//	Game.shipLabel.setLocation(Game.shipLabel.getX(),Game.shipLabel.getY()+Game.FLYING_UNIT); break;
		case KeyEvent.VK_RIGHT:
			Game.shipLabel.setLocation(Game.shipLabel.getX()+Game.FLYING_UNIT,Game.shipLabel.getY()); break;
		case KeyEvent.VK_LEFT:
			Game.shipLabel.setLocation(Game.shipLabel.getX()-Game.FLYING_UNIT,Game.shipLabel.getY()); break;
	}
	}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}