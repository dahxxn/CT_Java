package P_20250218;
import java.util.*; 

public class PhoneNums_Lv2 {

	class Solution {
	    boolean answer = false; 
	    
	    class phoneNumLengthSort implements Comparator<String>{
	        @Override 
	        public int compare(String s1, String s2){
	            if(s1.compareTo(s2)>0){        
	                return 1; 
	            }else if(s1.compareTo(s2) == 0){
	                return 0; 
	            }else{  
	                return -1; 
	            }
	        }
	    }
	    
	    public boolean solution(String[] phone_book) {
	        
	        List<String> phoneList = new ArrayList<>(Arrays.asList(phone_book)); 
	        
	        Collections.sort(phoneList, new phoneNumLengthSort()); 
	        
	        for(int i = 0 ; i<phoneList.size()-1 ; i++){
	            String currentNum = phoneList.get(i); 
	            
	            if(phoneList.get(i+1).startsWith(currentNum)){ // *** 
	                return false; 
	            }
	            
	           
	        }
	        
	        return true; 
	        
	    }
	}
}
