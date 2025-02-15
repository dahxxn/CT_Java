package P_20250215;

import java.util.*;

public class DiscountEvent_Lv2 {
	class Solution {
	    
	    public boolean isCondition(HashMap<String, Integer> state){
	        for(String product : state.keySet()){
	            if(state.get(product) > 0){
	                return false; 
	            }
	        }
	        return true; 
	    }
	    
	    public int solution(String[] want, int[] number, String[] discount) {
	        int answer = 0;
	        
	        HashMap<String, Integer> condition = new HashMap<>(); 
	        HashMap<String, Integer> state = new HashMap<>(); 
	        
	        for(int i = 0 ; i<want.length ; i++){
	            condition.put(want[i], number[i]); 
	            state.put(want[i], number[i]); 
	        }
	        int i = 0; 
	        
	        for(i = 0 ; i < 10 ; i++){
	            String product = discount[i]; 
	            if(state.containsKey(product)){
	                    state.put(product, state.get(product) - 1); 
	                
	            }
	        }
	        
	        if(isCondition(state)) answer ++; 
	        
	        for(; i < discount.length ; i++){
	            String removeProduct = discount[i-10]; 
	            if(state.containsKey(removeProduct)){
	                state.put(removeProduct, state.get(removeProduct) + 1); 
	            }
	            
	            String product = discount[i]; 
	            if(state.containsKey(product)){
	                    state.put(product, state.get(product) - 1); 
	                
	            }
	            if(isCondition(state)) answer ++; 
	        }
	 
	        return answer;
	    }
	}
}


