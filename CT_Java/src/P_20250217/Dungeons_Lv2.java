package P_20250217;

import java.util.*; 

public class Dungeons_Lv2 {

	static class Solution {
	    private static int max = 0; 
	    
	    public void findMaxDfs(int k, int[][] dungeons, int current, boolean[] visited, int count){
	        if(current == dungeons.length){
	            max = Math.max(max, count); 
	            return ; 
	        }
	        
	        for(int i = 0 ; i < dungeons.length ; i++){
	            if(visited[i]) continue;
	            visited[i] = true; 
	            
	            if(k >= dungeons[i][0]){
	                findMaxDfs(k - dungeons[i][1], dungeons, current+1, visited, count+1); 
	            }
	            else{
	                findMaxDfs(k, dungeons, current+1, visited, count); 
	            }
	            
	            visited[i] = false; 
	        }
	        
	        return; 
	    }
	    
	    public int solution(int k, int[][] dungeons) {
	        int answer = -1;
	        
	        boolean[] visited = new boolean[dungeons.length]; 
	        
	        findMaxDfs(k, dungeons, 0, visited,0); 
	        answer = max; 
	        
	        return answer;
	    }
	}
}
