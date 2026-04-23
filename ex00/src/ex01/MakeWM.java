package ex01;

public class MakeWM {
	public static void main(String[] args) {

		for (int i = 0; i<19; i++) {
			for (int j=0; j<19; j++) {
				if (j==i || j==9 || j==18-i || i==8 )
					System.out.print("*");
				
				else if ((j==0 && i<9)||(j==18 && i>8))
					System.out.print("*");
				
				else if ((j>9 && i==0)||(j<10 && i==18))
					System.out.print("*");
				
				else
					System.out.print(" ");
			}
			System.out.println();
		}}}