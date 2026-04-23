package ex01;

public class Circle_3 {
	int radius;
	public Circle_3(int radius) {this.radius = radius;}
	public void set(int radius) {this.radius = radius;}
	public static void main(String[] args) {
		Circle_3 ob1 = new Circle_3(1);
		Circle_3 ob2 = new Circle_3(2);
		Circle_3 s;
		
		s = ob2;
		ob1 = ob2;
		System.out.println("ob1.radius="+ob1.radius);
		System.out.println("ob2.radius="+ob2.radius);
	}
}
