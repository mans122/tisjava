package Practice4;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class Practice04 {
	JFrame f = new JFrame("연습문제09-4");
	JPanel p1 = new JPanel();
	//public CenterPanel cp = new CenterPanel();
	public static CenterPanel cp = new CenterPanel();
	public NorthPanel np= new NorthPanel();
	public static JLabel[] bk = new JLabel[4];
	public static JTextField[] tf_num = new JTextField[4];
	public static Integer[] bk_num = new Integer[4];
	public static int sum =0;
	public static int[] bkp = {0,0,0,0};
	public static int gap = 0;

	public Practice04() {
		f.setTitle("연습문제9-4");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임의 x버튼을 활성화하여 닫기버튼이 실행가능해짐
		f.add(p1); // 프레임 f에 판넬p1을 추가한다.
		p1.setLayout(new BorderLayout()); // 판넬의 레이아웃을 BorderLayout으로 지정
		np.setLayout(new FlowLayout());
		p1.add(cp,BorderLayout.CENTER); //Center에 cp패널 올림
		p1.add(np,BorderLayout.NORTH);
		f.setSize(560, 500); // 프레임 사이즈 지정
		f.setVisible(true); // 프레임을 보이게 함
	}	
	//BorderLayout.NORTH 패널 생성 및 들어갈 내용 작성
	class NorthPanel extends JPanel{
		JButton input = new JButton("입력");
		public NorthPanel() {
			//라벨,텍스트필드 생성
			for(int i=0;i<4;i++) {
				bk[i] = new JLabel((i+1)+"/4분기");
				tf_num[i] = new JTextField(5);
			}
			//ActionListener 생성 후 input패널 등록
			MyActionListener ma = new MyActionListener();
			input.addActionListener(ma);
			
			
			//패널에 bk,tf_num 등록
			this.setBackground(Color.LIGHT_GRAY);
			for(int i=0;i<4;i++) {
				this.add(bk[i]);
				this.add(tf_num[i]);
			}
			this.add(input);
		}
	}

	static class CenterPanel extends JPanel{
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			System.out.println(sum);
			g.setFont(new Font("Gothic",Font.ITALIC,15));
			g.drawString("분기별 매출현황 파이차트", 570, 120);
			g.setColor(Color.BLUE);
			g.drawString("1/4분기", 480, 150);
			g.fillRect(450, 140, 20, 10);
			g.fillArc(100, 50, 300, 300, 0, bkp[0]);
			
			g.setColor(Color.RED);
			g.drawString("2/4분기", 480, 170);
			g.fillRect(450, 160, 20, 10);
			g.fillArc(100, 50, 300, 300, bkp[0],bkp[1]);
			
			g.setColor(Color.BLACK);
			g.drawString("3/4분기", 480, 190);
			g.fillRect(450, 180, 20, 10);
			g.fillArc(100, 50, 300, 300, bkp[0]+ bkp[1],bkp[2]);
			
			g.setColor(Color.MAGENTA);
			g.drawString("4/4분기", 480, 210);
			g.fillRect(450, 200, 20, 10);
			g.fillArc(100, 50, 300, 300, bkp[0]+ bkp[1]+ bkp[2],bkp[3]+gap);
		}
	}
	//MyActionListener 클래스를 외부클래스로 빼버림
	/*
	class MyActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i=0;i<4;i++) {
				bk_num[i] = Integer.parseInt(tf_num[i].getText());
				sum+=bk_num[i];
			}
			for(int i=0;i<4;i++) {
				bkp[i] = (360*bk_num[i])/sum;
				bkp[i] = Math.round(bkp[i]);
				gap += bkp[i];
				System.out.println(bkp[i]);
			}
			gap = 360-gap;
			System.out.println("gap :" + gap);
			cp.repaint();
		}
	}
*/
	public static void main(String args[]) {
		new Practice04();
	}
}