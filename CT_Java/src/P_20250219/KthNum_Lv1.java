package P_20250219;

import java.util.*;

public class KthNum_Lv1 {

	class Solution {
		public int[] solution(int[] array, int[][] commands) {
			int[] answer = new int[commands.length];

			for (int i = 0; i < commands.length; i++) {
				int start = commands[i][0];
				int end = commands[i][1];
				int k = commands[i][2];

				int[] subArr = new int[end - start + 1];
				int cnt = 0;
				for (int j = start - 1; j < end; j++) {
					subArr[cnt] = array[j];
					cnt++;
				}

				Arrays.sort(subArr);
				answer[i] = subArr[k - 1];

			}

			return answer;
		}
	}
}
