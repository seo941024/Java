package ex00;
import java.util.Scanner;

public class ExchangeD {
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		
	
		System.out.println("원화를 입력하세요 (단위 원) : ");
		int number = in.nextInt();
		double Dnumber;
		Dnumber = (double)number/1200;
			System.out.println(number + "원은 $"+ Dnumber+"입니다.");
		
		
		in.close();
	
	}

}