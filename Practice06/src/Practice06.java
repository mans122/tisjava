import java.util.*; 

public class Practice06 {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		ArrayList<String> book = new ArrayList<>();
		
		//1번문제		
		System.out.println("책제목, 저자 , 출판사, 가격을 입력하세요");
		for(int i=0; i<4;i++) {
			book.add(s.nextLine());
		}
					
		//2번문제
		HashMap<String,String> bookInfo = new HashMap<>();
		bookInfo.put("책제목",book.get(0));
		bookInfo.put("저자",book.get(1));
		bookInfo.put("출판사",book.get(2));
		bookInfo.put("가격",book.get(3));
		
		
		Set<String> keys = bookInfo.keySet();
		Iterator<String> it = keys.iterator();
		while(it.hasNext()) {
			String name = it.next();
			bookInfo.get(name);
			System.out.println(bookInfo.get(name));
		}
		s.close();
	}
}