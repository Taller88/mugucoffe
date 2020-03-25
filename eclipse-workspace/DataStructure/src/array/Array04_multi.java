package array;

import java.time.DayOfWeek;

public class Array04_multi {
	//������ ����ϼ��� ����ϴ� ���α׷� 
	static int [][]days= {
				{31,28,31,30,31,30,31,31,30,31,30,31},// ���	
				{31,29,31,30,31,30,31,31,30,31,30,31}//����	
					};
	
	public static int isLeap(int year) {
		
		return (year%4==0 && year%100!=0 ||year%400==0)?1:0;
	}
	
	//while���� ���� ����
	public static int dayOfYear(int year, int month, int day) {//����ϼ�
		int i=0;
		int sum=0;
		while((i+2)<=month) {
			sum+=days[isLeap(year)][i];
			i++;
		}
		sum+=day;
		return sum;
		
	}
	
	//while���� ���� ����
		public static int leftDay(int year, int month, int day) {//����ϼ�
			
			int sum=0;
			sum=365-dayOfYear(year, month, day);
			return sum;
			
		}
	
	public static void main(String []args) {
		System.out.println(dayOfYear(2020,3,1));
		System.out.println(leftDay(2020,3,1));
	}
	
}
