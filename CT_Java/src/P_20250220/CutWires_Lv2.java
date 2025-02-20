package P_20250220;

import java.util.*;

public class CutWires_Lv2 {

	class Solution {
		public int solution(int n, int[][] wires) {
			int answer = -1;

			HashMap<Integer, List<Integer>> w = new HashMap<>();
			boolean[] checked = new boolean[n];

			for (int i = 0; i < wires.length; i++) {
				int a = wires[i][0];
				int b = wires[i][1];

				List<Integer> aH = w.getOrDefault(a, new ArrayList<>());
				aH.add(b);

				List<Integer> bH = w.getOrDefault(b, new ArrayList<>());
				bH.add(a);

				w.put(a, aH);
				w.put(b, bH);
			}

			answer = 100;

			for (int i = 0; i < wires.length; i++) {
				int start1 = wires[i][0];
				int start2 = wires[i][1];
				// System.out.println("전선 자르기 : " + start1 + " -- " + start2);

				int[] cnt = { 0, 0 };

				for (int j = 0; j < wires[i].length; j++) {
					Queue<Integer> q = new LinkedList<>();
					q.offer(wires[i][j]);

					boolean[] visited = new boolean[101];
					visited[start1] = true;
					visited[start2] = true;

					while (!q.isEmpty()) {
						int current = q.poll();
						visited[current] = true;
						cnt[j]++;
						// System.out.print(current + ", ");

						List<Integer> linked = w.get(current);

						for (int link : linked) {
							if (!visited[link]) {
								q.offer(link);
							}
						}
					}
					// System.out.println();

				}

				answer = Math.min(answer, Math.abs(cnt[0] - cnt[1]));
			}
			return answer;
		}
	}
}
