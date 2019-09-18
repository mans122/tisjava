  class Person {
 
	void speak() {System.out.println("speak");}
	void walk() {System.out.println("walk");}
	void eat() {System.out.println("eat");}
	//Overloading
	void eat(String object) {System.out.println(object);}
	void sleep() {System.out.println("sleep");}
}
class Researcher extends Person{
	void search() {System.out.println("search");}
}
class Student extends Person {
	void study() {System.out.println("study");}
}
class StudentWorker extends Student{
	void worker() {System.out.println("worker");}
	void sleep() {
		System.out.println("deepsleep");//overriding
	}
}

class Point{
	private int x;
	private int y;
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setXY(int x,int y) {
	}
}
public class Ex05 {
	public static void main(String[] args) {
		Person p = new Person();
		p.speak();
		Student s = new Student();
		s.study();
		s.sleep();
		Researcher r = new Researcher();
		r.speak();
		r.search();
		r.sleep();
		StudentWorker sp= new StudentWorker();
		sp.worker();
		sp.speak();
		sp.study();
		sp.sleep();
		p.sleep();
		p.eat("ÇÜ¹÷");
		p.eat();
		
		Point po = new Point();
		po.setX(3);
		po.setY(4);
	}

}
