package ex01;

public class MakeStar {
	public static void main(String[] args) {

		for (int i = 0; i<5; i++) {
			for (int j=0; j<9; j++) {
				if (j==i+4 || j==4-i)
					System.out.print("*");
				else if (i==4)
					System.out.print("*");
				else
					System.out.print(" ");
			}
			System.out.println();
		}
		
		/*for (int i = 0; i<5; i++) {
			for (int j=0; j<5; j++) {
				if (j==4 || j==4-i)
					System.out.print("*");
				else if (i==4) 
					System.out.print("*");
				else
					System.out.print(" ");
			}
			System.out.println();
		}*/
		
		
		/*for (int i = 0; i<5; i++) {
			for (int j=0; j<i+1;j++) {
				if (j==0 || j==i)
					System.out.print("*");
				else if (i==4) 
					System.out.print("*");
				else
					System.out.print(" ");
			}
			System.out.println();
		}*/
	}
}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*for (int i = 0; i < n; i++) {
		    for (int j = i; j < n - 1; j++) {
		        System.out.print(" ");
		    }

		    for (int j = 0; j < 2 * i + 1; j++) {
		        if (i == n - 1 || j == 0 || j == 2 * i) {
		            System.out.print("*"); 
		        }
		        else {
		            System.out.print(" ");
		        }
		    }
		    System.out.println();
		} 
	}
}
*/
