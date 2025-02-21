package P_20250221;

import java.util.*;

public class Dict_Lv2 {

	class Solution {
		int count = -1;
		boolean flag = false;

		public void dfs(String[] alpha, String word, String findWord) {
			count++;

			if (word.equals(findWord)) {
				flag = true;
				return;
			}
			if (word.length() == 5)
				return;

			for (int i = 0; i < alpha.length && !flag; i++) {
				dfs(alpha, word + alpha[i], findWord);
			}
		}

		public int solution(String word) {

			String[] alpha = { "A", "E", "I", "O", "U" };

			dfs(alpha, "", word);
			int answer = count;
			return answer;

		}
	}
}
