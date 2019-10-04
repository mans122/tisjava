package Chapter14;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class OptionPaneEx extends JFrame {
	public OptionPaneEx() {
		setTitle("옵션팬 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		setSize(500,300);
		c.add(new MyPanel(),BorderLayout.NORTH);
		setVisible(true);
	}
	
	class MyPanel extends Panel	{
		private JButton confirmBtn = new JButton("Confirm");
		private JButton messageBtn = new JButton("Message");
		public MyPanel() {
			add(confirmBtn);
			add(messageBtn);
			
			confirmBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
//					int result = JOptionPane.showConfirmDialog(null, "계속할?","confirm",JOptionPane.YES_NO_OPTION);
//					if(result == JOptionPane.YES_OPTION) {
//						System.out.println("삭제처리");
//					}
					if(JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?", "삭제", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
						System.out.println("삭제처리");
					}
				}
			});
		}
	}
	public static void main(String[] args) {
		new OptionPaneEx();
	}

}
