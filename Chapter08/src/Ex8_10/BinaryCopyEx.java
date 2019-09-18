package Ex8_10;
import java.io.*;
public class BinaryCopyEx {
	public static void main(String[] args) {
		File src = new File("c:\\temp\\eclipse-jee-2019-06-R-win32-x86_64.zip");
		File dest = new File("c:\\temp\\javasample\\copy.zip");
		@SuppressWarnings("unused")
		int c;
		try {
			FileInputStream fi = new FileInputStream(src);
			FileOutputStream fo = new FileOutputStream(dest);
			byte[] buf = new byte[1024*100]; // 10kb버퍼
			
			while(true) {
				int n = fi.read(buf);
				fo.write(buf,0,n);
				if(n<buf.length) {
					break;
				}
			}
			/*
			while((c=fi.read()) !=-1){
				fo.write((byte)c);
			}
			 */
			fi.close();
			fo.close();
			System.out.println(src.getPath()+"를 "+dest.getPath()+"로 복사하였습니다.");
		}
		catch(IOException e) {
			System.out.println("파일 복사 오류");
		}
	}

}
