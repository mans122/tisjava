package Ex6_7;
public class StringEx {
	public static void main(String[] args) {
		String a = new String(" C#");
		String b = new String(",C++ ");
		
		System.out.println(a+"의 길이는 " + a.length());
		System.out.println(b.contains(" "));
		
		a = a.concat(b);
		System.out.println(a);
		
		a= a.trim();
		System.out.println(a);
		
		a = a.replace("C#", "Java");
		System.out.println(a);
		
		String[] s = a.split(",");
		for(int i =0; i<s.length;i++) {
			System.out.println("분리된 문자열"+i+":"+s[i]);
		}
		System.out.println(a);
		System.out.println(a.substring(2,3));
		a = a.substring(2);
		System.out.println(a);
		
		char c = a.charAt(0);
		System.out.println(c);
		
		String fileName="2019.09.10.list.hwp";
		System.out.println(fileName.lastIndexOf("."));
		//fileName = fileName.substring(fileName.lastIndexOf(".")+1);
		System.out.println(fileName);
		System.out.println(fileName.substring(fileName.lastIndexOf(".")+1));
		
		StringBuffer sb = new StringBuffer("2019.txt");
		sb.reverse();
		sb = sb.delete(sb.indexOf("."), sb.length());
		sb.reverse();
		System.out.println(sb);
		
	}

}
