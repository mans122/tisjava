package Practice09;
import javax.swing.*;
import java.awt.*;
public class Practice01 extends JFrame {
	private MyPanel panel = new MyPanel();
	
	public Practice01() {
		setTitle("paintComponent()예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);//Mypanel 클래스로 생성한 panel을 메인프레임에 부착
		setSize(1000,800);
		setVisible(true);
	}
	//MyPanel 클래스 생성
	class MyPanel extends JPanel{
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			//1번문제 막대그래프 그리기
			g.setFont(new Font("Jokerman",Font.ITALIC,15));
			g.drawString("분기별 매출현황 막대그래프", 80, 35);
			g.setColor(Color.BLUE);
			g.drawString("1/4분기", 10, 65);
			g.fillRect(70, 50, 150, 20);
			g.setColor(Color.RED);
			g.drawString("2/4분기", 10, 95);
			g.fillRect(70, 80, 200, 20);
			g.setColor(Color.BLACK);
			g.drawString("3/4분기", 10, 125);
			g.fillRect(70, 110, 250, 20);
			g.setColor(Color.MAGENTA);
			g.drawString("4/4분기", 10, 155);
			g.fillRect(70, 140, 320, 20);
			
			//2번 분기별 매출현황 꺾은선 그래프
			g.drawString("분기별 매출현황 꺾은선그래프", 80, 250);
			int width=50; // 분기마다 x축으로 50씩 늘리기위해 미리 50의값을가지는 변수 width 선언
			g.setColor(Color.BLUE);
			g.drawString("1/4분기", 50, 610);
			g.drawLine(50, 600, 50+width, 600-150);	//x값50에서 width만큼 증가시키고, y값 600에서 1/4분기 값만큼 빼줌
			g.drawString("2/4분기", 100, 460);
			g.drawLine(50+width, 450, 50+width*2, 600-200);
			g.drawString("3/4분기", 150, 410);
			g.drawLine(50+width*2, 400, 50+width*3, 600-250);
			g.drawString("4/4분기", 200, 360);
			g.drawLine(50+width*3, 350, 50+width*4, 600-320);
			
			//3번 분기별 매출현황 파이차트 그리기
			g.drawString("분기별 매출현황 파이차트", 570, 120);
			g.setColor(Color.BLUE);
			g.fillArc(550, 150, 200, 200, 0, 58);
			g.setColor(Color.RED);
			g.fillArc(550, 150, 200, 200, 58, 78);
			g.setColor(Color.BLACK);
			g.fillArc(550, 150, 200, 200,136, 125);
			g.setColor(Color.MAGENTA);
			g.fillArc(550, 150, 200,200,261,99);
		}
	}
	public static void main(String[] args) {
		new Practice01(); 
	}

}
