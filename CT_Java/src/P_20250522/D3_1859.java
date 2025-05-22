package P_20250522;

import java.io.*;
import java.util.*;


// 9:15 - 9:34 
public class D3_1859 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int T; 
	static int N; 
	static List<Integer> priceList; 
	
	
	public static void main(String[] args) throws IOException {
		inputAndRun(); 
	}
	
	public static void inputAndRun() throws IOException{
		T = Integer.parseInt(br.readLine()); 
		
		for(int testcase = 1 ; testcase <= T ; testcase++) {
			N = Integer.parseInt(br.readLine()); 
			
			priceList=new ArrayList<>(); 
			
			String[] nums = br.readLine().split(" "); 
			
			int max = 0; 
			
			long totalValue = 0; 
			
			for(int i = N-1 ; i>= 0 ; i--) {
				int cur = Integer.parseInt(nums[i]); 
				if(max < cur) {
					max = cur;
					continue; 
				}
				
				totalValue += (max-cur); 
			}
			
			System.out.printf("#%d %d\n", testcase, totalValue); 
		}
		
		br.close(); 
	}

}

