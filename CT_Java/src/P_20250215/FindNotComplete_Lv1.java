package P_20250215;

import java.util.*; 

public class FindNotComplete_Lv1 {
	class Solution {
	    public String solution(String[] participant, String[] completion) {
	        String answer = "";
	        
	        Map<String, Integer> participantCheck = new HashMap<>(); 
	        
	        for(String name : participant){
	            if(participantCheck.containsKey(name)) participantCheck.put(name, participantCheck.get(name) + 1); 
	            else{
	                participantCheck.put(name,1); 
	            }
	        }
	        
	        for(String name : completion){
	            int count = participantCheck.get(name); 
	            
	            if(count == 1) participantCheck.remove(name); 
	            else{
	                participantCheck.put(name, participantCheck.get(name) - 1); 
	            }
	        }
	        
	        for(String name : participantCheck.keySet()){
	            answer = name; 
	        }
	        
	        return answer; 
	        
	    }
	}
}
