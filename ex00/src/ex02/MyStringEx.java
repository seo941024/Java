package ex02;

class MyString {
	
	String str;
	//char[] s = new char[50];
	
	public MyString(String str) {
		this.str = str;
	}
	
	public String toString() {
		return str;
	}
	
	// 직접 구현
	public String concat(MyString str) {
		String concatStr = new String();
		this.str += str.str;
		concatStr = this.str;
		return concatStr;
	}
	public String trim() {
		String trimStr = new String();
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == ' ')
				continue;
			trimStr += str.charAt(i);
		}
		str = trimStr;
		return trimStr;
	}
	public String replace(String chk, String rpl) {
		int x = 0, y = 0;
		String outStr = new String();
		out:for(int i = 0; i < str.length(); i++) {
			String replace = new String();
			for(int j = i; j < str.length(); j++) {
				replace += str.charAt(j);
				System.out.println(replace);
				if(chk.equals(replace)) {
					x = j;	y = i;	break out;
				}
			}
		}
		System.out.println("확인:" + x + "," + y);
		for(int i = 0; i < y; i++)
			outStr += str.charAt(i);
		outStr += rpl;
		for(int j = x + 1; j < str.length(); j++)
			outStr += str.charAt(j);
		str = outStr;
		return outStr;
	}
	public String split() {
		return "";
	}
	public String substring() {
		return "";
	}
}

public class MyStringEx {
	public static void main(String[] args) {
		MyString a = new MyString(" C#");
		MyString b = new MyString(",C++");
		System.out.println(a);
		System.out.println(b);
		System.out.println(a.concat(b));
		System.out.println(a.trim());
		System.out.println(a.replace("C#", "Java"));
		System.out.println(a);
	}
}