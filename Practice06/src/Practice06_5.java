import java.io.*;
import java.util.*;
class Book2 {
	ArrayList<String> bookInfo = new ArrayList<>();
	public Book2() {}
	public Book2(String bookName,String writer, String pub, String price) {
		this.bookInfo.add(bookName);
		this.bookInfo.add(writer);
		this.bookInfo.add(pub);
		this.bookInfo.add(price);
		for(int i=0;i<4;i++) {
			System.out.print(bookInfo.get(i)+" ");
		}
	}
}

public class Practice06_5 {
	public static void main(String[] args) throws Exception {
		//String bookName,writer,pub,price;
		Scanner s = new Scanner(System.in);
		Book2 book = new Book2();
		FileWriter fout = null;
		FileReader fin = null;
		System.out.println("책제목, 저자 , 출판사, 가격을 입력하세요");
		book = new Book2(s.nextLine(),s.nextLine(),s.nextLine(),s.nextLine());
		//bookName = s.nextLine();
		//writer = s.nextLine();
		//pub = s.nextLine();
		//price = s.nextLine();
		//book = new Book2(bookName,writer,pub,price);

		for(int i=0;i<4;i++) {
			System.out.println(book.bookInfo.get(i));
		}
		fout = new FileWriter("c:\\Temp\\data.txt");
		fout.write(book.bookInfo.toString());
		fout.close();
		
		fin = new FileReader("c:\\Temp\\data.txt");
		int c;
		while((c=fin.read()) != -1) {
			System.out.print((char) c);
		}
		fin.close();
		s.close();
	}
}