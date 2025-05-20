package P_20250510;

import java.io.*;
import java.util.*;

// 나의 피파 팀 가치  10 : 30 -  11:00 
public class Main {
	static int N; 
	static int K; 
	
	// 포지션, 포지션 별 선수 (가치) 리스트 
	static HashMap<Integer, PriorityQueue<Integer>> playersByPos = new HashMap<>(); 
	
	// 포지션 별 선발 선수 
	static HashMap<Integer, Integer> selections = new HashMap<>(); 

	public static void main(String[] args) throws IOException {
		input();
		
		for(int year = 1 ; year <= K ; year++) {
			// 3월 : 선발 
			select(); 
			
			// 8월 선발 감소 
			worthDecline(); 
			
			// 11월 : 재선발 
			select(); 
		}
		
		
		printResult(); 
	}

	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line = br.readLine(); 
		
		N = Integer.parseInt(line.split(" ")[0]); 
		K = Integer.parseInt(line.split(" ")[1]); 
		
		
		for(int i = 0 ; i<N ; i++) {
			line = br.readLine(); 
			
			String[] split = line.split(" "); 
			
			int pos = Integer.parseInt(split[0]); 
			int worth = Integer.parseInt(split[1]); 
			
			PriorityQueue<Integer> posList =  playersByPos.getOrDefault(pos, new PriorityQueue<>(Collections.reverseOrder())); 
			posList.add(worth); // 자동으로 큰 수가 앞에 옴 
			
			playersByPos.put(pos, posList); 
		}
		

		br.close();

	}
	
	public static void select() {
		
		for(int pos : playersByPos.keySet()){
			
			int currentSelectWorth = selections.getOrDefault(pos, 0); // 현재 선발 가치 
			
			if(currentSelectWorth < playersByPos.get(pos).peek()) {
				playersByPos.get(pos).add(currentSelectWorth); 
				currentSelectWorth = playersByPos.get(pos).poll(); 
				selections.put(pos, currentSelectWorth); 
			}
		}
	}
	
	public static void worthDecline() {
		for(int pos: selections.keySet()) {
			int currentSelectWorth = selections.get(pos); 
			
			currentSelectWorth--; 
			if(currentSelectWorth < 0) currentSelectWorth = 0; 
			
			selections.put(pos, currentSelectWorth); 
		}
	}
	
	public static void printResult() {
		long sum = 0; 
		for(int pos : selections.keySet()) {
			sum += selections.get(pos); 
		}
		
		System.out.println(sum); 
		
	}


}
