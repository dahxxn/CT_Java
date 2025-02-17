package P_20250217;
import java.util.*; 

public class MinRectangle_Lv1 {

	class Solution {
	    
	    public boolean checkCard(int w, int h, int card_w, int card_h){
	        if(card_w <= w && card_h <= h){
	            return true; 
	        }
	        return false; 
	    }
	    
	    public int solution(int[][] sizes) {
	        int answer = 0;
	        int w =0; int h = 0; 
	        
	        for(int i = 0 ; i<sizes.length ; i++){
	            int card_w = sizes[i][0]; 
	            int card_h = sizes[i][1]; 
	            
	            if(card_h > card_w){
	                int temp = card_w; 
	                card_w = card_h; 
	                card_h = temp; 
	            }
	            
	            if(!checkCard(w,h,card_w,card_h)){
	                if(card_w > w) {
	                    w = card_w; 
	                }
	                if(card_h > h){
	                    h = card_h;
	                }
	            }
	            
	        }
	        
	        answer = w * h; 
	        return answer;
	    }
	}
}
