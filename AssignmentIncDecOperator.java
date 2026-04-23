package ex00;

public class AssignmentIncDecOperator {
	public static void main(String[] args) {
		int a=3, b=3, c=3;
		
		a+=3;
		b*=3;
		c%=2;
		System.out.println("a="+ a +", b=" + b + ", c=" + c);
		
		/* a++ a를 1증가하고 증가전의 값 반환, ++a a를 1증가하고 증가된 값 반환
		a-- a를 1감소하고 감소전의 값 반환, --a a를 1감소하고 감소된 값 반환 */
		
		int d=3;
		a=d++; 
		System.out.println("a=" + a + ", d="+d);
		a=++d;
		System.out.println("a=" + a + ", d="+d);
		a=d--;
		System.out.println("a=" + a + ", d="+d);
		a=--d;
		System.out.println("a=" + a + ", d="+d);
		
	}
}
