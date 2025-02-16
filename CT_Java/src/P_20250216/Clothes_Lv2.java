package P_20250216;

import java.util.*;

public class Clothes_Lv2 {
	class Solution {
		public int solution(String[][] clothes) {
			int answer = 0;

			int totalCount = clothes.length;

			HashMap<String, List<String>> clothesMap = new HashMap<>();

			for (int i = 0; i < clothes.length; i++) {
				String type = clothes[i][1];
				String name = clothes[i][0];
				List<String> clothList = clothesMap.getOrDefault(type, new ArrayList<String>());

				clothList.add(name);
				clothesMap.put(type, clothList);
			}

			answer = 1;
			for (String type : clothesMap.keySet()) {
				List<String> clothList = clothesMap.get(type);
				answer *= (clothList.size() + 1);
			}

			return answer - 1;
		}
	}
}
