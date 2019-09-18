class Circle{
	int radius;
	String name;
	public Circle() {}
	public double getArea() {
		return 3.14*radius*radius;
	}
}
public class Ex4_1 {
	public static void main(String[] args) {
		Circle pizza;
		pizza = new Circle();
		pizza.name="ÇÇÀÚÇÇÀÚ";
		pizza.radius=10;
		double area = pizza.getArea();
		System.out.println(pizza.name+"ÀÇ ³ÐÀÌ :"+area);

		Circle donut = new Circle();
		donut.name = "µµ³Óµµ³Ó";
		donut.radius=2;
		area = donut.getArea();
		System.out.println(donut.name+"ÀÇ ³ÐÀÌ :"+area);

	}

}
