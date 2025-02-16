package P_20250216;

public class HarshadNumber_Lv1 {
	class Solution {
		public int getSum(int x) {
			int sum = 0;

			while (x > 0) {
				sum += (x % 10);
				x /= 10;
			}

			return sum;
		}

		public boolean solution(int x) {
			int sum = getSum(x);
			return (x % sum == 0) ? true : false;
		}
	}
}
