package P_20250218;
import java.util.*; 

public class NumStringAndAlphabet_Lv1 {

	class Solution {
	    public String findNumber(String s){
	        switch(s){
	            case "zero":
	                return "0"; 
	            case "one": 
	                return "1"; 
	            case "two": 
	                return "2"; 
	            case "three":
	                return "3"; 
	            case "four":
	                return "4"; 
	            case "five":
	                return "5";
	            case "six":
	                return "6"; 
	            case "seven":
	                return "7";
	            case "eight":
	                return "8"; 
	            case "nine" : 
	                return "9"; 
	            default :
	                return null; 
	        }
	        
	    }
	    
	    public int solution(String s) {
	        int answer = 0;
	    
	        StringBuilder realNum = new StringBuilder();
	        StringBuilder key = new StringBuilder(); 
	        
	        for(int i = 0 ; i<s.length() ; i++){
	            char c = s.charAt(i); 
	            
	            if(Character.isAlphabetic(c)){ 
	                key.append(c); 
	                if(key.length() > 0 && findNumber(key.toString()) != null){
	                    realNum.append(findNumber(key.toString()));
	                    key = new StringBuilder(); 
	                }
	            }else{
	                if(key.length() > 0) {
	                    realNum.append(findNumber(key.toString()));
	                    key = new StringBuilder(); 
	                }
	                realNum.append(c); 
	            }
	        }
	  
	        answer = Integer.parseInt(realNum.toString()); 
	      
	        return answer;
	    }
	}
}
