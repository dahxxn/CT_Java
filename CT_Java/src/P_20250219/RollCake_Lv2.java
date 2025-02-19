package P_20250219;

import java.util.*;

public class RollCake_Lv2 {

	class Solution {
		public int solution(int[] topping) {
			int answer = 0;
			int cakeSize = topping.length;

			HashMap<Integer, Integer> A = new HashMap<>();
			HashMap<Integer, Integer> B = new HashMap<>();

			for (int i = 0; i < topping.length; i++) {
				int top = topping[i];

				int cnt = A.getOrDefault(top, 0);
				A.put(top, cnt + 1);
			}

			for (int i = 0; i < topping.length; i++) {
				int top = topping[i];
				int cnt = B.getOrDefault(top, 0);

				B.put(top, cnt + 1);

				int A_cnt = A.get(top);
				if (A_cnt == 1) {
					A.remove(top);
				} else {
					A.put(top, A_cnt - 1);
				}

				if (A.size() == B.size()) {
					answer++;
				}
			}

			return answer;
		}
	}
}
