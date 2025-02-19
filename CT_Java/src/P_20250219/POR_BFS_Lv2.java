package P_20250219;

import java.util.*;

public class POR_BFS_Lv2 {

	class Solution {
		public int solution(int[][] maps) {
			int answer = 0;

			int[][] path = new int[maps.length][maps[0].length];
			path[0][0] = 1; // y,x

			Queue<int[]> q = new LinkedList<>();
			q.offer(new int[] { 0, 0 });

			boolean[][] visited = new boolean[maps.length][maps[0].length];
			visited[0][0] = true;

			int[] y = { 1, 0, -1, 0 };
			int[] x = { 0, 1, 0, -1 }; // 남 동 서 북

			while (!q.isEmpty()) {
				int[] pos = q.poll();
				// System.out.println("current pos : " + pos[0] + ", " + pos[1]);
				if ((pos[0] == maps.length - 1) && (pos[1] == maps[0].length - 1))
					break;

				for (int i = 0; i < 4; i++) {
					int nextX = pos[1] + x[i];
					int nextY = pos[0] + y[i];
					if (nextX >= 0 && nextX < maps[0].length && nextY >= 0 && nextY < maps.length) {
						if (maps[nextY][nextX] == 1 && !visited[nextY][nextX]) {
							// System.out.println(nextX + " , " + nextY);
							q.offer(new int[] { nextY, nextX });
							visited[nextY][nextX] = true;
							path[nextY][nextX] = path[pos[0]][pos[1]] + 1;
						}
					}
				}

			}

			if (path[maps.length - 1][maps[0].length - 1] == 0)
				return -1;

			return path[maps.length - 1][maps[0].length - 1];
		}
	}
}
