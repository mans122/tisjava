import java.awt.event.*;
import java.sql.*;
import javax.swing.JOptionPane;

public class MyActionListener implements ActionListener {
	public static void list(){
		try{
			// Select문 실행
			ResultSet rs=Haksa.stmt.executeQuery("select * from student");

			//JTable 초기화
			Haksa.model.setNumRows(0);
			while(rs.next()){
				String[] row=new String[4];
				row[0]=rs.getString("id");
				row[1]=rs.getString("name");
				row[2]=rs.getString("dept");
				row[3]=rs.getString("address");
				Haksa.model.addRow(row);
			}
			rs.close();
		}
		catch(Exception e1){
			System.out.println(e1.getMessage());
		}
	}
	
	public static boolean isStringInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		//값비었는지 확인하기위한 value값 변수 선언
		int value = 0;

		//값을 받아오기위한 변수선언 및 값 입력
		String id = Haksa.tf_num[0].getText();
		String name = Haksa.tf_num[1].getText();
		String dept = Haksa.tf_num[2].getText();
		String address = Haksa.tf_num[3].getText();
		boolean isNumber = false;
		if(id.length()!=0)
		{
			if (isStringInt(id))
				isNumber=true;
			else
				isNumber=false;
		}

		switch(cmd) {
		case "등록":
			for(int i=0;i<3;i++) {//tf_num값 비어있는게 있는지 확인
				if(Haksa.tf_num[i].getText().length()==0 || Haksa.tf_num[i].getText().equals("")) {
					value = 1;//비었으면 value값을 1로 설정
				}
			}

			if (value != 1){ // id,name,dept 값이 비어있지 않을 경우
				if(id.length()==7 && isNumber == true ) { // id의 값이 길이가 7이고,Int형일 경우
					try {
						Haksa.stmt.executeUpdate("insert into student values('"+id+"','"+name+"','"+dept+"','"+address+"')");
						JOptionPane.showMessageDialog(null,"등록되었습니다.","알림",JOptionPane.INFORMATION_MESSAGE);
						list();
						//등록이 완료된 텍스트필드를 비워주기위한 코드
						for(int i=0;i<4;i++) {
							Haksa.tf_num[i].setText("");
						}
					}
					//SQL에서 에러가 발생하면 알림창 생성
					catch(SQLException sqlE) {
						JOptionPane.showMessageDialog(null,"이미 존재하는 학번입니다.","경고",JOptionPane.WARNING_MESSAGE);
						sqlE.printStackTrace();
					}
					catch(Exception inputE) {
						inputE.printStackTrace();
					}
					//권장되는 코드
					finally {
						try{
							//if(Haksa.stmt!=null) {Haksa.stmt.close();}
							if(Haksa.rs!=null) {	Haksa.rs.close();}
						}catch(Exception ee) {
							ee.printStackTrace();
						}
					}
				}
				//id가 길이가 7자,정수형이 아닐때 정수형이 아니면 출력
				else if(isNumber == false) {
					JOptionPane.showMessageDialog(null,"ID는 숫자만 입력해주세요","경고",JOptionPane.WARNING_MESSAGE);}
				//id가 정수형이지만, 7자리가 아닐경우
				else {
					JOptionPane.showMessageDialog(null,"ID는 7자리로 입력해주세요","경고",JOptionPane.WARNING_MESSAGE);}
			}
			else {//비었으면 메시지 출력
				JOptionPane.showMessageDialog(null,"값이 입력되지 않았습니다.","경고",JOptionPane.WARNING_MESSAGE);}
			
			break;
			//--------------------------------------------------------------			
		case "목록":
				list();
			break;
			//--------------------------------------------------------------
		case "수정":
			try {
				if(id.length()!=0) {
					//해당학번으로 조회를해서 rowcount에 조회된 행 수를 조회
					//ResultSet ws = Haksa.stmt.executeQuery("select * from student where id = '"+id+"'");
					Haksa.rs = Haksa.stmt.executeQuery("select * from student where id = '"+id+"'");
					Haksa.rs.next();

					//해당학번이 존재하면 검색결과가 1줄 출력되고 getRow()에 의해 rowcount에 1 이들어감
					if(Haksa.rs.getRow() == 1) {
						//이름,학과,주소를 적었으면 수정
						if(name.length()!=0) {
							Haksa.stmt.executeUpdate("update student set name='"+name+"' where id = '"+id+"'");	}
						if(dept.length()!=0) {
							Haksa.stmt.executeUpdate("update student set dept='"+dept+"' where id = '"+id+"'");	}
						if(address.length()!=0) {
							Haksa.stmt.executeUpdate("update student set address='"+address+"' where id = '"+id+"'");	}
						JOptionPane.showMessageDialog(null,"수정이 완료되었습니다.","알림",JOptionPane.INFORMATION_MESSAGE);
						for(int i=0;i<4;i++) {
							Haksa.tf_num[i].setText("");
						}
						list();
					}
					//rowcount 가 0이면 조회되는 학번이 없다는말임
					else {
						JOptionPane.showMessageDialog(null,"해당 학번이 존재하지 않습니다.","알림",JOptionPane.INFORMATION_MESSAGE);
						break;
					}
					//수정버튼을 눌렀을때 학번에 아무것도 안써있으면 경고출력
				}else {
					JOptionPane.showMessageDialog(null,"수정할 학번을 입력해주세요","경고",JOptionPane.WARNING_MESSAGE);
					break;
				}
				break;
			}
			catch(Exception inputE) {
				inputE.printStackTrace();
			}
			for(int i=0;i<4;i++) {
				Haksa.tf_num[i].setText("");
			}
			break;
			//--------------------------------------------------------------			
		case "삭제":
			if(JOptionPane.showConfirmDialog(null, "정말삭제하시겠습니까?", "삭제", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
				if(id.length()!=0) {
					try {
						Haksa.stmt.executeUpdate("delete from student where id = '"+id+"'");
						list();
						for(int i=0;i<4;i++) {
							Haksa.tf_num[i].setText("");
						}
					}
					catch(Exception deleteE) {
						deleteE.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null,"삭제할 학번을 입력해주세요","경고",JOptionPane.WARNING_MESSAGE);
				}
			}
			break;
		}
	}
}


