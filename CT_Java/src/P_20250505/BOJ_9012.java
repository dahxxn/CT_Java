package P_20250505;

import java.io.*;
import java.util.*;

public class BOJ_9012 {
	static int N; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		run(br); 
	}
	
	public static void run(BufferedReader br) throws IOException {

		for(int test = 0 ; test<N ; test++) {
			Stack<Character> stack = new Stack<>(); 
			boolean flag = true;  
			
			String line = br.readLine(); 
			
			for(char c : line.toCharArray()) {
				if(c == '(') stack.push(c); 
				else if(c == ')') {
					if(stack.isEmpty()) {
						flag = false; 
						break; 
					}
					stack.pop(); 
				}
			}
			
			String result = flag && stack.isEmpty() ? "YES" : "NO" ; 
			
			System.out.println(result); 
		}
	}
	
}

