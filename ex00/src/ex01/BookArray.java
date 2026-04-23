package ex01;
import java.util.Scanner;
class Book_3{
	String title, author;
	public Book_3(String title, String author) {
		this.title = title;
		this.author = author;
	}
}
public class BookArray {
	public static void main(String[] args) {
		Book_3 [] book_3 = new Book_3[2];
		
		Scanner scanner= new Scanner(System.in);
		for(int i=0; i<book_3.length;i++) {
			System.out.print("제목>>");
			String title = scanner.nextLine();
			System.out.print("저자>>");
			String author = scanner.nextLine();
			book_3[i] = new Book_3(title, author);
		}
		
		for(int i =0; i<book_3.length; i++)
			System.out.print("("+book_3[i].title+","+book_3[i].author+")");
		
		scanner.close();
	}

}
