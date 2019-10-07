package swingTest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class IteratorTest {
	
	public IteratorTest() {
		// 반복자 연습 hashmap
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("hello", 3232);
		map.put("nice", 3521);
		
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
	}

	public static void main(String[] args) {
		new IteratorTest();
	}

}
