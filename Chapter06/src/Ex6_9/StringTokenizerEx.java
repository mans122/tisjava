package Ex6_9;

import java.util.StringTokenizer;

public class StringTokenizerEx {
	public static void main(String[] args) {
		StringTokenizer st = new StringTokenizer("È«±æµ¿/ÀåÈ­/È«·Ã/ÄáÁã/ÆÏÁã","/");
		
		while(st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}
		
		
		String as = new String("È«±æµ¿/ÀåÈ­/È«·Ã/ÄáÁã/ÆÏÁã");
		String[] a = as.split("/");
		for(int i=0;i<a.length;i++) {
			System.out.println(a[i]);
		}
	}

}
