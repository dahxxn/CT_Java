package P_20250218;

import java.util.*; 

public class Poketmons_Lv1 {

	class Solution {

	    public int solution(int[] nums) {
	        int answer = 0;
	        
	        HashSet<Integer> poketmonsType = new HashSet<>(); 
	        for(int i = 0 ; i<nums.length ; i++){
	            poketmonsType.add(nums[i]); 
	        }
	        
	        answer  = Math.min(nums.length / 2 , poketmonsType.size()); 
	        
	        return answer;
	    }
	}
}
