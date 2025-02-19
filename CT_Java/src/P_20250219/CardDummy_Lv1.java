package P_20250219;

import java.util.*;

public class CardDummy_Lv1 {

	class Solution {
		public String solution(String[] cards1, String[] cards2, String[] goal) {
			String answer = "";

			Queue<String> cardOneList = new LinkedList<>(Arrays.asList(cards1));
			Queue<String> cardTwoList = new LinkedList<>(Arrays.asList(cards2));

			for (int i = 0; i < goal.length; i++) {
				String word = goal[i];

				if (cardOneList.contains(word)) {
					if (cardOneList.peek().equals(word)) {
						cardOneList.poll();
					} else {
						return "No";
					}
				} else if (cardTwoList.contains(word)) {
					if (cardTwoList.peek().equals(word)) {
						cardTwoList.poll();
					} else {
						return "No";
					}
				}

			}

			return "Yes";
		}
	}
}
