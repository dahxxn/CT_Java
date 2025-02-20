package P_20250220;

import java.util.*;
import java.util.stream.Collectors;

public class HIndex_Lv2 {

	class Solution {
		public int solution(int[] citations) {
			int answer = 0;

			List<Integer> cList = Arrays.stream(citations).boxed().collect(Collectors.toList());

			Collections.sort(cList, Collections.reverseOrder());
			System.out.println(cList);

			for (int i = 0; i < cList.size(); i++) { // i = 0~n
				int count = cList.get(i);

				int h = cList.size() - i; // h = n -> 0 : 인용 크기
				int h_cnt = 0; // h번 이상 인용된 논문 수

				for (int j = 0; j < cList.size(); j++) {
					if (cList.get(j) >= h) {
						h_cnt++;
					} else {
						break;
					}
				}

				if (h_cnt >= h) {
					return h;
				}
			}

			return 0;
		}
	}
}
