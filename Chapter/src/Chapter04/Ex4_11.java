package Chapter04;
class StaticSample{
	public int n;
	public void g() {
		m=20;
	}
	public void h() {
		m=30;
	}
	public static int m;
	public static void f() {
		m=5;
	}
}
public class Ex4_11 {
	public static void main(String[] args) {
		StaticSample.m=10;
		
		StaticSample s1;
		s1 = new StaticSample();
		System.out.println(s1.m);
		s1.f();
		StaticSample.f();
	}

}
