package ex02;
class Point3 {
	private int x, y;
	public Point3(int x, int y) {
		this.x=x;
		this.y=y;
	}
	public String toString() {
		return "Point("+x+","+y+")";
	}
}
public class ToStringEx {
	public static void main(String [] args) {
		Point3 p = new Point3(2,3);
		System.out.println(p.toString());
		System.out.println(p);
		System.out.println(p+ "입니다.");
		
	}
}
