package Chapter08;
import java.io.*;
import java.util.Scanner;
public class BufferedIOEx {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		FileReader fin = null;
		int c;
		try {
			fin = new FileReader("c:\\temp\\test2.txt");
			BufferedOutputStream out = new BufferedOutputStream(System.out,5);
			while((c=fin.read())!= -1) {
				out.write(c);
			}
			new Scanner(System.in).nextLine();
			out.flush();	//Buffer에 남은 내용을 출력
			fin.close();
			out.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
