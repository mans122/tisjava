
public class Ex06 {

	public static void main(String[] args) {
		
		String a="Hello";
		String b="Hello";
		String c = new String("Hello");
		String d = new String("Hello");
		System.out.println(a.hashCode());
		System.out.println(b.hashCode());
		System.out.println(d.hashCode());
		System.out.println(Integer.toHexString(System.identityHashCode(b)));
		b="java";
		System.out.println(Integer.toHexString(System.identityHashCode(b)));
		System.out.println(Integer.toHexString(System.identityHashCode(c)));
		System.out.println(Integer.toHexString(System.identityHashCode(d)));
		System.out.println(a);
		System.out.println(b);
		
	}

}

