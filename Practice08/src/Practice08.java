import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
//연습문제8 ㅁㅁ
public class Practice08 extends JFrame{
	private int index = 1;
	private JButton btnLeft;
	private JButton btnRight;
	private ImageIcon[] image = {
			new ImageIcon("img/1.png"),
			new ImageIcon("img/2.png"),
			new ImageIcon("img/3.png"),
			new ImageIcon("img/4.png"),
			new ImageIcon("img/5.png"),
			new ImageIcon("c:\\myPhoto\\6.png")
	};
	private JLabel imageLabel = new JLabel();
	public Practice08() {
		setTitle("연습문제 08");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER,0,50));
		//JPanel imgPanel = new JPanel();
		//c.add(imgPanel);
		imageLabel.setIcon(image[1]);
		c.add(imageLabel);
		
		ImageIcon previous = new ImageIcon("img/previous.png");
		ImageIcon next = new ImageIcon("img/next.png");
		this.btnLeft = new JButton(previous);
		this.btnRight = new JButton(next);
		c.add(btnLeft);
		c.add(btnRight);
		btnLeft.addActionListener(new MyItemListener());
		btnRight.addActionListener(new MyItemListener());



		setSize(512,1000);
		setVisible(true);
	}

	class MyItemListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnLeft) {
				index --;
				if(index >= 0) {
					imageLabel.setIcon(image[index]);
				}
				else {
					index = image.length-1;
					imageLabel.setIcon(image[index]);	
				}
			}
			else {
				index ++;
				if(index <= image.length-1) {
					imageLabel.setIcon(image[index]);
				}
				else {
					index = 0;
					imageLabel.setIcon(image[index]);	
				}
	
			}
		}
	}
	public static void main(String[] args) {
		new Practice08();
	}

}
