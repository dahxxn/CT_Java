package P_20250217;
import java.util.*; 

public class LottoBestWorst_Lv2 {


	class Solution {
	    
	    public int getCorrectCount(int[] lottos, int[] win_nums){
	        int count = 0; 
	        
	        Set<Integer> checkCorrect = new HashSet<>(); 
	        for(int i = 0 ; i<6 ; i++){
	            checkCorrect.add(lottos[i]); 
	            checkCorrect.add(win_nums[i]); 
	        }
	        count = lottos.length + win_nums.length - checkCorrect.size();
	        if(getZeroCount(lottos) > 1){
	            count -= getZeroCount(lottos);
	            count += 1; 
	        }
	        
	        return count; 
	    }
	    
	    public int getZeroCount(int[] lottos){
	        int count = 0; 
	        for(int i = 0 ; i<lottos.length ; i++){
	            if(lottos[i] == 0) count++; 
	        }
	        return count; 
	    }
	    
	    public int getRank(int count){
	        if(count < 2) return 6; 
	        if(count < 3) return 5; 
	        if(count < 4) return 4; 
	        if(count < 5) return 3; 
	        if(count < 6) return 2; 
	        return 1; 
	    }
	    
	    public int[] solution(int[] lottos, int[] win_nums) {
	        int[] answer = new int[2];
	        
	        int totalCorrectCount = getCorrectCount(lottos, win_nums); 
	        int maxCount = totalCorrectCount + getZeroCount(lottos); 
	        int minCount = totalCorrectCount; 
	        
	        
	        answer[0] = getRank(maxCount); 
	        answer[1] = getRank(minCount); 
	        
	        return answer;
	    }
	}
}
