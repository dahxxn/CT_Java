package P_20250217;
import java.util.*; 

public class Tuple_Lv2 {

	class Solution {
	    public int[] solution(String s) {
	        int[] answer = {};
	        
	        String newS = s.substring(1, s.length()-1); 
	        String[] sets = newS.split("[{}]"); 
	              
	        HashMap<Integer, List<Integer>> tuple = new HashMap<>(); 
	        
	        for(int i = 0 ; i<sets.length ; i++){
	            if(sets[i].equals(",") || sets[i].equals(""))continue; 
	            
	            String[] numStr = sets[i].split(","); 
	            
	            int pos = numStr.length; 
	            List<Integer> numList = new ArrayList<>(); 
	            for(int j = 0 ; j <numStr.length ; j++){
	                int num = Integer.parseInt(numStr[j]); 
	                numList.add(num); 
	            }
	            tuple.put(pos,numList); 
	        }
	                
	        List<Integer> keySets = new ArrayList<>(tuple.keySet());
	        List<Integer> visitedNums = new ArrayList<>(); 
	        
	        Collections.sort(keySets); 
	                
	        for(int i : keySets){
	            List<Integer> numList = tuple.get(i); 
	            
	            for(int n : numList){
	                if(!visitedNums.contains(n)){
	                    visitedNums.add(n); 
	                    break; 
	                }
	            }
	        }
	        
	        answer = new int[visitedNums.size()]; 
	        
	        for(int i = 0 ; i<visitedNums.size() ; i++){
	            answer[i] = visitedNums.get(i); 
	        }
	        
	        return answer;
	    }
	}
}
