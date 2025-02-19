package P_20250219;

public class isPrimeNum_Lv1 {
	class Solution {

		public boolean isNum(int n) {
			if (n == 2)
				return true;

			for (int i = 2; i <= Math.sqrt(n); i++) {
				if (n % i == 0)
					return false;
			}

			return true;
		}

		public int solution(int n) {
			int answer = 0;

			for (int i = 2; i <= n; i++) {
				if (isNum(i))
					answer++;
			}

			return answer;
		}
	}
}
