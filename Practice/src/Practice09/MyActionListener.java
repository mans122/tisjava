package Practice09;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//input버튼을 MyActionListener에 등록했으므로 이벤트가 발생하면 실행됨
public class MyActionListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<4;i++) {
			//bk_num에  사용자가 입력한 텍스트값을 정수형으로 변환해서 저장함.
			Practice04.bk_num[i] = Integer.parseInt(Practice04.tf_num[i].getText());
			Practice04.sum+=Practice04.bk_num[i]; // sum에 들어오는 값들을 모두 저장함.
		}
		for(int i=0;i<4;i++) {
			//분기의 퍼센트비율 각도값은 분기값/분기값합*360 임.
			Practice04.bkp[i] = (360*Practice04.bk_num[i])/Practice04.sum;
			Practice04.bkp[i] = Math.round(Practice04.bkp[i]);//Math.round메소드로 소수점 반올림해줌
			Practice04.gap += Practice04.bkp[i];//gap에 모든 분기값을 더해서 넣어줌
			System.out.println(Practice04.bkp[i]);//분기값이 잘들어갔는지 확인하기위한 코드
		}
		Practice04.gap = 360-Practice04.gap;//소수점반올림한 분기값의 합이 360이 안될경우 그 차이값을 구하기위한 코드
		System.out.println("gap :" + Practice04.gap);//gap값이 잘 들어갔나 확인하기위한 코드
		Practice04.cp.repaint();//각 값을 입력해줬으면 차트를출력할 패널을 다시그려야함.
	}
}
