package Practice03;
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
		Member ȫ�浿 = new Member();
		Member �̼��� = new Member();
		
		int num=0;
		System.out.println("�Է��� ���� :");
		num = s.nextInt();
		Member[] info = new Member[num];

		for(int i=0;i<info.length;i++) {
			System.out.println((i+1)+"��° �̸�,�ּ�,����,���̸� �Է��ϼ���");
			info[i] = new Member(s.next(),s.next(),s.next(),s.nextInt());
		}
		for(int i=0;i<info.length;i++) {
			System.out.println(info[i].name+","+info[i].address+","+info[i].job+","+info[i].age);
			info[i].show();
		}
		
		s.close();
	}

}