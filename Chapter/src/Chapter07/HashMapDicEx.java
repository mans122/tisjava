package Chapter07;
import java.util.*;

public class HashMapDicEx {
	public static void main(String[] args) {
		//Hashmap생성
		HashMap<String, String> dic = new HashMap<>();
		//var dic = new HashMap<String,String>();로 간략히 사용 가능

		dic.put("baby","아기");
		dic.put("love","사랑");
		dic.put("apple","사과");

		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.print("찾고싶은 단어 ");
			String eng = sc.next();
			if(eng.equals("exit")) {
				System.out.println("종료");
				break;
				}
			String kor = dic.get(eng);
			if(kor == null) {
				System.out.println(eng+"는 없는단어");
				}
			else {
				System.out.println(kor);
				}
		}
		//Iterator를 이용해 모든 key값 출력
		Set<String> keys = dic.keySet();
		Iterator<String> it = keys.iterator();
		while(it.hasNext()) {
			//System.out.println(it.next());
			String name = it.next();
			dic.get(name);
			System.out.println(dic.get(name));
		}
		System.out.println(dic.get("baby"));
		sc.close();

	}

}
