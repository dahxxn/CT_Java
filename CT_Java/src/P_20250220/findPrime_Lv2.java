package P_20250220;

import java.util.*;

public class findPrime_Lv2 {

	class Solution {
		List<Integer> primeNums = new ArrayList<>();
		List<Integer> checkNumList = new ArrayList<>();

		public boolean isPrime(String number) {
			int num = Integer.parseInt(number);

			if (num == 1 || num == 0) {
				return false;
			}

			if (num == 2) {
				checkNumList.add(num);
				primeNums.add(num);
				return true;
			}

			checkNumList.add(num);
			for (int i = 2; i <= Math.sqrt(num); i++) {
				if (num % i == 0) {
					return false;
				}
			}
			primeNums.add(num);
			return true;
		}

		public void dfs(int[] nums, boolean[] visited, String currentNum) {

			for (int i = 0; i < nums.length; i++) {
				if (!visited[i]) {
					String newNums = currentNum + Integer.toString(nums[i]);

					if (newNums.length() > 1) {
						if (newNums.charAt(0) == '0')
							continue;
					}

					// System.out.println(newNums);
					visited[i] = true;

					if (!checkNumList.contains(Integer.parseInt(newNums))) {
						isPrime(newNums);
					}

					dfs(nums, visited, newNums);
					visited[i] = false;
				}
			}

		}

		public int solution(String numbers) {
			int answer = 0;

			int[] numList = new int[numbers.length()];
			boolean[] visited = new boolean[numbers.length()];

			for (int i = 0; i < numbers.length(); i++) {
				int num = (int) numbers.charAt(i) - '0';
				numList[i] = num;
			}

			String currentNum = "";
			dfs(numList, visited, currentNum);
			answer = primeNums.size();

			return answer;
		}
	}
}
