package Ex5_5;
abstract class Shape{
	public Shape next;
	public Shape() { next = null;}
	public void draw2() {System.out.println("Shape");}
	abstract public void draw(); //{System.out.println("Shape");}
}
class Line extends Shape{
	@Override
	public void draw() {System.out.println("Line");	}
}
class Rect extends Shape{
	@Override
	public void draw() {System.out.println("Rect");}
}
class Circle extends Shape{
	@Override
	public void draw() {System.out.println("Circle");}
}

public class MethodOverridingEx {
	static void paint(Shape p) {
		p.draw();
	}
	public static void main(String[] args) {
		Shape s = new Line();
		s.next = new Circle();
		s.next.draw2();
		s.draw();
		
		/*
		Shape s = new Rect();
		paint(line);
		paint(s);
		paint(new Shape());
		paint(new Line());
		paint(new Rect());
		paint(new Circle());
		
		
		Shape start,last,obj;
		start = new Line();
		last= start;
		obj = new Rect();
		last.next = obj;
		last = obj;
		obj = new Line();
		last.next = obj;
		last = obj;
		obj= new Circle();
		last.next = obj;
		
		Shape p = start;
		while(p != null) {
			p.draw();
			p= p.next;
		}*/
		
	}

}
