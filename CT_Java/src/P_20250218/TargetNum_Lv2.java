package P_20250218;

import java.util.*;

public class TargetNum_Lv2 {

	class Solution {
		private int count = 0;

		public void cal(int[] numbers, int target, int index, int result, List<Integer> numList) {
			if ((numList.size() == numbers.length)) {
				if (result == target) {
					count++;
					return;
				} else {
					return;
				}
			}

			int current = numbers[index];
			int minusC = (-1) * numbers[index];

			numList.add(current);
			cal(numbers, target, index + 1, result + current, numList);
			numList.remove(numList.indexOf(current));

			numList.add(minusC);
			cal(numbers, target, index + 1, result + minusC, numList);
			numList.remove(numList.indexOf(minusC));
		}

		public int solution(int[] numbers, int target) {
			int answer = 0;

			List<Integer> numList = new ArrayList<>();
			cal(numbers, target, 0, 0, numList);

			answer = count;
			return answer;
		}
	}
}
