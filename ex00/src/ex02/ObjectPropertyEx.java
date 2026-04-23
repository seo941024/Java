package ex02;
class Point_2 {
	private int x, y;
	public Point_2(int x, int y) {
		this.x = x;
		this.y = y;
		
	}
}
public class ObjectPropertyEx {
	public static void print(Object obj) {
		System.out.println(obj.getClass().getSimpleName());
		System.out.println(obj.hashCode()); 
		System.out.println(obj.toString());
		System.out.println(obj);
	}
	public static void main(String[] args) {
		Point p = new Point(2,3);
		print(p);
	}
}
