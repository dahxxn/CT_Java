package P_20250217;
import java.util.*; 

public class Progress_Lv2 {

	class Workflow{
	    public int progress; 
	    public int speed; 
	    
	    public Workflow(int progress, int speed){
	        this.progress = progress; 
	        this.speed = speed; 
	    }
	    
	    public void updateProgress(int day){
	        this.progress += (this.speed * day); 
	    }
	    
	}

	class Solution {
	    
	    public int getCompleteDays(int progress, int speed){
	        int remains= 100- progress; 
	        
	        int days = remains/speed; 
	        if(remains%speed >0) days ++; 
	        return days; 
	    }
	    
	    public int[] solution(int[] progresses, int[] speeds) {
	        int[] answer = {};
	        
	        Queue<Workflow> works  = new LinkedList<>(); 
	        for(int i = 0 ; i<progresses.length ; i++){
	            Workflow work = new Workflow(progresses[i], speeds[i]); 
	            works.offer(work); 
	        }
	        
	        HashMap<Integer, Integer> dayAndCount = new HashMap<>(); 
	        
	        
	        int days = 0;
	                
	         while(works.size() > 0){
	            Workflow firstWork = works.poll(); 
	            int firstWorkDays = getCompleteDays(firstWork.progress, firstWork.speed); 
	            days += firstWorkDays; 
	             
	            dayAndCount.put(days, 1); 
	            int worksSize = works.size();
	            boolean flag = false; 
	             
	            for(int i = 0 ; i<worksSize; i++){
	                Workflow work = works.poll(); 
	                work.updateProgress(firstWorkDays); 

	                if(work.progress >= 100 && !flag){
	                    dayAndCount.put(days, dayAndCount.get(days) + 1); 
	                }else{
	                    works.offer(work); 
	                    flag = true; 
	                }
	            }
	        }
	        
	        answer = new int[dayAndCount.size()]; 
	        
	        int i = 0; 
	        List<Integer> keys = new ArrayList<>(dayAndCount.keySet()); 
	        
	        Collections.sort(keys); 
	        for(int day : keys){
	            answer[i] = dayAndCount.get(day); 
	            i++; 
	        }
	        
	        return answer;
	    }
	}
}
