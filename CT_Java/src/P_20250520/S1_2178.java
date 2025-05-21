package P_20250520;

import java.io.*;
import java.util.*;

// 9:29 - 9:59 

public class S1_2178 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
	static StringTokenizer st; 
	
	static int N; 
	static int M; 
	
	static int[][] map; 
	
	static boolean[][] visited; 
	
	static int[][] dp; 
	public static void main(String[] args) throws IOException {
		input(); 
		findShortest(); 
		
		System.out.println(dp[N-1][M-1]); 
	}
	
	public static void findShortest() {
		// 1,1 -> N,M 
		
		Queue<int[]> queue = new LinkedList<>(); 
		queue.add(new int[] {0,0}); 
		visited[0][0] = true; 
		dp[0][0] = 1; 
		
		
		while(!queue.isEmpty()) {
			int[] pos = queue.poll(); 
						

			List<int[]> nears = getNear(pos); 
			
			for(int[] near : nears) {
				queue.offer(near); 
				visited[near[0]][near[1]] = true; 
				dp[near[0]][near[1]] = dp[pos[0]][pos[1]] + 1; 
			}
		}
		
	}
	
	public static List<int[]> getNear(int[] pos){
		int[] row = {0,0,-1,1}; 
		int[] col = {1,-1,0,0}; // 동,서,남,북
		
		List<int[]> nears = new ArrayList<>(); 
		
		for(int i = 0 ; i<4 ; i++) {
			int x = pos[0] + row[i]; 
			int y = pos[1] + col[i]; 
			
			if(x >= 0 && x < N  && y >= 0 && y <M) {
				if(!visited[x][y] && map[x][y] == 1) nears.add(new int[] {x,y}); 
			}
		}
		
		return nears; 
	}
	
	public static void input() throws IOException {
		st = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken()); 
		
		map = new int[N][M]; 
		
		visited = new boolean[N][M]; 
		
		dp = new int[N][M]; 
		for(int i = 0 ; i<N ; i++) {
			String line = br.readLine(); 
			
			for(int j = 0 ; j<M ; j++) {
				map[i][j] = Character.getNumericValue(line.charAt(j)) ; 
			}
		}
		
		br.close(); 
	}
}

