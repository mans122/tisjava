package sync;

public class SynchronizedEx {
	public static void main(String[] args) {
		SharedBoard board = new SharedBoard();//공유데이터 생성

		//스레드 생성 시 공유데이터의 주소를 알려주낟. 두 스레드는 공유데이터에 동시에 접근한다.
		Thread th1 = new StudentThread("기태",board);
		Thread th2 = new StudentThread("한별",board);

		//두 스레드 실행
		th1.start();
		th2.start();
	}
}
//공유데이터를 시뮬레이션하는 클래스
//두 StudentThread스레드 에 의해 동시 접근됨
class SharedBoard{
	private int sum = 0;
	//synchronized 
	public void add() {
		int n = sum;
		Thread.yield();//현재 실행중인 스레드 양보
		n +=10;
		sum = n;
		System.out.println(Thread.currentThread().getName() + " : " + sum);	
	}
	public int getSum() { return sum;}
}

//학생을 시뮬레이션하는 스레드 클래스
class StudentThread extends Thread{
	private SharedBoard board; //공유데이터 주소

	public StudentThread(String name,SharedBoard board) {
		super(name);
		this.board = board;
	}
	//집계판 10번 접근 카운팅
	public void run() {
		try {
			for(int i = 0;i<20;i++) {
				board.add();
				Thread.sleep(200);
			}
		}catch(Exception e) {}
	}
}