package Ex3;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Game extends JFrame{
	public final static int FLYING_UNIT = 10;
	public static JLabel shipLabel = new JLabel();
	public static JLabel enermyLabel = new JLabel();
	public Game() {
		JLabel backImgLabel = new JLabel();
		Container c = getContentPane();
		
		c.setLayout(null);
		c.addKeyListener(new MyCharacterListener(backImgLabel));
		setTitle("게임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//배경이미지넣기 ------------------------------------------
		//backImgLabel.setIcon(new ImageIcon("img/bgi.jpg"));
		//backImgLabel.setSize(730, 500);
		//backImgLabel.setLocation(0,0);
		//------------------------------------------------------
		
		//내 캐릭터 구현 --------------------------------------------
		shipLabel.setIcon(new ImageIcon("img/ship.png"));
		shipLabel.setLocation(350, 400);
		shipLabel.setSize(60, 60);
		//------------------------------------------------------
		//적 비행기 구현 ---------------------------------------------
		enermyLabel.setIcon(new ImageIcon("img/enermy2.png"));
		enermyLabel.setLocation(350, 20);
		enermyLabel.setSize(64, 64);
		enermyThread enermyThread = new enermyThread(enermyLabel);
		
		//------------------------------------------------------
		c.setFocusable(true);
		c.requestFocus();
		c.add(backImgLabel);
		c.add(enermyLabel);
		c.add(shipLabel);
		enermyThread.start();
		
		//포커스 잃으면 마우스클릭으로 포커스잡아오기==============================
		c.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Component com = (Component)e.getSource();//마우스가 클릭된 컴포넌트
				com.setFocusable(true);
				com.requestFocus();//마우스클릭된 컴포넌트에게 포커스 
			}
		});
		//===========================================================
		
		setSize(730,500);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Game();
	}
}

class enermyThread extends Thread{
	private JLabel enermyLabel;
	private int nowX =0;
	private int randomX = ((int)(Math.random()*10))+3;
	public enermyThread(JLabel enermyLabel) {
		this.enermyLabel = enermyLabel;
	}
	public void run() {
		//int randomX = random.nextInt(20)+1; 
		while(true) {
			try {
				enermyLabel.setLocation(nowX,10);
				nowX+=randomX;
				if(nowX>=730){
					nowX=0;
					randomX =((int)(Math.random()*10))+3;
					System.out.println(randomX);
				}
				Thread.sleep(7);
			}
			catch(Exception e) {	return;	}
		}
	}
}
