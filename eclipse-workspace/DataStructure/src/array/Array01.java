package array;

public class Array01 {

	//�迭�� ���� : ���� �ڷ����� ������ �̷���� ���� ��Ұ� ���� ��

	//�迭�� ����
		//int a[]  int[]a=new int[5];
		//int a[]=new int[]{1,2,3,4,5};
	
	public static void main(String[] args) {
	//�迭�� ���� 
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
		System.out.println("a�迭�� �ִ�: "+aMax);

		System.out.println();
		for(int i=0; i<b.length; i++) {
			if(b[i]>bMax) {
				bMax=b[i];
			}
		
		}
		System.out.println("b�迭�� �ִ�: "+bMax);

	}

}
