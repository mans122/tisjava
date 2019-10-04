package Chapter06;

public class MathEx {
	public static void main(String[] args) {
		System.out.println(Math.PI);
		System.out.println(Math.ceil(4));
		System.out.println(Math.floor(5));
		System.out.println(Math.sqrt(9));
		System.out.println(Math.exp(2));
		System.out.println(Math.round(3.14));
		
		//[1,45]사이 정수형난수5개
		System.out.println("이번주 행운의 번호는 ");
		for(int i=0;i<5;i++) {
			System.out.print((int)(Math.random()*45+1)+" ");
		}
		System.out.println("\n이번주 행운번호는");
		int[] num = new int[6];
		for(int i=0; i<6; i++) {
			num[i]=(int)(Math.random()*45+1);
			for(int j=0;j<6;j++) {
				if(i==j) {continue;}
				if(num[i] == num[j]) {
					i--;}
			}
		}
		for(int i=0; i<6; i++) {
			System.out.print(num[i]+" ");
		}
	}

}
/* int[] num = new int[6];
 * boolean flag=true;
 * while(true){
 * for(int i=0;i<6;i++){
 * num[i] = (int)(Math.random()*45+1);
 * }
 * for(int j=0;j<6;j++){
 * for(int k=0;k<6;k++){
 * if(j==k){
 * continue;
 * }
 * if(num[j]==num[k]){
 * flag = false;
 * }
 * }
 * }
 * if(flag){
 * break;
 * }
 * }
 */
