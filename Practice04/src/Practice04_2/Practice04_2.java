package Practice04_2;

abstract class Mouse{
	public Mouse() {}
	String leftButton,rightButton,optical;
	abstract public void mouseMove();
}
class WheelMouse extends Mouse{
	public WheelMouse() {
		this.optical = "Ball";
	}
	@Override
	public void mouseMove() {}
	public void scrollWheel() {}
}
class LaserMouse extends WheelMouse{
	public LaserMouse() {
		this.optical = "Laser";
	}
	@Override
	public void mouseMove() {}
}
public class Practice04_2 {
	public static void main(String[] args) {
		WheelMouse wm = new WheelMouse();
		LaserMouse lm = new LaserMouse();
		
		WheelMouse[] wm2 = new WheelMouse[3];
	}

}
