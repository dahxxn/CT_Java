package P_20250412;

import java.io.*;
import java.util.*;

class searchResult {
	int rotate;
	List<int[]> findList;
	int[] middle;
	int[][] cave;

	public searchResult(int rotate, List<int[]> findList, int[] middle, int[][] cave) {
		this.rotate = rotate;
		this.findList = new ArrayList<>(findList);
		this.middle = middle;
		this.cave = cave;
	}

}

public class CT_고대문명유적탐사 {
	static int K;
	static int M;
	static int[][] caves = new int[5][5];
	static List<Integer> walls = new ArrayList<>();

	// 3x3 격자 중심 좌표 목록
	static List<int[]> middlePoint = new ArrayList<>();

	// 동,서,남,북
	static int[] directRow = { 0, 0, 1, -1 };
	static int[] directCol = { 1, -1, 0, 0 };

	public static void input() {
		Scanner sc = new Scanner(System.in);
		K = sc.nextInt();
		M = sc.nextInt();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				caves[i][j] = sc.nextInt();
			}
		}

		for (int i = 0; i < M; i++) {
			walls.add(sc.nextInt());
		}

		sc.close();

		for (int i = 1; i <= 3; i++) {
			for (int j = 1; j <= 3; j++) {
				int[] point = { i, j };
				middlePoint.add(point);
			}
		}
	}

	static int wallIndex = 0; 
	public static void main(String[] args) {

		// 입력 받기
		input();

// 첫번째 턴
		List<searchResult> searchs = new ArrayList<>();

		for (int[] middle : middlePoint) {

			int[][] tempCave = new int[5][5];
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					tempCave[i][j] = caves[i][j];
				}
			}

			for (int r = 0; r < 3; r++) { // 90 > 180 > 270 회전
				int[][] rotateCave = new int[5][5]; 
				turnRight(middle, tempCave);
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++) {
						rotateCave[i][j] = tempCave[i][j];
					}
				}
				// 유물 가치 계산
				checkValue(tempCave);
				searchs.add(new searchResult(r, findList, middle, rotateCave));
			}

		}

		Collections.sort(searchs, new Comparator<>() {

			@Override
			public int compare(searchResult o1, searchResult o2) {
				if (o1.findList.size() == o2.findList.size()) {
					if (o1.rotate == o2.rotate) {
						if (o1.middle[1] == o2.middle[1]) {
							return o1.middle[0] - o2.middle[0];
						}
						return o1.middle[1] - o2.middle[1];
					}
					return o1.rotate - o2.rotate;
				}
				return o2.findList.size() - o1.findList.size();
			}

		});
		
		searchResult sr = searchs.getFirst(); 
	
		Collections.sort(sr.findList, new Comparator<>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[1] == o2[1]) {
					return o2[0] - o1[0];
				}
				return o1[1] - o2[1];
			}

		});

		int sum = sr.findList.size();
		
		caves = sr.cave; 

		for (int[] rm : sr.findList) {
			caves[rm[0]][rm[1]] = walls.get(wallIndex);
			wallIndex++;
		}
	
		// 연쇄 획득

		do {
			// 유물 가치 계산
			checkValue(caves);
			
			if (findList.size() == 0)
				break;

			Collections.sort(findList, new Comparator<>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					if (o1[1] == o2[1]) {
						return o2[0] - o1[0];
					}
					return o1[1] - o2[1];
				}

			});
		

			sum += findList.size();
			
			if (findList.size() == 0)
				break;
		
			for (int[] rm : findList) {
				caves[rm[0]][rm[1]] = walls.get(wallIndex);
				wallIndex++;
			}
			

		} while (findList.size() > 0);
		
		

		System.out.printf(sum + " ");

		// 턴의 반복

		for (int turn = 1; turn < K; turn++) {
			
			
			searchs = new ArrayList<>();

			for (int[] middle : middlePoint) {

				int[][] tempCave = new int[5][5];
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++) {
						tempCave[i][j] = caves[i][j];
					}
				}

				for (int r = 0; r < 3; r++) { // 90 > 180 > 270 회전
					int[][] rotateCave = new int[5][5]; 
					turnRight(middle, tempCave);
					for (int i = 0; i < 5; i++) {
						for (int j = 0; j < 5; j++) {
							rotateCave[i][j] = tempCave[i][j];
						}
					}
					// 유물 가치 계산
					checkValue(tempCave);
					searchs.add(new searchResult(r, findList, middle, rotateCave));
				}

			}

			Collections.sort(searchs, new Comparator<>() {

				@Override
				public int compare(searchResult o1, searchResult o2) {
					if (o1.findList.size() == o2.findList.size()) {
						if (o1.rotate == o2.rotate) {
							if (o1.middle[1] == o2.middle[1]) {
								return o1.middle[0] - o2.middle[0];
							}
							return o1.middle[1] - o2.middle[1];
						}
						return o1.rotate - o2.rotate;
					}
					return o2.findList.size() - o1.findList.size();
				}

			});

			sr = searchs.get(0); 
			// 선택 후 제거
	
			Collections.sort(sr.findList, new Comparator<>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					if (o1[1] == o2[1]) {
						return o2[0] - o1[0];
					}
					return o1[1] - o2[1];
				}

			});

			sum = sr.findList.size();
			
			caves = sr.cave;
			
			
			for (int[] rm : sr.findList) {
				caves[rm[0]][rm[1]] = walls.get(wallIndex);
				wallIndex++;
			}

			// 연쇄 획득

			do {
				// 유물 가치 계산
				checkValue(caves);
				
				if (findList.size() == 0)
					break;

				Collections.sort(findList, new Comparator<>() {

					@Override
					public int compare(int[] o1, int[] o2) {
						if (o1[1] == o2[1]) {
							return o2[0] - o1[0];
						}
						return o1[1] - o2[1];
					}

				});
			

				sum += findList.size();
			
				if (findList.size() == 0)
					break;
				
				for (int[] rm : findList) {
					caves[rm[0]][rm[1]] = walls.get(wallIndex);
					wallIndex++;
				}
				

			} while (findList.size() > 0);

			if(sum == 0) break; 
			System.out.printf(sum + " ");
		}

	}

	// 90도 오른쪽으로 돌리기
	public static int[][] turnRight(int[] middle, int[][] tempCave) {

		int row = middle[0];
		int col = middle[1];

		int[] c1 = new int[3];
		int[] c2 = new int[3];
		int[] c3 = new int[3];

		int c_row = 2; 
		for (int j = 0; j < 3; j++, c_row--) {
			c1[c_row] = tempCave[row - 1 + j][col - 1];
		}
		c_row = 2; 
		for (int j = 0; j < 3; j++, c_row--) {
			c2[c_row] = tempCave[row - 1 + j][col];
		}
		c_row = 2; 
		for (int j = 0; j < 3; j++, c_row--) {
			c3[c_row] = tempCave[row - 1 + j][col + 1];
		}

		int colPlus = 0;
		for (int i = 2; i >= 0; i--, colPlus++) {
			tempCave[row - 1][col - 1 + colPlus] = c1[colPlus];
		}

		colPlus = 0;
		for (int i = 2; i >= 0; i--, colPlus++) {
			tempCave[row][col - 1 + colPlus] = c2[colPlus];
		}

		colPlus = 0;
		for (int i = 2; i >= 0; i--, colPlus++) {
			tempCave[row + 1][col - 1 + colPlus] = c3[colPlus];
		}
		
		return tempCave; 

	}

	static List<int[]> findList = new ArrayList<>();

	// 현재 유물 가치 파악하기
	public static void checkValue(int[][] currentCave) {
		// 0 - 25, 현재 위치 동서남북에 똑같은 유물이 있으면 추가
		boolean[][] visited = new boolean[5][5];
		findList = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (!visited[i][j]) {
					List<int[]> group = new ArrayList<>();
					group.add(new int[] { i, j });

					searchGroup(i, j, currentCave, visited, group);

					if (group.size() >= 3) { // 3개 이상 연결된 것만 없앰
						findList.addAll(group);
					}
				}

			}
		}

	}

	public static void searchGroup(int i, int j, int[][] currentCave, boolean[][] visited, List<int[]> group) {

		visited[i][j] = true;

		// 동서남북 체크
		for (int k = 0; k < 4; k++) {
			int row = i + directRow[k];
			int col = j + directCol[k];

			if (checkRange(row, col) && !visited[row][col] && currentCave[row][col] == currentCave[i][j]) {
				group.add(new int[] { row, col });
				searchGroup(row, col, currentCave, visited, group);
			}
		}

	}

	public static boolean checkRange(int row, int col) {
		return row >= 0 && row < 5 && col >= 0 && col < 5;
	}

}
