package array;

public class Array01 {

	//배열의 정의 : 같은 자료형의 변수로 이루어진 구성 요소가 모인 것

	//배열의 선언
		//int a[]  int[]a=new int[5];
		//int a[]=new int[]{1,2,3,4,5};
	
	public static void main(String[] args) {
	//배열의 복제 
		int []a= {1,2,3,4,5};
		int []b=a.clone();
		
		b[3]=8;
		
		int aMax=a[0];
		int bMax=b[0];
		
		for(int i=0; i<a.length; i++) {
			if(a[i]>aMax) {
				aMax=a[i];
				
			}
		}
		System.out.println("a배열의 최댓값: "+aMax);

		System.out.println();
		for(int i=0; i<b.length; i++) {
			if(b[i]>bMax) {
				bMax=b[i];
			}
		
		}
		System.out.println("b배열의 최댓값: "+bMax);

	}

}
