package P_20250214;

import java.util.Arrays;

class Solution {
	public String solution(String s) {
		String answer = "";

		boolean newWord = true;
		char[] sToArray = s.toCharArray();

		for (int i = 0; i < sToArray.length; i++) {
			char current = sToArray[i];

			if (current == ' ') {
				newWord = true;
				continue;
			}

			if (newWord) {
				if (Character.isLowerCase(current)) {
					sToArray[i] = (char) (current - 32);
				}
				newWord = false;
			} else if (!newWord && Character.isUpperCase(current)) {
				sToArray[i] = (char) (current + 32);
			}
		}

		answer = new String(sToArray);

		return answer;
	}
}

class TestCase {
	public String s;
}

public class JadenCase {
	public static void main(String[] args) {
		Solution s = new Solution();
		TestCase case1 = new TestCase();
		case1.s = " aa aab";

		TestCase case2 = new TestCase();
		case2.s = "for the last week";

		System.out.println(s.solution(case1.s));
		System.out.println(s.solution(case2.s));
	}

}
