package ex00;
import java.util.Scanner;

public class NestedIf {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
		System.out.println("점수를 입력하세요 : ");
		int score = scanner.nextInt();
		
		
		if(score > 100 || score < 0) {
			System.out.println("제대로된 점수를 기입해주세요.");
			continue;
		}
		
		System.out.println("학년을 입력하세요 : ");
		int year = scanner.nextInt();
		
		if(year > 4 || year < 1) {
			System.out.println("제대로된 학년을 기입해주세요.");
			continue;
		}
		
			if (score >= 60) {
				if(year != 4)
					System.out.println("합격!");
				else if(score >= 70)
					System.out.println("합격!");
				else
					System.out.println("불합격!");
			}
			else
				System.out.println("불합격!");
		
			break;
			
		
		}
		scanner.close();
	}
}


