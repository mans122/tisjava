import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class MyActionListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		int value = 0;
		switch(cmd) {
		case "등록":
			for(int i=0;i<4;i++) {//tf_num값 비어있는게 있는지 확인
				if(Haksa.tf_num[i].getText().length()==0 || Haksa.tf_num[i].getText().equals("")) {
					value = 1;//비었으면 value값을 1로 설정
				}
			}
			if(value==1) {//비었으면 메시지 출력
				JOptionPane.showMessageDialog(null,"값이 입력되지 않았습니다.","경고",JOptionPane.WARNING_MESSAGE);
			}
			else {
				System.out.println("등록");
				for(int i=0;i<4;i++) {
					Haksa.tf_num[i].setText("");
				}
			}
			break;
		case "목록":
			System.out.println("목록");
			break;
		case "수정":
			System.out.println("수정");
			break;
		case "삭제":
			if(JOptionPane.showConfirmDialog(null, "정말삭제하시겠습니까?", "삭제", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) 
				System.out.println("삭제처리");
			break;
		}
	}
	
}
