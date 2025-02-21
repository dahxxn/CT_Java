package P_20250221;

import java.util.*;

public class HateSameNum_Lv1 {

	public class Solution {
		public int[] solution(int[] arr) {
			int[] answer = {};

			Stack<Integer> stack = new Stack<>();
			stack.push(arr[0]);
			for (int i = 1; i < arr.length; i++) {
				int lastOne = stack.peek();

				if (lastOne != arr[i]) {
					stack.push(arr[i]);
				}
			}
			answer = new int[stack.size()];

			int n = stack.size();
			for (int i = 0; i < n; i++) {
				answer[n - i - 1] = stack.pop();
			}

			return answer;
		}
	}
}
