import java.util.Scanner;
public class WhileSample {
	public static void main(String[] args) {
		int count=0;
		int sum=0;
		Scanner s = new Scanner(System.in);
		System.out.println("정수 입력하고 마지막에 0입력");
		
//		int n = s.nextInt();
//		while(n != 0) {
//			sum+=n;
//			count++;
//			n=s.nextInt();
//		}
		
		
		if(count==0) {
			System.out.println("입력 개수 0개");
		}
		else {
			System.out.println("정수의 개수는"+count+"개 이며, 평균은 "+(float)sum/count+"입니다.");
		}
		s.close();
	}

}

