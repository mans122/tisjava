//ArrayList<Book> data = new ArrayList<Book>();
import java.util.*;
class Bookp {
	String bookName,writer,pub;
	int price;
	public Bookp() {}
	public Bookp(String bookName,String writer, String pub, int price) {
		this.bookName = bookName;
		this.writer = writer;
		this.pub = pub;
		this.price =price;
	}
	@Override
	public String toString() {
		return "책제목" + bookName + ", 저자=" + writer + ", 출판사=" + pub + ", 가격=" + price + "]";
	}
	
}

public class Practice6hd {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		ArrayList<Bookp> data = new ArrayList<Bookp>();
		Bookp bookInfo = new Bookp();
		//for(int i=0;i<3;i++) {
		System.out.println("책제목, 저자 , 출판사, 가격을 입력하세요");
		bookInfo = new Bookp(s.nextLine(),s.nextLine(),s.nextLine(),s.nextInt());
		s.nextLine();
		data.add(bookInfo);
		//}
		//for(int i=0;i<3;i++) {
		//data.get(i).toString();
		System.out.println(data.get(0).toString());
		//}

		s.close();
	}
}