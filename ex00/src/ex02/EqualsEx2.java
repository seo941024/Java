package ex02;
class Rect3 {
	private int width;
	private int height;
	public Rect3(int width, int height) {
		this.width = width;
		this.height = height;
	}
	public boolean equals(Object obj) {
		Rect3 p = (Rect3)obj;
		if (width*height == p.width*p.height)
			return true;
		else
			return false;
	}
}

public class EqualsEx2 {
	public static void main(String[] args) {
		Rect3 a = new Rect3(2,3);
		Rect3 b = new Rect3(3,2);
		Rect3 c = new Rect3(3,4);
		if(a.equals(b))
			System.out.println("a is equal to b");
		if(a.equals(c))
			System.out.println("a is equal to c");
		if(a.equals(c))
			System.out.println("n is equal to c");
	}

}
