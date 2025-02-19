package P_20250219;

import java.util.*;

public class POR_DFS_Lv2 {

	class Solution {
		private int minPath = 100000;
		private List<int[]> NSEW = new ArrayList<>();
		private int[] N = { -1, 0 };
		private int[] S = { 1, 0 };
		private int[] E = { 0, 1 };
		private int[] W = { 0, -1 };

		public List<int[]> getNextPath(int x, int y, int[][] maps, boolean[][] visited) {
			List<int[]> nextPathList = new ArrayList<>();
			for (int[] point : NSEW) {
				int xNext = x + point[1];
				int yNext = y + point[0];

				if (((xNext >= 0) && (xNext < maps[0].length) && (yNext >= 0) && (yNext < maps.length))) {
					if (maps[yNext][xNext] == 1) {
						int[] next = new int[2];
						next[0] = xNext;
						next[1] = yNext;
						nextPathList.add(next);
					}
				}
			}

			return nextPathList;
		}

		public void fastforward(int x, int y, int[][] maps, boolean[][] visited, int path) {
			if (x == maps[0].length - 1 && y == maps.length - 1) {
				minPath = Math.min(path, minPath);
				return;
			}

			List<int[]> nextPathList = getNextPath(x, y, maps, visited);
			if (nextPathList.size() == 0)
				return;

			for (int[] next : nextPathList) {
				maps[next[1]][next[0]] = 0;
				fastforward(next[0], next[1], maps, visited, path + 1);
				maps[next[1]][next[0]] = 1;
			}

		}

		public int solution(int[][] maps) {
			int answer = 0;
			NSEW.add(N);
			NSEW.add(S);
			NSEW.add(E);
			NSEW.add(W);

			boolean[][] visited = new boolean[maps.length][maps[0].length];

			for (int i = 0; i < maps.length; i++) {
				for (int j = 0; j < maps[0].length; j++) {
					if (maps[i][j] == 0) {
						visited[i][j] = true;
					}
				}
			}

			visited[0][0] = true;
			fastforward(0, 0, maps, visited, 1);

			if (minPath == 100000)
				answer = -1;
			else {
				answer = minPath;
			}
			return answer;
		}
	}
}
