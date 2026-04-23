package ex01;
import java.util.Random;

public class Circle_2 {
	int radius;
	String name;
	
	public Circle_2() {
		radius = 1; name = "";
	}
	public Circle_2(int r, String n) {
		radius = r; name = n;
	}
	public double getArea() {
		return 3.14*radius*radius;
	}
	
	public static void main(String[] args) {
		Random rd = new Random();
		int min = 1, max=10;
		
		Circle_2 pizza = new Circle_2(rd.nextInt(max - min +1)+min, "Javapizza");
		
		double area = pizza.getArea();
		System.out.println(pizza.name+"의 면적은"+area);
		
		Circle_2 donut = new Circle_2();
		donut.name = "DonutPizza";
		area = donut.getArea();
		System.out.println(donut.name + "의 면적은" + area);
		}
	}
