package Ex7_3;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListEx {
	public static void main(String[] args) {
		//문자열만 삽입 가능한 ArrayList 생성
		ArrayList<String> a = new ArrayList<>();
		//Scanner 객체 생성
		Scanner sc = new Scanner(System.in);
		
		//키보드로부터 4개의 이름을 입력받아 ArrayList 삽입
		for(int i=0;i<4;i++) {
			System.out.print("이름을 입력하세요");
			a.add(sc.next());
		}
		
		//ArrayList에 있는 모든 이름 출력
		for(int i=0;i<a.size();i++) {
			System.out.print(a.get(i)+" ");
		}
		
		//가장 긴 이름 출력
		int longestIndex = 0;
		for(int i=1;i<a.size();i++) {
			if(a.get(longestIndex).length() < a.get(i).length()) {
				longestIndex = i;
			}
		}
		System.out.println("\n가장 긴 이름 : "+a.get(longestIndex)+" "+a.get(longestIndex).length());
		sc.close();
		
	}

}
