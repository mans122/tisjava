package Ex6_11;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

public class CalendarEx {
	public static void main(String[] args) {
		Calendar now = Calendar.getInstance();
		/*
		System.out.println(now.get(Calendar.YEAR)+"년");
		System.out.println(now.get(Calendar.MONTH)+1+"월");
		System.out.println(now.get(Calendar.DAY_OF_MONTH)+"일");
		System.out.println(now.get(Calendar.HOUR_OF_DAY)+"시");
		System.out.println(now.get(Calendar.HOUR));
		System.out.println(now.get(Calendar.MINUTE)+"분");
		System.out.println(now.get(Calendar.SECOND));
		*/
		now.set(2019, 10, 25);
		System.out.println(now.get(Calendar.YEAR)+"년");
		System.out.println(now.get(Calendar.MONTH)+"월");
		System.out.println(now.get(Calendar.DAY_OF_MONTH)+"일");
		
		Vector<Integer> v = new Vector<Integer>();
		v.add(Integer.valueOf(5));
		v.add(Integer.valueOf(4));
		v.add(Integer.valueOf(-1));
		Integer obj = v.get(0);
		int i = obj.intValue();
		int i2 = v.get(1);
		System.out.println(i+" "+i2);
		ArrayList<Integer> a = new ArrayList<>();
		a.add(20);
		a.add(10);
		a.add(-10);
		int aa = a.get(0);
		System.out.println(aa);
		
		
		
	}

}
