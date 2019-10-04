package Chapter13;
import java.util.Calendar;
import javax.swing.JLabel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimerThread extends Thread{
	private JLabel timerLabel;
	public TimerThread(JLabel timerLabel) {
		this.timerLabel = timerLabel;
	}

	@Override
	public void run() {
		int n=0;
		while(n!=50) {
			try {
			n++;
			timerLabel.setText(Integer.toString(n));
						Calendar now = Calendar.getInstance();
						SimpleDateFormat format1 = new SimpleDateFormat ("yyyy≥‚MMø˘dd¿œ HH:mm:ss");
						Date time = new Date();
						timerLabel.setText(format1.format(time));
			Thread.sleep(100);
			}
			catch(Exception e) {	return;	}

		}
	}
}