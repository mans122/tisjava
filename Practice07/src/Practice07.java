import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

class MyActionListener2 extends JFrame implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn=(JButton)e.getSource();
		if(btn.getText().equals("버튼2")) {
			//Practice07.tf_time.setText(now.get(Calendar.YEAR)+"년"+(now.get(Calendar.MONTH)+1)+"월"+now.get(Calendar.DAY_OF_MONTH)+"일");
			if(Practice07.tf_time.getText().equals("")) {
				Practice07.tf_time.setText(Practice07.time1);
				}
			else {
				Practice07.tf_time.setText("");
			}
		}
	}
}



public class Practice07 extends JFrame implements ActionListener {
	public static JTextField tf_time=null;
	public static String time1;
	public Practice07() {
		Calendar now = Calendar.getInstance();
		setTitle("연습문제 7번");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		SimpleDateFormat format1 = new SimpleDateFormat ("yyyy년MM월dd일 HH:mm:ss");
		Date time = new Date();
		Practice07.time1 = format1.format(time);
		//---------------------------------
		//1번문제
		/*
		c.setLayout(new FlowLayout());
		c.add(new JLabel("이름"));
		c.add(new JTextField(20));
		c.add(new JLabel("학과"));
		c.add(new JTextField(20));
		c.add(new JLabel("주소"));
		c.add(new JTextField(20));
		 */
		//---------------------------------
		//2번문제
		c.setLayout(null);
		JLabel label_time = new JLabel("현재시간");
		label_time.setBounds(50,20,100,30);
		c.add(label_time);
		Practice07.tf_time = new JTextField("");
		Practice07.tf_time.setBounds(150,20,180,30);
		c.add(tf_time);

		JButton btn1 = new JButton("버튼1");
		btn1.setBounds(10,70,80,30);
		c.add(btn1);

		JButton btn2 = new JButton("버튼2");
		btn2.setBounds(100,70,80,30);
		c.add(btn2);
		JButton btn3 = new JButton("버튼3");
		btn3.setBounds(190,70,80,30);
		c.add(btn3);
		btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton btn=(JButton)e.getSource();
				if(btn.getText().equals("버튼3")) {
					Practice07.tf_time.setText(Practice07.time1);
				}
			}
		});

		JButton btn4 = new JButton("버튼4");
		btn4.setBounds(280,70,80,30);
		c.add(btn4);
		btn4.addActionListener(this);

		MyActionListener ma = new MyActionListener();
		btn1.addActionListener(ma);
		MyActionListener2 ma2 = new MyActionListener2();
		btn2.addActionListener(ma2);

		//3번문제
		JLabel label_time2 = new JLabel("Good Day");
		label_time2.setBounds(150,120,100,30);
		c.add(label_time2);

		if(now.get(Calendar.HOUR_OF_DAY)<12) {
			label_time2.setText("Good Morning");
		}else if(now.get(Calendar.HOUR_OF_DAY)<18) {
			label_time2.setText("Good Afternoon");
		}else{
			label_time2.setText("Good Evening");
		}

		setSize(400, 500);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Practice07();
	}

	@Override
	public void actionPerformed(ActionEvent e){
		JButton btn=(JButton)e.getSource();
		if(btn.getText().equals("버튼4")) {
			Practice07.tf_time.setText(Practice07.time1);
		}

	}

}
