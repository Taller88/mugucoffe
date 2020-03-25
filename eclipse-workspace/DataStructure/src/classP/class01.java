package classP;

public class class01 {

	
	public static class student{
		public String name;
		public int height;
		public double vision;
		
		student(String name, int height, double vision) {
			this.name=name;
			this.height=height;
			this.vision=vision;
		}
		
	}
	
	public static double avgHeight(student[] inStu) {
		int totalHeight=0;
		for(int i=0; i<inStu.length; i++) {
			totalHeight+=inStu[i].height;
		}
		return (double)totalHeight/inStu.length;
		
	}
	
	
	public static void main(String[]args) {
		student[] arrStu= {
				new student("Á¤Áø¿ì", 188, 1.0),
				new student("°­Çö±Ô", 183, 1.0),
				new student("¹Ú½ÂÁ¾", 154, 1.0),
				new student("¹Ú¼º¿¬", 175, 1.0),
				new student("ÃÖÀçÈÆ", 179, 1.0),
				new student("±è¹ÎÁö", 148, 1.0),
		};
		
		System.out.println(avgHeight(arrStu));
		
		
		
	}
}
