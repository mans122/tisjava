//ArrayList<Book> data = new ArrayList<Book>();
import java.util.*;
class Book {
	ArrayList<String> book = new ArrayList<>();
	String bookName,writer,pub,price;
	public Book() {}
	/*
	public void showArray() {
		book.add(bookName);
		book.add(writer);
		book.add(pub);
		book.add(price);
		for(int i=0;i<4;i++) {
			System.out.print(book.get(i)+" ");
		}
	}*/
	public Book(String bookName,String writer, String pub, String price) {
		this.book.add(bookName);
		this.book.add(writer);
		this.book.add(pub);
		this.book.add(price);
		for(int i=0;i<4;i++) {
			System.out.print(this.book.get(i)+" ");
		}
	}
}
class Book3 extends Book{
	public Book3() {}
	
}
public class Practice06_3 {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		//Book bookInfo = new Book();
		//System.out.println("책제목, 저자 , 출판사, 가격을 입력하세요");
		//Book bookInfo = new Book(s.nextLine(),s.nextLine(),s.nextLine(),s.nextLine());
		Book[] bookInfo = new Book[5];
		for(int i=0;i<5;i++) {
			System.out.println("책제목, 저자 , 출판사, 가격을 입력하세요");
			bookInfo[i] = new Book(s.nextLine(),s.nextLine(),s.nextLine(),s.nextLine());
		}
		System.out.print("\n");
		for(int i=0;i<4;i++) {
			System.out.print(bookInfo[0].book.get(i)+" ");
		}
//		bookInfo.bookName = s.nextLine();
//		bookInfo.writer = s.nextLine();
//		bookInfo.pub = s.nextLine();
//		bookInfo.price = s.nextLine();
//		bookInfo.showArray();
		
		s.close();
	}
}