package Ex_2;

import java.io.File;
import java.util.ArrayList;


public class ImgFolder {
	public static ArrayList<String> fileName = new ArrayList<>();
	public static int fileNum;
	public static String url;
	public static String[] str;
	public static void listDirectory(File dir) {
		File[] subFiles = dir.listFiles();
		for(int i=0;i<subFiles.length;i++) {
			File f = subFiles[i];
			ImgFolder.fileName.add(i,f.getPath());
			System.out.println(ImgFolder.fileName.get(i));
		}
		ImgFolder.fileNum = fileName.size();
		System.out.println(ImgFolder.fileNum);
	}
	public ImgFolder() {}
}
