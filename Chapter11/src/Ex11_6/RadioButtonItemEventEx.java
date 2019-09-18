package Ex11_6;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
public class RadioButtonItemEventEx extends JFrame{
	private JRadioButton[] radio = new JRadioButton[3];
	private String[] text = {"사과","배","체리"};
	private ImageIcon[] image = {
			new ImageIcon("img/apple.jpg"),
			new ImageIcon("img/pear.jpg"),
			new ImageIcon("img/cherry.jpg")};
	private JLabel imageLabel = new JLabel();

	public RadioButtonItemEventEx() {
		setTitle("라디오 버튼");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());

		JPanel radioPanel = new JPanel();
		radioPanel.setBackground(Color.GRAY);

		ButtonGroup g =new ButtonGroup();
		for(int i =0;i<radio.length;i++) {
			radio[i] = new JRadioButton(text[i]);
			g.add(radio[i]);
			radioPanel.add(radio[i]);
			radio[i].addItemListener(new MyItemListener());
		}

		radio[2].setSelected(true);
		c.add(radioPanel,BorderLayout.NORTH);
		c.add(imageLabel,BorderLayout.CENTER);
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

		setSize(250,200);
		setVisible(true);

	}

	//Item리스너 작성
	class MyItemListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.DESELECTED) {return;	}
			//선택이 해제된 경우 그냥 리턴(아무것도 실행하지 않겠다.)
			if(radio[0].isSelected()) {
				imageLabel.setIcon(image[0]);}
			else if(radio[1].isSelected()) {
				imageLabel.setIcon(image[1]);}
			else{
				imageLabel.setIcon(image[2]);}
		}
	}
	public static void main(String[] args) {
		new RadioButtonItemEventEx();
	}

}
