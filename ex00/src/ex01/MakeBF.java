package ex01;

public class MakeBF {
	public static void main(String[] args) {

		for (int i = 0; i<11; i++) {
			for (int j=0; j<11; j++) {
				if (j==0 || j==5 || j==10)
					System.out.print("*");
				else if (i==0 && (j==4 || j==6))
					System.out.print("*");
				else if (j==i || j==10-i)
					System.out.print("*");
				else
					System.out.print(" ");
			}
			System.out.println();
		}
	}
}  