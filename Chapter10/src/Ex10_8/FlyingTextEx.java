package Ex10_8;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
public class FlyingTextEx extends JFrame {
	private final int FLYING_UNIT = 10;
	private JLabel la = new JLabel("hello");

	public FlyingTextEx() {
		setTitle("상하좌우키");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(null);
		c.addKeyListener(new MyKeyListener());
		la.setLocation(50,50);
		la.setSize(100,20);
		c.add(la);

		setSize(300,300);
		setVisible(true);
		
		c.setFocusable(true);
		c.requestFocus();//컨텐트팬이 키 입력 받을 수 있게 포커스 강제지정
		
		//컨텐트팬에서 포커스를 잃을 경우 마우스를 클릭하면 다시 포커스를 얻게 함.
		c.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Component com = (Component)e.getSource();//마우스가 클릭된 컴포넌트
				com.setFocusable(true);
				com.requestFocus();//마우스클릭된 컴포넌트에게 포커스 
			}
		});
	}
	//key Listener 구현
	class MyKeyListener extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			//입력된 keyCode값 
			int keyCode = e.getKeyCode();
			//KeyCode 값으로 상하좌우이동
			switch(keyCode) {
			case KeyEvent.VK_UP:
				la.setLocation(la.getX(),la.getY()-FLYING_UNIT); break;
			case KeyEvent.VK_DOWN:
				la.setLocation(la.getX(),la.getY()+FLYING_UNIT); break;
			case KeyEvent.VK_RIGHT:
				la.setLocation(la.getX()+FLYING_UNIT,la.getY()); break;
			case KeyEvent.VK_LEFT:
				la.setLocation(la.getX()-FLYING_UNIT,la.getY()); break;
			}
		}
	}
	
	public static void main(String[] args) {
		new FlyingTextEx();
	}

}
