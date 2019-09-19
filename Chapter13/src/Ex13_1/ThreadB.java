package Ex13_1;

public class ThreadB extends Thread{
	public void run() {
		for(int i=0;i<100;i++) {
			System.out.println("ThreadB");
			try {
				//Thread.sleep(100);
			}
			catch(Exception e) {}
		}
	}
}