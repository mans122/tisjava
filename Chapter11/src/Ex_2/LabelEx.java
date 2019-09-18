package Ex_2;
import javax.swing.*;
import java.awt.*;
public class LabelEx extends JFrame{
	public LabelEx() {
		setTitle("레이블 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		ImageIcon beauty;
		//문자열 레이블 생성
		JLabel textLabel = new JLabel("사랑합니더");
		
		//이미지 레이블 생성
		beauty = new ImageIcon("img/beauty.jpg");
		JLabel imageLabel = new JLabel(beauty);
		
		//문자열 이미지 모두 가진 레이블 생성
		ImageIcon normalIcon = new ImageIcon("img/call.jpg");
		JLabel label = new JLabel("전화해라",normalIcon,SwingConstants.CENTER);
		label.setPreferredSize(new Dimension(380,200));
		label.setOpaque(true);
		label.setBackground(Color.MAGENTA);
		
		c.add(textLabel);
		c.add(imageLabel);
		c.add(label);
		
		setSize(1024,1050);
		setVisible(true);
	}
	public static void main(String[] args) {
		new LabelEx();
	}

}
