import java.util.Scanner;

class Member{
	String name,address,job;
	int age;
	public Member() {}
	public Member(String name,String address, String job,int age) {
		this.name = name;
		this.address = address;
		this.job = job;
		this.age = age;
	}
	void show() {System.out.println(name+","+address+","+job+","+age);}
}
public class Practice03 {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Member 홍길동 = new Member();
		Member 이순신 = new Member();
		
		int num=0;
		System.out.println("입력할 개수 :");
		num = s.nextInt();
		Member[] info = new Member[num];

		for(int i=0;i<info.length;i++) {
			System.out.println((i+1)+"번째 이름,주소,직업,나이를 입력하세요");
			info[i] = new Member(s.next(),s.next(),s.next(),s.nextInt());
		}
		for(int i=0;i<info.length;i++) {
			System.out.println(info[i].name+","+info[i].address+","+info[i].job+","+info[i].age);
			info[i].show();
		}
		
		s.close();
	}

}
