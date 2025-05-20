package P_20250508;

import java.io.*;
import java.util.*;

// 10 : 36 - 11:05 
public class S4_균형잡힌세상 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String line;
		while (!(line = br.readLine()).equals(".")) { // "."만 있는 줄이면 종료

			Stack<Character> stack = new Stack<>();
			boolean isBalanced = true;

			for (char c : line.toCharArray()) {
				if (c == '(' || c == '[')
					stack.push(c);

				else if (c == ')' || c == ']') {
					if (stack.isEmpty()) {
						isBalanced = false;
						break;
					}

					char c2 = stack.pop();

					if ((c == ')' && c2 != '(') || (c == ']' && c2 != '[')) {
						isBalanced = false;
						break;
					}
				}
			}
			
			if(!stack.isEmpty()) isBalanced = false; 
			
			
			if(isBalanced) System.out.println("yes"); 
			else System.out.println("no"); 
		}

	}
}
