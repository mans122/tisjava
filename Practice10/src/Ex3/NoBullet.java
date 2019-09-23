package Ex3;

public class NoBullet extends Thread implements Runnable{
	public NoBullet() {}
	@Override
	public void run() {
		while(true) {
			try {
				if(MyCharacterListenerSpace.s == 1) {
					Back.noBullet.setText("장전중...");
					Thread.sleep(1000);
					MyCharacterListenerSpace.bulletCount=0;
					Back.noBullet.setVisible(false);
 					MyCharacterListenerSpace.s =0;
				}
				Back.bulletEa.setText("남은총알 : "+(99-MyCharacterListenerSpace.bulletCount));
				if(MyCharacterListenerSpace.bulletCount==99) {
					Back.noBullet.setVisible(true);
					Back.noBullet.setLocation(Back.shipLabel.getX()+70, Back.shipLabel.getY()-10);
				}
				Thread.sleep(1);
			}catch(Exception e) {
				return; 
			}

		}

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
