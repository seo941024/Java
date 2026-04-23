package ex01;

public class DoWhileSample {
	public static void main (String[] args) {
		char ch = 'A';
		
		do {
			System.out.print(ch);
			ch = (char)(ch+1);
		} while ( ch <= 'Z');	
	}
}
