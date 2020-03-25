package array;

public class Array02_sort {
	public static int[] swap(int in[]) {
		int temp=0;
		int len=in.length;
		for(int i=0; i<len/2; i++) {
			temp=in[i];
			in[i]=in[len-1-i];
			in[len-i-1]=temp;
		}
		
		return in;
	}
	
	public static int sumOf(int[]a) {
		int total=0;
		for(int i=0; i<a.length-1; i++) {
			total+=a[i];
		}
		return total;
	}
	public static void main(String[]args) {
	int in[]= {1,2,3,4,5,6,5};
	for(int i=0; i<in.length; i++) {
		System.out.println(in[i]);
	}
	System.out.println("------------------");
	in=swap(in);
	for(int i=0; i<in.length; i++) {
		System.out.println(in[i]);
	}
	
	System.out.println("¹è¿­ÀÇ ÃÑÇÕ: "+sumOf(in));
	
	}

}
