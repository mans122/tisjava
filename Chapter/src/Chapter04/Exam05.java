//package Chapter04;
//import java.util.Scanner;
//class Circle{
//	private double x,y;
//	private int radius;
//	private double maxx,maxy;
//	private int maxradius;
//	private int max=0;
//	public Circle() {}
//	public Circle(double x, double y,int radius) {
//		this.x = x;this.y = y; this.radius=radius;
//	}
//	public void show() {
//		System.out.println("("+x+","+y+")"+radius);
//	}
////	public void max(double x, double y, int radius) {
////		if(radius>max) {
////			max = radius;
////			this.maxx = x;this.maxy = y; this.maxradius=radius;
////		}
////	}
//	public void showMax() {
//		System.out.println("("+maxx+","+maxy+")"+maxradius);
//	}
//}
//public class Exam05 {
//
//	public static void main(String[] args) {
//		Scanner s = new Scanner(System.in);
//		Circle[] c = new Circle[3];
//		Circle a= new Circle();
//		for(int i=0;i<c.length;i++) {
//			System.out.print("x, y, radius >>");
//			double x = s.nextDouble();
//			double y = s.nextDouble();
//			int radius = s.nextInt();
//			c[i] = new Circle(x,y,radius);
//		}
//		
//		for(int i=0;i<c.length;i++) {
//			c[i].show();
//		}
//		a.showMax();
//		s.close();
//	}
//
//}
