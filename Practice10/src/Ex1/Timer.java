package Ex1;
import java.util.Calendar;
import javax.swing.JLabel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Timer extends JFrame{
	public Timer() {
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

		th.start();//타이머 스레드 실행
		setSize(300,100);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Timer();
	}
}

class TimerThread extends Thread{
	private JLabel timerLabel;
	public TimerThread(JLabel timerLabel) {
		this.timerLabel = timerLabel;
	}
	public void run() {
		while(true) {
			try {
				Calendar now = Calendar.getInstance();
				SimpleDateFormat format1 = new SimpleDateFormat ("yyyy년MM월dd일 HH:mm:ss");
				Date time = new Date();
				timerLabel.setText(format1.format(time));
				Thread.sleep(100);
			}
			catch(Exception e) {	return;	}
		}
	}
}