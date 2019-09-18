//nextLine() > enter입력까지 받아들임
import java.util.Scanner;
class Member{
	String id;
	public Member() {}
	public Member(String id) {
		id.trim();
		id.replace(" ","");
		this.id = id;
		
	}
	public boolean equals(char obj) {
		for(int i=0; i<id.length();i++) {
			if(id.charAt(i) == obj) {
				System.out.println("아이디에 !,@,#,$,%,^가 포함되면 안됩니다.");
				return false;
			}
		}
		return true;
	}
}
//0번문제
interface Mouse{
	abstract public void mouse();
	abstract public void mouseRight();
	abstract public void mouseLeft();
	abstract public void mouseMove();
}
class WheelMouse implements Mouse{
	public WheelMouse() {}
	public void scrollWheel() {}
	@Override
	public void mouseMove() {}
	@Override
	public void mouse() {}
	@Override
	public void mouseRight() {}
	@Override
	public void mouseLeft() {}


}
class LaserMouse extends WheelMouse{
	public LaserMouse() {	}
	@Override
	public void mouseMove() {}
}
public class Practice05 {

	public static void main(String[] args) {
		WheelMouse wm = new WheelMouse();
		LaserMouse lm = new LaserMouse();

		WheelMouse[] wm2 = new WheelMouse[3];
		wm2[0] = new WheelMouse();
		wm2[1] = new WheelMouse();
		wm2[2] = new WheelMouse();
		//1번문제
		String phoneNum = new String("010-1234-5678");
		System.out.println(phoneNum.substring(0,phoneNum.indexOf("-")));

		//2번문제
		System.out.println(phoneNum.replace("-",""));
		String[] s = phoneNum.split("-");
		for(int i=0;i<s.length;i++) {
			System.out.print(s[i]);
		}
		System.out.println();

		//3번문제
		String pNum = new String("E20160001");
		System.out.println(pNum.charAt(0));
		System.out.println(pNum.substring(0,1));

		//4번문제
		System.out.println(pNum.substring(1,5));

		//5번문제
		String info = new String("홍길동,010-1111-2222,hkd@hkd.com");
		String[] newInfo = info.split(",");
		for(int i=0;i<newInfo.length;i++) {
			System.out.println(newInfo[i]);
		}

		//6번문제
		String data = "EL201800001,CH201800021,EN12231";
		String[] dep = data.split(","); 
		for(int i=0;i<dep.length;i++) {
			if(dep[i].contains("EL")==true) {
				System.out.println(dep[i]+": 전자공학과");
			}
			else if(dep[i].contains("CH")==true) {
				System.out.println(dep[i]+": 화학공학과");
			}
			else if(dep[i].contains("EN")==true) {
				System.out.println(dep[i]+": 영어영문학과");
			}
		}

		//7번문제
		Scanner sc = new Scanner(System.in);
		System.out.println("아이디를 입력하세요");
		Member admin = new Member(sc.nextLine());
		String id = admin.id;
		//=================================
		for(int i=0;i<id.length();i++) {
			char c = id.charAt(i);
			if(c == '!' || c == '@' || c == '#' || c == '$' || c == '%' || c == '^') {
				System.out.println("아이디에 !,@,#,$,%,^가 포함되면 안됩니다.");
				break;
			}		
		}
		//=====================================	
		
		admin.equals('!');
		admin.equals('@');
		admin.equals('#');
		admin.equals('$');
		admin.equals('%');
		admin.equals('^');
		
		if(id.contains("!")==true || id.contains("@")==true || id.contains("#")==true || id.contains("$")==true || id.contains("%")==true || id.contains("^")==true) {
			System.out.println("아이디에 !,@,#,$,%,^가 포함되면 안됩니다.");
		}




	}

}
