package P_20250220;

import java.util.*;

public class MockTest_Lv1 {

	class Solution {

		public int[] solution(int[] answers) {
			int[] answer = {};

			Queue<Integer> One = new LinkedList<>();
			Queue<Integer> Two = new LinkedList<>();
			Queue<Integer> Three = new LinkedList<>();

			for (int i = 1; i <= 5; i++) {
				One.offer(i);
			}
			List<Integer> twoList = Arrays.asList(2, 1, 2, 3, 2, 4, 2, 5);
			List<Integer> threeList = Arrays.asList(3, 3, 1, 1, 2, 2, 4, 4, 5, 5);

			Two = new LinkedList<>(twoList);
			Three = new LinkedList<>(threeList);

			HashMap<Integer, Integer> correctCnt = new HashMap<>();
			correctCnt.put(1, 0);
			correctCnt.put(2, 0);
			correctCnt.put(3, 0);

			int maxCount = 0;

			for (int i = 0; i < answers.length; i++) {
				int ans = answers[i];
				// System.out.println("current Ans = " + ans);
				int oneAns = One.poll();
				int twoAns = Two.poll();
				int threeAns = Three.poll();

				if (oneAns == ans) {
					int cnt = correctCnt.get(1);
					correctCnt.put(1, cnt + 1);
					maxCount = Math.max(maxCount, cnt + 1);
					// System.out.println(oneAns);
				}
				if (twoAns == ans) {
					int cnt = correctCnt.get(2);
					correctCnt.put(2, cnt + 1);
					maxCount = Math.max(maxCount, cnt + 1);
					// System.out.println(twoAns);
				}
				if (threeAns == ans) {
					int cnt = correctCnt.get(3);
					correctCnt.put(3, cnt + 1);
					maxCount = Math.max(maxCount, cnt + 1);
					// System.out.println(threeAns);

				}

				One.offer(oneAns);
				Two.offer(twoAns);
				Three.offer(threeAns);
			}

			List<Integer> answerList = new ArrayList<>();
			for (int i = 1; i <= 3; i++) {
				if (maxCount == correctCnt.get(i))
					answerList.add(i);
			}

			answer = new int[answerList.size()];
			for (int i = 0; i < answerList.size(); i++) {
				answer[i] = answerList.get(i);
			}

			return answer;
		}
	}
}
