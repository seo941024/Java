package ex00;
import java.util.Scanner;

public class Grading {
	public static void main(String[] args) {
		char grade;
		Scanner scanner = new Scanner(System.in);
		
	
		while(true) {
			System.out.println("점수를 입력하세요 : ");
			int score = scanner.nextInt();
					
			if(score > 100 || score < 0) {
			System.out.println("제대로 된 점수를 기입해주세요.");
			continue;
			}
		if(score >= 90)
			grade = 'A' ;
		else if(score >= 80)
			grade = 'B' ;
		else if(score >= 70)
			grade = 'C' ;
		else if(score >= 60)
			grade = 'D' ;
		else
			grade = 'F' ;
		System.out.println("학점은 \"" + grade + "\" 입니다.");
		break;
		
		}
		scanner.close();
	}
}
