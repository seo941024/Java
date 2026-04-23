package ex00;
import java.util.Scanner;

public class CirclePoint {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("원의 중심 x, y와 반지름 r을 입력하시오 : ");
		
		double x = scanner.nextDouble();
		double y = scanner.nextDouble();
		double r = scanner.nextDouble();
		
		System.out.println("점 a, b를 입력하시오 : ");
		
		double a = scanner.nextDouble();
		double b = scanner.nextDouble();
		
		//r = Math.sqrt(Math.pw(x, 2) + Math.pow(y, 2)
		if ((x-a)*(x-a)+(y-b)*(y-b) < r*r)
			System.out.println("점 ("+a+","+b+")는 원 안에 있습니다.");
		
		else if ((x-a)*(x-a)+(y-b)*(y-b) == r*r)
			System.out.println("점 ("+a+","+b+")는 원 경계에 있습니다.");
		
		else
			System.out.println("점 ("+a+","+b+")는 원 바깥에 있습니다.");
		
		scanner.close();
	}

}
