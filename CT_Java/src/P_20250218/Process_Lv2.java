package P_20250218;
import java.util.*; 

public class Process_Lv2 {

	class Solution {    
	    
	    class Process{
	        int id; 
	        int prior; 
	        
	        public Process(int id, int prior){
	            this.id = id; 
	            this.prior = prior; 
	        }
	    }
	    
	    public int solution(int[] priorities, int location) {
	        int answer = 0;
	        
	        Queue<Process> waitQueue = new LinkedList<>(); 
	        PriorityQueue<Integer> pQueue = new PriorityQueue<>(Comparator.reverseOrder()); 
	                
	        for(int i = 0 ; i<priorities.length ; i++){
	            int currentId = i; 
	            int currentPrior = priorities[i]; 
	            
	            Process p = new Process(currentId, currentPrior); 
	            waitQueue.offer(p); 
	            pQueue.offer(currentPrior); 
	        }
	        
	        
	        int count = 0; 
	        while(!waitQueue.isEmpty()){
	            Process currentProcess = waitQueue.poll(); 
	            int maxPrior = pQueue.peek(); 
	            
	            if(currentProcess.prior < maxPrior){
	                waitQueue.offer(currentProcess); 
	                continue;    
	            }else{                
	                count++; 
	                pQueue.poll(); 
	                
	                if(currentProcess.id == location){
	                    answer = count; 
	                    break; 
	                }
	            }
	        }
	        
	        return answer;
	    }

	}
}
