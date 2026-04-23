package ex01;

public class MakeWM2 {
	public static void main(String[] args) {

		for (int i = 0; i<19; i++) {
			for (int j=0; j<19; j++) {
				if (j==9 || i==8 )
					System.out.print("*");
				
				else if ((j==0 && i<9)||(j==18 && i>8))
					System.out.print("*");
				
				else if ((i==j*2-1 && i<9)||(i+16==j*2-1 && i>8))
					System.out.print("*");
				
				else if ((j== 18-(i*3) && i<4)||(j== 6 && i==16)||(j== 3 && i==17))
					System.out.print("*");
				
				else if ((j>9 && i==0)||(j<10 && i==18))
					System.out.print("*");
				
				else
					System.out.print(" ");
			}
			System.out.println();
		}}}