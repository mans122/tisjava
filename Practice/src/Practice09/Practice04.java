package Practice09;
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
	JPanel p1 = new JPanel();
	//public CenterPanel cp = new CenterPanel();
	public static CenterPanel cp = new CenterPanel(); // CenterPanel클래스로 만든 메인프레임 가운데 붙일 패널 cp생성
	public NorthPanel np= new NorthPanel();	//NotrhPanel클래스로 만든 위에붙일 패널 np 생성
	public static JLabel[] bk = new JLabel[4];//분기를 표시해줄 JLabel bk 4개 배열로 생성
	public static JTextField[] tf_num = new JTextField[4];//분기별 값을 입력받을 JTextField tf_num을 배열로 4개 생성
	public static Integer[] bk_num = new Integer[4]; //tf_num에 들어온 값을 정수형으로 변환해서 받을 정수형변수 bk_nume 을 배열로 4개생성
	public static int sum =0;//들어온 분기값들의 합을 구해줄 sum 선언
	public static int[] bkp = {0,0,0,0};//파이차트에서 분기별 각도를 구하기위해 bkp에 분기별 퍼센트를구해 각도를 저장함
	public static int gap = 0;//360도에서 모자란 만큼 4/4분기에 더해주기 위해 값을 따로 구함 

	public Practice04() {
		JFrame f = new JFrame("연습문제09-4");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임의 x버튼을 활성화하여 닫기버튼이 실행가능해짐
		f.add(p1); // 메인프레임 f에 판넬p1을 추가한다.
		p1.setLayout(new BorderLayout()); //메인에 올라간 p1판넬의 레이아웃을 BorderLayout으로 지정
		np.setLayout(new FlowLayout());//np패널은 FlowLayout으로 정렬
		p1.add(cp,BorderLayout.CENTER); //CENTER에 cp패널 올림
		p1.add(np,BorderLayout.NORTH);//NORTH에 np패널 올림
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
			//ActionListener 생성 후 input버튼 등록
			MyActionListener ma = new MyActionListener();
			input.addActionListener(ma);
			
			//패널에 bk,tf_num 등록
			this.setBackground(Color.LIGHT_GRAY);
			for(int i=0;i<4;i++) {
				this.add(bk[i]);
				this.add(tf_num[i]);
			}
			this.add(input); //input버튼 등록
		}
	}
	//CenterPanel 클래스 작성
	static class CenterPanel extends JPanel{
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			System.out.println(sum); // 입력한 값의 합이 잘 저장되었는지 확인하기위해 작성
			g.setFont(new Font("Gothic",Font.ITALIC,15));
			g.drawString("분기별 매출현황 파이차트", 570, 120);
			g.setColor(Color.BLUE);
			g.drawString("1/4분기", 480, 150);
			g.fillRect(450, 140, 20, 10);
			g.fillArc(100, 50, 300, 300, 0, bkp[0]);//최초 각도 0에서 1/4분기까지 가야함.
			
			g.setColor(Color.RED);
			g.drawString("2/4분기", 480, 170);
			g.fillRect(450, 160, 20, 10);
			g.fillArc(100, 50, 300, 300, bkp[0],bkp[1]); //1/4분기 다음부터 2/4분기가 생성되야함
			
			g.setColor(Color.BLACK);
			g.drawString("3/4분기", 480, 190);
			g.fillRect(450, 180, 20, 10);
			g.fillArc(100, 50, 300, 300, bkp[0]+ bkp[1],bkp[2]);//3/4분기의 시작은 1,2분기가 더해진 값 다음부터 생성되야함
			
			g.setColor(Color.MAGENTA);
			g.drawString("4/4분기", 480, 210);
			g.fillRect(450, 200, 20, 10);
			g.fillArc(100, 50, 300, 300, bkp[0]+ bkp[1]+ bkp[2],bkp[3]+gap);
			//4분기는 1,2,3분기 다 더해진다음 시작되야 하고, 소수점버리고 정수형으로 받다보니 1~4분기 합이 360이 안되는경우가 생김,360에서 모자란 값을 gap으로 구해서 더해줌
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