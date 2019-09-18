import java.util.Scanner;

public class Practice02 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		/*
		//1번문제
		System.out.println("이름,학번,학과를 입력하세요(공백으로 구분)");
		String name = s.next();
		int num = s.nextInt();
		String dep = s.next();
		System.out.println("이름:"+name+",학번:"+num+",학과:"+dep);
		
		//2번문제
		
		System.out.println("국어,영어,수학 점수를 입력하세요(공백으로 구분)");
		int kor=s.nextInt();
		int eng=s.nextInt();
		int math=s.nextInt();
		System.out.println("세 과목의 합:"+(kor+eng+math)+"\n세 과목의 평균:"+((float)(kor+eng+math)/3));
		
		
		//3번문제
		int count=0;
		int sum=0;
		
		System.out.println("정수 입력하고 마지막에 -1을 입력하세요.");
		int n = s.nextInt();
		do {
			sum+=n;
			count++;
			n=s.nextInt();
		}while(n!=-1);
		if(count==0) {
			System.out.println("입력된 수가 없습니다.");
		}else {
			System.out.println("정수의 개수는"+count+"개 이며,");
			System.out.println("평균은 "+(double)sum/count+"입니다.");
		}
		*/
		
		//4번문제
		
		System.out.println("학생수를 입력하세요.");
		int studentNum = s.nextInt();
		String[] studentName = new String[studentNum];
		int[] studentKor = new int[studentNum];
		int[] studentEng = new int[studentNum];
		int[] studentMath = new int[studentNum];
		int korSum=0,engSum=0,mathSum=0;
		double korAvg=0, engAvg=0,mathAvg=0,allAvg=0;
		
		for(int i=0;i<studentNum;i++) {
				System.out.println((i+1)+"번째 학생의 이름, 국어, 영어, 수학 점수를 입력하세요(공백으로 구분)");
				studentName[i] = s.next();
				studentKor[i] = s.nextInt();
				studentEng[i] = s.nextInt();
				studentMath[i] = s.nextInt();
				korSum+=studentKor[i];
				engSum+=studentEng[i];
				mathSum+=studentMath[i];
		}
		korAvg=korSum/studentNum;
		engAvg=engSum/studentNum;
		mathAvg=mathSum/studentNum;
		allAvg=(korAvg+engAvg+mathAvg)/3;
		System.out.println("과목별 평균\n국어:"+korAvg+"\n영어:"+engAvg+"\n수학:"+mathAvg+"\n전체 평균:"+allAvg);
		/*
		
		double korAvg=0, engAvg=0,mathAvg=0,allAvg=0;
		int korSum=0, engSum=0,mathSum=0;
		System.out.println("학생수를 입력하세요.");
		int studentNum = s.nextInt();
		String[][] studentArray =new String[studentNum][4];
		int[] kor =new int[studentNum];
		int[] eng =new int[studentNum];
		int[] math =new int[studentNum];
		
		
		
		for(int i=0;i<studentNum;i++) {
			System.out.println((i+1)+"번째 학생의 이름, 국어, 영어, 수학 점수를 입력하세요(공백으로 구분)");
			studentArray[i][0] = s.next();
			studentArray[i][1] = s.next();
			studentArray[i][2] = s.next();
			studentArray[i][3] = s.next();
			kor[i] = Integer.parseInt(studentArray[i][1]);
			eng[i] = Integer.parseInt(studentArray[i][2]);
			math[i] = Integer.parseInt(studentArray[i][3]);
			korSum+=kor[i];
			engSum+=eng[i];
			mathSum+=math[i];
		}
		korAvg=korSum/studentNum;
		engAvg=engSum/studentNum;
		mathAvg=mathSum/studentNum;
		allAvg=(korAvg+engAvg+mathAvg)/3;
		System.out.println("과목별 평균\n국어:"+korAvg+"\n영어:"+engAvg+"\n수학:"+mathAvg+"\n전체 평균:"+allAvg);
		*/
		s.close();
		}
	
}
