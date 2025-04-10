package P_20250410;
import java.io.*;
import java.util.*;

public class BJ_12100 {
	static int N;
	static int result;
	static char[] directions = { 'E', 'W', 'S', 'N' }; // 오른쪽, 왼쪽, 위, 아래

	// 가장 큰 블록의 값 반환하는 함수
	public static int getMaxNum(int[][] map) {
		int max = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (max < map[i][j])
					max = map[i][j];
			}
		}

		return max;
	}

	// 이동한 방향에 따른 맵 업데이트
	public static int[][] updateMap(int[][] originMap, char direction) {
		int[][] map = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = originMap[i][j];
			}
		}

		switch (direction) {
		case 'E': // col ++
			for (int row = 0; row < N; row++) {
				for (int col = N - 1; col > 0; col--) {
					for (int next = col - 1; next >= 0; next--) {
						if (map[row][col] == 0) { // 0이면 옆의 값 가져오기
							map[row][col] = map[row][next];
							map[row][next] = 0;
						}

						else if (map[row][col] == map[row][next]) { // 같은 값 충돌 시 더하기
							map[row][col] *= 2;
							map[row][next] = 0;
							break; 
						}

						else if(map[row][next] == 0) {
							continue; 
						}
						
						else { // 다른 값이면 그 다음 포지션으로
							break;
						}
					}

				}
			}

			break;

		case 'W': // col --
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N - 1; col++) {
					for (int next = col + 1; next < N; next++) {
						if (map[row][col] == 0) { // 0이면 옆의 값 가져오기
							map[row][col] = map[row][next];
							map[row][next] = 0;
						}

						else if (map[row][col] == map[row][next]) { // 같은 값 충돌 시 더하기
							map[row][col] *= 2;
							map[row][next] = 0;
							break; 

						}


						else if(map[row][next] == 0) {
							continue; 
						}
						
						else { // 다른 값이면 그 다음 포지션으로
							break;
						}
					}
				}
			}

			break;

		case 'S': // row ++
			for (int col = 0; col < N; col++) {
				for (int row = N - 1; row > 0; row--) {
					for (int next = row - 1; next >= 0; next--) {
						if (map[row][col] == 0) { // 0이면 옆의 값 가져오기
							map[row][col] = map[next][col];
							map[next][col] = 0;
						}

						else if (map[row][col] == map[next][col]) { // 같은 값 충돌 시 더하기
							map[row][col] *= 2;
							map[next][col] = 0;
							break; 
						}

						else if(map[next][col] == 0) {
							continue; 
						}
						
						else { // 다른 값이면 그 다음 포지션으로
							break;
						}
					}

				}
			}

			break;

		case 'N': // row --
			for (int col = 0; col < N; col++) {
				for (int row = 0; row < N - 1; row++) {
					for (int next = row + 1; next < N; next++) {
						if (map[row][col] == 0) { // 0이면 옆의 값 가져오기
							map[row][col] = map[next][col];
							map[next][col] = 0;
						}

						else if (map[row][col] == map[next][col]) { // 같은 값 충돌 시 더하기
							map[row][col] *= 2;
							map[next][col] = 0;
							break; 

						}
						
						else if(map[next][col] == 0) {
							continue; 
						}

						else { // 다른 값이면 그 다음 포지션으로
							break;
						}
					}

				}
			}

			break;
		}

		return map;
	}

	public static void dfs(int[][] map, int count, List<Character> directs) {

		// 5번 넘어가면 최댓값 갱신 후 리턴
		if (count == 5) {
			result = Math.max(result, getMaxNum(map)); 
			return;
		}

		for (char direction : directions) {
			int[][] newMap = updateMap(map, direction);

			directs.add(direction); 
			dfs(newMap, count + 1, directs);
			directs.remove(directs.size() - 1); 
		}

	}

	public static void main(String[] args) {
		// 입력받기
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		int[][] map = new int[N][N];

		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				if (max < map[i][j])
					max = map[i][j];
			}
		}

		// 실행 전 result는 현재 최댓값
		result = max;

		// 실행
		dfs(map, 0, new ArrayList<Character>());


		// 결과
		System.out.println(result);
	}
}
