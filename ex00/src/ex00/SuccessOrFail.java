package ex00;
import java.util.Scanner;

public class SuccessOrFail {
	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
		System.out.println("점수를 입력하시오 : ");
		int score = scanner.nextInt();
			if (score > 100 || score < 0) {
				System.out.println("0부터 100사이의 숫자를 기입하세요.");
				continue;
			}
			if (score >= 80) 
				System.out.println("축하합니다. 합격입니다.");
			else
				System.out.println("불합격입니다.");
			
			break;
		}
			scanner.close();
			
	}

}
