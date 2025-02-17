package P_20250217;
import java.util.*; 

public class Date2016_Lv1 {

	class Solution {
	    public String solution(int a, int b) {
	        String answer = "";
	        
	        Queue<String> date = new LinkedList<>(); 
	        date.offer("FRI"); 
	        date.offer("SAT"); 
	        date.offer("SUN"); 
	        date.offer("MON"); 
	        date.offer("TUE"); 
	        date.offer("WED"); 
	        date.offer("THU"); 

	        
	        HashMap<Integer, Integer> monthDays = new HashMap<>(); 
	        
	        for(int i = 1 ; i<= 7 ; i++){
	            if(i == 2){
	                monthDays.put(i,29); 
	            }
	            else if(i%2 == 0){
	                monthDays.put(i,30); 
	            }else{
	                monthDays.put(i,31); 
	            }
	        }
	        
	        for(int i = 8 ; i<= 12 ; i++){
	            if(i%2 == 0){
	                monthDays.put(i,31); 
	            }else{
	                monthDays.put(i,30); 
	            }
	        }
	        
	        int totalDays = 0; 
	        for(int i = 1 ; i<= a-1 ; i++){
	            totalDays += monthDays.get(i); 
	        }
	        totalDays += b; 
	        
	        
	        for(int i = 1 ; i< totalDays ; i++){
	            answer = date.poll(); 
	            date.offer(answer); 
	        }
	        answer = date.poll();
	        
	            
	        return answer;
	    }
	}
}
