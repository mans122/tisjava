import java.util.Scanner;
public class ArrayAccess {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("입력할 개수를 정해주세요");
		int a=s.nextInt();
		int intArray[] = new int[a];
		String[][] b;
		int max=0;
		
		System.out.println("양수"+a+"개를 입력하세요.");
		for(int i=0;i<intArray.length;i++) {
			intArray[i] = s.nextInt();
			if(intArray[i]>max) {
				max = intArray[i];
			}
		}
		System.out.println("가장큰 수는"+max+"입니다.");
		
		for(int k : intArray) {
			System.out.print(k+",");
		}
		
		System.out.println("행의 개를 정해주세요");
		int A=s.nextInt();
		System.out.println("열의 개를 정해주세요");
		int B=s.nextInt();
		b = new String[A][B];
		for(int i=0;i<A;i++) {
			for(int j=0;j<B;j++) {
				System.out.println("b["+i+"]["+j+"]문자열을 입력하세요");
				String c=s.next();
				b[i][j]=c;
			}
		}
		
		s.close();
	}

}
