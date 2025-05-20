package P_20250512;

import java.io.*;
import java.util.*;

// 8 : 42 - 9 : 03 
public class S2_11725 {
	static int N; 
	
	static List<List<Integer>> tree = new ArrayList<>(); 
	static int[] parents; 
	
	public static void main(String[] args) throws IOException {
		
		inputAndSetTree(); 
		findParents(); 
		printResult(); 
	
	}
	
	public static void inputAndSetTree() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N  = Integer.parseInt(br.readLine()); 
		
		StringTokenizer st; 
		
		for(int i = 1 ; i<= N ; i++) {
			tree.add(new ArrayList<>()); 
		}
		parents= new int[N+1]; 
		
		for(int i = 0 ; i<N-1 ; i++) {
			String line = br.readLine(); 
			st = new StringTokenizer(line); 
			
			int node1 = Integer.parseInt(st.nextToken()); 
			int node2 = Integer.parseInt(st.nextToken()); 
			
			tree.get(node1-1).add(node2); 
			tree.get(node2-1).add(node1); 
		}
		
		br.close(); 
	}
	
	public static void findParents() {
		Queue<Integer> queue = new LinkedList<>(); 
		boolean[] visited = new boolean[N+1]; 
		queue.add(1); 
		
		while(!queue.isEmpty()) {
			int parent = queue.poll(); 
			visited[parent] = true; 
			List<Integer> childs = tree.get(parent-1); 
			
			for(int child : childs) {
				if(parents[child] == 0) parents[child] = parent; 
				if(!visited[child]) queue.add(child); 
			}
		}
	}
	
	public static void printResult() {
		for(int i = 2 ; i<= N ; i++) {
			System.out.println(parents[i]); 
		}
	}

}

