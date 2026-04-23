package ex00;
import java.util.Scanner;

public class ChangeCalc {
	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("금액을 입력하세요 : ");
		int change= scanner.nextInt();
		
		int change50k = change/50000;
		change = change % 50000;
		int change10k = change/10000;
		change = change % 10000;
		int change1k = change/1000;
		change = change % 1000;
		int change500= change/500;
		change = change % 500;
		int change100= change/100;
		change = change % 100;
		int change50 = change/50;
		change = change % 50;
		int change10= change/10;
		change = change % 10;
		int change1 = change/1;
		change = change % 1;
		
		System.out.println("5만원권" + change50k +"매");
		System.out.println("1만원권" + change10k +"매");
		System.out.println("1천원권" + change1k +"매");
		System.out.println("오백원" + change500 +"개");
		System.out.println("백원" + change100 +"개");
		System.out.println("백원" + change50 +"개");
		System.out.println("십원" + change10 +"개");
		System.out.println("일원" + change1 +"개\n입니다.");
		
		scanner.close();
}
}