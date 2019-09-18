package Ex8_1;
public class Calc {
	public static void main(String[] args) {
		double sum=0.0;
		
		for(int i=0;i<args.length;i++) {
			sum+=Double.parseDouble(args[i]);
		}
		
		System.out.println("sum="+sum);
	int a=100;
	int b=0;
	try {
	System.out.println(a/b);
	}catch(Exception e) {
		System.out.println("에러에러");
	}
	System.out.println("합="+sum);
	System.out.println("\\/");
	
	}
}
