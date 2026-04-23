package ex01;

class Circle_4{
	int radius;
	public Circle_4(int radius) {
		this.radius = radius;
	}
	public double getArea() {
		return 3.14*radius*radius;
	}
}

public class CircleArray {
	public static void main (String[] args) {
		Circle_4 []c;
		c = new Circle_4[5];
		
	for(int i=0; i<c.length; i++)
		c[i] = new Circle_4(i);
	
	for(int i=0; i<c.length; i++)
		System.out.print((int)(c[i].getArea())+"");
		}
}
