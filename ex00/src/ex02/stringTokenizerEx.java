package ex02;
import java.util.StringTokenizer;
public class stringTokenizerEx {
	public static void main(String[] args) {
		StringTokenizer st = new StringTokenizer("홍길동/장화/홍련/콩쥐/팢튀","/");
		while (st.hasMoreTokens())
			System.out.println(st.nextToken());
	}

}
