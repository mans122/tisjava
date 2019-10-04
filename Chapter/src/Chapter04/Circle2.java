package Chapter04;
public class Circle2 {
	int radius;
	public Circle2(int radius) {this.radius = radius;}
	public void set(int radius) {this.radius=radius*radius;}
	
	public static void main(String[] args) {
		Circle2 ob1=new Circle2(1);
		Circle2 ob2=new Circle2(2);
		Circle2 s;
		
		s=ob2;
		ob1 = ob2;
		System.out.println("ob1.radius="+ob1.radius);
		System.out.println("ob1.radius="+s.radius);
		System.out.println("ob2.radius="+ob2.radius);
		Circle2 ob3 = new Circle2(0);
		ob3.set(10);
		System.out.println("ob2.radius="+ob3.radius);
		Circle2 ob4 = new Circle2(5);
		System.out.println("ob2.radius="+ob4.radius);
		ob4 = new Circle2(10);
		System.out.println("ob2.radius="+ob4.radius);
		
	}
}

