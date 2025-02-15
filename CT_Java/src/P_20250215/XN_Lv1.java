package P_20250215;

public class XN_Lv1 {
	class Solution {
	    public long[] solution(int x, int n) {
	        long[] answer = new long[n]; 
	        
	        long current = 0; 
	        for(int i = 0 ; i< n ; i++){
	            answer[i] = (long)(current + x); 
	            current = answer[i]; 
	        }
	        
	        return answer;
	    }
	}
}
