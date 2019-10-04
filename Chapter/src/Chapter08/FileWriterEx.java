package Chapter08;
import java.io.*;
import java.util.*;

public class FileWriterEx {
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		FileWriter fout = null;
		try {
			fout = new FileWriter("C:\\Temp\\test.txt");
			while(true) {
				String line = s.nextLine();
				if(line.length()==0) {
					break;
				}
				fout.write(line,0,line.length());
				fout.append(line);
				fout.write("\r\n",0,2);
			}
			
			fout.close();
		}catch(Exception e) {
			System.out.print("¿À·ù");
		}
		
		s.close();
	}

}
