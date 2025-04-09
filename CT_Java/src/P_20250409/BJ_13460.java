package P_20250409;

import java.io.*;
import java.util.*;


class Game {
	int N; // 행
	int M; // 열
	List<String> walls = new ArrayList<>();
	int[] whole = new int[2];

	int pathCount = 11; // 정답, 항상 최솟값 유지

	public Game(List<String> walls, int[] whole, int N, int M) {
		this.walls = walls;
		this.whole = whole;
		this.N = N;
		this.M = M;
	}

	public void run(int[] Red, int[] Blue) {
		List<String> RedPath = new ArrayList<>();

		dfs(Red, Blue, RedPath, 0);
	}

	public String pathToString(int[] start, int[] end) {
		return start[0] + "," + start[1] + "-" + end[0] + "," + end[1];
	}

	public void dfs(int[] Red, int[] Blue, List<String> RedPath, int count) {
		if (count == 10) { // 10번 넘어가기 때문에 리턴
			return;
		}

		char[] directions = { 'E', 'W', 'S', 'N' }; // 동>서>남>북
		
		for (char direction : directions) {
			// 빨강 구슬 이동 먼저
			int[] newRed = moveEWSN(Red, direction);

			// 파란 구슬도 그 방향으로 이동 체크
			int[] newBlue = moveEWSN(Blue, direction);
			
			// BluePath에 구멍이 있는지 체크
			if (checkWholeExist(Blue, newBlue)) { // 파란 구슬 가는 길에 구멍이 있으면 다른 방향체크
				continue;
			}

			// 이동 경로에 구슬끼리 겹치는지 체크후 처리
			if (newRed[0] == newBlue[0] && newRed[1] == newBlue[1]) {
				switch (direction) {
					case 'E':
						if (Red[1] < Blue[1])
							newRed[1]--;
						else
							newBlue[1]--;
						break;
	
					case 'W':
						if (Red[1] > Blue[1])
							newRed[1]++;
						else
							newBlue[1]++;
						break;
	
					case 'S':
						if (Red[0] < Blue[0])
							newRed[0]--;
						else
							newBlue[0]--;
						break;
	
					case 'N':
						if (Red[0] > Blue[0])
							newRed[0]++;
						else
							newBlue[0]++;
						break;
				}
			}

			// 못움직이면 다른 방향 체크
			if ((newRed[0] == Red[0] && newRed[1] == Red[1])&&(newBlue[0] == Blue[0] && newBlue[1] == Blue[1])) continue;
			
			// 빨강 경로의 새로운 이동 경로
			String newPath = pathToString(Red, newRed);
			
			if (checkWholeExist(Red, newRed)) {
				this.pathCount = Math.min(pathCount, count+1); // 빨간 구슬 가는 길에 구멍 있으면, 끝내기
				return;
			}

			RedPath.add(newPath);
			dfs(newRed, newBlue, RedPath, count + 1); // 여기까지 이동 한 것에서 재귀 함수 호출
			RedPath.remove(RedPath.size() - 1);

		}

	}


	// 좌표와 방향을 주면 해당 방향으로 움직이기 -> 벽만 감지 , 구슬/구멍 감지 x
	public int[] moveEWSN(int[] pos, char direction) {
		int row = pos[0];
		int col = pos[1];
		
		int[] newPos = new int[2]; 

		switch (direction) {
		case 'E': // 오른쪽
			while (col < M - 1) {
				col++;
				String p = row + "," + col;
				if (walls.contains(p)) { // 벽 만나면 그 전 위치로
					newPos[0] = row;
					newPos[1] = col - 1;
					return newPos;
				}
			}

			newPos[0] = row;
			newPos[1] = col;
			return newPos;

		case 'W': // 왼쪽
			while (col > 0) {
				col--;
				String p = row + "," + col;
				if (walls.contains(p)) { // 벽 만나면 그 전 위치로
					newPos[0] = row;
					newPos[1] = col + 1;
					return newPos;
				}
			}

			newPos[0] = row;
			newPos[1] = col;
			return newPos;

		case 'N': // 위쪽
			while (row > 0) {
				row--;
				String p = row + "," + col;
				if (walls.contains(p)) { // 벽 만나면 그 전 위치로
					newPos[0] = row + 1;
					newPos[1] = col;
					return newPos;
				}
			}

			newPos[0] = row;
			newPos[1] = col;
			return newPos;

		case 'S': // 아래쪽
			while (row < M - 1) {
				row++;
				String p = row + "," + col;
				if (walls.contains(p)) { // 벽 만나면 그 전 위치로
					newPos[0] = row - 1;
					newPos[1] = col;
					return newPos;
				}
			}

			newPos[0] = row;
			newPos[1] = col;
			return newPos;
		}

		return newPos;
	}

	// 이동하려는 경로/도착지 가 구멍이 있을때 : True 반환 없으면 False 반환
	public boolean checkWholeExist(int[] start, int[] end) {
		if (start[0] == end[0] && start[0] == whole[0]) { // 가로로 이동하는 상황 일때
			// 구멍이 있으면
			if(start[1] <= whole[1] && whole[1] <= end[1]) return true; 
			else if(end[1] <= whole[1] && whole[1] <= start[1]) return true; 
		} else if (start[1] == end[1] && start[1] == whole[1]) { // 세로로 이동하는 상황 일때
			// 구멍이 있으면
			if(start[0] <= whole[0] && whole[0] <= end[0]) return true; 
			else if(end[0] <= whole[0] && whole[0] <= start[0]) return true;
		}
		return false;
	}

}

public class BJ_13460 {
	public static String posToString(int row, int col) {
		return Integer.toString(row) + "," + Integer.toString(col);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 행, 열 크기 입력받기
		int N = sc.nextInt();
		int M = sc.nextInt();

		// 지도 정보 입력받기
		List<String> walls = new ArrayList<>();
		int[] whole = new int[2];
		int[] Red = new int[2];
		int[] Blue = new int[2];

		for (int i = 0; i < N; i++) {
			String row = sc.next();

			for (int j = 0; j < M; j++) {
				char c = row.charAt(j);
				// 벽 감지
				if (c == '#') {
					String pos = posToString(i, j);
					walls.add(pos);
				} else if (c == 'O') {
					whole[0] = i;
					whole[1] = j;
				} else if (c == 'R') {
					Red[0] = i;
					Red[1] = j;
				} else if (c == 'B') {
					Blue[0] = i;
					Blue[1] = j;
				}
			}
		}

		Game game = new Game(walls, whole, N, M);

		game.run(Red, Blue);
		
		if(game.pathCount == 11) game.pathCount = -1; 

		System.out.println(game.pathCount);

	}
}
