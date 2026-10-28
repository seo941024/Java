package ex03;
import java.util.*;
public class HashMapScoreEx {
	public static void main(String[] args) {
		HashMap<String, Integer> scoreMap = new HashMap<String, Integer>();
		
		scoreMap.put("김성동", 97);
		scoreMap.put("황기태", 12);
		scoreMap.put("김남윤", 84);
		scoreMap.put("이재문", 57);
		scoreMap.put("한원선", 2);
		
		System.out.println("HashMap의 요소 개수 : " + scoreMap.size());
		
		Set<String> keys = scoreMap.keySet();
		
		Iterator<String> it = keys.iterator();
		
		while(it.hasNext()) {
			String name = it.next();
			int score = scoreMap.get(name);
			System.out.println(name + ":" + score);
		}
	}
}
