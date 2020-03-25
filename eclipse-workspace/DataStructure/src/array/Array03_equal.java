package array;

public class Array03_equal {

	public static boolean equal(int a[], int b[]) {
		
		if(a.length != b.length)
			return false;
			
		for(int i=0; i<a.length; i++) {
			if(a[i]!=b[i])
				return false;
		}
		return true;
		
	}
	
	
	
	public static void main(String[]args) {
		int a[]= {1,2,3,4,5};
		int b[]= {1,2,3,4,6};
		
		System.out.println(equal(a,b)?"같습니다":"같지않습니다");
		
		
		
	}
	
}
