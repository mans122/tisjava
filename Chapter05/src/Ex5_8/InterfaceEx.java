package Ex5_8;
interface PhoneInterface{	//인터페이스 선언
	public static final int TIMEOUT = 10000;	//상수필드 선언
	public abstract void sendCall();			//추상메소드
	public abstract void receiveCall();			//추상메소드
	public default void printLogo() {			//dafault메소드
		System.out.println("**Phone**");
	}
}

class SamsungPhone implements PhoneInterface{
	//PhoneInterface의 모든 추상메소드 구현

	public void flash() {System.out.println("불켜졌");}
	@Override
	public void sendCall() {System.out.println("띠리리리링");}

	@Override
	public void receiveCall() {System.out.println("전화왔습니다.");	}
}

public class InterfaceEx {

	public static void main(String[] args) {
		SamsungPhone phone = new SamsungPhone();
		phone.printLogo();
		phone.sendCall();
		phone.flash();
		phone.receiveCall();
	}

}
