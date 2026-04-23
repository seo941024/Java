package ex00;
import java.util.Scanner;

public class ExchangeD {
	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in); // 객체 생성
		
		System.out.println("원화를 입력하세요 (단위 원) : "); // 값을 입력 받는다
		double number = scanner.nextInt(); 
		double Dnumber;
		Dnumber = (double)number/1200;
			System.out.println(number + "원은 $"+ Dnumber+"입니다.");
			System.out.printf("%d원은 $%.2f입니다.", number, Dnumber); /*"{number} + 원은 {Dnumber}입니다."*/ // 첫번째온느게 문자열
		
		scanner.close();
	
	}

}