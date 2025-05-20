package P_20250412;

import java.io.*;
import java.util.*;

class virus {
	int row;
	int col; // 가장 최근에 전염된 장소
	int direct;
	int v;

	public virus(int r, int c, int d, int v) {
		this.row = r;
		this.col = c;
		this.direct = d;
		this.v = v;
	}
}

public class CT_미지의공간탈출 {
	static int N;
	static int M;
	static int F;
	static int[][] TwoD; // 평면도
	static List<int[][]> ThreeD; // 단면도 0 1 2 3 : 동 서 남 북
	static List<virus> virusList;
	static int[] machine = new int[2];
	static int[] exit = new int[2]; // 진짜 출구
	static int[] exit3D = new int[2]; // 시간의 벽에서 출구

	// 동서 남북
	static int[] d_r = { 0, 0, -1, 1 };
	static int[] d_c = { 1, -1, 0, 0 };

	// 단면도에서의 방향 별 시작, 끝 인덱스
	static int[][] flat_dr = new int[4][2];
	static int[][] flat_dc = new int[4][2];

	static int[][] flat3D;
	static int[] exit3D_flat = new int[2];

	// 거리 수
	static int answer = 0;

	public static void initSettingAndInput() {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		F = sc.nextInt();

		for (int i = 0; i < 4; i++) {
			if (i < 2) {// 동, 서
				flat_dr[i][0] = M;
				flat_dr[i][1] = 2 * M;

				if (i == 0) {
					flat_dc[i][0] = 2 * M;
					flat_dc[i][1] = 3 * M;
				} else {
					flat_dc[i][0] = 0;
					flat_dc[i][1] = M;
				}

			} else { // 남 북
				flat_dc[i][0] = M;
				flat_dc[i][1] = 2 * M;

				if (i == 2) {
					flat_dr[i][0] = 2 * M;
					flat_dr[i][1] = 3 * M;
				} else {
					flat_dr[i][0] = 0;
					flat_dr[i][1] = M;
				}
			}
		}

		flat3D = new int[3 * M][3 * M];
		for (int i = 0; i < 3 * M; i++) {
			Arrays.fill(flat3D[i], 1);
		}

		TwoD = new int[N][N];
		int[] timeStart = new int[2];
		boolean timeSFlag = false;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int n = sc.nextInt();

				if (n == 4) {
					exit[0] = i;
					exit[1] = 2;
				} else if (n == 3 && !timeSFlag) {
					timeStart[0] = i;
					timeStart[1] = j;
					timeSFlag = true;
				}

				TwoD[i][j] = n;
			}
		}

		boolean stopFlag = false;

		for (int i = timeStart[0]; i < timeStart[0] + M; i++) {
			for (int j = timeStart[1]; j < timeStart[1] + M; j++) {
				// 동서 남북 방향으로 체크 -> 0이 있으면 거기가 시간의 벽 출구
				for (int d = 0; d < 4; d++) {
					int row = i + d_r[d];
					int col = j + d_c[d];
					if (checkRange2D(row, col) && TwoD[row][col] == 0) {
						// 해당 방향의 단면도 + row/col 위치 가 답
						if (d < 2) {
							int exitRow = flat_dr[d][0] + i - timeStart[0];
							int exitCol = flat_dc[d][1] - 1;

							exit3D[0] = exitRow;
							exit3D[1] = exitCol;

						} else {
							int exitRow = flat_dr[d][1] - 1;
							int exitCol = flat_dc[d][0] + j - timeStart[1];

							exit3D[0] = exitRow;
							exit3D[1] = exitCol;
						}

						exit3D_flat[0] = row;
						exit3D_flat[1] = col;

						stopFlag = true;
						break;
					}
				}
			}
		}

		// System.out.println("시간의 벽에서 출구 (3M x 3M) : " + Arrays.toString(exit3D));

		for (int i = 0; i < 4; i++) { // 동서남북 입력
			for (int r = 0; r < M; r++) {
				for (int c = 0; c < M; c++) {
					int n = sc.nextInt();

					int row = flat_dr[i][0] + r;
					int col = flat_dc[i][0] + c;
					
					flat3D[row][col] = n;
				}

			}
		}

		for (int i = M; i < 2 * M; i++) {
			for (int j = M; j < 2 * M; j++) {
				flat3D[i][j] = sc.nextInt();
				if (flat3D[i][j]== 2) {
					machine[0] = i;
					machine[1] = j;
				}
			}
		}

		virusList = new ArrayList<>();

		for (int i = 0; i < F; i++) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			int d = sc.nextInt();
			int v = sc.nextInt();

			virus newV = new virus(r, c, d, v);
			virusList.add(newV);
		}

		sc.close();

	}

	public static void main(String[] args) {
// 입력받기 
		initSettingAndInput();

// 시간의 공간에서 최단 거리 구하기 
		if (!getSP_timeSpace()) {
			System.out.println("time space ; -1");
			System.out.println(pathTo_timeSpace);

			return;
		}

// 평면에서 탈출구까지 최단 거리 구하기 
		if (!getSP_flat()) {
			System.out.println("flat ; -1");
			return;
		}

		System.out.println(answer);

	}

	public static boolean checkRange2D(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}

	public static boolean checkRange3D(int row, int col) {
		return row >= 0 && row < 3 * M && col >= 0 && col < 3 * M;
	}

	static List<Integer> pathTo_timeSpace = new ArrayList<>();

	public static boolean getSP_timeSpace() {
		boolean[][] visited = new boolean[3 * M][3 * M];

		System.out.println("before SP ts");
		System.out.println("start : " + Arrays.toString(machine));
		System.out.println("goal : " + Arrays.toString(exit3D));

		visited[machine[0]][machine[1]] = true;

		dfs_timeSpace(machine, visited, new ArrayList<>());
		
		if (pathTo_timeSpace.size() == 0) return false;

		answer += pathTo_timeSpace.size();

		return true;

	}

	public static void dfs_timeSpace(int[] pos, boolean[][] visited, List<Integer> paths) {
		
		System.out.println("current : " + Arrays.toString(pos)); 
		System.out.println("path : " + paths); 
		
		if (pos[0] == exit3D[0] && pos[1] == exit3D[1]) { // 도착했을 경우 :리턴
			if (pathTo_timeSpace.size() == 0) {
				pathTo_timeSpace = new ArrayList<>(paths);
				System.out.println("ts update first path : " + pathTo_timeSpace);

				return;
			}

			if (pathTo_timeSpace.size() > paths.size()) {
				pathTo_timeSpace = new ArrayList<>(paths);
				System.out.println("ts update path : " + pathTo_timeSpace);

				return;
			}

			return;
		}

		for (int i = 0; i < 4; i++) { // 현재 위치에서 동서남북 방향으로 탐색
			int next_r = pos[0] + d_r[i];
			int next_c = pos[1] + d_c[i];

			if (checkRange3D(next_r, next_c) && !visited[next_r][next_c] && flat3D[next_r][next_c] == 0) {
				int[] next = { next_r, next_c };
				paths.add(i);
				visited[pos[0]][pos[1]] = true;

				
				dfs_timeSpace(next, visited, paths);
				paths.remove(paths.size() - 1);
				visited[pos[0]][pos[1]] = false;

				
			}
		}
	}

	static List<Integer> pathTo_flat = new ArrayList<>();

	public static boolean getSP_flat() {
		boolean[][] visited = new boolean[N][N];

		dfs_flat(exit3D_flat, visited, new ArrayList<>());
		if (pathTo_flat.size() == 0)
			return false;

		answer += pathTo_flat.size();

		return true;

	}

	public static void dfs_flat(int[] pos, boolean[][] visited, List<Integer> paths) {
		visited[pos[0]][pos[1]] = true;
		if (pos[0] == exit[0] && pos[1] == exit[1]) { // 도착했을 경우 :리턴
			if (pathTo_flat.size() == 0) {
				pathTo_flat = new ArrayList<>(paths);
				return;
			}

			if (pathTo_flat.size() > paths.size()) {
				pathTo_flat = new ArrayList<>(paths);
				return;
			}

			return;
		}

		for (int i = 0; i < 4; i++) { // 현재 위치에서 동서남북 방향으로 탐색
			int next_r = pos[0] + d_r[i];
			int next_c = pos[1] + d_c[i];

			if (checkRange2D(next_r, next_c) && !visited[next_r][next_c] && TwoD[next_r][next_c] == 0) {
				int[] next = { next_r, next_c };
				paths.add(i);
				dfs_flat(next, visited, paths);
				paths.remove(paths.size() - 1);
			}
		}

	}

}
