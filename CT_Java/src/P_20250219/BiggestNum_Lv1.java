package P_20250219;

import java.util.*;

public class BiggestNum_Lv1 {

	class Solution {
		public String solution(int[] numbers) {
			String answer = "";
			String[] numberStr = new String[numbers.length];
			for (int i = 0; i < numbers.length; i++) {
				numberStr[i] = Integer.toString(numbers[i]);
			}

			Arrays.sort(numberStr, new Comparator<String>() {
				@Override
				public int compare(String num1, String num2) {
					return (num2.concat(num1)).compareTo(num1.concat(num2));
				}
			});

			StringBuilder sb = new StringBuilder();

			if (numberStr[0].equals("0"))
				return "0";

			for (int i = 0; i < numberStr.length; i++) {
				sb.append(numberStr[i]);
			}

			return sb.toString();
		}
	}
}
