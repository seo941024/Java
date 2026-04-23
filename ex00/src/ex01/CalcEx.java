package ex01;
class Calc_2{
	public static int abs(int a) {return a>0?a:-a;}
	public static int max(int a,int b) {return (a>b)?a:b;}
	public static int min(int a,int b) {return (a>b)?b:a;}
}
public class CalcEx {
	public static void main(String[] args) {
		System.out.println(Calc_2.abs(-5));
		System.out.println(Calc_2.max(10,8));
		System.out.println(Calc_2.min(-3,-8));
	}

}
	