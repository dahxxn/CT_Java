package P_20250216;

import java.util.*; 

public class Failure_Lv1 {

	class FailureInfo{
	    public int stageNum; 
	    public int stopCount; 
	    public int passCount; 
	    public double failure; 
	    
	    FailureInfo(int stageNum){
	        this.stageNum = stageNum; 
	        this.stopCount = 0; 
	        this.passCount = 0; 
	        this.failure = 0; 
	    }
	    
	    public void updateCount(int stop, int pass){
	        this.stopCount += stop;
	        this.passCount += pass; 
	        this.failure = (double)this.stopCount / (double)this.passCount;  

	    }
	}

	class FailureComparator implements Comparator<FailureInfo> { 
	    @Override    
	    public int compare(FailureInfo f1, FailureInfo f2) {
	        if(f1.failure > f2.failure){
	            return 1; 
	        }else if(f1.failure < f2.failure){
	            return -1; 
	        }
	        return 0; 
	    }
	}

	class Solution {

	    public int[] solution(int N, int[] stages) {
	        int[] answer = new int[N];
	        
	        List<FailureInfo> failureList = new ArrayList<>();  
	        
	        for(int i = 0 ; i<N ; i++){
	            FailureInfo stage = new FailureInfo(i); 
	            failureList.add(stage); 
	        }
	        
	        for(int i = 0 ; i<stages.length ; i++){
	            int stopStage = stages[i] - 1; 
	            
	            for(int j = 0 ; j < N ; j++){
	                if(stopStage == j){
	                    failureList.get(j).updateCount(1,1); 
	                }else if(stopStage > j){
	                    failureList.get(j).updateCount(0,1); 
	                }
	            }
	        }
	        
	        
	        Collections.sort(failureList, new FailureComparator().reversed());
	        int i = 0; 
	        for(FailureInfo f : failureList){
	            answer[i] = f.stageNum + 1; 
	            i++; 
	        }
	        return answer;
	    }
	}
}
