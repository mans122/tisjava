package Exam;
class TV{
	String brand;
	int age,inch;
	public TV() {}
	public TV(String brand,int age, int inch) {
		this.brand=brand;
		this.age=age;
		this.inch=inch;
	}
	public void show() {
		System.out.println(brand+"에서 만든"+age+"년형"+inch+"인치 TV");
	}
}
public class Exam01 {

	public static void main(String[] args) {
		TV myTV = new TV("LG",2017,32);
		myTV.show();
				
	}

}
