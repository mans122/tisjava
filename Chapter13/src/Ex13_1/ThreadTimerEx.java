package Ex13_1;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
class ThreadA extends Thread{
	public void run() {
		
		for(int i=0;i<50;i++) {
			System.out.println("ThreadA");
			try {
				//Thread.sleep(100);				
			}
			catch(Exception e) {}}}}
class ThreadB extends Thread{
	public void run() {
		
		for(int i=0;i<100;i++) {
			//System.out.println("ThreadB");
			try {
				//Thread.sleep(100);
			}
			catch(Exception e) {}}}}
class ThreadC implements Runnable{
	@Override
	public void run() {
		for(int i=0;i<50;i++) {
			System.out.println("ThreadC");
		}
	}

}
class TimerThread extends Thread{
	private JLabel timerLabel;


	public TimerThread(JLabel timerLabel) {
		this.timerLabel = timerLabel;
	}

	@Override
	public void run() {
		int n=0;
		while(n!=50) {
			n++;
			//timerLabel.setText(Integer.toString(n));
			//			Calendar now = Calendar.getInstance();
			//			SimpleDateFormat format1 = new SimpleDateFormat ("yyyy년MM월dd일 HH:mm:ss");
			//			Date time = new Date();
			//			timerLabel.setText(format1.format(time));
			System.out.println("TimerThread");
			try {
				//Thread.sleep(100);
			}
			catch(Exception e) {	return;	}

		}
	}
}
public class ThreadTimerEx extends JFrame{
	public ThreadTimerEx() {
		setTitle("타이머예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		//타이머 값을 출력할 레이블 생성
		JLabel timerLabel = new JLabel();
		timerLabel.setFont(new Font("Gothic",Font.ITALIC,20));
		c.add(timerLabel);//레이블을 컨텐트팬에 부착

		//타이머 스레드 객체 생성. 타이머 값을 출력할 레이블을 생성자에 전달
		TimerThread th = new TimerThread(timerLabel);
		ThreadA ta = new ThreadA();
		ThreadB tb = new ThreadB();
		ThreadC tc = new ThreadC();
		Thread threadC= new Thread(tc);
		th.start();//타이머 스레드 실행
		ta.start();
		tb.start();
		threadC.start();
		ta.setPriority(5);
		tb.setPriority(10);
		th.setPriority(3);
		threadC.setPriority(1);


		for (int i = 0; i<50 ;i ++) {System.out.println("MainThread");	}

		//setSize(300,100);
		//setVisible(true);


	}
	public static void main(String[] args) {
		new ThreadTimerEx();
		new ThreadA();
		new ThreadB();
		long id = Thread.currentThread().getId();
		String name = Thread.currentThread().getName();
		int priority = Thread.currentThread().getPriority();
		Thread.State s = Thread.currentThread().getState();
		System.out.println("현재 스레드 이름 = "+name);
		System.out.println("현재 스레드 ID = "+id);
		System.out.println("현재 스레드 우선순위값 = "+priority);
		System.out.println("현재 스레드 상태 = "+s);
		
	}

}
