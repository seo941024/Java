package ex01;
import java.util.Random;

public class Circle {
	int radius;
	String name;
	
	public Circle() {}
	
	public double getArea() {
		return 3.14*radius*radius;
		
	}
	public static void main(String[] args) {
		Random rd = new Random();
		int min = 1, max=10;
		Circle pizza;
		pizza = new Circle();
		pizza.radius = rd.nextInt(max - min +1)+min;
		pizza.name = "JavaPizza";
		double area = pizza.getArea();
		System.out.println(pizza.name + "의 면적은" + area);
		
		Circle donut = new Circle();
		donut.radius = rd.nextInt(max - min +1)+min;
		donut.name = "JavaDonut";
		area = donut.getArea();
		System.out.println(donut.name+"의 면적은" + area);
	}
}
	
