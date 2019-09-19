package Practice4;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
//JFormattedTextField jTextField = new JFormattedTextField(new NumberFormatter());
public class Practice04 {
	JFrame f = new JFrame("연습문제09-4");
	JPanel p1 = new JPanel();
	public CenterPanel cp = new CenterPanel();
	public NorthPanel np= new NorthPanel();
	public int sum =0;
	public Practice04() {
		f.add(p1); // 프레임 f에 판넬p1을 추가한다.
		p1.setLayout(new BorderLayout()); // 판넬의 레이아웃을 BorderLayout으로 지정
		np.setLayout(new FlowLayout());
		p1.add(cp,BorderLayout.CENTER); //Center에 cp패널 올림
		p1.add(np,BorderLayout.NORTH);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임의 x버튼을 활성화하여 닫기버튼이 실행가능해짐
		f.setSize(560, 500); // 프레임 사이즈 지정
		f.setVisible(true); // 프레임을 보이게 함
	}	

	class NorthPanel extends JPanel{
		public JLabel[] bk = new JLabel[4];
		public JTextField[] tf_num = new JTextField[4];
		JButton input = new JButton("입력");
		//String[] bk_num = new String[4];
		Integer[] bk_num = new Integer[4];
		public NorthPanel() {
			//라벨,텍스트필드 생성
			for(int i=0;i<4;i++) {
				bk[i] = new JLabel((i+1)+"/4분기");
				tf_num[i] = new JTextField(5);
			}
			input.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for(int i=0;i<4;i++) {
						//bk_num[i] = tf_num[i].getText();
						bk_num[i] = Integer.parseInt(tf_num[i].getText());
						sum+=bk_num[i];
					}
					cp.repaint();
				}
			});


			//패널에 bk,tf_num 등록
			this.setBackground(Color.LIGHT_GRAY);
			for(int i=0;i<4;i++) {
				this.add(bk[i]);
				this.add(tf_num[i]);
			}
			this.add(input);
		}
	}

	class CenterPanel extends JPanel{
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			System.out.println(sum);
			g.drawString("분기별 매출현황 파이차트", 570, 120);
			g.setColor(Color.BLUE);
			g.fillArc(100, 50, 300, 300, 0, 58);
			g.setColor(Color.RED);
			g.fillArc(100, 50, 300, 300, 58, 78);
			g.setColor(Color.BLACK);
			g.fillArc(100, 50, 300, 300,136, 125);
			g.setColor(Color.MAGENTA);
			g.fillArc(100, 50, 300,300,261,99);
		}
	}
	public static void main(String args[]) {
		new Practice04();
	}
}