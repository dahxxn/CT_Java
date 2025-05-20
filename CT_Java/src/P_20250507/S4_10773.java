package P_20250507;

import java.io.*;
import java.util.*;

// 6:15 - 6 : 22 
public class S4_10773 {
	static int N; 
	static Stack<Integer> stack = new Stack<>(); 
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		long sum = 0; 
		for(int i = 0 ; i<N ; i++) {
			int num = Integer.parseInt(br.readLine());
			
			if(num == 0) {
				sum -= stack.pop(); 
			}else {
				sum += num;  
				stack.push(num); 
			}
			
		}
		
		System.out.println(sum); 
				
	}
}

