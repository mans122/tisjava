package Ex13_1;

public class ThreadC implements Runnable{
	@Override
	public void run() {
		for(int i=0;i<50;i++) {
			System.out.println("ThreadC");
		}
	}

}