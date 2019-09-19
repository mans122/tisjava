package Ex13_1;

public class ThreadA extends Thread{
		public void run() {
			
			for(int i=0;i<50;i++) {
				System.out.println("ThreadA");
				try {
					//Thread.sleep(100);				
				}
				catch(Exception e) {}
			}
		}
}
