package ex02;

class Shape2 {
    public Shape2 next;

    public Shape2() {
        next = null;
    }

    public void draw() {
        System.out.println("Shape");
    }
}

class Line2 extends Shape2 {
    public void draw() {
        System.out.println("Line");
    }
}

class Rect2 extends Shape2 {
    public void draw() {
        System.out.println("Rect");
    }
}

class Circle2 extends Shape2 {
    public void draw() {
        System.out.println("Circle");
    }
}

public class UsingOverride {

    static void paint(Shape2 p) {
        p.draw();
    }

    public static void main(String[] args) {

        paint(new Line2());
        paint(new Shape2());
        paint(new Rect2());
        paint(new Circle2());

        System.out.println("-----");
        
        Shape2 start = new Line2();
        Shape2 last = start;

        Shape2 obj = new Rect2();
        last.next = obj;
        last = obj;

        obj = new Line2();
        last.next = obj;
        last = obj;

        obj = new Circle2();
        last.next = obj;

        // 🔥 순회 출력
        Shape2 p = start;
        while (p != null) {
            p.draw();
            p = p.next;
        }
    }
}