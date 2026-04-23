package ex01;

public class Book_2 {
	String title;
	String author;
	void show() {System.out.println(title + "" + author);}
	
	public Book_2() {
		this("","");
		System.out.println("생성자 호출됨");
	}
	
	public Book_2(String title) {
		this(title, "작자미상");
	}
	
	public Book_2(String title, String author) {
		this.title = title; this.author = author;
		
	}
	
	public static void main(String [] args) {
		Book_2 littlePrince= new Book_2("어린왕자","생텍쥐페리");
		Book_2 loveStory = new Book_2("춘향전");
		Book_2 emptyBook = new Book_2();
		loveStory.show();
	}
}
