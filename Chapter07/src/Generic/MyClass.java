package Generic;

public class MyClass<T> {
	T val;
	void set(T a) {
		this.val =a;
	}
	T get() {
		return this.val;
	}
	public static void main(String[] args) {
		MyClass<String> s = new MyClass<>();
		s.set("Hello");
		MyClass<Integer> i = new MyClass<>();
		i.set(10);
		System.out.println(s.get()+i.get()*10);
	}
}
