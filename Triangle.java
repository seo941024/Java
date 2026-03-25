package ex00;
import java.util.Scanner;

public class Triangle {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("정수 3개를 입력하시오 : ");
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		int c = scanner.nextInt();
		
		if (a>0 && b>0 && c>0) {		
			if (a+b>c && a+c>b && b+c>a) {
				System.out.println("삼각형이 됩니다.");
				}
			else
				System.out.println("불가능 합니다.");
		}
		else
			System.out.println("0이상의 숫자를 기입해주세요.");
		scanner.close();
	}

}

